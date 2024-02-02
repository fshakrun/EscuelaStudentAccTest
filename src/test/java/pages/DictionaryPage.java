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

    @FindBy(xpath= "//*[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]")
    public WebElement dictSection;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div/div[1]/div[1]")
    public WebElement dailyWords;

    public void dictSectionClick() {dictSection.click();
    }
}
