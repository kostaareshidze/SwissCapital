package tbcitacademy.data.datas;

import ge.tbc.tbcitacademy.data.models.lombokBooking.Bookingdates;
import ge.tbc.tbcitacademy.data.models.lombokBooking.Bookings;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DataProviderClass {
    @DataProvider
    public Object[][] getData(){
        Response response = given()
                .baseUri(Constants.bookStoreURL)
                .when()
                .get(Constants.booksURL);
        List<String> isbn = response.jsonPath().getList("books.isbn");
        Object[][] data = new Object[isbn.size()][2];
        for (int i = 0; i < isbn.size(); i++) {
            data[i][0] = isbn.get(i);
            data[i][1] = i;
        }
        return data;
    }
//    @DataProvider
//    public static Object[][] bookingData() {
//        return new Object[][] {
//                { new Bookings()
//                        .firstname("Kosta")
//                        .lastname("areshidze")
//                        .depositpaid(true)
//                        .totalprice(150)
//                        .additionalneeds("Supper")
//                        .passportNo("0101")
//                        .saleprice(85)
//                        .bookingdates(new Bookingdates()
//                                            .checkin("2018-04-01")
//                                            .checkout("2018-06-01")
//                )},
//                { new Bookings()
//                        .firstname("Giorgi")
//                        .lastname("amilaxvari")
//                        .depositpaid(false)
//                        .totalprice(250)
//                        .additionalneeds("Breakfast")
//                        .passportNo("0109")
//                        .saleprice(125)
//                        .bookingdates(new Bookingdates()
//                        .checkin("2018-05-12")
//                        .checkout("2018-09-08")
//                )}
//        };
//    }
}
