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

import java.time.Duration;

import static org.testng.Assert.assertEquals;

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
        options.addArguments("--window-size=1920,1080");
        WebDriverManager.chromedriver().clearDriverCache().setup();
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

    // Редактирование имени ученика и сохранение изменений
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

    @Test
    @DisplayName("7. Enter Romain Letters First Name")
    public void enterRomainLettersFirstName() throws InterruptedException {
        studentsProfilePage.clearFirstName();
        studentsProfilePage.studentFirstNameField.click();
        studentsProfilePage.studentFirstNameField.sendKeys("Anna");
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.saveButton));
        studentsProfilePage.saveButtonProfile();
        String lastName = studentsProfilePage.studentFirstNameField.getAttribute("value");
        assertEquals(lastName, "Anna");

    }

    // Редактирование фамилии ученика и сохранение изменений
    @Test
    @DisplayName("8. Enter Cyrillic Last Name")
    public void enterCyrillicLastName() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentLastNameField));
        studentsProfilePage.clearLastName();
        studentsProfilePage.studentLastNameField.click();
        studentsProfilePage.studentLastNameField.sendKeys("Ирина");
        studentsProfilePage.saveButtonProfile();
        String lastName = studentsProfilePage.studentLastNameField.getAttribute("value");
        assertEquals(lastName, "Ирина");
    }

    @Test
    @DisplayName("9. Enter Long Cyrillic Last Name")
    public void enterLongLastName() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentLastNameField));
        studentsProfilePage.clearLastName();
        studentsProfilePage.studentLastNameField.click();
        studentsProfilePage.studentLastNameField.sendKeys("ИринаПопоноваИвановаСебастьяновнаПерепелкинаКовшикова");
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.saveButton));
        studentsProfilePage.saveButtonProfile();
        assert (studentsProfilePage.lastNameNotification.isDisplayed());
    }

    @Test
    @DisplayName("10. Enter Short Cyrillic Last Name")
    public void enterShortLastName() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentLastNameField));
        studentsProfilePage.clearLastName();
        studentsProfilePage.studentLastNameField.click();
        studentsProfilePage.studentLastNameField.sendKeys("И");
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.saveButton));
        studentsProfilePage.saveButtonProfile();
        assert (studentsProfilePage.lastNameNotification.isDisplayed());
    }

    @Test
    @DisplayName("11. Enter Cyrillic Last Name With Hyphen")
    public void enterHyphenCyrillicLastName() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentLastNameField));
        studentsProfilePage.clearLastName();
        studentsProfilePage.studentLastNameField.click();
        studentsProfilePage.studentLastNameField.sendKeys("Анна-Мария");
        String lastName = studentsProfilePage.studentLastNameField.getAttribute("value");
        assertEquals(lastName, "Анна-Мария");
    }

    @Test
    @DisplayName("12. Enter Space In Last Name")
    public void enterSpaceLastName() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentLastNameField));
        studentsProfilePage.clearLastName();
        studentsProfilePage.studentLastNameField.click();
        studentsProfilePage.studentLastNameField.sendKeys(" ");
        studentsProfilePage.saveButtonProfile();
        assert (studentsProfilePage.lastNameNotification.isDisplayed());

    }

    @Test
    @DisplayName("13. Enter Romain Letters Last Name")
    public void enterRomainLettersLastName() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentLastNameField));
        studentsProfilePage.clearLastName();
        studentsProfilePage.studentLastNameField.click();
        studentsProfilePage.studentLastNameField.sendKeys("Anna");
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.saveButton));
        String lastName = studentsProfilePage.studentLastNameField.getAttribute("value");
        assertEquals(lastName, "Anna");
    }

    // Редактирование даты рождения ученика и сохранение изменений
    @Test
    @DisplayName("14. Enter Correct Birth Date")
    public void enterCorrectBirthDate() throws InterruptedException {
        studentsProfilePage.clearBirthDate();
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentBirthDateField));
        studentsProfilePage.studentBirthDateField.click();
        studentsProfilePage.studentBirthDateField.sendKeys("11111989");
        String birthDate = studentsProfilePage.studentBirthDateField.getAttribute("value");
        assertEquals(birthDate, "11.11.1989");
    }

