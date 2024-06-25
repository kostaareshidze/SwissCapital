package tests;

import com.github.javafaker.Faker;
import ge.tbc.tbcitacademy.data.datas.Constants;
import ge.tbc.tbcitacademy.data.models.addPet.AddPet;
import ge.tbc.tbcitacademy.data.models.addPet.Category;
import ge.tbc.tbcitacademy.data.models.addPet.TagsItem;
import ge.tbc.tbcitacademy.steps.PetStoreSteps;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static ge.tbc.tbcitacademy.data.datas.Utils.extractNumberFromMessage;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetStoreTest {
    File img;
    PetStoreSteps petStoreSteps;
    Faker faker = new Faker();
    AddPet addPet;
    Category category;
    TagsItem tagsItem;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Constants.petStoreURL;
        img = new File("cat.jpg");
        petStoreSteps = new PetStoreSteps();
        addPet = new AddPet();
        category = new Category();
        tagsItem = new TagsItem();
    }

    @Test(priority = 1)
    public void placingOrderTest() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 31);
        requestBody.put("petId", 77);
        requestBody.put("quantity", 1);
        requestBody.put("shipDate", Constants.date);
        requestBody.put("status", "placed");
        requestBody.put("complete", true);
        given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post(Constants.orderURL)
                .then()
                .statusCode(200)
                .body("id", notNullValue(),
                        "petId", equalTo(77),
                        "quantity", equalTo(1),
                        "status", equalTo("placed"),
                        "complete", equalTo(true));
    }

    @Test(priority = 2)
    public void testPet() {
        given()
                .formParam("petId", 123)
                .formParam("name", "Buddy")
                .formParam("status", "available")
                .post(Constants.petIdURL, 123)
                .then()
                .body("code", notNullValue(),
                        "type", notNullValue(),
                        "message", notNullValue());
    }

    @Test
    public void invalidRequestTest() {
        Response response = given()
                .pathParam("petId", 2.5)
                .get(Constants.petIdURL);
        response
                .then()
                .statusCode(404)
                .body("code", equalTo(404));
    }

    @Test
    public void loginAndGetNumber() {
        Response response = given()
                .queryParam("username", "your_username")
                .queryParam("password", "your_password")
                .when()
                .get(Constants.logInURL);
        String message = response.jsonPath().getString("message");
        String number = extractNumberFromMessage(message);
        assertThat(number.length(), greaterThanOrEqualTo(10));
        System.out.println(number);
    }

    @Test
    public void uploadImage() {
        Response response = given()
                .contentType(ContentType.MULTIPART)
                .multiPart("petId", 31)
                .multiPart("file", img, "image/jpeg")
                .multiPart("additionalMetadata", Constants.additionalData)
                .when()
                .post(Constants.uploadImgURL, 31)
                .then()
                .contentType(ContentType.JSON)
                .assertThat()
                .body("message", Matchers.containsString(Constants.additionalData))
                .body("message", Matchers.containsString(img.getName()))
                .extract().response();
        assertThat(petStoreSteps.getActualImgLength(response), is(petStoreSteps.getImgLength(img)));
    }

    @Test
    public void updatePet() {
        int id = faker.number().randomDigit();
        addPet.setId(id);
        addPet.setName(faker.animal().name());
        addPet.setStatus(Constants.sold);
        tagsItem.setId(faker.number().randomDigit());
        tagsItem.setName(faker.lorem().word());
        List<TagsItem> tags = new ArrayList<>();
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(faker.lorem().word());
        addPet.setPhotoUrls(photoUrls);
        tags.add(tagsItem);
        addPet.setTags(tags);
        category.setId(faker.number().randomDigit());
        category.setName(faker.lorem().word());
        addPet.setCategory(category);
        given()
                .contentType(ContentType.JSON)
                .body(addPet)
                .post(Constants.petURL);

        given()
                .queryParam(Constants.status, Constants.dead)
                .get(Constants.findByStatusURL)
                .then()
                .assertThat()
                .body(Constants.id, hasItem(id));
        given()
                .contentType(ContentType.URLENC)
                .formParam(Constants.name, Constants.sold)
                .formParam(Constants.status, Constants.sold)
                .when()
                .post(Constants.petIdURL, id);
        given()
                .accept(ContentType.JSON)
                .pathParam(Constants.petId, id)
                .get(Constants.petIdURL)
                .then()
                .assertThat()
                .body(Constants.status, is(Constants.sold),
                        Constants.name, is(Constants.sold));
    }


}
