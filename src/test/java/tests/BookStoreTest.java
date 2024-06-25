package tests;

import ge.tbc.tbcitacademy.data.datas.Constants;
import ge.tbc.tbcitacademy.data.datas.DataProviderClass;
import ge.tbc.tbcitacademy.data.models.books.Books;
import ge.tbc.tbcitacademy.steps.BookStoreSteps;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbc.tbcitacademy.data.datas.Utils.validateBookDetails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookStoreTest {
BookStoreSteps bookStoreSteps;
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Constants.bookStoreURL;
        bookStoreSteps = new BookStoreSteps();
    }

    @Test(priority = 1)
    public void assureFirstTest() {
        Response bookListResponse = given()
                .when()
                .get(Constants.booksURL);

        String firstBookISBN = bookListResponse.jsonPath().getString("books[0].isbn");
        String firstBookAuthor = bookListResponse.jsonPath().getString("books[0].author");
        String secondBookISBN = bookListResponse.jsonPath().getString("books[1].isbn");
        String secondBookAuthor = bookListResponse.jsonPath().getString("books[1].author");

        validateBookDetails(firstBookISBN, firstBookAuthor);
        validateBookDetails(secondBookISBN, secondBookAuthor);
    }

    @Test(dataProvider = "getData", dataProviderClass = DataProviderClass.class, priority = 2)
    public void isbnDataProvider(String isbn, int index) {
        given()
                .formParam("index", index)
                .formParam("isbn", isbn)
                .get(Constants.isbnURl, isbn)
                .then()
                .log().body();
    }

    @Test(priority = 3)
    public void testDelete() {
        Response response = given()
                .accept("application/json")
                .when()
                .delete(Constants.booksURL);
        response
                .then()
                .statusCode(401)
                .body("message", equalTo(Constants.userFailed));
    }

    @Test
    public void lessThanThousandPage() {
        Books books = bookStoreSteps.getBooks()
                .then()
                .extract().as(Books.class);
        int length = books.getBooks().size();
        for (int i = 0; i < books.getBooks().size(); i++) {
            Assert.assertTrue(books.getBooks().get(i).getPages() < 1000);
        }
        Assert.assertEquals(books.getBooks().get(length - 2).getAuthor(), Constants.marijin);
        Assert.assertEquals(books.getBooks().get(length - 1).getAuthor(), Constants.zekas);
    }
}
