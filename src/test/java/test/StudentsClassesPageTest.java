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
import pages.StudentClassesPage;

import java.awt.*;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

// Для запуска тестов согласно названию
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentsClassesPageTest {
    public static LoginPage loginPage;
    public static StudentClassesPage studentClassesPage;
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
        studentClassesPage = new StudentClassesPage(driver);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    // Проверка наличия текущего курса в разделе "Мои классы"
    @Test
    @Order(1)
    @DisplayName("1. Current Course Presence Test")
    public void shouldCheckCurrentCoursePresence() throws InterruptedException, AWTException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
        loginPage.friendPromoBanner.click();
        wait.until(ExpectedConditions.visibilityOf(studentClassesPage.classesSection));
        studentClassesPage.classesSection.click();
        wait.until(ExpectedConditions.visibilityOf(studentClassesPage.currentCourseTitle));
        String courseTitle = studentClassesPage.currentCourseTitle.getText();
        assertThat(courseTitle).contains("Curso A0");
    }

    // Проверка наличия уроков в разделе "Мои классы"
    @Test
    @Order(2)
    @DisplayName("2. My Classes Presence Test")
    public void shouldCheckMyClassesPresence() throws InterruptedException, AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentClassesPage.myClassesTab));
        studentClassesPage.myClassesTab.click();
        assert (studentClassesPage.firstOfMyClasses).isDisplayed();
    }

    // Проверка наличия всех курсов в в разделе "Мои классы"
    @Test
    @Order(3)
    @DisplayName("3. All Courses Presence Test")
    public void shouldCheckAllCoursesPresence() throws InterruptedException, AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentClassesPage.myClassesTab));
        studentClassesPage.allCoursesTab.click();
        assert (studentClassesPage.firstOfAllCourses).isDisplayed();
    }

    // Проверка отображения контента в открытом уроке текущего курса
    @Test
    @Order(4)
    @DisplayName("4. Passed Lesson Content Displaying")
    public void shouldCheckPassedLessonDisplaying() {
        wait.until(ExpectedConditions.visibilityOf(studentClassesPage.currentCourse));
        studentClassesPage.currentCourse.click();
        wait.until(ExpectedConditions.visibilityOf(studentClassesPage.firstLesson));
        studentClassesPage.firstLesson.click();
        wait.until(ExpectedConditions.visibilityOf(studentClassesPage.firstLessonPicture));
        assert (studentClassesPage.firstLessonPicture).isDisplayed();
    }
}
