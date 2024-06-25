package tbcitacademy.steps;

import ge.tbc.tbcitacademy.data.models.petStore.Order;
import ge.tbc.tbcitacademy.data.models.xmlPetStore.Category;
import ge.tbc.tbcitacademy.data.models.xmlPetStore.PetStore;
import ge.tbc.tbcitacademy.data.models.xmlPetStore.TagsItem;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OtherPetStoreSteps {
    @Step("posting new Pet")
    public Response postNewPetOrder(Order order){
        return given().filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(order)
                .log().body()
                .when()
                .post("/api/v3/store/order");
    }
    @Step("posting new Pet with POJO")
    public Order createNewOrder(){
        return new Order()
                .id(20)
                .complete(true)
                .petId(20)
                .quantity(9)
                .shipDate("2022-05-10T10:47:22.580Z")
                .status("available");
    }
    @Step("posting new Pet with XML")
    public Response postPetWithXML(PetStore petStore){
        return given().filter(new AllureRestAssured())
                .log()
                .body()
                .contentType(ContentType.XML)
                .body(petStore, ObjectMapperType.JAKARTA_EE)
                .when()
                .post("/api/v3/pet");
    }
    @Step("Creating Pet Store object")
    public PetStore createPetStore() {
        PetStore petStore = new PetStore();
        petStore.setId(5);
        petStore.setName("gio");
        petStore.setStatus("sold");
        petStore.setCategory(createCategory());
        petStore.setTags(createTags());
        return petStore;
    }
    @Step("Creating category object")
    private Category createCategory() {
        Category category = new Category();
        category.setId(0);
        category.setName("string");
        return category;
    }

    @Step("Creating Tag items object")
    private List<TagsItem> createTags() {
        TagsItem tagsItem = new TagsItem();
        tagsItem.setId(0);
        tagsItem.setName("string");
        List<TagsItem> tagsItems = new ArrayList<>();
        tagsItems.add(tagsItem);
        return tagsItems;
    }

}
