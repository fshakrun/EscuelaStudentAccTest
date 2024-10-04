package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.ConfProperties;
import pages.LoginPage;
import pages.StudentsHomeworkPage;
import pages.StudentsProfilePage;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class StudentHomeworkPageTest {

    public static WebDriver driver;
    public static LoginPage loginPage;
    public static StudentsHomeworkPage studentsHomeworkPage;
    public static StudentsProfilePage studentsProfilePage;
    public static WebDriverWait wait;

    @BeforeAll
    public static void setup() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        loginPage = new LoginPage(driver);
        studentsHomeworkPage = new StudentsHomeworkPage(driver);
        studentsProfilePage = new StudentsProfilePage(driver);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    // Проверка что у ученика не исчезла заданная домашка
    @Test
    @Order(1)
    @DisplayName("1. Checking Interactive Homework Is Present")
    public void shouldCheckParticularHw() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        // wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
        //loginPage.friendPromoBanner.click();
        // wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
        // loginPage.friendPromoBanner.click();
        wait.until(ExpectedConditions.visibilityOf(studentsHomeworkPage.homeworkSection));
        studentsHomeworkPage.homeworkSectionClick();
        wait.until(ExpectedConditions.visibilityOf(studentsHomeworkPage.interactiveHomework));
        String HomeworkTitle = (studentsHomeworkPage.interactiveHomework).getText();
        assertThat(HomeworkTitle).contains("1. ¿Cómo te llamas?");
    }
    // Проверка что заданная домашка открывается
    @Test
    @Order(2)
    @DisplayName("2. Checking Homework First Internal Page Opened")
    public void shouldProceedToHw() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsHomeworkPage.proceedToInteractiveHomework));
        studentsHomeworkPage.proceedToInteractiveHomework.click();
        wait.until(ExpectedConditions.visibilityOf(studentsHomeworkPage.firstExercicePageTitle));
        String HomeworkTitle = studentsHomeworkPage.firstExercicePageTitle.getText();
        assert (HomeworkTitle).contains("Deberes");
        studentsHomeworkPage.returnToHomework.click();
    }

    // Проверка наличия не выполненного сочинения
    @Test
    @Order(3)
    @DisplayName("3. Checking Incomplete Essay Presence")
    public void shouldFindIncompleteEssay() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsHomeworkPage.proceedToInteractiveHomework));
        assert(studentsHomeworkPage.incompleteEssay).isDisplayed();
    }

    // Проверка наличия отправленного учителю сочинения
    @Test
    @Order(4)
    @DisplayName("4. Checking Essay Waiting For Verification")
    public void shouldFindEssaySendToCheck() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsHomeworkPage.proceedToInteractiveHomework));
        assert(studentsHomeworkPage.essayWaitingForCheck).isDisplayed();
    }

    // Проверка выполненного сочинения
    @Test
    @Order(5)
    @DisplayName("5. Checking Completed Essay Presence")
    public void shouldFindCompletedEssay() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsHomeworkPage.proceedToInteractiveHomework));
        assert(studentsHomeworkPage.completedCheckedEssay).isDisplayed();
    }

    // Проверка наличия у выполненного сочинения комментария от учителя
    @Test
    @Order(6)
    @DisplayName("6. Checking Teachers Comment About Completed Essay Presence")
    public void shouldFindTeachersComment() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsHomeworkPage.proceedToInteractiveHomework));
        assert(studentsHomeworkPage.teachersCommentOnEssay).isDisplayed();
    }

    // Отображение новых домашек при соответствующей настройке в личном кабинете ученика
    @Test
    @Order(7)
    @DisplayName("7. Checking Homework Counter Is On")
    public void shouldShowNewHomeWorks() throws InterruptedException{
        studentsProfilePage.studentsProfileClick();
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.homeWorkDisplayingOptions));
        studentsProfilePage.homeWorkDisplayingOptions.click();
        studentsProfilePage.showNewHomeWorkOption.click();
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.saveButton));
        studentsProfilePage.saveButton.click();
        assert (studentsHomeworkPage.homeworkCounter).isDisplayed();
    }

    // Неотображение новых домашек при соответствующей настройке в личном кабинете ученика
    @Test
    @Order(8)
    @DisplayName("8. Checking Homework Counter Is Off")
    public void shouldNotShowNewHomeWorks() throws InterruptedException{
        studentsProfilePage.studentsProfileClick();
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.homeWorkDisplayingOptions));
        studentsProfilePage.homeWorkDisplayingOptions.click();
        studentsProfilePage.notShowNewHomeWorkOption.click();
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.saveButton));
        studentsProfilePage.saveButton.click();
        assert (studentsHomeworkPage.noHomeworkCounterState).isDisplayed();
    }

}
