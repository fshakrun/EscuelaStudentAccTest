package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ConfProperties;
import pages.LoginPage;
import pages.StudentsHomeworkPage;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentHomeworkPageTest {


    public static WebDriver driver;

    public static LoginPage loginPage;

    public static StudentsHomeworkPage studentsHomeworkPage;

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
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(1)
    @DisplayName("1. Checking Interactive Homework Is Present")
    public void shouldCheckParticularHw() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
        loginPage.friendPromoBanner.click();
        wait.until(ExpectedConditions.visibilityOf(studentsHomeworkPage.homeworkSection));
        studentsHomeworkPage.homeworkSectionClick();
        wait.until(ExpectedConditions.visibilityOf(studentsHomeworkPage.interactiveHomework));
        String HomeworkTitle = (studentsHomeworkPage.interactiveHomework).getText();
        assertThat(HomeworkTitle).contains("1. ¿Cómo te llamas?");


    }

    @Test
    @Order(2)
    @DisplayName("2. Checking Homework First Internal Page Opened")
    public void shouldProceedToHw() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsHomeworkPage.proceedToInteractiveHomework));
        studentsHomeworkPage.proceedToInteractiveHomework.click();
        wait.until(ExpectedConditions.visibilityOf(studentsHomeworkPage.firstExercicePageTitle));
        String HomeworkTitle = studentsHomeworkPage.firstExercicePageTitle.getText();
        assert (HomeworkTitle).contains("Deberes");

    }

}
