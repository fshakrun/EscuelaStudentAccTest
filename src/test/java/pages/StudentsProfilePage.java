package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StudentsProfilePage {

    public WebDriver driver;

    public StudentsProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath ="//*[@id='app']/div[1]/div[1]/div[1]/div[1]/a/span")
    public WebElement studentsProfile;

    public void studentsProfileClick(){
        studentsProfile.click();
    }

}
