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

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]")
    public WebElement studentsAccount;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/p[1]")
    public WebElement studentSchedule;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[1]/div[3]/div[3]/a/button/div")
    public WebElement topUpSectionButton;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/a[1]")
    public WebElement googlePlayButton;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/a[2]")
    public WebElement appStoreButton;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/a[1]")
    public WebElement vkButton;

    @FindBy(xpath = "//a[@href='https://www.instagram.com/escuela.pro']")
    public WebElement instButton;

    @FindBy(xpath = "//a[@href='https://www.facebook.com/esp.escuela/']")
    public WebElement fbButton;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[1]/div[2]/div[1]/div/div[1]/button")
    public WebElement joinLessonBtn;

    @FindBy(xpath = "/html/body/div[11]/div/div/div/div")
    public WebElement friendspromoBanner;

    @FindBy(className = "calender-time")
    public WebElement calendarTime;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[1]/div[2]/div[1]/div/div[1]/div[1]/div[2]")
    public WebElement nextLessonNotification;

    @FindBy(xpath = "//*[text() = 'Помощь']")
    public WebElement techSuppButton;

    @FindBy(className = "arsf-message-list")
    public WebElement supportWindow;

    @FindBy(xpath = "//*[@id='apppopupmax']/div/div/div/div/div/div/div/div/div/div/div[2]/form/textarea")
    public WebElement messageInputField;

    @FindBy(xpath = "//*[@id='apppopupmax']/div/div/div/div/div/div/div/div/div/div/div[2]/form/div[2]/button")
    public WebElement sendMessageButton;

    @FindBy(className = "bubble")
    public WebElement dialogBubble;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div/button/div")
    public WebElement networkingBanner;

    @FindBy(xpath ="//*[@id='app']/div[1]/div[1]/div[2]/div[2]/div[1]/a")
    public WebElement blogArticlesShow;


    public void studentsAccountClick() {
        studentsAccount.click();
    }

    public void sendMessageToTechSupp() {
        messageInputField.click();
        messageInputField.sendKeys("Тест отправки сообщения. Можно не отвечать.");
        sendMessageButton.click();
    }

}
