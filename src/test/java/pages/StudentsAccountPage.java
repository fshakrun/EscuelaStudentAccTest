package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class StudentsAccountPage {
    public WebDriver driver;

    public StudentsAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }

    @FindBy (xpath = "//*[@id='app']/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]")
    public WebElement studentsAccount;

    public void studentsAccountClick(){
        studentsAccount.click();
    }

}
