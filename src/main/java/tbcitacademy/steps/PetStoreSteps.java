package tbcitacademy.steps;


import com.github.javafaker.Faker;
import ge.tbc.tbcitacademy.data.datas.Constants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetStoreSteps {
    Faker faker = new Faker();

    public long getImgLength(File img) {
        return img.length();
    }

    private int getIndex(Response response) {
        return response.getBody().jsonPath().getString("message").indexOf("bytes");
    }

    private String getSubstringOfSize(Response response) {
        return response.jsonPath().getString("message").substring(0, getIndex(response)).trim();
    }

    public long getActualImgLength(Response response) {
        return Long.parseLong(getSubstringOfSize(response).replaceAll("\\D", ""));
    }

    public PetStoreSteps addId(JSONObject requestBody, int id) {
        requestBody.put("id", id);
        return this;
    }

    public PetStoreSteps addCategory(JSONObject requestBody, JSONObject responseBody) {
        requestBody.put("category", responseBody);
        return this;
    }

    public PetStoreSteps addIdInCategory(JSONObject requestBody) {
        requestBody.put("id", faker.number().randomDigit());
        return this;
    }

    public void addNameInCategory(JSONObject requestBody) {
        requestBody.put("name", faker.animal().name());
    }

    public PetStoreSteps addName(JSONObject requestBody) {
        requestBody.put("name", faker.name().firstName());
        return this;
    }

    public PetStoreSteps addStatus(JSONObject requestBody) {
        requestBody.put("status", "dead");
        return this;
    }

    public PetStoreSteps addPhotoURL(JSONObject requestBody, JSONArray array) {
        array.put(faker.internet().url());
        requestBody.put("photoUrls", array);
        return this;
    }

    public PetStoreSteps addTags(JSONObject requestBody) {
        JSONArray tags = new JSONArray();
        JSONObject tag = new JSONObject();
        tag.put("id", faker.number().randomDigit());
        tag.put("name", faker.lorem().word());
        tags.put(tag);
        requestBody.put("tags", tags);
        return this;
    }
    public PetStoreSteps addPet(Object obj){
        given()
                .contentType(ContentType.JSON)
                .body(obj)
                .post(Constants.petURL);
        return this;
    }

}
