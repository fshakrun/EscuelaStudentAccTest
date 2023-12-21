package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatPage {

    public WebDriver driver;
    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(css= "#app > div.sc-fAMvPz.dGDqTo > div.sc-bvLVNa.fqgbQw > div.sc-iwCbjw.fauYpK > div.sc-jwExjx.lmFjuz > div:nth-child(2) > a:nth-child(4)")
    public WebElement chatSection;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[2]/div/span")
    public WebElement chatNotSelected;

    public void chatSectionClick(){
        chatSection.click();
    }



    }


