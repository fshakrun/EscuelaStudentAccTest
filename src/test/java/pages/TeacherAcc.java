package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TeacherAcc {

    public WebDriver driver;


    public TeacherAcc(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "/html/body/div[3]/div/div/div/div")
    public WebElement haveToCheckHomeWorkPopUp;

    @FindBy(xpath = "/html/body/div[3]/div/div/div/div/button[1]")
    public WebElement proceedToCheckingHomeWorkButton;

    @FindBy(xpath = "//*[@id='swJTVlm93MR5rt9BJtZ7dCm0g3y1']/span")
    public WebElement teacherSchedule;

    @FindBy (xpath = "//a[@href='/teacher']")
    public WebElement teacherMainPage;

    @FindBy(xpath = "//a[@href='/teacher/classes']")
    public WebElement teacherClasses;

    @FindBy(xpath = "//a[@href='/teacher/lesson/history']")
    public WebElement teacherLessonsHistory;

    @FindBy(xpath = "//a[@href='/teacher/exercise-factory']")
    public WebElement teacherExerciceFactory;

    @FindBy(xpath = "//a[@href='/teacher/homework']")
    public WebElement teacherHomework;

    @FindBy(xpath = "/html/body/div[1]/div[1]/main/div/div[1]/div/div[2]")
    public WebElement particularStudentHomeworkTab;

    @FindBy(xpath = "/html/body/div[1]/div[1]/main/div/div[3]/div/div/div[5]/button")
    public WebElement homeworkNotCheckedByTeacher;

    @FindBy(xpath = "/html/body/div[1]/div[1]/main/div/div[4]/div[2]")
    public WebElement interactiveHomeworkInProgress;

    @FindBy(xpath = "/html/body/div[1]/div[1]/main/div/div[4]/div[1]")
    public WebElement homeworkNotStarted;

    @FindBy(xpath = "//*[@id='app']/div[1]/main/div/div[4]/div[2]/div")
    public WebElement homeworkInProgress;

    @FindBy(xpath = "/html/body/div[1]/div[1]/main/div/div[5]/div[1]/div[2]/div")
    public WebElement commentOfCheckedHomework;

    @FindBy(xpath = "//a[@href='/teacher/chat']")
    public WebElement teacherChat;

    @FindBy(xpath = "//a[@href='/teacher/library']")
    public WebElement teacherLibrary;

    @FindBy(xpath = "//a[@href='/courses']")
    public WebElement teacherCourseBuilder;

    @FindBy(xpath= "/html/body/div[1]/div[1]/main/div[1]/div[2]/div/div[2]/div/table/tbody/tr/td/div/div/div/div[2]/table/tbody/tr/td[5]/div/div[2]/div/a/div/div")
    public WebElement particularRegulaLesson;

    @FindBy(xpath = "/html/body/div[5]/div/div/div/form/header/div[2]/div/div/div[1]/div")
    public WebElement particularRegularLessonTime;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div/div[1]")
    public WebElement particularStudentChoice;

    @FindBy(xpath = "//*[@id='app']/div[1]/main/div[2]/button/span[1]")
    public WebElement teacherCreateNewClass;

    @FindBy(xpath = "//*[@id='react-select-5-input']")
    public WebElement teacherSelectStudent;

    @FindBy(xpath = "/html/body/div[6]/div/div/div/div/div/div[3]/div[1]/div/input")
    public WebElement lessonName;

    @FindBy(xpath = "//*[text() = 'Save']")
    public WebElement saveLessonBtn;

    @FindBy(xpath = "//*[text() = 'Autotestclasses']")
    public WebElement teacherSelectExistingLesson;

    @FindBy(xpath = "//*[@id='remoteVideoStreamId']/div[3]/div[2]/button[3]")
    public WebElement startLessonBtn;

    @FindBy(xpath = "//*[@id='remoteVideoStreamId']/div[1]/div[2]")
    public WebElement studentLessonBalance;

    @FindBy(xpath = "//*[text() = ' Call']")
    public WebElement callStudentButton;

    @FindBy(xpath = "//*[@id='app']/div[1]/nav/div/div/a[2]")
    public WebElement teacherLogOut;

    public void closeLessonPopup() throws AWTException {
        Robot particularRegularLessonTime = new Robot();
        particularRegularLessonTime.keyPress(KeyEvent.VK_ESCAPE);
        particularRegularLessonTime.keyRelease(KeyEvent.VK_ESCAPE);
    }
}
