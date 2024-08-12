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
import pages.NotesPage;
import pages.StudentsAccountPage;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentsNotesPageTest {

    public static LoginPage loginPage;
    public static NotesPage notesPage;
    public static StudentsAccountPage studentsAccountPage;
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void setup() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        loginPage = new LoginPage(driver);
        notesPage = new NotesPage(driver);
        studentsAccountPage = new StudentsAccountPage(driver);
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @Order(1)
    @DisplayName("1. Valid Credential Account Log In Test")
    public void enterAccountTest() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
        loginPage.friendPromoBanner.click();
        // ожидание появления элемента — расписание
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.studentSchedule));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student");

    }

    @Test
    @Order(2)
    @DisplayName("2. Notes Page Access")
    public void shouldOpeNotesPage() throws InterruptedException {

        notesPage.notesSectionClick();
        wait.until(ExpectedConditions.visibilityOf(notesPage.notesPageLogo));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/notes");

    }

    @Test
    @Order(3)
    @DisplayName("3. Check If One Note Is Present")
    public void shouldCheckIfNoteExist() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(notesPage.oneParticularNote));
        notesPage.oneParticularNote.click();
        assert (notesPage.storyGenerateButton).isDisplayed();

    }

    @Test
    @Order(4)
    @DisplayName("4. Notes Voiceover Instruction Pop-Up Checking")
    public void shouldCheckNotesVoiceoverPopupExist() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(notesPage.notesVoiceoverPopup));
        notesPage.notesVoiceoverPopup.click();
        assert (notesPage.voiceoverPopupText).isDisplayed();
        notesPage.closeVoiceoverPopup();
    }
}


