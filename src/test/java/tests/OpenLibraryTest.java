package tests;

import ge.tbc.tbcitacademy.data.datas.Constants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OpenLibraryTest {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Constants.openLibraryURL;
    }
    @Test
    public void searchBooks() {
        given()
                .queryParam("q", Constants.harryPotter)
                .when()
                .get(Constants.searchURL)
                .then()
                .statusCode(200)
                .body("docs", not(empty()),
                        "docs[0].title", equalTo(Constants.philosopherStone),
                        "docs[0].author_name", contains(Constants.jk),
                        "docs[0].place", hasItems("England",
                                Constants.hogwarts,
                                Constants.platform));
    }
}
