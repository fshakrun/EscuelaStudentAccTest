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
import pages.DictionaryPage;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentDictionaryPageTest {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static DictionaryPage dictionaryPage;
    public static WebDriverWait wait;

    // "--headless",
    @BeforeAll
    public static void setup() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--window-size=1920,1080");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        loginPage = new LoginPage(driver);
        dictionaryPage = new DictionaryPage(driver);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    // Проверка открытия вкладки словаря со словами за сегодняшний день
    @Test
    @Order(1)
    @DisplayName("1. Checking Todays Words Tab Opens")
    public void shouldCheckTodaysWordsTab() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(loginPage.emailField));
        loginPage.emailEnter(ConfProperties.getProperty("email"));
        loginPage.passwordEnter(ConfProperties.getProperty("password"));
        loginPage.enterClick();
        wait.until(ExpectedConditions.visibilityOf(loginPage.friendPromoBanner));
        loginPage.friendPromoBanner.click();
        wait.until(ExpectedConditions.visibilityOf(dictionaryPage.dictSection));
        dictionaryPage.dictSectionClick();
        wait.until(ExpectedConditions.visibilityOf(dictionaryPage.todaysWords));
        dictionaryPage.todaysWords.click();
        assert(dictionaryPage.pictureWhenEmpty).isDisplayed();
    }

    // Проверка открытия вкладки словаря со словами за неделю
    @Test
    @Order(2)
    @DisplayName("2. Checking Words Of The Week Tab Opens")
    public void shouldCheckWeeksWords() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(dictionaryPage.dictSection));
        dictionaryPage.dictSectionClick();
        wait.until(ExpectedConditions.visibilityOf(dictionaryPage.wordsForWeek));
        dictionaryPage.wordsForWeek.click();
        assert(dictionaryPage.pictureWhenEmpty).isDisplayed();
    }

    // Проверка открытия вкладки словаря со словами за месяц
    @Test
    @Order(3)
    @DisplayName("3. Checking Words Of The Months Tab Opens")
    public void shouldCheckMonthsWords() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(dictionaryPage.dictSection));
        dictionaryPage.dictSectionClick();
        wait.until(ExpectedConditions.visibilityOf(dictionaryPage.wordsForMonth));
        dictionaryPage.wordsForMonth.click();
        assert(dictionaryPage.pictureWhenEmpty).isDisplayed();
    }

    // Проверка открытия вкладки словаря со словами за 3 месяц
    @Test
    @Order(4)
    @DisplayName("4. Checking Words Of 3 Months Tab Opens")
    public void shouldCheck3MonthsWords() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(dictionaryPage.dictSection));
        dictionaryPage.dictSectionClick();
        wait.until(ExpectedConditions.visibilityOf(dictionaryPage.wordsFor3Months));
        dictionaryPage.wordsFor3Months.click();
        assert(dictionaryPage.pictureWhenEmpty).isDisplayed();
    }

    // Проверка открытия вкладки словаря со всеми словами
    @Test
    @Order(5)
    @DisplayName("5. Checking All Words Tab Opens")
    public void shouldCheckAllWords() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(dictionaryPage.dictSection));
        dictionaryPage.dictSectionClick();
        wait.until(ExpectedConditions.visibilityOf(dictionaryPage.allWords));
        dictionaryPage.allWords.click();
        assert(dictionaryPage.firstWordOfAllWords).isDisplayed();
    }

}
