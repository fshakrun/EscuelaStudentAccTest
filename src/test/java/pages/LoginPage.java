package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;


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



    public void emailEnter(String email) {
        emailField.sendKeys(email);
    }

    public void passwordEnter(String password) {

        passwordField.sendKeys(password);
    }

    public void enterClick() {
        enterButton.click();

    }


}