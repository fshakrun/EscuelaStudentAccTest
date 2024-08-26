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
import pages.MiniCoursesPage;
import pages.StudentsAccountPage;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentMiniCourseTest {
    public static LoginPage loginPage;
    public static StudentsAccountPage studentsAccountPage;
    public static MiniCoursesPage miniCoursesPage;
    public static WebDriver driver;
    public static WebDriverWait wait;


    @BeforeAll
    public static void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        loginPage = new LoginPage(driver);
        studentsAccountPage = new StudentsAccountPage(driver);
        miniCoursesPage = new MiniCoursesPage(driver);
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @Order(1)
    @DisplayName("1.Check A Minicourse Is On Page")
    public void checkMinicourseOpens() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
//        wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
//        loginPage.friendPromoBanner.click();
        // ожидание появления элемента — расписание
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.studentSchedule));
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.minicoursesSection));
        miniCoursesPage.minicoursesSectionClick();
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.testPracticamosCourse));
        miniCoursesPage.testPracticamosCourse.click();
        String minicourseURl = driver.getCurrentUrl();
        assert(minicourseURl).contains("Tmgt0u7FIeBhalOEpIvU");

    }

}
