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
public class StudentsTestPageTest {
    public static LoginPage loginPage;
    public static StudentTestsPage studentTestsPage;
    public static StudentsAccountPage studentsAccountPage;
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
        studentsAccountPage = new StudentsAccountPage(driver);
        studentTestsPage = new StudentTestsPage(driver);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    // Проверка наличия назначенного ученику теста
    @Test
    @Order(1)
    @DisplayName("1. Checking Assigned Test Presence")
    public void shouldFindAssignedTest() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
//        wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
//        loginPage.friendPromoBanner.click();
        // ожидание появления элемента — расписание
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.studentSchedule));
        wait.until(ExpectedConditions.visibilityOf(studentTestsPage.testSection));
        studentTestsPage.testSectionClick();
        wait.until(ExpectedConditions.visibilityOf(studentTestsPage.testSectionLogo));
        assert (studentTestsPage.firstStudentsTest).isDisplayed();
    }

    @Test
    @Order(2)
    @DisplayName("2. Checking Assigned Test Inner Content Presence")
    public void shouldFCheckAssignedTestContent() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentTestsPage.firstStudentsTest));
        studentTestsPage.passToTest();
        wait.until(ExpectedConditions.visibilityOf(studentTestsPage.passToAssignedTest)).isDisplayed();
        studentTestsPage.passToAssignedTest.click();
        assert (studentTestsPage.testResultTab).isDisplayed();
    }


}
