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
import static org.assertj.core.api.Assertions.assertThat;

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
        options.addArguments("--headless","--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        loginPage = new LoginPage(driver);
        studentsAccountPage = new StudentsAccountPage(driver);
        studentsProfilePage = new StudentsProfilePage(driver);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(1)
    @DisplayName("1. Valid Credential Account Log In Log Out Test")
    public void enterAccWithValidCredTest() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
         // ожидание появления элемента — расписание
        wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
        loginPage.friendPromoBanner.click();
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.studentSchedule));
        studentsProfilePage.studentsProfileClick();
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.logOutButton));
        studentsProfilePage.logOutButton.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/login");
    }

    @Test
    @Order(2)
    @DisplayName("2. Invalid Login Valid Password Test")
    public void enterInvalidLoginTest() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailField.sendKeys("checktimezonete11st@gmail.com");
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        assert (loginPage.invalidCredentialsNotification.isDisplayed());
    }

    @Test
    @Order(3)
    @DisplayName("3. Valid Login Invalid Password Test")
    public void enterValidLoginInvalidTest() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordField.sendKeys("11111");
        loginPage.enterClick();
        assert (loginPage.invalidCredentialsNotification.isDisplayed());
    }

    @Test
    @Order(4)
    @DisplayName("4. Password Recovery Page Accessibility Test")
    public void forgottenPasswordRecoveryTest() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.recoveryPasswordButton.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/restore");

    }

    // Проверка невозможности зарегистрироваться с уже использованной почтой
    @Test
    @Order(5)
    @DisplayName("5. Sign Up Using Existing Email")
    public void shouldCheckExistingMail(){
        loginPage.signingUpUsingExistingMail();
        wait.until(ExpectedConditions.visibilityOf(loginPage.existingMailNotification));
        String Notification = loginPage.existingMailNotification.getText();
        assertThat(Notification).contains("занят");
    }

    @Test
    @Order(6)
    @DisplayName("6. Valid Login Enter Test")
    public void enterValidEmailTest() throws InterruptedException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailField.sendKeys("checktimezonete11st@gmail.com");
        String Email = loginPage.emailField.getAttribute("value");
        assertEquals(Email, "checktimezonete11st@gmail.com");
    }


}
