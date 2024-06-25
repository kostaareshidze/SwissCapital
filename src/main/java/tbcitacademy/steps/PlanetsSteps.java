package tbcitacademy.steps;

import ge.tbc.tbcitacademy.data.models.planets.Planets;
import ge.tbc.tbcitacademy.data.models.planets.PlanetsResponse;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class PlanetsSteps {
    @Step("getting all planets")
    public Response getAllPlanets() {
        return given().filter(new AllureRestAssured())
                .get("/api/planets/?format=json");
    }
    @Step("getting one planet")
    public Response getPlanet(int number) {
        return given().filter(new AllureRestAssured())
                .when()
                .get("/api/planets/" + number);
    }

    @Step("sort list with creation date")
    public List<PlanetsResponse> sortPlanetsByCreationDate(List<PlanetsResponse> planets) {
        return planets.stream()
                .sorted(Comparator.comparing(PlanetsResponse::created).reversed())
                .collect(Collectors.toList());
    }

    @Step("getting most recent planets")
    public List<PlanetsResponse> getMostRecentPlanets(List<PlanetsResponse> sortedPlanets, int count) {
        int endIndex = Math.min(sortedPlanets.size(), count);
        return sortedPlanets.subList(0, endIndex);
    }

    @Step("processing response")
    public List<PlanetsResponse> processResponse(Planets response, int count) {
        List<PlanetsResponse> planets = response.results();

        List<PlanetsResponse> sortedPlanets = sortPlanetsByCreationDate(planets);
        return getMostRecentPlanets(sortedPlanets, count);
    }

    @Step("processing response")
    public List<PlanetsResponse> handleResponse(Planets response) {
        return processResponse(response, 3); // Default to returning 3 most recent planets
    }
    public PlanetsResponse getMaxRotatedPlanet(List<PlanetsResponse> planets){
        return planets.stream()
                .max(Comparator.comparingInt(planet -> Integer.parseInt(planet.rotationPeriod())))
                .orElse(null);
    }
}
