package steps;

import pages.AddEmployeePage;
import pages.EmployeeInformationPage;
import pages.EmployeeSearchPage;
import pages.LoginPage;

public class PageInitializers {
    public static LoginPage login;
    public static EmployeeSearchPage employeeSearchPage;
    public static AddEmployeePage addEmployeePage;
    public static EmployeeInformationPage employeeInformationPage;
    public static void initializePageObjects(){
        login = new LoginPage();
        employeeSearchPage = new EmployeeSearchPage();
        addEmployeePage = new AddEmployeePage();
        employeeInformationPage = new EmployeeInformationPage();
    }
}
