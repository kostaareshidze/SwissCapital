package tbcitacademy.steps;

import ge.tbc.tbcitacademy.data.datas.Constants;
import ge.tbc.tbcitacademy.data.models.lombokBooking.Bookings;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

public class BookHerokuAppSteps {
    public BookHerokuAppSteps addFirstName(JSONObject request){
        request.put("firstname", "Kosta");
        return this;
    }
    public BookHerokuAppSteps addLastName(JSONObject request){
        request.put("lastname", "Areshidze");
        return this;
    }
    public BookHerokuAppSteps addTotalPrice(JSONObject request){
        request.put("totalprice", 150);
        return this;
    }
    public BookHerokuAppSteps isDepositPaid(JSONObject request){
        request.put("depositpaid", true);
        return this;
    }
    public BookHerokuAppSteps addCheckInDate(JSONObject request){
        request.put("checkin", "2024-05-07");
        return this;
    }
    public BookHerokuAppSteps addCheckOutDate(JSONObject request){
        request.put("checkout", "2024-05-10");
        return this;
    }
    public BookHerokuAppSteps addBookingDates(JSONObject request, JSONObject response){
        request.put("bookingdates", response);
        return this;
    }
    public BookHerokuAppSteps addAdditionalNeeds(JSONObject request){
        request.put("additionalneeds", "Breakfast");
        return this;
    }
    public Response partialUpdate(int id){
        return given()
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=abc123")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body("{ \"firstname\": \"Konstantine\" }")
                .patch("/booking/" + id);
    }
    public Response deleteBooking(){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=abc123")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .delete("/booking/204");
    }
    public Response createBooking(String token, Object obj){
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(obj)
                .post("/booking");
    }
    public Response goThrough(){
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(Constants.logIn)
                .when()
                .post("/auth");
    }
    @Step("update Booking request")
    public Response updateBooking(Bookings bookings, int number){
        return given().filter(new AllureRestAssured())
                .header("Content-Type", Constants.json)
                .header("Accept", Constants.json)
                .header("Content-Type", Constants.json)
                .header("Authorization", Constants.verification)
                .body(bookings)
                .log()
                .body()
                .when()
                .put("/booking/" + number);
    }
}
