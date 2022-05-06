package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class EmployeeInformationPage extends CommonMethods {
    @FindBy(xpath = "//div[@id='profile-pic']/h1")
    public WebElement employeeName;

    @FindBy(xpath = "//*[@id='resultTable']/tbody/tr")
    public List<WebElement> rowData;

    public EmployeeInformationPage(){
        PageFactory.initElements(driver,this);
    }
}
