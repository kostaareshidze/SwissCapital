package swissCapital.sauceDemo.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PaymentPage {
    public SelenideElement firstName = $("#first-name");
    public SelenideElement lastName = $("#last-name");
    public SelenideElement postalCode = $("#postal-code");
    public SelenideElement continueButton = $("#continue");
    public SelenideElement finishButton = $("#finish");
    public SelenideElement completeText = $x("//h2");
}
