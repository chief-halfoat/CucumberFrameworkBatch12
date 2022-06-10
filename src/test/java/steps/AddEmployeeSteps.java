package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AddEmployeePage;
import utils.CommonMethods;
import utils.Constants;
import utils.DBUtils;
import utils.ExcelReader;

import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {
    String empId;
    String firstName;
    String dbFirstName;
    String dbEmpId;

    @When("user clicks on pim option")
    public void user_clicks_on_pim_option() {
        click(employeeSearchPage.pimOption);
    }

    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        click(employeeSearchPage.addEmployeeOption);
    }

    @When("user enters firstName, middleName, and lastName")
    public void user_enters_first_name_middle_name_and_last_name() {
        sendText(addEmployeePage.firstNameField, "Benjamin");
        sendText(addEmployeePage.middleNameField, "Franklin");
        sendText(addEmployeePage.lastNameField, "Pearce");
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        click(addEmployeePage.saveBtn);
    }

    @Then("employee is added successfully")
    public void employee_is_added_successfully() {
        System.out.println("Employee added");
    }

    @When("user enters {string} {string} {string}")
    public void user_enters(String firstNameValue, String middleNameValue, String lastNameValue) {
        sendText(addEmployeePage.firstNameField, firstNameValue);
        sendText(addEmployeePage.middleNameField, middleNameValue);
        sendText(addEmployeePage.lastNameField, lastNameValue);
    }
    @When("user provides {string} {string} and {string}")
    public void user_provides_and(String firstName, String middleName, String lastName) {
        sendText(addEmployeePage.firstNameField, firstName);
        sendText(addEmployeePage.middleNameField, middleName);
        sendText(addEmployeePage.lastNameField, lastName);
    }

    @When("user provides multiple employee's data and verify they are added")
    public void user_provides_multiple_employee_s_data_and_verify_they_are_added(DataTable dataTable) {
        List<Map<String, String>> employeeNames = dataTable.asMaps();
        for(Map<String,String> employee:employeeNames){
            String firstNameValue = employee.get("firstName");
            String middleNameValue = employee.get("middleName");
            String lastNameValue = employee.get("lastName");

            sendText(addEmployeePage.firstNameField, firstNameValue);
            sendText(addEmployeePage.middleNameField, middleNameValue);
            sendText(addEmployeePage.lastNameField, lastNameValue);
            click(addEmployeePage.saveBtn);
            String fullNameValue = firstNameValue+" "+middleNameValue+" "+lastNameValue;
            if(fullNameValue.equals(employeeInformationPage.employeeName.getText())) {
                System.out.println(employeeInformationPage.employeeName.getText() + " was added to the database");
            }
            click(employeeSearchPage.addEmployeeOption);
        }
    }
    @When("user adds multiple employees from an excel file using {string} sheet and verify the employee is added")
    public void user_adds_multiple_employees_from_an_excel_file_using_sheet_and_verify_the_employee_is_added(String sheetName) throws InterruptedException {
        List<Map<String,String>> newEmployees = ExcelReader.excelIntoMap(Constants.TESTDATA_FILEPATH,sheetName);
        Iterator<Map<String,String>> itr = newEmployees.iterator();
        //checks whether the next element exists
        while(itr.hasNext()){
            //returns the key and value for employees
            Map<String,String> mapNewEmp = itr.next();

            sendText(addEmployeePage.firstNameField, mapNewEmp.get("FirstName"));
            sendText(addEmployeePage.middleNameField, mapNewEmp.get("MiddleName"));
            sendText(addEmployeePage.lastNameField, mapNewEmp.get("LastName"));
            String empIdValue = addEmployeePage.employeeId.getAttribute("value");

            sendText(addEmployeePage.photo, mapNewEmp.get("Photograph"));

            if(!addEmployeePage.checkbox.isSelected()){
                click(addEmployeePage.checkbox);
            }
            sendText(addEmployeePage.createUsername, mapNewEmp.get("Username"));
            sendText(addEmployeePage.createPassword, mapNewEmp.get("Password"));
            sendText(addEmployeePage.confirmPassword, mapNewEmp.get("Password"));
            click(addEmployeePage.saveBtn);
            Thread.sleep(3000);
            click(employeeSearchPage.empListOption);
            sendText(employeeSearchPage.idField, empIdValue);
            click(employeeSearchPage.searchButton);

            List<WebElement> rowData = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
            for (int i=0; i<rowData.size();i++){
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);
                String expectedData = empIdValue+" "+mapNewEmp.get("FirstName")+" "+mapNewEmp.get("MiddleName")
                        +" "+mapNewEmp.get("LastName");
                Assert.assertEquals(expectedData, rowText);
            }
            click(employeeSearchPage.addEmployeeOption);
            Thread.sleep(2000);
        }
    }

    @And("user grabs the employee id")
    public void userGrabsTheEmployeeId() {
        empId=addEmployeePage.employeeId.getAttribute("value");
        firstName=addEmployeePage.firstNameField.getAttribute("value");
    }

    @And("user queries the database for same employee id")
    public void userQueriesTheDatabaseForSameEmployeeId() {
        String query = "select * from hs_hr_employees where employee_id='"+empId+"'";
        dbFirstName = DBUtils.getDataFromDB(query).get(0).get("emp_firstname");
        dbEmpId = DBUtils.getDataFromDB(query).get(0).get("employee_id");
    }

    @Then("user verifies the employee was added")
    public void userVerifiesTheEmployeeWasAdded() {
        System.out.println("firstName from Front End : "+firstName);
        System.out.println("firstName from Database : "+dbFirstName);
        System.out.println("employee_id from Front End : "+empId);
        System.out.println("employee_id from Database : "+dbEmpId);
        Assert.assertEquals(firstName,dbFirstName);
    }
}
