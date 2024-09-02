package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DictionaryPage {
    public WebDriver driver;

    public DictionaryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]")
    public WebElement dictSection;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div/div[1]/div[1]")
    public WebElement todaysWords;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div/div[1]/div[2]")
    public WebElement wordsForWeek;
    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div/div[1]/div[3]")
    public WebElement wordsForMonth;
    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div/div[1]/div[4]")
    public WebElement wordsFor3Months;
    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div/div[1]/div[5]")
    public WebElement allWords;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div/div[2]/img")
    public WebElement pictureWhenEmpty;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div/div[2]/div[1]/div[2]")
    public WebElement firstWordOfAllWords;

    public void dictSectionClick() {
        dictSection.click();
    }
}
