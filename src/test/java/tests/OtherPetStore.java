package tests;

import ge.tbc.tbcitacademy.data.datas.Constants;
import ge.tbc.tbcitacademy.data.models.petStore.Order;
import ge.tbc.tbcitacademy.data.models.xmlPetStore.Category;
import ge.tbc.tbcitacademy.data.models.xmlPetStore.PetStore;
import ge.tbc.tbcitacademy.data.models.xmlPetStore.TagsItem;
import ge.tbc.tbcitacademy.steps.OtherPetStoreSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Epic("Pet Store")
public class OtherPetStore {
    OtherPetStoreSteps otherPetStoreSteps;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Constants.otherPetStoreURL;
        otherPetStoreSteps = new OtherPetStoreSteps();
        RestAssured.filters(new AllureRestAssured());
    }

    @Test(description = "validating id with lombok")
    public void validateUsingLombokFluent() {
        Order order = otherPetStoreSteps.createNewOrder();
        otherPetStoreSteps.postNewPetOrder(order);
        assertThat(order.petId(), is(20));
    }

    @Test(description = "validating id with xml")
    public void postPetWithXML() {
        PetStore petStore = otherPetStoreSteps.createPetStore();
        otherPetStoreSteps.postPetWithXML(petStore)
                .then()
                .assertThat()
                .body("//id[1]", is(petStore.getId())); //id is ver mivwvdi veranairadd
    }
}
