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

// Для запуска тестов согласно названию
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentsAccAccessibilityTest {
    public static LoginPage loginPage;

    public static DictionaryPage dictionaryPage;
    public static ChatPage chatPage;

    public static NotesPage notesPage;

    public static MiniCoursesPage miniCoursesPage;

    public static StudentClassesPage studentClassesPage;

    public static StudentsHomeworkPage studentsHomeworkPage;

    public static StudentsAccountPage studentsAccountPage;

    public static StudentsProfilePage studentsProfilePage;

    public static StudentTestsPage studentTestsPage;
    public static TopUpPage topUpPage;

    public static WebDriver driver;
    public static WebDriverWait wait;


    @BeforeAll
    public static void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        loginPage = new LoginPage(driver);
        chatPage = new ChatPage(driver);
        dictionaryPage = new DictionaryPage(driver);
        notesPage = new NotesPage(driver);
        miniCoursesPage = new MiniCoursesPage(driver);
        studentClassesPage = new StudentClassesPage(driver);
        studentsHomeworkPage = new StudentsHomeworkPage(driver);
        studentsAccountPage = new StudentsAccountPage(driver);
        studentsProfilePage = new StudentsProfilePage(driver);
        studentTestsPage = new StudentTestsPage(driver);
        topUpPage = new TopUpPage(driver);
        driver.get(ConfProperties.getProperty("loginpage"));

    }


    @Test
    @DisplayName("0. Login Page Availablility Test")
    public void loginPageTest() throws InterruptedException {

        driver.get(ConfProperties.getProperty("loginpage"));
        // ожидание появления элемента — поля для ввода мейла
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        String title = driver.getTitle();
        assertThat(title).contains("Web-Escuela");

    }

    @Test
    @DisplayName("1. Valid Credential Authentification Passed Test")
    public void enterAccountTest() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        // wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
        // loginPage.friendPromoBanner.click();
        // ожидание появления элемента — расписание
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.studentSchedule));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student");


    }

    @Test
    @DisplayName("2. Chat Page Access")
    public void shouldOpenChatPage() throws InterruptedException {

//        loginPage.emailEnter(ConfProperties.getProperty("email"));
//        loginPage.passwordEnter(ConfProperties.getProperty("password"));
//        loginPage.enterClick();

        chatPage.chatSectionClick();
        wait.until(ExpectedConditions.visibilityOf(chatPage.searchField));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/chat");


    }

    @Test
    @DisplayName("3. Dictionary Page Access")
    public void shouldOpenDictPage() throws InterruptedException {

        dictionaryPage.dictSectionClick();
        wait.until(ExpectedConditions.visibilityOf(dictionaryPage.dailyWords));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/dict");

    }

    @Test
    @DisplayName("4. Notes Page Access")
    public void shouldOpeNotesPage() throws InterruptedException {

        notesPage.notesSectionClick();
        wait.until(ExpectedConditions.visibilityOf(notesPage.notesPageLogo));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/notes");

    }

    @Test
    @DisplayName("5. Mini-courses Page Access")
    public void shouldOpeMinicoursesPage() throws InterruptedException {

        miniCoursesPage.minicoursesSectionClick();
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.practicamosTitle));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/minicourses");

    }

    @Test
    @DisplayName("6. Classes Page Access")
    public void shouldOpeClassesPage() throws InterruptedException {

        studentClassesPage.classesSectionClick();
        wait.until(ExpectedConditions.visibilityOf(studentClassesPage.currentCourse));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/classes");


    }

    @Test
    @DisplayName("7. Homework Page Access")
    public void shouldOpeHomeworkPage() throws InterruptedException {

        studentsHomeworkPage.homeworkSectionClick();
        wait.until(ExpectedConditions.visibilityOf(studentsHomeworkPage.logoPicture));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/homework");


    }

    @Test
    @DisplayName("8. Student Account Page Access")
    public void shouldOpeAccountPage() throws InterruptedException {

        studentsAccountPage.studentsAccountClick();
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.studentSchedule));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student");


    }

    @Test
    @DisplayName("9. Student Profile Page Access")
    public void shouldOpeProfilePage() throws InterruptedException {

        studentsProfilePage.studentsProfileClick();
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentFirstNameField));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/profile");


    }

    @Test
    @DisplayName("10. Tests Page Access")
    public void shouldOpeTestsPage() throws InterruptedException {

        studentTestsPage.testSectionClick();
        wait.until(ExpectedConditions.visibilityOf(studentTestsPage.testSectionLogo));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/tests");


    }

    @Test
    @DisplayName("11. Top Up Page Access")
    public void shouldOpeTopUpPage() throws InterruptedException {

        topUpPage.topUpSectionClick();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.topUpSectionTitle));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/buy");
    }
}

