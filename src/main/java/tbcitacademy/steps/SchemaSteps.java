package tbcitacademy.steps;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SchemaSteps {
    @Step("getting response about credit cards")
    public Response getResponseForSchema(){
        return given().filter(new AllureRestAssured())
                .accept(ContentType.JSON)
                .when()
                .get("/api/v1/credit_cards?_quantity=2");
    }
}
