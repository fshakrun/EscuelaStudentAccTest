package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class TopUpPage {
    public WebDriver driver;

    public TopUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/a/button/div")
    public WebElement topUpSection;

    @FindBy (xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/p")
    public WebElement topUpSectionTitle;
    
    @FindBy (xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[3]/div/button")
    public WebElement getMyGiftButton;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div")
    public WebElement shareMyPromocodePopUp;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div/div/div[1]/div[1]")
    public WebElement shareMyPromocodePopUpTitle;

    @FindBy(xpath = "//*[@id='app']/div[2]/div/div/div[1]")
    public WebElement promocodeIsCopiedNotification;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div/div/div[1]/div[2]/div[2]")
    public WebElement copyPromocodeButton;
    @FindBy(xpath = "/html/body/div[7]/div/div/div/div/img")
    public WebElement closeShareMyPromocodePopUp;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div/div/div[1]/a")
    public WebElement sendToTelegramButton;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div/div")
    public WebElement currencyChoice;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div/div[2]/div[1]")
    public WebElement rubCurrencyChoice;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div/div[2]/div[2]")
    public WebElement euroCurrencyChoice;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[6]/div/div[1]/span[3]/span")
    public WebElement firstPackagePrice;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[4]/div[2]/div")
    public WebElement familyPackages;

    @FindBy(xpath ="//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[4]/div[3]/div")
    public WebElement nativeSpeakerPackages;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[4]/div[1]/div")
    public WebElement ordinaryPackages;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[6]/div/div[1]/span[1]")
    public WebElement numberLessonFirstPackage;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[6]/div/div[1]/button")
    public WebElement fourLessonsBtn;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[6]/div/div[2]/button/span")
    public WebElement eightLessonsBtn;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[6]/div/div[3]/button")
    public WebElement sixteenLessonsBtn;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[6]/div/div[4]/button/span")
    public WebElement thirtytwoLessonBtn;

    public void topUpSectionClick(){
        topUpSection.click();
    }

}