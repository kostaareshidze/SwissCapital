package swissCapital;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import swissCapital.sauceDemo.pages.LogInPage;
import swissCapital.sauceDemo.pages.PaymentPage;
import swissCapital.sauceDemo.pages.SwagLabsPage;
import swissCapital.sauceDemo.steps.LogInSteps;
import swissCapital.sauceDemo.steps.PaymentSteps;
import swissCapital.sauceDemo.steps.SwagLabsSteps;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.currentFrameUrl;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static swissCapital.sauceDemo.data.Constants.*;

@Epic("Inventory")
public class SwagLabTests {

    LogInSteps logInSteps;
    LogInPage logInPage;
    SwagLabsPage swagLabsPage;
    SwagLabsSteps swagLabsSteps;
    PaymentPage paymentPage;
    PaymentSteps paymentSteps;


    @BeforeMethod
    public void setUp() {
        logInSteps = new LogInSteps();
        logInPage = new LogInPage();
        swagLabsPage = new SwagLabsPage();
        swagLabsSteps = new SwagLabsSteps();
        paymentPage = new PaymentPage();
        paymentSteps = new PaymentSteps();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        Configuration.browserCapabilities = options;
        open(SAUCEDEMO_URL);
        WebDriverManager.chromedriver().setup();
        getWebDriver().manage().window().maximize();
        logInSteps
                .fillsUserName(logInPage.usernameField, logInPage.loginUsername, USERNAME_NUMBER)
                .fillsPassword(logInPage.passwordField, logInPage.loginPassword, USERNAME_NUMBER)
                .logIn(logInPage.logIn);
    }

    @Feature("Add To Cart")
    @Test(description = "Adding items to cart and checking if added")
    public void addToCart() {
        swagLabsSteps
                .addItemsToCart(swagLabsPage.items)
                .goToShoppingCart(swagLabsPage.cart);
        Assert.assertEquals(swagLabsSteps
                .checksIfItemsAreSameInCart(swagLabsPage.items, swagLabsPage.items), PASSWORD_NUMBER);
        //If items in inventory page are not like items in cart checksIfItemsAreSameInCart returns nonzero number,
        // so it will fail
    }

    @Feature("Add To Cart")
    @Test(description = "Adding elements in cart then removing and completing payment")
    public void threeComponentTest() {
        swagLabsSteps
                .addToCart(swagLabsPage.backpack)
                .addToCart(swagLabsPage.jacket)
                .addToCart(swagLabsPage.shirt);
        Assert.assertEquals(swagLabsPage.cart.find(By.tagName("span")).text(), THREE);
        //checks if cart has number 3 on it after adding 3 items

        swagLabsSteps
                .goToShoppingCart(swagLabsPage.cart)
                .removeElement(swagLabsPage.prices);
        Assert.assertEquals(swagLabsPage.cart.find(By.tagName("span")).text(), TWO);
        //If we remove expensive item on cart will appear 2 instead of 3

        swagLabsSteps.goToCheckout(swagLabsPage.checkout);
        paymentSteps
                .fillFirstName(paymentPage.firstName, NAME_KOSTA)
                .fillLastName(paymentPage.lastName, SURNAME_ARESHIDZE)
                .fillPostalCode(paymentPage.postalCode, ZIP_CODE)
                .pressContinue(paymentPage.continueButton)
                .pressFinish(paymentPage.finishButton);
        Assert.assertEquals(currentFrameUrl(), COMPLETE_PAGE);
        Assert.assertEquals(paymentPage.completeText.text(), THANKING_TEXT);
        //Both of this asserts checks ending page
    }
}
