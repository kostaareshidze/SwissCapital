package swissCapital;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import swissCapital.sauceDemo.pages.LogInPage;
import swissCapital.sauceDemo.steps.LogInSteps;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.currentFrameUrl;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static swissCapital.sauceDemo.data.Constants.*;

@Epic("SauceDemo")
public class LogInTests {
    LogInSteps logInSteps;
    LogInPage logInPage;
    Faker faker;

    @BeforeMethod
    public void setUp() {
        logInSteps = new LogInSteps();
        logInPage = new LogInPage();
        faker = new Faker();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        Configuration.browserCapabilities = options;
        open(SAUCEDEMO_URL);
        WebDriverManager.chromedriver().setup();
        getWebDriver().manage().window().maximize();
    }

    @Feature("Login")
    @Test(description = "Login with valid details and check page")
    public void correctUsername()  {
        logInSteps
                .fillsUserName(logInPage.usernameField, logInPage.loginUsername, USERNAME_NUMBER)
                .fillsPassword(logInPage.passwordField, logInPage.loginPassword, USERNAME_NUMBER)
                .logIn(logInPage.logIn);
        Assert.assertEquals(currentFrameUrl(), INVENTORY_URL);
    }

    @Feature("Login")
    @Test(description = "Login with incorrect details and check error")
    public void incorrectUsername() {
        logInSteps
                .fillsUserName(logInPage.usernameField, faker.toString())
                .fillsUserPassword(logInPage.passwordField, faker.toString())
                .logIn(logInPage.logIn);
        Assert.assertEquals(logInPage.error.$(By.tagName("h3")).text(), ERROR_TEXT);
    }

}
