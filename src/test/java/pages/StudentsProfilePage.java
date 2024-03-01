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

    @FindBy(name = "lastName")
    public WebElement studentLastNameField;
    @FindBy(name = "birthDate")
    public WebElement studentBirthDateField;

    @FindBy(xpath = "//*[@id='input-skype']")
    public WebElement studentSkypeField;

    @FindBy(xpath ="//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/form/div[2]/div[3]/div[1]/div[2]/div/div/div/input")
    public WebElement studentPhoneField;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[2]/button")
    public WebElement saveButton;
    @FindBy(xpath = "//*[@id='input-first-name-helper-text']")
    public WebElement firstNameNotification;
    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/div[1]/form/div[2]/div[2]/div[2]/p")
    public WebElement lastNameNotification;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/form/button")
    public WebElement logOutButton;


    public void clearFirstName() {
        studentFirstNameField.click();
        studentFirstNameField.sendKeys(Keys.CONTROL + "A");
        studentFirstNameField.sendKeys(Keys.BACK_SPACE);
    }

    public void saveButtonProfile() {
        saveButton.click();
    }

    public void clearLastName() {
        studentLastNameField.click();
        studentLastNameField.sendKeys(Keys.CONTROL + "A");
        studentLastNameField.sendKeys(Keys.BACK_SPACE);
    }

    public void clearBirthDate() {
        studentBirthDateField.click();
        studentBirthDateField.sendKeys(Keys.CONTROL + "A");
        studentBirthDateField.sendKeys(Keys.BACK_SPACE);
    }

    public void clearSkypeField() {
        studentSkypeField.click();
        studentSkypeField.sendKeys(Keys.CONTROL + "A");
        studentSkypeField.sendKeys(Keys.BACK_SPACE);
    }

    public void clearPhoneField() {
        studentPhoneField.click();
        studentPhoneField.sendKeys(Keys.CONTROL + "A");
        studentPhoneField.sendKeys(Keys.BACK_SPACE);
    }

}



