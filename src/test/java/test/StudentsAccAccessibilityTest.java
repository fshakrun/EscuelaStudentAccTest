package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.ConfProperties;
import pages.ChatPage;
import pages.DictionaryPage;
import pages.LoginPage;
import pages.NotesPage;
import pages.MiniCoursesPage;
import pages.StudentClassesPage;
import pages.StudentsHomeworkPage;
import pages.StudentsAccountPage;
import pages.StudentsProfilePage;
import pages.StudentTestsPage;
import pages.TopUpPage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

// Для запуска тестов согласно названию
@TestMethodOrder(MethodOrderer.DisplayName.class)
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


    @BeforeAll
    public static void setup() throws Exception {
        hromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        chatPage = new ChatPage(driver);
        dictionaryPage = new DictionaryPage(driver);
        notesPage = new NotesPage(driver);
        miniCoursesPage = new MiniCoursesPage(driver);
        studentClassesPage = new StudentClassesPage(driver);
        studentsHomeworkPage = new StudentsHomeworkPage(driver);
        studentsAccountPage = new StudentsAccountPage(driver);
        studentsProfilePage = new StudentsProfilePage(driver);
        studentTestsPage =new StudentTestsPage(driver);
        topUpPage = new TopUpPage(driver);
        driver.manage().timeouts();
        driver.get(ConfProperties.getProperty("loginpage"));
        Thread.sleep(11000);
    }


    @Test
    @DisplayName("0. Login Page Availablility Test")
    public void loginPageTest() throws InterruptedException {

        driver.get(ConfProperties.getProperty("loginpage"));
        String title = driver.getTitle();
        assertThat(title).contains("Web-Escuela");
        Thread.sleep(11000);

    }

    @Test
    @DisplayName("1. Valid Credential Authentification Passed Test")
    public void enterAccountTest() throws InterruptedException {

        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        Thread.sleep(12000);
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student");
        Thread.sleep(11000);


    }

    @Test
    @DisplayName("2. Chat Page Access")
    public void shouldOpenChatPage() throws InterruptedException {

//        loginPage.emailEnter(ConfProperties.getProperty("email"));
//        loginPage.passwordEnter(ConfProperties.getProperty("password"));
//        loginPage.enterClick();
        Thread.sleep(11000);
        chatPage.chatSectionClick();
        Thread.sleep(11000);
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/chat");
        Thread.sleep(11000);

    }

    @Test
    @DisplayName("3. Dictionary Page Access")
    public void shouldOpenDictPage() throws InterruptedException {
        Thread.sleep(11000);
        dictionaryPage.dictSectionClick();
        Thread.sleep(11000);
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/dict");

    }

    @Test
    @DisplayName("4. Notes Page Access")
    public void shouldOpeNotesPage() throws InterruptedException {
        Thread.sleep(11000);
        notesPage.notesSectionClick();
        Thread.sleep(11000);
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/notes");

    }

    @Test
    @DisplayName("5. Mini-courses Page Access")
    public void shouldOpeMinicoursesPage() throws InterruptedException {
        Thread.sleep(11000);
        miniCoursesPage.minicoursesSectionClick();
        Thread.sleep(11000);
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/minicourses");

    }

    @Test
    @DisplayName("6. Classes Page Access")
    public void shouldOpeClassesPage() throws InterruptedException {
        Thread.sleep(11000);
        studentClassesPage.classesSectionClick();
        Thread.sleep(11000);
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/classes");


    }

    @Test
    @DisplayName("7. Homework Page Access")
    public void shouldOpeHomeworkPage() throws InterruptedException {
        Thread.sleep(11000);
        studentsHomeworkPage.homeworkSectionClick();
        Thread.sleep(11000);
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/homework");


    }

    @Test
    @DisplayName("8. Student Account Page Access")
    public void shouldOpeAccountPage() throws InterruptedException {
        Thread.sleep(11000);
        studentsAccountPage.studentsAccountClick();
        Thread.sleep(11000);
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student");


    }

    @Test
    @DisplayName("9. Student Profile Page Access")
    public void shouldOpeProfilePage() throws InterruptedException {
        Thread.sleep(11000);
        studentsProfilePage.studentsProfileClick();
        Thread.sleep(11000);
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/profile");


    }

    @Test
    @DisplayName("10. Tests Page Access")
    public void shouldOpeTestsPage() throws InterruptedException {
        Thread.sleep(11000);
        studentTestsPage.testSectionClick();
        Thread.sleep(11000);
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/tests");


    }

    @Test
    @DisplayName("11. Top Up Page Access")
    public void shouldOpeTopUpPage() throws InterruptedException {
        Thread.sleep(11000);
        topUpPage.topUpSectionClick();
        Thread.sleep(11000);
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/buy");
    }
}

