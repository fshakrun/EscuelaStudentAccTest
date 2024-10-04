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

import java.awt.*;
import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherAccountTest {

    public static LoginPage loginPage;

    public static WebDriver driver;

    public static TeacherAcc teacherAcc;

    public static WebDriverWait wait;

    @BeforeAll
    public static void setup() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        loginPage = new LoginPage(driver);
        teacherAcc = new TeacherAcc(driver);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    // Проверка успешного входа в учительский аккаунт
    @Test
    @Order(1)
    @DisplayName("1. Valid Credential Teacher Account Log In Test")
    public void teacherAccLogIn() throws InterruptedException {
            wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
            loginPage.emailEnter(ConfProperties.getProperty("teacherEmail"));
            loginPage.passwordEnter(ConfProperties.getProperty("teacherPassword"));
            loginPage.enterClick();
            // ожидание появления элемента — расписание
            wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherSchedule));
            String URL = driver.getCurrentUrl();
            assertEquals(URL, "https://escuela-stage.web.app/teacher");
    }

    // Проверка уведомления о новых домашних работах от учеников
    @Test
    @Order(2)
    @DisplayName("2. Checking New Homework Notification")
    public void teacherNewHomeworkNotification() throws InterruptedException {
        // ожидание появления элемента — уведомление о необходимости проверки д/з
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.haveToCheckHomeWorkPopUp));
        teacherAcc.proceedToCheckingHomeWorkButton.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/teacher/homework");
    }

    // Проверка доступности раздела с историей уроков в личном кабинете учителя
    @Test
    @Order(4)
    @DisplayName("4. Lessons History Page Access")
    public void shouldOpenLessonsHistoryPage() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherLessonsHistory));
        teacherAcc.teacherLessonsHistory.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/teacher/lesson/history");
    }

    // Проверка доступности раздела Фабрики упражнений
    @Test
    @Order(5)
    @DisplayName("5. Exercise Factory Page Access")
    public void shouldOpenExerciseFactoryPage() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherExerciceFactory));
        teacherAcc.teacherExerciceFactory.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/teacher/exercise-factory");
    }

    // Проверка доступности раздела с домашними заданиями в личном кабинете учителя
    @Test
    @Order(6)
    @DisplayName("6. Homework Page Access")
    public void shouldOpenHomeworkPage() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherHomework));
        teacherAcc.teacherHomework.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/teacher/homework");
    }

    // Проверка доступности чата в личном кабинете учителя
    @Test
    @Order(7)
    @DisplayName("7. Chat Page Access")
    public void shouldOpenChatPage() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherChat));
        teacherAcc.teacherChat.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/teacher/chat");
    }

    // Проверка доступности раздела библиотеки в личном кабинете учителя
    @Test
    @Order(8)
    @DisplayName("8. Library Page Access")
    public void shouldOpenLibraryPage() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherLibrary));
        teacherAcc.teacherLibrary.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/teacher/library");
    }

    // Проверка доступности раздела с конструктором уроков
    @Test
    @Order(9)
    @DisplayName("9. Course Builder Page Access")
    public void shouldOpenCourseBuilderPage() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherCourseBuilder));
        teacherAcc.teacherCourseBuilder.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/courses");
    }

    // Проверка отображения регулярного урока в расписании учителя
    @Test
    @Order(10)
    @DisplayName("10. Checking Particular Regular Lesson Presence")
    public void shouldCheckRegularLessonPresence() throws InterruptedException, AWTException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherMainPage));
        teacherAcc.teacherMainPage.click();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherSchedule)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.particularRegulaLesson)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.particularRegulaLesson));
        teacherAcc.particularRegulaLesson.click();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.particularRegularLessonTime)).isDisplayed();
        String regularLessonTime = teacherAcc.particularRegularLessonTime.getText();
        assertThat(regularLessonTime).contains("03:00-04:00");
        teacherAcc.closeLessonPopup();
    }

    // У учителя отображается начатое учеником интерактивное упражнение
    @Test
    @Order(11)
    @DisplayName("11. Checking Interactive Homework In Progress Presence")
    public void shouldCheckInteractiveHomework() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherHomework));
        teacherAcc.teacherHomework.click();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.particularStudentHomeworkTab));
        teacherAcc.particularStudentHomeworkTab.click();
        assert (teacherAcc.interactiveHomeworkInProgress).isDisplayed();
    }

    // У учителя отображается домашка, которую ученик еще не начал
    @Test
    @Order(12)
    @DisplayName("12. Checking Assigned But Not Started Homework")
    public void shouldCheckNotStartedHomework() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherHomework));
        teacherAcc.teacherHomework.click();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.particularStudentHomeworkTab));
        teacherAcc.particularStudentHomeworkTab.click();
        assert (teacherAcc.homeworkNotStarted).isDisplayed();
    }

    // У учителя отображается коммент к выполенному сочинению
    @Test
    @Order(13)
    @DisplayName("13. Checking Comment Of Completed Homework Presence")
    public void shouldCheckHomeworkComment() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherHomework));
        teacherAcc.teacherHomework.click();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.particularStudentHomeworkTab));
        teacherAcc.particularStudentHomeworkTab.click();
        assert (teacherAcc.commentOfCheckedHomework).isDisplayed();
    }

    // У учителя отображается выполненное учеником, но еще не проверенное сочинение
    @Test
    @Order(14)
    @DisplayName("14. Checking Completed But Not Checked Homework Presence")
    public void shouldCheckCompletedHomework() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.teacherHomework));
        teacherAcc.teacherHomework.click();
        wait.until(ExpectedConditions.visibilityOf(teacherAcc.particularStudentHomeworkTab));
        teacherAcc.particularStudentHomeworkTab.click();
        assert (teacherAcc.homeworkNotCheckedByTeacher).isDisplayed();
    }





}

