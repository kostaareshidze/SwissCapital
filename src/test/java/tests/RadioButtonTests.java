package tests;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ge.tbcitacademy.data.Methods.selectYes;

@Test(groups = "RadioButtons-FrontEnd")
public class RadioButtonTests {

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
            File destFile = new File("RadioButtonFailedTests/" + testMethodName + ".png");
            screenshot.renameTo(destFile);
        }
    }

    @Test
    public void testRadioButtons() {
        open("https://demoqa.com/radio-button");
        getWebDriver().manage().window().maximize();
        SelenideElement yesButton = $(By.id("yesRadio")).shouldBe(visible);
        selectYes(yesButton);
    }

}