//    @Test
//    @DisplayName("14. Enter Future Date In Birth Date")
//    public void enterLongBirthDate() throws InterruptedException {
//        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentBirthDateField));
//        studentsProfilePage.studentBirthDateField.click();
//        studentsProfilePage.studentBirthDateField.sendKeys("11112025");
//        String birthDate = studentsProfilePage.studentBirthDateField.getAttribute("value");
//        assertEquals(birthDate, "11.11.1989");
//    }

    @Test
    @DisplayName("15. Enter Long Number In Birth Date")
    public void enterLongBirthDate() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentBirthDateField));
        studentsProfilePage.clearBirthDate();
        studentsProfilePage.studentBirthDateField.click();
        studentsProfilePage.studentBirthDateField.sendKeys("111120025");
        String birthDate = studentsProfilePage.studentBirthDateField.getAttribute("value");
        assertEquals(birthDate, "11.11.2002");
    }

    @Test
    @DisplayName("16. Enter Very Long Number In Birth Date")
    public void enterVeryLongBirthDate() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentBirthDateField));
        studentsProfilePage.clearBirthDate();
        studentsProfilePage.studentBirthDateField.click();
        studentsProfilePage.studentBirthDateField.sendKeys("11112002123456789101112");
        String birthDate = studentsProfilePage.studentBirthDateField.getAttribute("value");
        assertEquals(birthDate, "11.11.2002");
    }

    @Test
    @DisplayName("17. Enter Cyrillic In Birth Date")
    public void enterCyrillicBirthDate() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentBirthDateField));
        studentsProfilePage.clearBirthDate();
        studentsProfilePage.studentBirthDateField.click();
        studentsProfilePage.studentBirthDateField.sendKeys("абвгд");
        String birthDate = studentsProfilePage.studentBirthDateField.getAttribute("value");
        assertEquals(birthDate, "");
    }

    @Test
    @DisplayName("18. Enter Romain Letters In Birth Date")
    public void enterRomainLetterBirthDate() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentBirthDateField));
        studentsProfilePage.clearBirthDate();
        studentsProfilePage.studentBirthDateField.click();
        studentsProfilePage.studentBirthDateField.sendKeys("qwerty");
        String birthDate = studentsProfilePage.studentBirthDateField.getAttribute("value");
        assertEquals(birthDate, "");
    }

    @Test
    @DisplayName("19. Enter Special Symbols In Birth Date")
    public void enterSpecialSymbolsBirthDate() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentBirthDateField));
        studentsProfilePage.clearBirthDate();
        studentsProfilePage.studentBirthDateField.click();
        studentsProfilePage.studentBirthDateField.sendKeys("№;%:?");
        String birthDate = studentsProfilePage.studentBirthDateField.getAttribute("value");
        assertEquals(birthDate, "");
    }

    @Test
    @DisplayName("20. Enter Symbols In Birth Date")
    public void enterSymbolsBirthDate() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentBirthDateField));
        studentsProfilePage.clearBirthDate();
        studentsProfilePage.studentBirthDateField.click();
        studentsProfilePage.studentBirthDateField.sendKeys("-+=><");
        String birthDate = studentsProfilePage.studentBirthDateField.getAttribute("value");
        assertEquals(birthDate, "");
    }

    // Редактирование поля Скайп № 21 - 27 -- большинство тест-кейсов фейлятся. Создал задачи на фикс
    @Test
    @DisplayName("21. Enter Correct Skype Name")
    public void enterCorrectSkypeName() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentSkypeField));
        studentsProfilePage.clearSkypeField();
        studentsProfilePage.studentSkypeField.click();
        studentsProfilePage.studentSkypeField.sendKeys("super");
        studentsProfilePage.saveButtonProfile();
        String skypeName = studentsProfilePage.studentSkypeField.getAttribute("value");
        assertEquals(skypeName, "super");
    }
    @Test
    @DisplayName("28. Enter Correct Phone Number")
    public void enterCorrectPhoneNumber() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentPhoneField));
        studentsProfilePage.clearPhoneField();
        studentsProfilePage.studentPhoneField.click();
        studentsProfilePage.studentPhoneField.sendKeys("79999999999");
        studentsProfilePage.saveButtonProfile();
        String phoneNumber = studentsProfilePage.studentPhoneField.getAttribute("value");
        assertEquals(phoneNumber, "+7 (999) 999-99-99");
    }

    @Test
    @DisplayName("29. Enter Short Phone Number")
    public void enterShortPhoneNumber() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentPhoneField));
        studentsProfilePage.clearPhoneField();
        studentsProfilePage.studentPhoneField.click();
        studentsProfilePage.studentPhoneField.sendKeys("799");
        String phoneNumber = studentsProfilePage.studentPhoneField.getAttribute("value");
        assertEquals(phoneNumber, "+7 (99)");
    }
    @Test
    @DisplayName("30. Enter Cyrillic In Phone Number Field")
    public void enterCyrillicPhoneNumber() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentPhoneField));
        studentsProfilePage.clearPhoneField();
        studentsProfilePage.studentPhoneField.click();
        studentsProfilePage.studentPhoneField.sendKeys("абвг");
        String phoneNumber = studentsProfilePage.studentPhoneField.getAttribute("value");
        assertEquals(phoneNumber, "+");
    }

    @Test
    @DisplayName("31. Enter Romain Letters In Phone Number Field")
    public void enterRomainPhoneNumber() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentPhoneField));
        studentsProfilePage.clearPhoneField();
        studentsProfilePage.studentPhoneField.click();
        studentsProfilePage.studentPhoneField.sendKeys("qwerty");
        String phoneNumber = studentsProfilePage.studentPhoneField.getAttribute("value");
        assertEquals(phoneNumber, "+");
    }

    @Test
    @DisplayName("32. Enter Special Symbols In Phone Number Field")
    public void enterSpecialSymbolsPhoneNumber() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentPhoneField));
        studentsProfilePage.clearPhoneField();
        studentsProfilePage.studentPhoneField.click();
        studentsProfilePage.studentPhoneField.sendKeys("№;%:?*");
        String phoneNumber = studentsProfilePage.studentPhoneField.getAttribute("value");
        assertEquals(phoneNumber, "+");
    }

    @Test
    @DisplayName("33. Enter Space In Phone Number Field")
    public void enterSpacePhoneNumber() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentPhoneField));
        studentsProfilePage.clearPhoneField();
        studentsProfilePage.studentPhoneField.click();
        studentsProfilePage.studentPhoneField.sendKeys(" ");
        String phoneNumber = studentsProfilePage.studentPhoneField.getAttribute("value");
        assertEquals(phoneNumber, "+");
    }
    @Test
    @DisplayName("34. Changing Country In Phone Number Field")
    public void changingCountryPhoneNumber() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(studentsProfilePage.studentPhoneField));
        studentsProfilePage.clearPhoneField();
        studentsProfilePage.studentPhoneField.click();
        studentsProfilePage.studentPhoneField.sendKeys("22222");
        String phoneNumber = studentsProfilePage.studentPhoneField.getAttribute("value");
        assertEquals(phoneNumber, "+222 22");
    }
}

