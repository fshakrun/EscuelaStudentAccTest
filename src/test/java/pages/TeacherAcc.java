package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeacherAcc {

    public WebDriver driver;


    public TeacherAcc(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy (xpath = "//*[@id='swJTVlm93MR5rt9BJtZ7dCm0g3y1']/span")
    public WebElement teacherSchedule;
    @FindBy(xpath = "//a[@href='/teacher/classes']")
    public WebElement teacherClasses;

    @FindBy(xpath = "//*[text() = 'Anna']")
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

    @FindBy (xpath = "//*[text() = ' Call']")
    public WebElement callStudentButton;

    @FindBy(xpath = "//*[@id='app']/div[1]/nav/div/div/a[2]")
    public WebElement teacherLogOut;
}
