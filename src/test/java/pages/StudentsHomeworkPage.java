package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StudentsHomeworkPage {
    public WebDriver driver;

    public StudentsHomeworkPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[1]/div[1]/a[3]")
    public WebElement homeworkSection;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[1]/div/img")
    public WebElement logoPicture;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div/div")
    public WebElement interactiveHomework;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div/button")
    public WebElement proceedToInteractiveHomework;

    @FindBy(xpath = "//*[@id='6_0']/div/div/h3[1]/span")
    public WebElement firstExercicePageTitle;

    public void homeworkSectionClick() {
        homeworkSection.click();
    }

}
