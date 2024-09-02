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
import pages.StudentsAccountPage;
import pages.StudentsProfilePage;

import java.awt.*;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.testng.Assert.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentMainPageTest {

    public static LoginPage loginPage;
    public static StudentsProfilePage studentsProfilePage;
    public static StudentsAccountPage studentsAccountPage;
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void setup() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        loginPage = new LoginPage(driver);
        studentsAccountPage = new StudentsAccountPage(driver);
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @Order(1)
    @DisplayName("1. Valid Credential Account Log In Test")
    public void enterAccWithValidCredTest() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
//        wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
//        loginPage.friendPromoBanner.click();
        // ожидание появления элемента — расписание
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.studentSchedule));
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student");

    }

    @Test
    @Order(2)
    @DisplayName("2. Google Play Button Test")
    public void goToGooglePlay() throws InterruptedException, AWTException {

        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.googlePlayButton));

        String originalWindow = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;
        studentsAccountPage.googlePlayButton.click();
        wait.until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://play.google.com/store/apps/details?id=com.escuela.pro");
        driver.close();
        driver.switchTo().window(originalWindow);

    }

    @Test
    @Order(3)
    @DisplayName("3. App Store Button Test")
    public void goToAppStore() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.appStoreButton));

        String originalWindow = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;

        studentsAccountPage.appStoreButton.click();
        wait.until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String URL = driver.getCurrentUrl();
        assert (URL).contains("id1524291158");
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test
    @Order(4)
    @DisplayName("4. VK Button Test")
    public void goToVk() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.vkButton));

        String originalWindow = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;
        studentsAccountPage.vkButton.click();
        wait.until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://vk.com/escuelapro");
        driver.close();
        driver.switchTo().window(originalWindow);


    }

    @Test
    @Order(5)
    @DisplayName("5. Tuesday Regular Lesson Checking")
    public void shouldCheckTuesdayRegularLesson() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.studentSchedule));
        String TuesdayLesson = (studentsAccountPage.calendarTime).getText();
        assertThat(TuesdayLesson).contains("03:00\n" +
                "Testov Anton.");
    }

    @Test
    @Order(6)
    @DisplayName("6. Next Lesson Notification Checking")
    public void shouldCheckNextLessonNotification() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.nextLessonNotification));
        String NextLesson = (studentsAccountPage.nextLessonNotification).getText();
        assertThat(NextLesson).contains("Вторник");
    }


    @Test
    @Order(7)
    @DisplayName("7. Instagram Button Test")
    public void goToInstagram() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.instButton));
        String originalWindow = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;
        studentsAccountPage.instButton.click();
        wait.until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://www.instagram.com/escuela.pro");
        driver.close();
        driver.switchTo().window(originalWindow);

    }

    @Test
    @Order(8)
    @DisplayName("8. Facebook Button Test")
    public void goToFacebook() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.fbButton));
        String originalWindow = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;
        studentsAccountPage.fbButton.click();
        wait.until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://www.facebook.com/esp.escuela/");
        driver.close();
        driver.switchTo().window(originalWindow);

    }

    @Test
    @Order(9)
    @DisplayName("9. Tech Support Help Test")
    public void shouldSendMessageToSupport() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.techSuppButton));
        studentsAccountPage.techSuppButton.click();
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.supportWindow));
        studentsAccountPage.sendMessageToTechSupp();
        assert (studentsAccountPage.dialogBubble).isDisplayed();

    }

    @Test
    @Order(10)
    @DisplayName("10. Networking Bot Test")
    public void shouldCheckNetworkingBot() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.networkingBanner));
        String originalWindow = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;
        studentsAccountPage.networkingBanner.click();
        wait.until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String URL = driver.getCurrentUrl();
        assert (URL).contains("Escuela_Networking_Bot");
        driver.close();
        driver.switchTo().window(originalWindow);

    }
//
//    @Test
//    @Order(11)
//    @DisplayName("11. Blog Opening Test")
//    public void shouldOpenEscuelaBlog() throws InterruptedException {
//       wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.blogArticlesShow));
//       studentsAccountPage.blogArticlesShow.click();
//        String URL = driver.getCurrentUrl();
//        assertEquals(URL, "https://escuela.pro/blog");
//    }
}


