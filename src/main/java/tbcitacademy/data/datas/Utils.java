package tbcitacademy.data.datas;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.greaterThan;

public class Utils {
    public static void validateBookDetails(String isbn, String expectedAuthor) {
        given()
                .baseUri("https://bookstore.toolsqa.com")
                .pathParam("isbn", isbn)
                .when()
                .get("/BookStore/v1/Book?ISBN={isbn}")
                .then()
                .statusCode(200)
                .body("author", equalTo(expectedAuthor))
                .body("title", not(emptyOrNullString()))
                .body("publish_date", not(emptyOrNullString()))
                .body("pages", greaterThan(0));
    }
    public static String extractNumberFromMessage(String message) {
        return message.replaceAll("\\D", "");
    }

}
