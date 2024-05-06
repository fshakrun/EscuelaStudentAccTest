package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.awt.*;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.testng.Assert.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentTopUpTest {

    public static LoginPage loginPage;
    public static StudentsProfilePage studentsProfilePage;
    public static StudentsAccountPage studentsAccountPage;
    public static TopUpPage topUpPage;
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void setup() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        loginPage = new LoginPage(driver);
        studentsProfilePage = new StudentsProfilePage(driver);
        studentsAccountPage = new StudentsAccountPage(driver);
        topUpPage = new TopUpPage(driver);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(0)
    @DisplayName("0. Enter Top Up Section Test")
    public void enterTopUpSection() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/buy");
    }

    @Test
    @Order(1)
    @DisplayName("1. Share My Promocode Pop Up Appearence Test")
    public void openMyPromoCodePopUp() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.getMyGiftButton));
        topUpPage.getMyGiftButton.click();
        String promoCodePopUpTitle = topUpPage.shareMyPromocodePopUpTitle.getText();
        assertThat(promoCodePopUpTitle).contains("2");
    }

    @Test
    @Order(2)
    @DisplayName("2. Send Promocode To Telegram")
    public void shouldSendPromocodeToTelegram() throws InterruptedException {
            wait.until(ExpectedConditions.visibilityOf(topUpPage.sendToTelegramButton));
        String originalWindow = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;
        topUpPage.sendToTelegramButton.click();
        wait.until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://telegram.me/share/url?url=https://escuela.pro&text=%D0%92%D0%B0%D0%BC%20%D0%B1%D1%83%D0%B4%D1%83%D1%82%20%D0%B2%D1%80%D1%83%D1%87%D0%B5%D0%BD%D1%8B%20%D0%B4%D0%B2%D0%B0%");
        driver.switchTo().window(originalWindow);
        topUpPage.closeShareMyPromocodePopUp.click();
    }

    // Смена валюты
    @Test
    @Order(3)
    @DisplayName("3. Changing Currency To Euro")
    public void shouldChangeEuroCurrency() {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.euroCurrencyChoice.click();
        String firstPackagePrice = topUpPage.firstPackagePrice.getText();
        assert (firstPackagePrice).contains("€");

    }

    // Переключение между типами пакетов уроков: Обычный, Семейный, С носителем
    @Test
    @Order(4)
    @DisplayName("4. Changing Currency To Rub")
    public void shouldChangeRubCurrency() {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.rubCurrencyChoice.click();
        String firstPackagePrice = topUpPage.firstPackagePrice.getText();
        assert (firstPackagePrice).contains("₽");

    }

    @Test
    @Order(5)
    @DisplayName("5. Changing Package To Familial")
    public void shouldChangeToFamilyPackage() {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        String firstPackagePrice = topUpPage.numberLessonFirstPackage.getText();
        assert (firstPackagePrice).contains("4");

    }

    @Test
    @Order(7)
    @DisplayName("7. 4 Lessons Familial Package Payment Page")

    public void shouldProceedToPayment4FamilialLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        topUpPage.fourLessonsBtn.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @Order(8)
    @DisplayName("8. 8 Lessons Familial Package Payment Page")

    public void shouldProceedToPayment8FamilialLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        topUpPage.eightLessonsBtn.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));

    }
    @Test
    @Order(9)
    @DisplayName("9. 16 Lessons Familial Package Payment Page")

    public void shouldProceedToPayment16FamilialLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        topUpPage.sixteenLessonsBtn.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @Order(10)
    @DisplayName("10. 32 Lessons Familial Package Payment Page")

    public void shouldProceedToPayment32FamilialLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        topUpPage.thirtytwoLessonBtn.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @Order(11)
    @DisplayName("11. Changing Package To Spanish-speaking teacher")
    public void shouldChangeToNativePackage() {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.nativeSpeakerPackages));
        topUpPage.nativeSpeakerPackages.click();
        String firstPackagePrice = topUpPage.numberLessonFirstPackage.getText();
        assert (firstPackagePrice).contains("4");

    }


    @Test
    @Order(12)
    @DisplayName("12. 4 Lessons Native Package Payment Page")

    public void shouldProceedToPayment4NativelLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.nativeSpeakerPackages.click();
        topUpPage.fourLessonsBtn.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));

    }


    @Test
    @Order(13)
    @DisplayName("13. 8 Lessons Native Package Payment Page")

    public void shouldProceedToPayment8NativelLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.nativeSpeakerPackages.click();
        topUpPage.eightLessonsBtn.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @Order(14)
    @DisplayName("14. 16 Lessons Native Package Payment Page")

    public void shouldProceedToPayment16NativelLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.nativeSpeakerPackages.click();
        topUpPage.sixteenLessonsBtn.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @Order(15)
    @DisplayName("15. 32 Lessons Native Package Payment Page")

    public void shouldProceedToPayment32NativelLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.nativeSpeakerPackages.click();
        topUpPage.thirtytwoLessonBtn.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @Order(16)
    @DisplayName("16. Changing Package To Ordinary Teacher")
    public void shouldChangeToOrdinaryPackage() {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        String firstPackagePrice = topUpPage.numberLessonFirstPackage.getText();
        assert (firstPackagePrice).contains("4");

    }

    @Test
    @Order(17)
    @DisplayName("17. 4 Lessons Ordinary Package Payment Page")

    public void shouldProceedToPayment4OrdinarylLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        topUpPage.fourLessonsBtn.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @Order(18)
    @DisplayName("18. 8 Lessons Ordinary Package Payment Page")

    public void shouldProceedToPayment8OrdinarylLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        topUpPage.eightLessonsBtn.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @Order(19)
    @DisplayName("19. 16 Lessons Ordinary Package Payment Page")

    public void shouldProceedToPayment16OrdinarylLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        topUpPage.sixteenLessonsBtn.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @Order(20)
    @DisplayName("20. 32 Lessons Ordinary Package Payment Page")

    public void shouldProceedToPayment32OrdinarylLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        topUpPage.thirtytwoLessonBtn.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));

    }


}
