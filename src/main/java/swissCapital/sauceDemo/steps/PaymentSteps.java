package swissCapital.sauceDemo.steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class PaymentSteps {
    @Step("Fill first name: {str}")
    public PaymentSteps fillFirstName(SelenideElement element, String str) {
        element.sendKeys(str);
        return this;
    }

    @Step("Fill last name: {str}")
    public PaymentSteps fillLastName(SelenideElement element, String str) {
        element.sendKeys(str);
        return this;
    }

    @Step("Fill postal code: {str}")
    public PaymentSteps fillPostalCode(SelenideElement element, String str) {
        element.sendKeys(str);
        return this;
    }

    @Step("Press continue button")
    public PaymentSteps pressContinue(SelenideElement element) {
        element.scrollTo().click();
        return this;
    }

    @Step("Press finish button")
    public PaymentSteps pressFinish(SelenideElement element) {
        element.scrollTo().click();
        return this;
    }
}
