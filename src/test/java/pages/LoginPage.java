package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "//*[@id='app']/form/label[1]/span[2]/input" )
    private WebElement emailField;
    @FindBy (xpath = "//*[@id='app']/form/label[2]/span[2]/input")
    private WebElement passwordField;

    @FindBy (css = "[type=submit]" )
    private WebElement enterButton;

    public void emailEnter(String email){
        emailField.sendKeys(email);
    }

    public void passwordEnter(String password) {
        passwordField.sendKeys(password);
    }

    public void enterClick() {
        enterButton.click();

    }
}