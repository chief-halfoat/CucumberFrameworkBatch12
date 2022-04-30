package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {
    //object repository
    @FindBy(id="txtUsername")
    public WebElement usernameTextbox;

    @FindBy(id="txtPassword")
    public WebElement passwordTextbox;

    @FindBy(id="btnLogin")
    public WebElement loginBtn;

    @FindBy(id = "spanMessage")
    public WebElement spanMessage;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }
}
