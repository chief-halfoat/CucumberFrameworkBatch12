package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;

import java.util.concurrent.TimeUnit;

public class LoginSteps extends CommonMethods {
    @Then("admin user successfully logged in")
    public void admin_user_successfully_logged_in() {
        System.out.println("test passed");
        tearDown();
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
        tearDown();
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
        tearDown();
    }
}
