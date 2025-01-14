package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// страница чата
public class ChatPage {

    public WebDriver driver;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[1]/div[1]/a[4]")
    public WebElement chatSection;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[2]/div/span")
    public WebElement chatNotSelected;

    public void chatSectionClick() {
        chatSection.click();
    }

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div[1]/input")
    public WebElement searchField;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div[2]/div")
    public WebElement chatSearchResult;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[2]/div[2]/textarea")
    public WebElement messageField;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[2]/div[2]/button")
    public WebElement sendMessageButton;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[2]/div[1]/div/span[4]/div/div")
    public WebElement messageBubble;

    public void searchForTeachersChat(){
        searchField.click();
        searchField.sendKeys("Ant");
    }

    public void sendAMessageToTeacher(){
        messageField.click();
        messageField.sendKeys("Тестовое ради проверки доступности");
        sendMessageButton.click();
    }


}


