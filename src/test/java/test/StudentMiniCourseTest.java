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
public class StudentMiniCourseTest {
    public static LoginPage loginPage;
    public static StudentsAccountPage studentsAccountPage;
    public static MiniCoursesPage miniCoursesPage;
    public static TopUpPage topUpPage;
    public static WebDriver driver;
    public static WebDriverWait wait;


    @BeforeAll
    public static void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        loginPage = new LoginPage(driver);
        studentsAccountPage = new StudentsAccountPage(driver);
        miniCoursesPage = new MiniCoursesPage(driver);
        topUpPage = new TopUpPage(driver);
        driver.get(ConfProperties.getProperty("loginpage"));

    }

    @Test
    @Order(1)
    @DisplayName("1.Check A Minicourse Is On Page")
    public void checkMinicoursePresent() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
//        wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
//        loginPage.friendPromoBanner.click();
        // ожидание появления элемента — расписание
        wait.until(ExpectedConditions.visibilityOf(studentsAccountPage.studentSchedule));
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.minicoursesSection));
        miniCoursesPage.minicoursesSectionClick();
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.testPracticamosCourse));
        miniCoursesPage.testPracticamosCourse.click();
        String minicourseURl = driver.getCurrentUrl();
        assert(minicourseURl).contains("Tmgt0u7FIeBhalOEpIvU");
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.allCourseButton));
        miniCoursesPage.allCourseButton.click();
    }

    @Test
    @Order(2)
    @DisplayName("2.Check A Paid MiniCourse Opens")
    public void checkPaidMiniCourseOpens() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.paidPracticamosCourse));
        miniCoursesPage.paidPracticamosCourse.click();
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.buyPracticamosButton));
        String buyButton = miniCoursesPage.buyPracticamosButton.getText();
        assertThat(buyButton).contains("Купить");
    }

    @Test
    @Order(3)
    @DisplayName("3.Check Rub Currency")
    public void shouldSelectRubCurrency() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.currencySelectButton));
        miniCoursesPage.currencySelectButton.click();
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.rubCurrencySelect));
        miniCoursesPage.rubCurrencySelect.click();
        String buyButton = miniCoursesPage.buyPracticamosButton.getText();
        assertThat(buyButton).contains("руб");
    }

    @Test
    @Order(4)
    @DisplayName("4.Check Euro Currency")
    public void shouldSelectEuroCurrency() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.currencySelectButton));
        miniCoursesPage.currencySelectButton.click();
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.euroCurrencySelect));
        miniCoursesPage.euroCurrencySelect.click();
        String buyButton = miniCoursesPage.buyPracticamosButton.getText();
        assertThat(buyButton).contains("евро");
    }

    @Test
    @Order(5)
    @DisplayName("5.Check Tenge Currency")
    public void shouldSelectTengeCurrency() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.currencySelectButton));
        miniCoursesPage.currencySelectButton.click();
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.tengeCurrencySelect));
        miniCoursesPage.tengeCurrencySelect.click();
        String buyButton = miniCoursesPage.buyPracticamosButton.getText();
        assertThat(buyButton).contains("тенге");
    }

    @Test
    @Order(6)
    @DisplayName("6.Should Proceed To Payment In Euro")
    public void shouldProceedToEuroPayment() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.currencySelectButton));
        miniCoursesPage.currencySelectButton.click();
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.euroCurrencySelect));
        miniCoursesPage.euroCurrencySelect.click();
        miniCoursesPage.buyPracticamosButton.click();
        wait.until(ExpectedConditions.visibilityOf(topUpPage.paySelectionPopUp));
        String URL = driver.getCurrentUrl();
        assert (URL).contains("payselection.com");
        driver.get("https://escuela-stage.web.app/student/minicourses/5d4ElPi0pTQ6RPU8Wamm/");
    }

    @Test
    @Order(7)
    @DisplayName("7.Should Proceed To Payment In Rub")
    public void shouldProceedToRubPayment() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.currencySelectButton));
        miniCoursesPage.currencySelectButton.click();
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.rubCurrencySelect));
        miniCoursesPage.rubCurrencySelect.click();
        miniCoursesPage.buyPracticamosButton.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("https://yoomoney.ru/");
        driver.get("https://escuela-stage.web.app/student/minicourses/5d4ElPi0pTQ6RPU8Wamm/");
    }

    @Test
    @Order(8)
    @DisplayName("8.Should Proceed To Payment In Tenge")
    public void shouldProceedToTengePayment() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.currencySelectButton));
        miniCoursesPage.currencySelectButton.click();
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.tengeCurrencySelect));
        miniCoursesPage.tengeCurrencySelect.click();
        miniCoursesPage.buyPracticamosButton.click();
        String URL = driver.getCurrentUrl();
        assert (URL).contains("onevisionpay");
        driver.get("https://escuela-stage.web.app/student/minicourses");
    }

    @Test
    @Order(9)
    @DisplayName("9.Should Check Valid Promocode For Practicamos")
    public void shouldEnterValidPromocode() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.practicamosPromocodeField));
        miniCoursesPage.sendInvalidPracticamosPromocode(ConfProperties.getProperty("validPracticamosPromocode"));
        wait.until(ExpectedConditions.invisibilityOf(miniCoursesPage.checkingPromocodeNotification));
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.promocodeNotification));
        String Notification = miniCoursesPage.promocodeNotification.getText();
        assertThat(Notification).contains("подтвержден");
    }

    @Test
    @Order(10)
    @DisplayName("10.Should Check Ivalid Promocode For Practicamos")
    public void shouldEnterInvalidPromocode() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.practicamosPromocodeField));
        miniCoursesPage.practicamosPromocodeField.clear();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.practicamosPromocodeField));
        miniCoursesPage.sendInvalidPracticamosPromocode(ConfProperties.getProperty("invalidPracticamosPromocode"));
        wait.until(ExpectedConditions.invisibilityOf(miniCoursesPage.checkingPromocodeNotification));
        wait.until(ExpectedConditions.visibilityOf(miniCoursesPage.promocodeNotification));
        String Notification = miniCoursesPage.promocodeNotification.getText();
        assertThat(Notification).contains("Неверный промокод");
    }

}
