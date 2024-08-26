package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ConfProperties;
import pages.LoginPage;
import pages.TeacherAcc;

import java.awt.*;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherAccAccessibilityTest {

    public static LoginPage loginPage;

    public static WebDriver driver;

    public static TeacherAcc teacherAcc;

    public static WebDriverWait wait;

    @BeforeAll
    public static void setup() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--window-size=1920,1080");
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
    }

    @Test
    @Order(2)
    @DisplayName("2. Lessons History Page Access")
    public void shouldOpenLessonsHistoryPage() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherLessonsHistory));
        teacherAcc.teacherLessonsHistory.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/teacher/lesson/history");
    }

    @Test
    @Order(3)
    @DisplayName("3. Exercise Factory Page Access")
    public void shouldOpenExerciseFactoryPage() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherExerciceFactory));
        teacherAcc.teacherExerciceFactory.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/teacher/exercise-factory");
    }

    @Test
    @Order(4)
    @DisplayName("4. Homework Page Access")
    public void shouldOpenHomeworkPage() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherHomework));
        teacherAcc.teacherHomework.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/teacher/homework");
    }

    @Test
    @Order(5)
    @DisplayName("5. Chat Page Access")
    public void shouldOpenChatPage() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherChat));
        teacherAcc.teacherChat.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/teacher/chat");
    }

    @Test
    @Order(6)
    @DisplayName("6. Library Page Access")
    public void shouldOpenLibraryPage() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherLibrary));
        teacherAcc.teacherLibrary.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/teacher/library");
    }

    @Test
    @Order(7)
    @DisplayName("7. Course Builder Page Access")
    public void shouldOpenCourseBuilderPage() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherCourseBuilder));
        teacherAcc.teacherCourseBuilder.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/courses");
    }


}
