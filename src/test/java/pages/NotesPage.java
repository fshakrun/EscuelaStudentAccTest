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


    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[1]/div[2]/a[2]")
    public WebElement notesSection;

    @FindBy (xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[1]/div/img")
    public WebElement notesPageLogo;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div/div[2]")
    public WebElement oneParticularNote;

    @FindBy(xpath = "//*[text() = 'Сгенерировать историю']")
    public WebElement storyGenerateButton;

    @FindBy(xpath = "//*[text() = 'Как озвучивать заметки']")
    public WebElement notesVoiceoverPopup;

    @FindBy(xpath = "//*[text() = 'Объясняем про заметки тут']" )
    public WebElement voiceoverPopupText;


    @FindBy(xpath = "/html/body/div[7]/div/div/div/div/div/div[2]")
    public WebElement closeVoiceoverPopup;


    public void notesSectionClick(){notesSection.click();}

    public void closeVoiceoverPopup(){closeVoiceoverPopup.click();}
}