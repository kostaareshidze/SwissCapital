package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ge.tbcitacademy.data.Constants;
import ge.tbcitacademy.data.DataProviderClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AlternativeParametrizations {
    @BeforeClass

    public void setUp(){
        WebDriverManager.chromedriver().setup();
        open(Constants.demoqaURL);
    }
    @Parameters({ "firstName", "lastName", "gender", "mobileNumber" })
    @Test
    public void fillWithParameters(String firstName, String lastName, String gender, String mobileNumber){

        $("#firstName").sendKeys(firstName);
        $("#lastName").scrollTo().sendKeys(lastName);
        Selenide.$(byText(gender)).shouldBe(visible).click();
        $("#userNumber").sendKeys(mobileNumber);
        $("#submit").scrollTo().click();
        SelenideElement toCheck = $(byText(Constants.student)).sibling(0);
        String newString = firstName + " " + lastName;
        toCheck.shouldHave(text(newString));

    }
    @Test(dataProvider = "formDataProvider", dataProviderClass = DataProviderClass.class)
    public void fillWithDataProvider(String firstName, String lastName, String gender, String mobileNumber) {
        $("#firstName").sendKeys(firstName);
        $("#lastName").scrollTo().sendKeys(lastName);
        Selenide.$(byText(gender)).shouldBe(visible).click();
        $("#userNumber").sendKeys(mobileNumber);
        $("#submit").scrollTo().click();
        SelenideElement toCheck = $(byText(Constants.student)).sibling(0);
        String newString = firstName + " " + lastName;
        toCheck.shouldHave(text(newString));
        refresh();
    }
}
