package tests;

import ge.tbc.tbcitacademy.data.datas.Constants;
import ge.tbc.tbcitacademy.data.models.drivers.Driver;
import ge.tbc.tbcitacademy.data.models.drivers.DriverTable;
import ge.tbc.tbcitacademy.data.models.drivers.DriversItem;
import ge.tbc.tbcitacademy.data.models.drivers.MRData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class DriverTest {
    MRData mrData;
    Driver driver;
    DriversItem driversItem;
    DriverTable driverTable;
    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = Constants.baseURL;
        mrData = new MRData();
        driver = new Driver();
        driverTable = new DriverTable();
        driversItem = new DriversItem();
    }
    @Test
    public void driversTest() {
        List<DriversItem> drivers = new ArrayList<>();
        driversItem.setDriverId("alonso");
        driversItem.setPermanentNumber("14");
        driversItem.setCode("ALO");
        driversItem.setUrl(Constants.nameInWikipedia);
        driversItem.setGivenName("Fernando");
        driversItem.setFamilyName("Alonso");
        driversItem.setDateOfBirth("1981-07-29");
        driversItem.setNationality("spanish");
        drivers.add(driversItem);
        driverTable.setSeason("2016");
        driverTable.setDrivers(drivers);
        mrData.setXmlns(Constants.erqastURL);
        mrData.setSeries("f1");
        mrData.setUrl(Constants.driversURL);
        mrData.setLimit("30");
        mrData.setOffset("0");
        mrData.setOffset("0");
        mrData.setTotal("24");
        mrData.setDriverTable(driverTable);

        Driver driver = given()
                .accept(ContentType.JSON)
                .get(Constants.driversAPI)
                .then()
                .log()
                .body().extract().body().as(Driver.class);

    }
}
