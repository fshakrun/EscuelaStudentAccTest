package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.*;

import java.awt.*;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
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
        options.addArguments("--headless","--window-size=1920,1080");
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
    @Order(1)
    @DisplayName("1. Enter Top Up Section Test")
    public void enterTopUpSection() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
        loginPage.friendPromoBanner.click();
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://escuela-stage.web.app/student/buy");
    }

//    На доработке
//    @Test
//    @Order(2)
//    @DisplayName("2. Share My Promocode Pop Up Appearence Test")
//    public void openMyPromoCodePopUp() throws InterruptedException {
//        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
//        studentsAccountPage.topUpSectionButton.click();
//        wait.until(ExpectedConditions.visibilityOf(topUpPage.getMyGiftButton));
//        topUpPage.getMyGiftButton.click();
//        String promoCodePopUpTitle = topUpPage.shareMyPromocodePopUpTitle.getText();
//        assertThat(promoCodePopUpTitle).contains("2");
//    }
//
//    @Test
//    @Order(3)
//    @DisplayName("3. Send Promocode To Telegram")
//    public void shouldSendPromocodeToTelegram() throws InterruptedException {
//        wait.until(ExpectedConditions.visibilityOf(topUpPage.sendToTelegramButton));
//        String originalWindow = driver.getWindowHandle();
//        assert driver.getWindowHandles().size() == 1;
//        topUpPage.sendToTelegramButton.click();
//        wait.until(numberOfWindowsToBe(2));
//        for (String windowHandle : driver.getWindowHandles()) {
//            if (!originalWindow.contentEquals(windowHandle)) {
//                driver.switchTo().window(windowHandle);
//                break;
//            }
//        }
//        String URL = driver.getCurrentUrl();
//        assert (URL).contains("https://telegram.me/");
//        driver.switchTo().window(originalWindow);
//        topUpPage.promocodePopUpClose();
//    }


    // Смена валюты
    @Test
    @Order(2)
    @DisplayName("2. Changing Currency To Rub")
    public void shouldChangeRubCurrency() {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.rubCurrencyChoice.click();
        String firstPackagePrice = topUpPage.firstPackagePrice.getText();
        assert (firstPackagePrice).contains("₽");
    }

    // Переключение между типами пакетов уроков: Обычный, Семейный, С носителем

    @Test
    @Order(3)
    @DisplayName("3. Changing Package To Rub Familial")
    public void shouldChangeToFamilyPackage() {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        String firstPackagePrice = topUpPage.numberLessonFirstPackage.getText();
        assert (firstPackagePrice).contains("4");
    }

    // Проверка наличия всплывающего окна с условиями приобретения пакетов уроков
    @Test
    @Order(4)
    @DisplayName("4. Checking Terms Of Lessons Use Pop Up")
    public void checkTermsOfUsePopUp() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        topUpPage.fourLessonsBtn.click();
        assert(topUpPage.termsOfUsePopUp).isDisplayed();
    }

    // Проверка того что кнопка перехода к оплате изначально неактивна
    @Test
    @Order(5)
    @DisplayName("5. Checking Terms Of Lessons Use Pop Up Button Inactive")
    public void checkTermsOfUsePopUpInactive() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(topUpPage.proceedToPayment));
        Assert.assertFalse((topUpPage.proceedToPayment).isEnabled());
    }

    // Проверка того что кнопка перехода активна после согласия с политикой
    @Test
    @Order(6)
    @DisplayName("6. Checking Terms Of Lessons Use Pop Up Button Active")
    public void checkTermsOfUsePopUpActive() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(topUpPage.proceedToPayment));
        topUpPage.termsOfUseCheckBox.click();
        Assert.assertTrue((topUpPage.proceedToPayment).isEnabled());
    }

    @Test
    @Order(7)
    @DisplayName("7. 4 Lessons Rub Familial Package Payment Page")
    public void shouldProceedToPayment4FamilialLessons() throws AWTException {
        topUpPage.proceedToPayment.click();
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
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
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
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
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
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
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
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
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
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(12)
    @DisplayName("12. 16 Lessons Native Package Payment Page")
    public void shouldProceedToPayment16NativelLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.nativeSpeakerPackages.click();
        topUpPage.sixteenLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(14)
    @DisplayName("14. 32 Lessons Native Package Payment Page")
    public void shouldProceedToPayment32NativelLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.nativeSpeakerPackages.click();
        topUpPage.thirtytwoLessonBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(15)
    @DisplayName("15. Changing Package To Ordinary Teacher")
    public void shouldChangeToOrdinaryPackage() {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        String firstPackagePrice = topUpPage.numberLessonFirstPackage.getText();
        assert (firstPackagePrice).contains("4");
    }

    @Test
    @Order(16)
    @DisplayName("16. 4 Lessons Ordinary Package Payment Page")
    public void shouldProceedToPayment4OrdinarylLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        topUpPage.fourLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(17)
    @DisplayName("17. 8 Lessons Ordinary Package Payment Page")
    public void shouldProceedToPayment8OrdinarylLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        topUpPage.eightLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(18)
    @DisplayName("18. 16 Lessons Ordinary Package Payment Page")
    public void shouldProceedToPayment16OrdinarylLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        topUpPage.sixteenLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(19)
    @DisplayName("19. 32 Lessons Ordinary Package Payment Page")
    public void shouldProceedToPayment32OrdinarylLessons() throws AWTException {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        topUpPage.thirtytwoLessonBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(20)
    @DisplayName("20. Changing Currency To Euro")
    public void shouldChangeEuroCurrency() {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.euroCurrencyChoice.click();
        String firstPackagePrice = topUpPage.firstPackagePrice.getText();
        assert (firstPackagePrice).contains("€");
    }

    @Test
    @Order(21)
    @DisplayName("21. Change Package To Familial In Euro")
    public void shouldChangeToEuroFamilyPack() {
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.euroCurrencyChoice.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        String firstPackagePrice = topUpPage.numberLessonFirstPackage.getText();
        assert (firstPackagePrice).contains("4");
    }

    @Test
    @Order(22)
    @DisplayName("22. 4 Lessons Euro Familial Package Payment Page")
    public void shouldProceedToEuroPayment4FamilialLessons() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        topUpPage.fourLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.paySelectionPopUp));
        String URL = driver.getCurrentUrl();
        assert (URL).contains("payselection.com");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(23)
    @DisplayName("23. 8 Lessons Euro Familial Package Payment Page")
    public void shouldProceedToEuroPayment8FamilialLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.eightLessonsBtn));
        topUpPage.eightLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.paySelectionPopUp));
        String URL = driver.getCurrentUrl();
        assert (URL).contains("payselection.com");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(24)
    @DisplayName("24. 16 Lessons Euro Familial Package Payment Page")
    public void shouldProceedToEuroPayment16FamilialLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.sixteenLessonsBtn));
        topUpPage.sixteenLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.paySelectionPopUp));
        String URL = driver.getCurrentUrl();
        assert (URL).contains("payselection.com");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(25)
    @DisplayName("25. 32 Lessons Euro Familial Package Payment Page")
    public void shouldProceedToEuroPayment32FamilialLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.thirtytwoLessonBtn));
        topUpPage.thirtytwoLessonBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.paySelectionPopUp));
        String URL = driver.getCurrentUrl();
        assert (URL).contains("payselection.com");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(26)
    @DisplayName("26. 4 Lessons Euro Native Package Payment Page")
    public void shouldProceedToEuroPayment4NativeLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.nativeSpeakerPackages));
        topUpPage.nativeSpeakerPackages.click();
        topUpPage.fourLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.paySelectionPopUp));
        String URL = driver.getCurrentUrl();
        assert (URL).contains("payselection.com");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(27)
    @DisplayName("27. 8 Lessons Euro Native Package Payment Page")
    public void shouldProceedToEuroPayment8NativeLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.nativeSpeakerPackages));
        topUpPage.nativeSpeakerPackages.click();
        topUpPage.eightLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUsePopUp));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("payselection.com");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(28)
    @DisplayName("28. 16 Lessons Euro Native Package Payment Page")
    public void shouldProceedToEuroPayment16NativeLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.nativeSpeakerPackages));
        topUpPage.nativeSpeakerPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.sixteenLessonsBtn));
        topUpPage.sixteenLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.paySelectionPopUp));
        String URL = driver.getCurrentUrl();
        assert (URL).contains("payselection.com");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(29)
    @DisplayName("29. 32 Lessons Euro Native Package Payment Page")
    public void shouldProceedToEuroPayment32NativeLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.nativeSpeakerPackages));
        topUpPage.nativeSpeakerPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.thirtytwoLessonBtn));
        topUpPage.thirtytwoLessonBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.paySelectionPopUp));
        String URL = driver.getCurrentUrl();
        assert (URL).contains("payselection.com");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(30)
    @DisplayName("30. 4 Lessons Euro Ordinary Package Payment Page")
    public void shouldProceedToEuroPayment4OrdinaryLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.fourLessonsBtn));
        topUpPage.fourLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.paySelectionPopUp));
        String URL = driver.getCurrentUrl();
        assert (URL).contains("payselection.com");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(31)
    @DisplayName("31. 8 Lessons Euro Ordinary Package Payment Page")
    public void shouldProceedToEuroPayment8OrdinaryLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.eightLessonsBtn));
        topUpPage.eightLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.paySelectionPopUp));
        String URL = driver.getCurrentUrl();
        assert (URL).contains("payselection.com");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(32)
    @DisplayName("32. 16 Lessons Euro Ordinary Package Payment Page")
    public void shouldProceedToEuroPayment16OrdinaryLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.sixteenLessonsBtn));
        topUpPage.sixteenLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.paySelectionPopUp));
        String URL = driver.getCurrentUrl();
        assert (URL).contains("payselection.com");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(33)
    @DisplayName("33. 32 Lessons Euro Ordinary Package Payment Page")
    public void shouldProceedToEuroPayment32OrdinaryLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.thirtytwoLessonBtn));
        topUpPage.thirtytwoLessonBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.paySelectionPopUp));
        String URL = driver.getCurrentUrl();
        assert (URL).contains("payselection.com");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(34)
    @DisplayName("34. Changing Currency To USD")
    public void shouldChangeTengeCurrency() {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.tengeCurrencyChoice.click();
        String firstPackagePrice = topUpPage.firstPackagePrice.getText();
        assert (firstPackagePrice).contains("8");
    }

    @Test
    @Order(35)
    @DisplayName("35. 4 Lessons Tenge Ordinary Package Payment Page")
    public void shouldProceedToTengePayment4OrdinaryLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.tengeCurrencyChoice.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.fourLessonsBtn));
        topUpPage.fourLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("onevisionpay");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(36)
    @DisplayName("36. 8 Lessons Tenge Ordinary Package Payment Page")
    public void shouldProceedToTengePayment8OrdinaryLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.tengeCurrencyChoice.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.eightLessonsBtn));
        topUpPage.eightLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("onevisionpay");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(37)
    @DisplayName("37. 16 Lessons Tenge Ordinary Package Payment Page")
    public void shouldProceedToTengePayment16OrdinaryLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.tengeCurrencyChoice.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.sixteenLessonsBtn));
        topUpPage.sixteenLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("onevisionpay");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(38)
    @DisplayName("38. 32 Lessons Tenge Ordinary Package Payment Page")
    public void shouldProceedToTengePayment32OrdinaryLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.tengeCurrencyChoice.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.ordinaryPackages));
        topUpPage.ordinaryPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.thirtytwoLessonBtn));
        topUpPage.thirtytwoLessonBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("onevisionpay");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(39)
    @DisplayName("39. 4 Lessons Tenge Family Package Payment Page")
    public void shouldProceedToTengePayment4FamilyLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.tengeCurrencyChoice.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.fourLessonsBtn));
        topUpPage.fourLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("onevisionpay");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(40)
    @DisplayName("40. 8 Lessons Tenge Family Package Payment Page")
    public void shouldProceedToTengePayment8FamilyLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.tengeCurrencyChoice.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.eightLessonsBtn));
        topUpPage.eightLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("onevisionpay");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(41)
    @DisplayName("41. 16 Lessons Tenge Family Package Payment Page")
    public void shouldProceedToTengePayment16FamilyLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.tengeCurrencyChoice.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.sixteenLessonsBtn));
        topUpPage.sixteenLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("onevisionpay");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(42)
    @DisplayName("42. 32 Lessons Tenge Family Package Payment Page")
    public void shouldProceedToTengePayment32FamilyLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.tengeCurrencyChoice.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.familyPackages));
        topUpPage.familyPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.thirtytwoLessonBtn));
        topUpPage.thirtytwoLessonBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("onevisionpay");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(43)
    @DisplayName("43. 4 Lessons Tenge Native Package Payment Page")
    public void shouldProceedToTengePayment4NativeLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.tengeCurrencyChoice.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.nativeSpeakerPackages));
        topUpPage.nativeSpeakerPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.fourLessonsBtn));
        topUpPage.fourLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("onevisionpay");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(44)
    @DisplayName("44. 8 Lessons Tenge Native Package Payment Page")
    public void shouldProceedToTengePayment8NativeLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.tengeCurrencyChoice.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.nativeSpeakerPackages));
        topUpPage.nativeSpeakerPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.eightLessonsBtn));
        topUpPage.eightLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("onevisionpay");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(45)
    @DisplayName("45. 16 Lessons Tenge Native Package Payment Page")
    public void shouldProceedToTengePayment16NativeLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.tengeCurrencyChoice.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.nativeSpeakerPackages));
        topUpPage.nativeSpeakerPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.sixteenLessonsBtn));
        topUpPage.sixteenLessonsBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUsePopUp));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("onevisionpay");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    @Order(46)
    @DisplayName("46. 32 Lessons Tenge Native Package Payment Page")
    public void shouldProceedToTengePayment32NativeLessons() throws AWTException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.currencyChoice));
        topUpPage.currencyChoice.click();
        topUpPage.tengeCurrencyChoice.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.nativeSpeakerPackages));
        topUpPage.nativeSpeakerPackages.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.thirtytwoLessonBtn));
        topUpPage.thirtytwoLessonBtn.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.termsOfUseCheckBox));
        topUpPage.termsOfUseCheckBox.click();
        topUpPage.proceedToPayment.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("onevisionpay");
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    // Проверка обработки ввода валидного промокода
    @Test
    @Order(47)
    @DisplayName("47. Checking Valid Promocode")
    public void shouldCheckValidPromocode() throws AWTException, InterruptedException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.promocodeField));
        topUpPage.sendValidPromocode(ConfProperties.getProperty("validPromoCode"));
        wait.until(ExpectedConditions.invisibilityOf(topUpPage.checkingPromocodeNotification));
        wait.until(ExpectedConditions.visibilityOf(topUpPage.promocodeNotification));
        String Notification = topUpPage.promocodeNotification.getText();
        assertThat(Notification).contains("активирован");
    }

    // Проверка обработки ввода невалидного промокода
    @Test
    @Order(48)
    @DisplayName("48. Checking Invalid Promocode")
    public void shouldCheckInvalidPromocode() throws AWTException, InterruptedException {
        driver.get(ConfProperties.getProperty("loginpage"));
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.topUpSectionButton));
        studentsAccountPage.topUpSectionButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.promocodeField));
        topUpPage.sendInvalidPromocode(ConfProperties.getProperty("invalidPromocode"));
        wait.until(ExpectedConditions.invisibilityOf(topUpPage.checkingPromocodeNotification));
        wait.until(ExpectedConditions.visibilityOf(topUpPage.promocodeNotification));
        String Notification = topUpPage.promocodeNotification.getText();
        assertThat(Notification).contains("Неверный промокод");
    }

}
