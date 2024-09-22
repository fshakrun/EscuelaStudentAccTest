package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StudentTestsPage {

    public WebDriver driver;

    public StudentTestsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/a[4]")
    public WebElement testSection;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[1]/div/img")
    public WebElement testSectionLogo;

    @FindBy(xpath= "/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/div")
    public WebElement firstStudentsTest;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/div[3]/div[2]/div[2]")
    public WebElement passToAssignedTest;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div/div[2]/div[2]/div[1]/div/div[1]")
    public WebElement testResultTab;

    public void testSectionClick() {
        testSection.click();
    }

    public void passToTest(){
        new Actions(driver)
                .moveToElement(passToAssignedTest)
                .perform();
    }
}



