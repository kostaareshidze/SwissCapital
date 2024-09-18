package swissCapital.sauceDemo.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class SwagLabsSteps {
    @Step("Add items to cart")
    public SwagLabsSteps addItemsToCart(ElementsCollection elements) {
        for (SelenideElement element : elements) {
            ElementsCollection buttons = element.$$(By.tagName("button"));
            for (SelenideElement item : buttons) {
                item.scrollTo().click();
            }
        }
        return this;
    }

    @Step("Go to shopping cart")
    public SwagLabsSteps goToShoppingCart(SelenideElement element) {
        element.scrollTo().click();
        return this;
    }

    @Step("Check if items are the same in the cart")
    public Integer checksIfItemsAreSameInCart(ElementsCollection elements, ElementsCollection elements1) {
        int counter = 0;
        for (int i = 0; i < elements.size(); i++) {
            if (!elements.get(i).equals(elements1.get(i))) {
                counter++;
            }
        }
        return counter;
    }

    @Step("Add single item to cart")
    public SwagLabsSteps addToCart(SelenideElement element) {
        element.find(By.tagName("button")).scrollTo().click();
        return this;
    }

    @Step("Convert elements to prices")
    private List<Double> convertsToPrices(ElementsCollection elements) {
        List<Double> listOfPrices = new ArrayList<>();
        for (SelenideElement element : elements) {
            listOfPrices.add(Double.valueOf(element.text().replace("$", "")));
        }
        return listOfPrices;
    }

    @Step("Get maximum price from list")
    private String getMaxPrice(List<Double> lst) {
        double max = lst.get(0);
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i) > max) {
                max = lst.get(i);
            }
        }
        return Double.toString(max);
    }

    @Step("Find item with text {string}")
    private SelenideElement getItem(String string) {
        SelenideElement element = $(withText(string));
        return element.find(By.xpath("./ancestor::div[@class='item_pricebar']"));
    }

    @Step("Remove element with maximum price")
    public SwagLabsSteps removeElement(ElementsCollection elements) {
        getItem(getMaxPrice(convertsToPrices(elements))).find(By.tagName("button")).scrollTo().click();
        return this;
    }

    @Step("Go to checkout")
    public void goToCheckout(SelenideElement element) {
        element.scrollTo().click();
    }
}
