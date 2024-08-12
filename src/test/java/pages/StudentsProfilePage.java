package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[2]/div[1]/div")
    public WebElement interfaceLang;


    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/form/div[2]/div[3]/div[1]/div[2]/div/div/div/input")
    public WebElement studentPhoneField;

    @FindBy(css = "#menu-lang > div.MuiPaper-root.MuiMenu-paper.MuiPopover-paper.MuiPaper-elevation8.MuiPaper-rounded > ul > li:nth-child(1)")
    public WebElement russianInterface;

    @FindBy(xpath = "//li[@data-value='en'")
    public WebElement englishInterface;

    @FindBy(xpath = "//li[@data-value='es'")
    public WebElement spanishInterface;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/form/div[3]")
    public WebElement scheduleTitle;
    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[2]/button")
    public WebElement saveButton;
    @FindBy(css = "#input-first-name-helper-text")
    public WebElement nameNotification;


    @FindBy(xpath = "//*[@id='input-skype-helper-text']")
    public WebElement skypeNotification;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/form/button")
    public WebElement logOutButton;


    public void clearFirstName() {
        studentFirstNameField.click();
        studentFirstNameField.sendKeys(Keys.CONTROL + "A");
        studentFirstNameField.sendKeys(Keys.DELETE);
    }

    public void coursorMouseLangMenu() {
        WebElement coursMouse = interfaceLang;
        new Actions(driver)
                .moveToElement(coursMouse)
                .perform();
    }

    public void dropDownLangMenu() {
        interfaceLang.sendKeys(Keys.ARROW_DOWN);
        interfaceLang.sendKeys(Keys.ENTER);
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



