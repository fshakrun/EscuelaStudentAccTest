package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentChatPageTest {
    public static LoginPage loginPage;
    public static StudentsAccountPage studentsAccountPage;
    public static ChatPage chatPage;
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
        chatPage = new ChatPage(driver);
        studentsAccountPage = new StudentsAccountPage(driver);
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @DisplayName("1. Particular Chat Searching And Checking Its Presence Test")
    public void checkingParticularChatDisplaying() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        // ожидание появления элемента — расписание
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.studentSchedule));
        chatPage.chatSectionClick();
        wait.until(ExpectedConditions.visibilityOf(chatPage.searchField));
        chatPage.searchForTeachersChat();
        assert(chatPage.chatSearchResult).isDisplayed();
    }

    @Test
    @DisplayName("2. Particular Chat Searching And Checking Its Presence Test")
    public void checkingMessageSending() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(chatPage.searchField));
        chatPage.chatSearchResult.click();
        chatPage.sendAMessageToTeacher();
        assert (chatPage.messageBubble).isDisplayed();
    }
}



