package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;


public class StudentClassesPage {

    public WebDriver driver;

    public StudentClassesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[1]/div[1]/a[2]")
    public WebElement classesSection;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div[1]")
    public WebElement currentCourse;

    @FindBy(css = "#app > div.sc-jMakVo.kQxfEz > div.sc-iMTnTL.kzNFp > div > div.sc-jaXxmE.kyEfQS > div.sc-ibQAlb.sc-hLLwan.fOjcKx > div.sc-hwfkaJ.kIkgXG > div:nth-child(1)")
    public WebElement currentCourseTitle;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div[2]")
    public WebElement myClassesTab;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[1]/div[3]")
    public WebElement allCoursesTab;

    @FindBy(xpath = "//*[text() = 'Anton Testov']")
    public WebElement firstOfMyClasses;

    @FindBy(xpath = "//*[text() = '1. ¿Cómo te llamas?']")
    public WebElement firstLesson;

    @FindBy(className = "canvas-container")
    public  WebElement firstLessonPicture;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[2]/div")
    public WebElement firstOfAllCourses;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[1]/div/div[2]/div[2]/div[2]/div[4]/div[2]/div[1]")
    public WebElement firstLessonOfCurrentCourse;



    public void classesSectionClick() {
        classesSection.click();
    }

    public void scrollToViewLessons() throws AWTException {
        Robot selectStudent = new Robot();
        selectStudent.keyPress(KeyEvent.VK_PAGE_DOWN);


    }
}
