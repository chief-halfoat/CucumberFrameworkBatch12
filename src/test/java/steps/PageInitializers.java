package steps;

import pages.*;

public class PageInitializers {
    public static LoginPage login;
    public static EmployeeSearchPage employeeSearchPage;
    public static AddEmployeePage addEmployeePage;
    public static EmployeeInformationPage employeeInformationPage;
    public static DashboardPage dashboard;
    public static void initializePageObjects(){
        login = new LoginPage();
        employeeSearchPage = new EmployeeSearchPage();
        addEmployeePage = new AddEmployeePage();
        employeeInformationPage = new EmployeeInformationPage();
        dashboard = new DashboardPage();
    }
}
