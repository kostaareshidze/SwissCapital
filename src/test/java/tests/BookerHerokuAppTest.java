package tests;

import com.github.javafaker.Faker;
import ge.tbc.tbcitacademy.data.datas.Constants;
import ge.tbc.tbcitacademy.data.datas.DataProviderClass;
import ge.tbc.tbcitacademy.data.models.bookings.Bookingdates;
import ge.tbc.tbcitacademy.data.models.bookings.UpdateBooking;
import ge.tbc.tbcitacademy.data.models.lombokBooking.Bookings;
import ge.tbc.tbcitacademy.steps.BookHerokuAppSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Epic("Booking")
public class BookerHerokuAppTest {
    Faker faker;
    UpdateBooking updateBooking;
    BookHerokuAppSteps bookHerokuAppSteps;
    Bookingdates bookingdates;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Constants.bookingURL;
        bookHerokuAppSteps = new BookHerokuAppSteps();
        updateBooking = new UpdateBooking();
        bookingdates = new Bookingdates();
        faker  = new Faker();
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    public void updateBooking() {
        updateBooking.setFirstname("Kosta");
        updateBooking.setLastname("Areshidze");
        updateBooking.setTotalprice(101);
        bookingdates.setCheckin("2024-05-07");
        bookingdates.setCheckout("2024-09-07");
        updateBooking.setBookingdates(bookingdates);
        updateBooking.setAdditionalneeds("Breakfast");
        given()
                .header("Content-Type", Constants.json)
                .header("Accept", Constants.json)
                .header("Content-Type", Constants.json)
                .header("Authorization", Constants.verification)
                .body(updateBooking)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .put(Constants.bookingNumberURL);
        given()
                .get(Constants.bookingNumberURL)
                .then()
                .log().all().extract().body().as(UpdateBooking.class);

    }


    @Test
    public void tokenTest() {
        Response tokenResponse = bookHerokuAppSteps.goThrough();
        String token = tokenResponse.then().extract().body().jsonPath().getString("token");
        updateBooking.setFirstname("Kosta");
        updateBooking.setLastname("Areshidze");
        updateBooking.setTotalprice(101);
        bookingdates.setCheckin("2024-05-07");
        bookingdates.setCheckout("2024-09-07");
        updateBooking.setBookingdates(bookingdates);
        updateBooking.setAdditionalneeds("Breakfast");
        Response response = bookHerokuAppSteps.createBooking(token, updateBooking);
        response.then()
                .statusCode(200);
        int bookingId = response.then().extract().path("bookingid");
        bookHerokuAppSteps.partialUpdate(bookingId)
                .then()
                .statusCode(200);
        bookHerokuAppSteps.deleteBooking()
                .then()
                .statusCode(201);
    }

    @Test(dataProvider = "bookingData", dataProviderClass = DataProviderClass.class,
            description = "update booking with dataprovider")
    public void dataProviderBooking(Bookings bookings) {
        int number = faker.number().numberBetween(1, 80);
        bookHerokuAppSteps.updateBooking(bookings, number)
                .then()
                .statusCode(200)
                .assertThat()
                .body(Constants.firstname, is(bookings.firstname()))
                .log().body();
    }
}
