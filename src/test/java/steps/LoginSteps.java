package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginSteps extends CommonMethods {
    @Then("admin user successfully logged in")
    public void admin_user_successfully_logged_in() {
        Assert.assertTrue(dashboard.welcomeMessage.isDisplayed());
//        System.out.println("test passed");
     //   tearDown();
    }

    @When("user enters valid ess username and password")
    public void user_enters_valid_ess_username_and_password() {
        sendText(login.usernameTextbox,"tts12345");
        sendText(login.passwordTextbox, "Hum@nhrm123");
//        click(login.loginBtn);
//        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    @Then("ess user is successfully logged in")
    public void ess_user_is_successfully_logged_in() {
        //LEAVE FOR VALIDATION
        //tearDown();
    }

    @When("users enters invalid username and password")
    public void users_enters_invalid_username_and_password() {

        WebElement usernamefield = driver.findElement(By.id("txtUsername"));
        sendText(usernamefield, "TTS12345");
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        sendText(passwordField,"hum@nhrm");
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
    }
    @Then("user sees error message on the screen")
    public void user_sees_error_message_on_the_screen() {
        System.out.println("The Error Message is displayed : "+login.spanMessage.isDisplayed());
        System.out.println("The Error Message is : "+login.spanMessage.getText());
        //homework - verify error message for this
        //tearDown();
    }
    @When("user enters valid username and invalid password")
    public void user_enters_valid_username_and_invalid_password() {
        sendText(login.usernameTextbox, ConfigReader.getPropertyValue("username"));
        sendText(login.passwordTextbox, "kuwgbuwfc");
    }

    @Then("user gets {string} error message")
    public void user_gets_error_message(String string) {
        Assert.assertEquals(string, login.spanMessage.getText());
        System.out.println("Expected message: "+string);
        System.out.println("Actual message "+login.spanMessage.getText());
    }

    @When("user enters invalid username and valid password")
    public void user_enters_invalid_username_and_valid_password() {
        sendText(login.usernameTextbox, "kskjdjff");
        sendText(login.passwordTextbox, ConfigReader.getPropertyValue("password"));
    }

    @When("user enters no username and valid password")
    public void user_enters_no_username_and_valid_password() {
        sendText(login.usernameTextbox, "");
        sendText(login.passwordTextbox, ConfigReader.getPropertyValue("password"));
    }

    @When("user enters valid username and no password")
    public void user_enters_valid_username_and_no_password() {
        sendText(login.usernameTextbox, ConfigReader.getPropertyValue("username"));
        sendText(login.passwordTextbox, "");
    }

    @When("user enters combinations of valid and invalid or blank username and password and clicks login button and verify the error message")
    public void user_enters_combinations_of_valid_and_invalid_or_blank_username_and_password_and_clicks_login_button_and_verify_the_error_message(DataTable dataTable) throws InterruptedException {
        List<Map<String,String>> logins = dataTable.asMaps();
        for(Map<String,String> login:logins){
            String username = login.get("username");
            String password = login.get("password");
            String expectedMsg = login.get("expectedMessage");

            if(username==null){
            } else if(username.equals("valid")){
                username = ConfigReader.getPropertyValue("username");
            } else if (username.equals("invalid")){
                username = ConfigReader.getPropertyValue("invalidUsername");
            }

            if(password==null){
            } else if(password.equals("valid")){
                password = ConfigReader.getPropertyValue("password");
            } else if (password.equals("invalid")){
                password = ConfigReader.getPropertyValue("invalidPassword");
            }

            if(username==null){
                LoginPage.login.usernameTextbox.clear();
                sendText(LoginPage.login.passwordTextbox, password);
                click(LoginPage.login.loginBtn);
            } else if (password==null){
                sendText(LoginPage.login.usernameTextbox, username);
                LoginPage.login.passwordTextbox.clear();
                click(LoginPage.login.loginBtn);
            } else {

                sendText(LoginPage.login.usernameTextbox, username);
                sendText(LoginPage.login.passwordTextbox, password);
                click(LoginPage.login.loginBtn);
            }
            Assert.assertEquals(LoginPage.login.spanMessage.getText(),expectedMsg);
            System.out.println("Entered username:"+username);
            System.out.println("Entered password: "+password);
            System.out.println("Expected Error Message : "+expectedMsg);
            System.out.println("Actual Error Message : "+LoginPage.login.spanMessage.getText());
            driver.navigate().refresh();
        }
    }

}
