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

    @FindBy(css= "#app > div.sc-fAMvPz.dGDqTo > div.sc-bvLVNa.fqgbQw > div > div.sc-jwExjx.lmFjuz > div:nth-child(3) > a:nth-child(1)")
    public WebElement dictSection;

    public void dictSectionClick() {dictSection.click();
    }
}
