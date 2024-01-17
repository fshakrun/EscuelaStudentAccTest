package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;


import java.util.Optional;

import static io.netty.util.internal.SystemPropertyUtil.contains;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class StudentProfileTest {

    public static LoginPage loginPage;
    public static StudentsProfilePage studentsProfilePage;
    public static WebDriver driver;

    @BeforeAll
    public static void setup() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        studentsProfilePage = new StudentsProfilePage(driver);
        driver.manage().timeouts();
        driver.get(ConfProperties.getProperty("loginpage"));
        Thread.sleep(11000);
    }

    @Test
    @DisplayName("1. Enter Students Profile Test")
    public void enterProfile() throws InterruptedException {

        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        Thread.sleep(11000);
        studentsProfilePage.studentsProfileClick();
        Thread.sleep(11000);
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/profile");
        Thread.sleep(12000);


    }

    @Test
    @DisplayName("2. Enter Cyrillic First Name")
    public void enterCyrillicFirstName() throws InterruptedException {
        studentsProfilePage.clearFirstName();
        studentsProfilePage.studentFirstNameField.click();
        studentsProfilePage.studentFirstNameField.sendKeys("Ирина");
        String Name = studentsProfilePage.studentFirstNameField.getAttribute("value");
        assertEquals(Name, "Ирина");
        Thread.sleep(12000);
    }

    @Test
    @DisplayName("3. Enter Too Long Cyrillic First Name")
    public void enterLongCyrillicFirstName() throws InterruptedException {
        studentsProfilePage.clearFirstName();
        studentsProfilePage.studentFirstNameField.click();
        studentsProfilePage.studentFirstNameField.sendKeys("ИринаПопоноваИвановаСебастьяновнаПерепелкинаКовшикова");
        Thread.sleep(12000);
        studentsProfilePage.saveButtonProfile();
        assert (studentsProfilePage.firstNameNotification.isDisplayed());

    }

    @Test
    @DisplayName("4. Enter Too Short Cyrillic First Name")
    public void enterShortCyrillicFirstName() throws InterruptedException {
        studentsProfilePage.clearFirstName();
        studentsProfilePage.studentFirstNameField.click();
        studentsProfilePage.studentFirstNameField.sendKeys("И");
        Thread.sleep(12000);
        studentsProfilePage.saveButtonProfile();
        assert (studentsProfilePage.firstNameNotification.isDisplayed());

    }

    @Test
    @DisplayName("5. Enter Cyrillic First Name With Hyphen")
    public void enterHyphenCyrillicFirstName() throws InterruptedException {
        studentsProfilePage.clearFirstName();
        studentsProfilePage.studentFirstNameField.click();
        studentsProfilePage.studentFirstNameField.sendKeys("Анна-Мария");
        Thread.sleep(12000);
        studentsProfilePage.saveButtonProfile();
        String Name = studentsProfilePage.studentFirstNameField.getAttribute("value");
        assertEquals(Name, "Анна-Мария");

    }

    @Test
    @DisplayName("6. Enter Special Symbols In First Name Field")
    public void enterSpecialSymbolsFirstName() throws InterruptedException {
        studentsProfilePage.clearFirstName();
        studentsProfilePage.studentFirstNameField.click();
        studentsProfilePage.studentFirstNameField.sendKeys("!!№№444++");
        Thread.sleep(12000);
        studentsProfilePage.saveButtonProfile();
        Thread.sleep(12000);
        String Name = studentsProfilePage.studentFirstNameField.getAttribute("value");
        assertEquals(Name, "Анна-Мария");

    }

    @Test
    @DisplayName("7. Enter Space In First Name Field")
    public void enterSpaceFirstName() throws InterruptedException {
        studentsProfilePage.clearFirstName();
        studentsProfilePage.studentFirstNameField.click();
        studentsProfilePage.studentFirstNameField.sendKeys(" ");
        Thread.sleep(12000);
        studentsProfilePage.saveButtonProfile();
        assert (studentsProfilePage.firstNameNotification.isDisplayed());

    }
}

