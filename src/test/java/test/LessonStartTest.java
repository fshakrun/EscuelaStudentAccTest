package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ConfProperties;
import pages.LoginPage;
import pages.TeacherAcc;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static test.StudentAccLoginTest.studentsAccountPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LessonStartTest {

    public static LoginPage loginPage;

    public static WebDriver driver;

    public static TeacherAcc teacherAcc;

    public static WebDriverWait wait;

    @BeforeAll
    public static void setup() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        loginPage = new LoginPage(driver);
        teacherAcc = new TeacherAcc(driver);
        driver.get(ConfProperties.getProperty("loginpage"));
    }


    @Test
    @Order(1)
    @DisplayName("1. Valid Credential Teacher Account Log In Log Out Test")
    public void enterAccWithValidCredTest() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("teacherEmail"));
        loginPage.passwordEnter(ConfProperties.getProperty("teacherPassword"));
        loginPage.enterClick();
        // ожидание появления элемента — расписание
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherSchedule));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/teacher");
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherLogOut));
        teacherAcc.teacherLogOut.click();
        driver.manage().deleteAllCookies();
    }

    @Test
    @Order(3)
    @DisplayName("3. Teacher Starts Lesson")
    public void startLesson() throws InterruptedException, AWTException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("teacherEmail"));
        loginPage.passwordEnter(ConfProperties.getProperty("teacherPassword"));
        loginPage.enterClick();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherClasses));
        driver.get("https://escuela-stage.web.app/teacher/classes");
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.particularStudentChoice));
        teacherAcc.particularStudentChoice.click();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherSelectExistingLesson));
        teacherAcc.teacherSelectExistingLesson.click();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.startLessonBtn));
        teacherAcc.startLessonBtn.click();
        assert (teacherAcc.studentLessonBalance).isDisplayed();
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherLogOut));
        teacherAcc.teacherLogOut.click();
        driver.manage().deleteAllCookies();
    }

    @Test
    @Order(2)
    @DisplayName("2. Creating New Lesson Test")
    public void creatingNewLesson() throws InterruptedException, AWTException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("teacherEmail"));
        loginPage.passwordEnter(ConfProperties.getProperty("teacherPassword"));
        loginPage.enterClick();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherClasses));
        teacherAcc.teacherClasses.click();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherCreateNewClass));
        teacherAcc.teacherCreateNewClass.click();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherSelectStudent));
        teacherAcc.teacherSelectStudent.click();
        teacherAcc.teacherSelectStudent.sendKeys("firstlessonnotifiq@escuela.pro");
        Robot selectStudent = new Robot();
        selectStudent.keyPress(KeyEvent.VK_ENTER);
        selectStudent.keyRelease(KeyEvent.VK_ENTER);
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.lessonName));
        teacherAcc.lessonName.sendKeys("firstlessonnotifiq@escuela.pro");
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.saveLessonBtn));
        teacherAcc.saveLessonBtn.click();

    }


    @Test
    @Order(4)
    @DisplayName("4. Student Joins Started Lesson")
    public void studentJoinsLesson() throws IOException, InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        driver.navigate().refresh();
        assert (studentsAccountPage.joinLessonBtn).isDisplayed();
    }
}




