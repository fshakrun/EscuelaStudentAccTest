package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotesPage {
    public WebDriver driver;

    public NotesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    @FindBy(css = "#app > div.sc-fAMvPz.dGDqTo > div.sc-bvLVNa.fqgbQw > div.sc-iwCbjw.fauYpK > div.sc-jwExjx.lmFjuz > div:nth-child(3) > a:nth-child(2)")
    public WebElement notesSection;

    public void notesSectionClick(){notesSection.click();}

}