package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;


import java.time.Duration;
import java.util.Optional;

import static io.netty.util.internal.SystemPropertyUtil.contains;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static test.StudentsAccAccessibilityTest.studentsAccountPage;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class StudentProfileTest {

    public static LoginPage loginPage;
    public static StudentsProfilePage studentsProfilePage;
    public static StudentsAccountPage studentsAccountPage;
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void setup() throws Exception {
        ChromeOptions options = new ChromeOptions();
        // для headless режима добавить аргумент "--headless",
        options.addArguments("--headless","--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        loginPage = new LoginPage(driver);
        studentsProfilePage = new StudentsProfilePage(driver);
        studentsAccountPage = new StudentsAccountPage(driver);
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @DisplayName("0. Enter Students Profile Test")
    public void enterProfile() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.studentSchedule));
        studentsProfilePage.studentsProfileClick();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/profile");


    }

    @Test
    @DisplayName("1. Enter Cyrillic First Name")
    public void enterCyrillicFirstName() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentFirstNameField));
        studentsProfilePage.clearFirstName();
        studentsProfilePage.studentFirstNameField.click();
        studentsProfilePage.studentFirstNameField.sendKeys("Ирина");
        String Name = studentsProfilePage.studentFirstNameField.getAttribute("value");
        assertEquals(Name, "Ирина");
    }

    @Test
    @DisplayName("2. Enter Too Long Cyrillic First Name")
    public void enterLongCyrillicFirstName() throws InterruptedException {
        studentsProfilePage.clearFirstName();
        studentsProfilePage.studentFirstNameField.click();
        studentsProfilePage.studentFirstNameField.sendKeys("ИринаПопоноваИвановаСебастьяновнаПерепелкинаКовшикова");
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.saveButton));
        studentsProfilePage.saveButtonProfile();
        assert (studentsProfilePage.firstNameNotification.isDisplayed());

    }

    @Test
    @DisplayName("3. Enter Too Short Cyrillic First Name")
    public void enterShortCyrillicFirstName() throws InterruptedException {
        studentsProfilePage.clearFirstName();
        studentsProfilePage.studentFirstNameField.click();
        studentsProfilePage.studentFirstNameField.sendKeys("И");
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.saveButton));
        studentsProfilePage.saveButtonProfile();
        assert (studentsProfilePage.firstNameNotification.isDisplayed());

    }

    @Test
    @DisplayName("4. Enter Cyrillic First Name With Hyphen")
    public void enterHyphenCyrillicFirstName() throws InterruptedException {
        studentsProfilePage.clearFirstName();
        studentsProfilePage.studentFirstNameField.click();
        studentsProfilePage.studentFirstNameField.sendKeys("Анна-Мария");
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.saveButton));
        studentsProfilePage.saveButtonProfile();
        String Name = studentsProfilePage.studentFirstNameField.getAttribute("value");
        assertEquals(Name, "Анна-Мария");

    }

    // Тест на проверку возможности сохранения имени со спец. символами.
//    @Test
//    @DisplayName("5. Enter Special Symbols In First Name Field")
//    public void enterSpecialSymbolsFirstName() throws InterruptedException {
//        studentsProfilePage.clearFirstName();
//        studentsProfilePage.studentFirstNameField.click();
//        studentsProfilePage.studentFirstNameField.sendKeys("!!№№444++");
//        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.saveButton));
//        studentsProfilePage.saveButtonProfile();
//        String Name = studentsProfilePage.studentFirstNameField.getAttribute("value");
//        assertEquals(Name, "Анна-Мария");
//
//    }

    @Test
    @DisplayName("6. Enter Space In First Name Field")
    public void enterSpaceFirstName() throws InterruptedException {
        studentsProfilePage.clearFirstName();
        studentsProfilePage.studentFirstNameField.click();
        studentsProfilePage.studentFirstNameField.sendKeys(" ");
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.saveButton));
        studentsProfilePage.saveButtonProfile();
        assert (studentsProfilePage.firstNameNotification.isDisplayed());

    }
}

