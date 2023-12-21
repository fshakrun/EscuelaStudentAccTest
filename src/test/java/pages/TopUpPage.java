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

    public void topUpSectionClick(){
        topUpSection.click();
    }

}