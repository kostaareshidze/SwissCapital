package tbcitacademy.data;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.DataProvider;

import static com.codeborne.selenide.Selenide.*;

public class DataProviderClass {
    @DataProvider
    public static Object[][] getData() {
        return new Object[][]{
                {"//div[@class='special-offer']",
                        "//div[@class='discounted-prices']//p[@class='deal-voucher-price' and not(@style)]",
                        "//div[@class='discounted-prices']//p[@class='deal-voucher-price' and (@style)]"}
        };
    }

    @DataProvider
    public static Object[][] addToCart() {
        return new Object[][] {
                {".special-offer"}
        };
    }

    @DataProvider
    public static Object[][] formDataProvider() {
        return new Object[][] {
                { "kosta", "areshidze", "Male", "5998877550" },
                { "tekla", "areshidze", "Female", "5876554463" },
                { "nikia", "areshidze", "Other", "5555785510" }
        };
    }
}
