package pages;

import org.openqa.selenium.Keys;
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

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[1]/div[1]/a/span")
    public WebElement studentsProfile;

    public void studentsProfileClick() {
        studentsProfile.click();
    }

    @FindBy(css = "#input-first-name")
    public WebElement studentFirstNameField;


    public void clearFirstName(){
    studentFirstNameField.click();
    studentFirstNameField.sendKeys(Keys.CONTROL + "A");
    studentFirstNameField.sendKeys(Keys.BACK_SPACE);
    }

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[2]/button")
    public WebElement saveButton;

    public void saveButtonProfile(){
        saveButton.click();
    }

    @FindBy(xpath = "//*[@id='input-first-name-helper-text']")
    public WebElement firstNameNotification;





}



