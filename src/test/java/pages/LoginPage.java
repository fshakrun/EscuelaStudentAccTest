package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='app']/form/label[1]/span[2]/input")
    public WebElement emailField;
    @FindBy(xpath = "//*[@id='app']/form/label[2]/span[2]/input")
    public WebElement passwordField;

    @FindBy(xpath = "//*[@id='app']/form/p[2]")
    public WebElement invalidCredentialsNotification;

    @FindBy(xpath = "//*[@id='app']/form/div[3]/span/a[1]")
    public WebElement recoveryPasswordButton;

    @FindBy(css = "[type=submit]")
    private WebElement enterButton;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div/img[1]")
    public WebElement friendPromoBanner;

    @FindBy(xpath = "/html/body/div[10]/div/div/div/div/div[2]")
    public WebElement lessonsGiftPopup;

    @FindBy(xpath = "//*[@id='app']/form/div[2]/span")
    public WebElement existingMailNotification;

    //форма регистрации

    @FindBy(xpath = "//*[@id='app']/form/label[1]/span/input")
    public WebElement nameFieldSignUp;

    @FindBy(xpath = "//*[@id='app']/form/label[2]/span/input")
    public WebElement lastnameFieldSignUp;

    @FindBy(xpath = "//*[@id='app']/form/label[3]/span/input")
    public WebElement emailFieldSignUp;

    @FindBy(xpath = "//*[@id='app']/form/label[4]/span/div/input")
    public WebElement phonenumberFieldSignUp;

    @FindBy(xpath = "//*[@id='app']/form/button[2]")
    public WebElement signUpButton;


    public void emailEnter(String email) {
        emailField.sendKeys(email);
    }

    public void passwordEnter(String password) {

        passwordField.sendKeys(password);
    }

    public void enterClick() {
        enterButton.click();

    }

    public void lessonsGiftPopupClose() {
        WebElement element = lessonsGiftPopup;
        Actions action = new Actions(driver);
        action.moveToElement(element, 100, 100).click().build().perform();
    }

    public void signingUpUsingExistingMail() {
        driver.get("https://escuela-stage.web.app/register");
        nameFieldSignUp.sendKeys("Test");
        lastnameFieldSignUp.sendKeys("Test");
        emailFieldSignUp.sendKeys("firstlessonnotifiq@escuela.pro");
        phonenumberFieldSignUp.click();
        phonenumberFieldSignUp.sendKeys("1");
        signUpButton.click();

    }


}