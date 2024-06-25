package tests;

import com.codeborne.selenide.*;
import ge.tbcitacademy.data.Constants;
import ge.tbcitacademy.data.DataProviderClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedSwoopTests {
    SoftAssert sfa;
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        open(Constants.swoopURl);
        sfa = new SoftAssert();
    }

    @Test(dataProvider = "getData", dataProviderClass = DataProviderClass.class)
    public void checkSaleValuesTest(String offer, String discountedPrice, String fullPrice) {
        SelenideElement sport = $(".Menus").$x(".//a[@href='/category/110/sporti']");
        sport.click();
        ElementsCollection titleOfOffers = $$x(offer);
        ElementsCollection discounted = $$x(discountedPrice);
        ElementsCollection total = $$x(fullPrice);
        for (int i = 0; i < 10; i++) {
            titleOfOffers.get(i).$x("." + discountedPrice).shouldHave(text(discounted.get(i).text()));
            titleOfOffers.get(i).$x("." + fullPrice).shouldHave(text(total.get(i).text()));
        }
    }

    @Test(dataProvider = "addToCart", dataProviderClass = DataProviderClass.class)
    public void validateCartBehavior(String offerSelector) {
        List<String> title = new ArrayList<>();
        SelenideElement sport = $(".Menus").$x(".//a[@href='/category/110/sporti']");
        sport.click();
        ElementsCollection offers = $$(offerSelector);
        int size = 10;
        for (int i = 0; i < size; i++) {
            offers.get(i).scrollTo().click();
            SelenideElement basket = $(".addBasket");
            if (basket.is(visible)) {
                title.add($(".merchantTitle").text());
                basket.click();
            }else {
                size++;
            }
            sport.scrollTo().click();
        }
        SelenideElement basket = $x("//img[@src='/Images/NewDesigneImg/ReHeader/basket.svg']");
        basket.scrollTo().click();
        ElementsCollection titles = $$x("//p[@class='item-title desktop-version']/a");

        boolean allTitlesMatch = true;
        for (int i = 0; i < titles.size(); i++) {
            String expectedTitle = title.get(i);
            String actualTitle = titles.get(i).getText();
            if (!expectedTitle.equals(actualTitle)) {
                allTitlesMatch = false;
                break;
            }
        }
        sfa.assertTrue(allTitlesMatch);
        sfa.assertAll();
    }
}
