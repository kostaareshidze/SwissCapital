package tests;

import ge.tbcitacademy.data.RetryAnalyzer;
import ge.tbcitacademy.data.RetryFail;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FiveFailClass {
    @BeforeClass

    public void setUp(){
//        ConfigTests.setUp(browser);
        WebDriverManager.chromedriver().setup();
    }

    @RetryFail(count = 5)
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void failTest(){
        Assert.assertEquals(1, 9);
    }
}
