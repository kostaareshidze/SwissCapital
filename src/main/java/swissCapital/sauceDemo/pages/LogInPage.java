package swissCapital.sauceDemo.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LogInPage {
    public  SelenideElement usernameField = $("#user-name");
    public  SelenideElement passwordField = $("#password");
    public  ElementsCollection loginUsername = $$("#login_credentials");
    public  ElementsCollection loginPassword = $$x("//div[@class='login_password']");
    public  SelenideElement logIn = $("#login-button");
    public  SelenideElement error = $(".error-message-container");

}
