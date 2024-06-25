package tests;

import com.codeborne.selenide.*;
import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;


public class DependsOnTest {
    Pattern pattern;
    Matcher matcher;
    SoftAssert sfa;
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        sfa = new SoftAssert();
        open(Constants.swoopURl);
    }
    @Test
    public void searchTest(){

        SelenideElement search = $(".reheadersearch");
        search.sendKeys(Constants.burger);
        search.pressEnter();
        ElementsCollection offerDescriptions = $$x("//div[@class='special-offer']//p//a");
        ElementsCollection offerTitles = $$x("//div[@class='special-offer']//a//p");

        boolean foundText = false;
        for (SelenideElement offerDesc : offerDescriptions) {
            pattern = Pattern.compile(Constants.burgerPattern, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(offerDesc.text());
            foundText = matcher.find();
            if (!foundText) {
                for (SelenideElement offerTitle : offerTitles) {
                    pattern = Pattern.compile(Constants.burgerPattern, Pattern.CASE_INSENSITIVE);
                    matcher = pattern.matcher(offerDesc.text());
                    if (matcher.find()) {
                        foundText = true;
                        break;
                    }
                }
            }
            if (foundText) {
                break;
            }
        }

        sfa.assertTrue(foundText);

    }
    @Test(dependsOnMethods = "searchTest")
    public void validateIndividualOfferNameFromSearch(){
        SelenideElement search = $(".reheadersearch");
        search.sendKeys(Constants.burger);
        search.pressEnter();
        SelenideElement offer = $x
                ("//div[@class='special-offer-img-container']//a[@href='/offers/427152/Texas-BBQ-Burger-kombo-meniu/restornebi-da-barebi/kafe-bari']");
        String title = $x("//div[@class='special-offer'][1]//a//p").text();
        offer.click();
        SelenideElement titleToCheck = $(".merchantTitle");
        boolean isEqual = titleToCheck.text().equalsIgnoreCase(title);
        System.out.println(titleToCheck.text() + "\n"+ title);
        sfa.assertTrue(isEqual);
        sfa.assertAll();

    }
}
