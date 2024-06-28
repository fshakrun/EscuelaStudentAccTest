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

import java.io.IOException;
import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static test.StudentProfileTest.studentsAccountPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LessonStartTest {

    public static LoginPage loginPage;

    public static WebDriver driver;

    public static TeacherAcc teacherAcc;
    public static WebDriverWait wait;

    @BeforeAll
    public static void setup() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        loginPage = new LoginPage(driver);
        teacherAcc = new TeacherAcc(driver);
        driver.get(ConfProperties.getProperty("loginpage"));
    }


    @Test
    @Order(1)
    @DisplayName("1. Valid Credential Teacher Account Log In Test")
    public void enterAccWithValidCredTest() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("teacherEmail"));
        loginPage.passwordEnter(ConfProperties.getProperty("teacherPassword"));
        loginPage.enterClick();
        // ожидание появления элемента — расписание
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherSchedule));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/teacher");

    }


    @Test
    @Order(2)
    @DisplayName("2. Start Lesson Test")
    public void startLesson() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherClasses));
        teacherAcc.teacherClasses.click();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherSelectStudent));
        teacherAcc.teacherSelectStudent.click();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherSelectLesson));
        teacherAcc.teacherSelectLesson.click();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.startLessonBtn));
        teacherAcc.startLessonBtn.click();
        assert (teacherAcc.callStudentButton.isDisplayed());
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherLogOut));
        teacherAcc.teacherLogOut.click();
        driver.manage().deleteAllCookies();

    }

//    @Test
//    @Order(3)
//    @DisplayName("3. Student Joins Started Lesson")
//    public void studentJoinsLesson() throws IOException, InterruptedException {
//
//        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
//        loginPage.emailEnter(ConfProperties.getProperty("email"));
//        loginPage.passwordEnter(ConfProperties.getProperty("password"));
//        loginPage.enterClick();
//        driver.navigate().refresh();
//        //assert (studentsAccountPage.joinLessonBtn).isDisplayed();

    }



