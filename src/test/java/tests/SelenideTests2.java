package tests;

import com.codeborne.selenide.*;
import ge.tbcitacademy.data.Constants;
import ge.tbcitacademy.reportListener.ReportListener;
import ge.tbcitacademy.suiteListener.SuiteListener;
import ge.tbcitacademy.testListener.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


@Test(groups = "Selenide 2")
@Listeners({SuiteListener.class, TestListener.class, ReportListener.class})
public class SelenideTests2 extends SuiteListener {
    SoftAssert softAssert;
    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        ConfigTests.setUp(browser);
        softAssert = new SoftAssert();
    }
    @Test(priority = 1, description = "demos design validation")
    public void validateDemosDesign(){
        open(Constants.telerikURL);
        getWebDriver().manage().window().maximize();
        ElementsCollection webLinks = $$x("//div[@class='row u-mb8'][1]//div[@class='HoverImg u-mb1']");
        for (SelenideElement link: webLinks) {
            link.hover().shouldHave(cssValue(Constants.background, Constants.rgba));
        }
        ElementsCollection list = $$x
                ("//div[@id='ContentPlaceholder1_C329_Col00']//ul[@class='List Space--compact']/li//span");
        list.findBy(text(Constants.UI)).should(exist);
        SelenideElement container = $x("//div[@class='row u-mb8'][2]");
        ElementsCollection windows = container.findAll("img");
        softAssert.assertTrue(windows.size() == 2);
        ElementsCollection sectionLinks = $$x("//a[@class='icon-arrow u-dib u-fs10 u-fw5 u-icon-thick u-tdn']");
        SelenideElement telerik = $("#ContentPlaceholder1_C340_Col01");
        ElementsCollection images = telerik.findAll("img");
        softAssert.assertTrue(images.size() == 3);
        executeJavaScript(Constants.scrollToBottom);
        sectionLinks.forEach(name -> name.shouldBe(visible));
    }
    @Test(priority = 2, description = "order validation")
    public void validateOrderMechanics(){
        open(Constants.telerikURL);
        SelenideElement pricing = $(byText(Constants.pricing));
        pricing.click();
        SelenideElement buy = $x("//th[@class='Complete']//a[@class='Btn Btn--prim4 u-db']");
        executeJavaScript(Constants.scroll500);
        String devCraftPrice = $x("//th[@class='Complete']//span[@class='u-dib u-fs60 u-fw4']").text();
        buy.click();
        SelenideElement dismiss = $x("//i[@class='far fa-times label u-cp']").shouldBe(visible);
        dismiss.click();
        SelenideElement unitPrice = $x("//span[@class='e2e-price-per-license ng-star-inserted']");
        Assert.assertEquals(devCraftPrice, unitPrice.text().substring(1, unitPrice.text().length() - 3));
        SelenideElement dropdown = $(".dropdown--small.k-widget");
        dropdown.click();
//        SelenideElement element = $x("//td[@class='sm-no-spacing border-top']//span[@class='k-input']");
//        for (int i = 2; i <= 30; i++) {
//            SelenideElement choose = dropdown.$(byText(String.valueOf(i)));
//            choose.click();
//            if (Integer.parseInt(element.text()) > 1 && Integer.parseInt(element.text()) <= 5){
//                SelenideElement save = $(".label.label--grey.sm-hidden");
//                save.shouldHave(text("Save $84.95"));
//            }else {
//                SelenideElement save = $(".label.label--grey.sm-hidden");
//                save.shouldHave(text("Save $169.90"));
//            }
//        }

        ElementsCollection dropdownItems = $$(".dropdown--medium.k-widget li.k-item");
        for (int i = 0; i < dropdownItems.size(); i++) {
            dropdownItems.get(i).click();
            System.out.println(dropdownItems.get(i).text());
        }

        SelenideElement continueAsGuest = $(byText(Constants.guest));
        executeJavaScript(Constants.scroll500);
        continueAsGuest.click();
        SelenideElement name = $("#biFirstName");
        name.click();
        name.sendKeys(Constants.nameFirst);
        SelenideElement lastName = $("#biLastName");
        lastName.sendKeys(Constants.surname);
        SelenideElement email = $("#biEmail");
        email.click();
        email.sendKeys(Constants.email);
        SelenideElement company = $("#biCompany");
        company.click();
        company.sendKeys(Constants.company);
        SelenideElement phone = $("#biPhone");
        phone.click();
        phone.sendKeys(Constants.number);
        SelenideElement address = $("#biAddress");
        phone.scrollTo();
        address.click();
        address.sendKeys(Constants.address);
        SelenideElement city = $("#biCity");
        city.click();
        city.sendKeys(Constants.city);
//        SelenideElement submit = $(byText("Continue"));
//        submit.click();



    }
    @Test(description = "book name validation")
    public void chainedLocatorsTest(){
        open("https://demoqa.com/books");
        ElementsCollection bookElements = $$("div.rt-tr-group");
        bookElements.forEach(bookElement -> {
            boolean isOReillyMedia = bookElement.$(".rt-td").$x(".//div[text()=\"O'Reilly Media\"]").exists();
            if (isOReillyMedia) {
                // Find the parent of the publisher element and get the text of the 'a' element
                String bookTitle = bookElement.$(".rt-td").$x(".//div[text()=\"O'Reilly Media\"]")
                        .parent().$("a").text();
            }
        });
        for (SelenideElement bookElement : bookElements) {
            boolean isImg = bookElement.$(".rt-td").$("img").exists();
            if (isImg) {
                SelenideElement imageElement = bookElement.$(".rt-td img");
                imageElement.shouldHave(attribute("src"));
            }
        }
    }
    @Test(description = "second book name validation")
    public void softAssertTest(){
        open("https://demoqa.com/books");
        getWebDriver().manage().window().maximize();


        ElementsCollection books = $$(".rt-tr-group")
                .filterBy(text(Constants.publisher))
                .filterBy(text(Constants.title));

        softAssert.assertEquals(books.size(), 10);
        String firstTitle = books.first().find("a").text();
        softAssert.assertEquals(firstTitle, Constants.titleOfFirst);
        softAssert.assertAll();
    }
}
