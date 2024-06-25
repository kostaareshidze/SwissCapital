package tests;

import org.testng.annotations.Factory;

import static com.codeborne.selenide.Selenide.$;

public class FactoryClass {

    @Factory
    public Object[] createInstances() {
        return new Object[]{
                new ParametrizedSwoopTests2("//div[@class='Menus']//a[@href='/events']"),
                new ParametrizedSwoopTests2("//div[@class='Menus']//a[@href='/category/23/estetika']"),
                new ParametrizedSwoopTests2("//div[@class='Menus']//a[@href='/category/24/dasveneba']"),
                new ParametrizedSwoopTests2("//div[@class='Menus']//a[@href='/category/15/restornebi-da-barebi']"),
                new ParametrizedSwoopTests2("//div[@class='Menus']//a[@href='/category/636/sabavshvo']"),
                new ParametrizedSwoopTests2("//div[@class='Menus']//a[@href='/category/4/gasartobi-centrebi']"),
                new ParametrizedSwoopTests2("//div[@class='Menus']//a[@href='/category/110/sporti']")
        };
    }
}
