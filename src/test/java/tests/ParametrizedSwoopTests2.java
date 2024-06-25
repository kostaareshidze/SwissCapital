package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ge.tbcitacademy.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class ParametrizedSwoopTests2 {
    private String xpath = "";

    public ParametrizedSwoopTests2(String xpath){
        this.xpath = xpath;
    }
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        open(Constants.swoopURl);
    }

    @Test
    public void distributeOn4Months(){

        $x(xpath).click();
        $x("//a[@href='/BNPL']").shouldBe(visible);
    }
}
