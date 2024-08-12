package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MiniCoursesPage {

    public WebDriver driver;

    public MiniCoursesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[1]/div[2]/a[3]")
    public WebElement minicoursesSection;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/h1")
    public WebElement practicamosTitle;

    public void minicoursesSectionClick() {
        minicoursesSection.click();
    }


}
