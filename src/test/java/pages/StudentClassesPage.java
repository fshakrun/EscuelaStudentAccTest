package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class StudentClassesPage {

    public WebDriver driver;

    public StudentClassesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (xpath = "//*[@id='app']/div[1]/div[1]/div/div[1]/div[1]/a[2]")
    public WebElement classesSection;

    public void classesSectionClick(){
        classesSection.click();
    }
}
