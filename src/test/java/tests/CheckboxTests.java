package tests;

import com.codeborne.selenide.*;
import ge.tbcitacademy.data.Methods;
import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ge.tbcitacademy.data.Methods.check;
import static ge.tbcitacademy.data.Methods.unCheck;

@Test(groups = "CheckBoxes-FrontEnd")
public class CheckboxTests {
    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        ConfigTests.setUp(browser);
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            File screenshot = Screenshots.takeScreenShotAsFile();
            String testMethodName = result.getMethod().getMethodName();
            File destFile = new File("CheckboxFailedTests/" + testMethodName + ".png");
            screenshot.renameTo(destFile);
        }
    }

    @Test
    public void testCheckbox() {
        open("http://the-internet.herokuapp.com/checkboxes");
        getWebDriver().manage().window().maximize();
        ElementsCollection checkboxes = $$(By.tagName("input"));
        unCheck(checkboxes);
        for (SelenideElement checkbox : checkboxes) {
            checkbox.shouldBe(checked);
        }
        check(checkboxes);
        for (SelenideElement checkbox : checkboxes) {
            checkbox.shouldNotBe(checked);
        }

    }

}
