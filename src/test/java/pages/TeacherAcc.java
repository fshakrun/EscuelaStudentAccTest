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

    @FindBy(xpath = "//*[@id='app']/div[1]/nav/div/a[4]")
    public WebElement teacherClasses;

    @FindBy(xpath = "//*[@id='app']/div[1]/main/div[1]/div/div[4]/text()[1]")
    public WebElement teacherSelectStudent;

    @FindBy(id = "0s6R2dnuoB7J1ASWJIKb")
    public WebElement teacherSelectLesson;

    @FindBy(xpath = "//*[@id='remoteVideoStreamId']/div[3]/div[2]/button[3]")
    public WebElement startLessonBtn;

    @FindBy (xpath = "//*[@id='remoteVideoStreamId']/div[1]/button")
    public WebElement callStudentButton;

    @FindBy(xpath = "//*[@id='app']/div[1]/nav/div/div/a[2]")
    public WebElement teacherLogOut;
}
