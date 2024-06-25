package tbcitacademy.steps;

import ge.tbc.tbcitacademy.data.datas.Constants;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.lessThan;


public class BookStoreSteps {

    public void pagesAreLessThanThousand(Response response){
        response
                .then()
                .assertThat()
                .body("books.pages", everyItem(lessThan(1000)));
    }
    public Response getBooks(){
        return given()
                .get(Constants.booksURL);
    }
}
