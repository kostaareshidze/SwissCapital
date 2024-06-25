package tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbcitacademy.data.Constants;

import ge.tbcitacademy.reportListener.ReportListener;
import ge.tbcitacademy.suiteListener.SuiteListener;
import ge.tbcitacademy.testListener.TestListener;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


@Test(groups = "Selenide 2")
@Listeners({SuiteListener.class, TestListener.class, ReportListener.class})
public class SelenideTests {
    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        ConfigTests.setUp(browser);
    }

    @Test(priority = 1, description = "bundle offer validation")
    public void validateBundleOffers() {
        open(Constants.telerikURL);
        getWebDriver().manage().window().maximize();
        SelenideElement pricing = $(byText(Constants.pricing));
        pricing.click();
        //1
        ElementsCollection containerOfProducts =
                $$x("//th[@class='UI is-active']//div[@class='u-m-m0 u-m-mt1 u-pl1 u-pr0']//li");
        for (SelenideElement element : containerOfProducts) {
            element.shouldNotHave(text(Constants.mocking));
        }
        //2
        ElementsCollection productBundleTexts = $$x("//p[@class='u-fs12 u-fwn u-mb0']");

        for (int i = 0; i < productBundleTexts.size() - 1; i++) {
            productBundleTexts.get(i).shouldNotHave(text(Constants.issue));
        }
        SelenideElement devCraftUltimateText =
                $x("//th[@class='Ultimate']//p[@class='u-fs12 u-fwn u-mb0']");
        devCraftUltimateText.shouldHave(text(Constants.issue));
        //3
        ElementsCollection productBundleSupports = $$x("//th//ul");
        for (int i = 0; i < productBundleSupports.size() - 1; i++) {
            productBundleSupports.get(i).$x("./li").shouldNotHave(text(Constants.endToEnd));
        }
        ElementsCollection devCraftUltimateSupports = $$x("//th[@class='Ultimate']//ul/li");
        for (SelenideElement element : devCraftUltimateSupports) {
            if (element.has(text(Constants.endToEnd)))
                break;
        }
        //4
        SelenideElement button = $(By.cssSelector(".Btn-details"));
        executeJavaScript(Constants.scrollBy);
        button.click();
        SelenideElement product = $(byText(Constants.telerik));
        ElementsCollection tdsInProduct = product.$$x("./td");
        for (int i = 0; i < tdsInProduct.size() - 1; i++) {
            tdsInProduct.get(i).shouldBe(empty);
        }
        product.$x("./span").shouldNotBe(empty);
        //5
        SelenideElement kendo = $(byText(Constants.kendo));
        ElementsCollection tdsInKendo = kendo.$$x("./td");
        for (SelenideElement element : tdsInKendo) {
            element.shouldNotBe(empty);
        }
        //6
        SelenideElement spanInReportServer = $x("//td/p/span[@class='u-fs15']");
        spanInReportServer.shouldHave(text(Constants.instance15user));
        //7
        SelenideElement reporting = $(withText(Constants.reporting)).parent().parent();
        ElementsCollection listOfReporting = reporting.findAll("td");
//        reporting.$x("./td[@class='is-active']").shouldBe(empty);
        for (int i = 1; i < listOfReporting.size(); i++) {
            listOfReporting.get(i).shouldNotBe(empty);
        }
        //8
        SelenideElement accessDemandVideos = $(byText(Constants.access)).parent();
        ElementsCollection listOfDemands = accessDemandVideos.findAll("td");
        for (SelenideElement element : listOfDemands) {
            element.shouldNotBe(empty);
        }
        //sticky test
        executeJavaScript(Constants.scrollToBottom);
        ElementsCollection offerNames = $$(".track--pricing-body tr>:first-child");
        offerNames.forEach(name -> name.shouldBe(visible));
    }

    @Test(priority = 2, description = "individual offers validation")
    public void validateIndividualOffers() {
        open(Constants.telerikURL);
        SelenideElement pricing = $(byText(Constants.pricing));
        pricing.click();
        SelenideElement individualProduct = $(byText(Constants.individual));
        individualProduct.click();
        $("#ContentPlaceholder1_C714_Col01").scrollTo();
        ElementsCollection twoOffers = $$x
                ("//div[@id='ContentPlaceholder1_C713_Col00']//div[@class='row row--aligned']/div");
        for (SelenideElement element : twoOffers) {
            element.hover();
            $x("//img[@title='Kendo Ui Ninja']").shouldBe(visible);
        }
        ElementsCollection twoDropdown = $$x
                ("//div[@id='ContentPlaceholder1_C713_Col00']//a[@class='Btn Dropdown-control u-b1 u-c-black u-db u-pl2 u-tal u-tint-white']");
        for (SelenideElement element : twoDropdown) {
            element.shouldHave(text(Constants.priority));
        }
        SelenideElement kendoReactPrice = $x
                ("//div[@id='ContentPlaceholder1_C714_Col01']//span[@class='js-price']");
        SelenideElement kendoUIPrice = $x
                ("//div[@id='ContentPlaceholder1_C714_Col00']//span[@class='js-price']");
        kendoReactPrice.shouldHave(text(Constants.nines));
        kendoUIPrice.shouldHave(text(Constants.oneFour));


    }

    @Test(description = "checkbox validation")
    public void checkBoxTest() {
        open(Constants.internetURL);
        SelenideElement firstCheckbox = $("#checkboxes>:first-child");
        firstCheckbox.setSelected(true);
        ElementsCollection checkboxes = $$x("//input");
        for (SelenideElement element : checkboxes) {
            element.shouldHave(type(Constants.checkbox));
        }
    }

    @Test(description = "dropdown validation")
    public void dropDownTest() {
        open(Constants.dropdownURl);
        SelenideElement dropdownText = $x("//option[@selected='selected']");
        SelenideElement dropdown = $("#dropdown");
        dropdownText.shouldHave(text(Constants.defaultValue));
        dropdown.selectOption(Constants.optionTwo);
        dropdownText.shouldHave(text(Constants.optionTwo));
    }

    @Test(description = "collection validation")
    @Parameters("browser")
    public void collectionsTest(String browser){
        if (browser.equalsIgnoreCase("firefox")) {
            open(Constants.textBoxURL);
            SelenideElement name = $("#userName").scrollTo(); //cssSelector
            name.click();
            name.sendKeys(Constants.name);
            SelenideElement email = $x("//input[@class='mr-sm-2 form-control']"); //xpath
            email.click();
            email.sendKeys(Constants.email);
            SelenideElement currentAddress = $(By.id("currentAddress"));
            currentAddress.click();
            currentAddress.sendKeys(Constants.address);
            SelenideElement permanentAddress = $(byText(Constants.permanentAddress)).parent(); //bytext
            SelenideElement newElement = permanentAddress.sibling(0).find("textarea");
            newElement.click();
            newElement.sendKeys(Constants.address);
            SelenideElement submit = $("#submit");
            submit.click();
            SelenideElement newName = $(".border #name");
            SelenideElement newEmail = $(".border #email");
            SelenideElement newCurrAddress = $(".border #currentAddress");
            SelenideElement newPermanentAddress = $(".border #permanentAddress");
            newName.shouldHave(text(Constants.name));
            newEmail.shouldHave(text(Constants.email));
            newPermanentAddress.shouldHave(text(Constants.address));
            newCurrAddress.shouldHave(text(Constants.address));
        }  // Do nothing, skip this test for Chrome


    }
}
