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

    @FindBy(xpath = "//*[text() = '09 november teachers cours']")
    public WebElement testPracticamosCourse;

    @FindBy(xpath = "//*[text() = '16 may saving lessons test']")
    public WebElement paidPracticamosCourse;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div[2]/h4")
    public WebElement courseProgramTitle;

    @FindBy(css = "#app > div.sc-jMakVo.kQxfEz > div.sc-iMTnTL.kzNFp > div > div.sc-jaXxmE.kyEfQS > div.sc-ibQAlb.sc-hqUaMi.fOjcKx.gZONyb > div:nth-child(2) > div.MuiPaper-root.MuiCard-root.sc-hHrewP.hiByWN.MuiPaper-elevation1.MuiPaper-rounded > button")
    public WebElement minicourseCover;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[3]/div/div")
    public WebElement innerLessonOfTestCourse;

    @FindBy(xpath= "//*[text() = 'Купить курс за 102 руб']")
    public WebElement buyPracticamosCourseButton;

    public void minicoursesSectionClick() {
        minicoursesSection.click();
    }


}
