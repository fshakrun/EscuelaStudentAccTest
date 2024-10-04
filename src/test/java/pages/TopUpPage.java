package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/p")
    public WebElement topUpSectionTitle;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[3]/div/button")
    public WebElement getMyGiftButton;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div")
    public WebElement shareMyPromocodePopUp;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div/div/div[1]")
    public WebElement shareMyPromocodePopUpTitle;

    @FindBy(xpath = "//*[@id='app']/div[2]/div/div/div[1]")
    public WebElement promocodeIsCopiedNotification;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div/div/div[1]/div[2]/div[2]")
    public WebElement copyPromocodeButton;

    @FindBy(xpath = "/html/body/div[8]/div/div/div/div/img")
    public WebElement closeShareMyPromocodePopUp;

    @FindBy(xpath = "//*[text() = 'Отправить в телеграм']")
    public WebElement sendToTelegramButton;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div/div")
    public WebElement currencyChoice;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div/div[2]/div[1]")
    public WebElement rubCurrencyChoice;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div/div[2]/div[2]")
    public WebElement euroCurrencyChoice;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/div[1]/div/div[2]/div[3]")
    public WebElement usdCurrencyChoice;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[6]/div/div[1]/span[3]/span")
    public WebElement firstPackagePrice;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[4]/div[2]/div")
    public WebElement familyPackages;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[4]/div[3]/div")
    public WebElement nativeSpeakerPackages;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[4]/div[1]/div")
    public WebElement ordinaryPackages;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[6]/div/div[1]/span[1]")
    public WebElement numberLessonFirstPackage;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[6]/div/div[1]/button")
    public WebElement fourLessonsBtn;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[6]/div/div[2]/button")
    public WebElement eightLessonsBtn;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[6]/div/div[3]/button")
    public WebElement sixteenLessonsBtn;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[6]/div/div[4]/button")
    public WebElement thirtytwoLessonBtn;

    @FindBy(xpath = "//*[text() = 'Ссылка на оплату']")
    public WebElement paySelectionPopUp;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[3]/input")
    public WebElement promocodeField;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[3]/div/div")
    public WebElement promocodeNotification;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[3]/div/div/img")
    public WebElement checkingPromocodeNotification;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div")
    public WebElement termsOfUsePopUp;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div/div[3]/button")
    public WebElement proceedToPayment;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div/div[3]/div")
    public WebElement closeTermsOfUse;


    public void sendValidPromocode(String validPromoCode){
        promocodeField.click();
        promocodeField.sendKeys(validPromoCode);
    }

    public void sendInvalidPromocode(String invalidPromocode){
        promocodeField.click();
        promocodeField.sendKeys(invalidPromocode);
    }

    public void topUpSectionClick() {
        topUpSection.click();
    }

    public void promocodePopUpClose(){
        WebElement element = closeShareMyPromocodePopUp;
        Actions action = new Actions(driver);
        action.moveToElement(element, 100, 100).click().build().perform();
    }



}