package tests;

import com.github.javafaker.Faker;
import ge.tbc.tbcitacademy.data.datas.Constants;
import ge.tbc.tbcitacademy.data.models.planets.Planets;
import ge.tbc.tbcitacademy.data.models.planets.PlanetsResponse;
import ge.tbc.tbcitacademy.steps.PlanetsSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("Planets")
public class PlanetTest {
    Faker faker;
    PlanetsSteps planetsSteps;
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = Constants.planetsURL;
        faker = new Faker();
        planetsSteps = new PlanetsSteps();
        RestAssured.filters(new AllureRestAssured());
    }

    @Test(description = "making validations on planets")
    public void validationsForPlanet() {
        Planets response = planetsSteps.getAllPlanets()
                .then()
                .extract().body().as(Planets.class);
        int number = faker.number().numberBetween(0,60);
        Planets newPlanet = planetsSteps.getPlanet(number)
                .then()
                .extract().as(Planets.class);
        MatcherAssert.assertThat("name", Matchers.is(newPlanet.results().get(0).name()));
        MatcherAssert.assertThat("rotation_period", Matchers.is(newPlanet.results().get(0).rotationPeriod()));
        MatcherAssert.assertThat("diameter", Matchers.is(newPlanet.results().get(0).diameter()));
        MatcherAssert.assertThat("gravity", Matchers.is(newPlanet.results().get(0).gravity()));
        MatcherAssert.assertThat("terrain", Matchers.is(newPlanet.results().get(0).terrain()));
        List<PlanetsResponse> planets = response.results();
        Assert.assertEquals(3, planetsSteps.handleResponse(response).size());
        planetsSteps.getMaxRotatedPlanet(planets);
    }
}
