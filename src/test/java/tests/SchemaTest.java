package tests;

import ge.tbc.tbcitacademy.data.datas.Constants;
import ge.tbc.tbcitacademy.steps.SchemaSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("Schema")
public class SchemaTest {
    SchemaSteps schemaSteps;
    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = Constants.fakerApiURL;
        schemaSteps = new SchemaSteps();
        RestAssured.filters(new AllureRestAssured());
    }
    @Test(description = "checking validity with schema")
    public void testCreditCardsAPI() {
       schemaSteps.getResponseForSchema()
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema.json"));
    }
}
