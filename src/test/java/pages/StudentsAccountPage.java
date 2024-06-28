package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


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

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/a[2]")
    public WebElement instButton;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/a[3]")
    public WebElement fbButton;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[1]/div[2]/div[1]/div/div[1]/button")
    public WebElement joinLessonBtn;

    @FindBy(xpath = "/html/body/div[11]/div/div/div/div")
    public WebElement friendspromoBanner;


    public void studentsAccountClick() {
        studentsAccount.click();
    }

}
