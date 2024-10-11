package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MiniCoursesPage {

    public WebDriver driver;

    public MiniCoursesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[1]/div[2]/a[3]")
    public WebElement minicoursesSection;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/h1")
    public WebElement practicamosTitle;

    @FindBy(xpath = "//*[text() = '09 november teachers cours']")
    public WebElement testPracticamosCourse;

    @FindBy(xpath = "//a[@href='/student/minicourses']")
    public WebElement allCourseButton;

    @FindBy(xpath = "//*[text() = 'Кто то где-то']")
    public WebElement paidPracticamosCourse;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div[2]/h4")
    public WebElement courseProgramTitle;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/div[1]/div[1]/button/div[2]/div[3]/div/button")
    public WebElement buyPracticamosButton;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/div[1]/div[1]/button/div[2]/div[3]/div/div")
    public WebElement currencySelectButton;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/div[1]/div[1]/button/div[2]/div[3]/div/div/div[2]/div[1]")
    public WebElement rubCurrencySelect;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/div[1]/div[1]/button/div[2]/div[3]/div/div/div[2]/div[2]")
    public WebElement euroCurrencySelect;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/div[1]/div[1]/button/div[2]/div[3]/div/div/div[2]/div[3]")
    public WebElement usdCurrencySelect;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/div[2]/div/input")
    public WebElement practicamosPromocodeField;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/div[2]/div/div/div")
    public WebElement promocodeNotification;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[3]/div/div/img")
    public WebElement checkingPromocodeNotification;

    public void minicoursesSectionClick() {
        minicoursesSection.click();
    }

    public void sendValidPracticamosPromocode(String validPracticamosPromocode){
        practicamosPromocodeField.click();
        practicamosPromocodeField.sendKeys(validPracticamosPromocode);
    }

    public void sendInvalidPracticamosPromocode(String invalidPracticamosPromocode){
        practicamosPromocodeField.click();
        practicamosPromocodeField.sendKeys(invalidPracticamosPromocode);
    }



}
