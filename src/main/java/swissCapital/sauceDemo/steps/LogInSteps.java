package swissCapital.sauceDemo.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

public class LogInSteps {
    @Step("Fill username from elements list at position {num}")
    public LogInSteps fillsUserName(SelenideElement element, ElementsCollection elements, int num) {
        element.sendKeys(convertsToList(elements).get(num));
        return this;
    }

    @Step("Fill username: {string}")
    public LogInSteps fillsUserName(SelenideElement element, String string) {
        element.sendKeys(string);
        return this;
    }

    @Step("Fill password: {string}")
    public LogInSteps fillsUserPassword(SelenideElement element, String string) {
        element.sendKeys(string);
        return this;
    }

    @Step("Fill password from elements list at position {num}")
    public LogInSteps fillsPassword(SelenideElement element, ElementsCollection elements, int num) {
        element.sendKeys(convertsToList(elements).get(num));
        return this;
    }

    @Step("Click log in")
    public void logIn(SelenideElement element) {
        element.click();
    }

    @Step("Convert ElementsCollection to list of strings")
    private List<String> convertsToList(ElementsCollection elements) {
        List<String> list = new ArrayList<>();
        for (SelenideElement element : elements) {
            String fullText = element.text();
            String[] lines = fullText.split("\\r?\\n");
            for (String line : lines) {
                String trimmedLine = line.trim();
                if (!trimmedLine.isEmpty()) {
                    list.add(trimmedLine);
                }
            }
        }
        return list;
    }
}
