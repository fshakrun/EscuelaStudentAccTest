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
import pages.StudentsAccountPage;
import pages.StudentsProfilePage;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class StudentAccLoginTest {

    public static LoginPage loginPage;
    public static StudentsAccountPage studentsAccountPage;
    public static StudentsProfilePage studentsProfilePage;
    public static WebDriver driver;
    public static WebDriverWait wait;

    //"--headless",
    @BeforeAll
    public static void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        loginPage = new LoginPage(driver);
        studentsAccountPage = new StudentsAccountPage(driver);
        studentsProfilePage = new StudentsProfilePage(driver);
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @DisplayName("0. Valid Credential Account Log In Log Out Test")
    public void enterAccWithValidCredTest() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        // ожидание появления элемента — расписание
<<<<<<< HEAD
//        wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
//        loginPage.friendPromoBanner.click();
=======
        // wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
        // loginPage.friendPromoBanner.click();
>>>>>>> 9762874745359d6a90e93fd56a067060df453b41
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.studentSchedule));
        studentsProfilePage.studentsProfileClick();
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.logOutButton));
        studentsProfilePage.logOutButton.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/login");


    }

    @Test
    @DisplayName("1. Invalid Login Valid Password Test")
    public void enterInvalidLoginTest() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailField.sendKeys("checktimezonete11st@gmail.com");
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        assert (loginPage.invalidCredentialsNotification.isDisplayed());

    }

    @Test
    @DisplayName("2. Valid Login Invalid Password Test")
    public void enterValidLoginInvalidTest() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordField.sendKeys("11111");
        loginPage.enterClick();
        assert (loginPage.invalidCredentialsNotification.isDisplayed());

    }

    @Test
    @DisplayName("3. Password Recovery Page Accessibility Test")
    public void forgottenPasswordRecoveryTest() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.recoveryPasswordButton.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/restore");

    }

    @Test
    @DisplayName("4. Valid Login Enter Test")
    public void enterValidEmailTest() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailField.sendKeys("checktimezonete11st@gmail.com");
        String Email = loginPage.emailField.getAttribute("value");
        assertEquals(Email, "checktimezonete11st@gmail.com");
    }


}
