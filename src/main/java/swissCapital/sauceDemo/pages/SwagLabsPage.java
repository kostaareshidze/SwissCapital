package swissCapital.sauceDemo.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SwagLabsPage {
    public ElementsCollection items = $$(".inventory_item_name");
    public SelenideElement cart = $("#shopping_cart_container");
    public SelenideElement backpack = $(withText("Sauce Labs Backpack"))
            .find(By.xpath("./ancestor::div[@class='inventory_item']"));
    public SelenideElement jacket = $(withText("Sauce Labs Fleece Jacket"))
            .find(By.xpath("./ancestor::div[@class='inventory_item']"));
    public SelenideElement shirt = $(withText("Test.allTheThings() T-Shirt (Red)"))
            .find(By.xpath("./ancestor::div[@class='inventory_item']"));
    public ElementsCollection prices = $$(".inventory_item_price");
    public SelenideElement checkout = $("#checkout");

}
