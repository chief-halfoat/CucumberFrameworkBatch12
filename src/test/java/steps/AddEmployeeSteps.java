package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;

import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {
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

}
