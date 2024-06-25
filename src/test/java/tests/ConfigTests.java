package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class ConfigTests {
    @BeforeClass
    @Parameters("browser")
    public static void setUp(String browser){
        Configuration.timeout = 10000;
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            Configuration.browser = "chrome";
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            Configuration.browser = "firefox";
        }else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            Configuration.browser = "edge";
        }
    }
}
