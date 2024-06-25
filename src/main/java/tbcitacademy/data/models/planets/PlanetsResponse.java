package tbcitacademy.data.models.planets;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


public record PlanetsResponse(

	@JsonProperty("films") List<String> films,

	@JsonProperty("edited") String edited,

	@JsonProperty("created") String created,

	@JsonProperty("climate") String climate,

	@JsonProperty("rotation_period") String rotationPeriod,

	@JsonProperty("url") String url,

	@JsonProperty("population") String population,

	@JsonProperty("orbital_period") String orbitalPeriod,

	@JsonProperty("surface_water") String surfaceWater,

	@JsonProperty("diameter") String diameter,

	@JsonProperty("gravity") String gravity,

	@JsonProperty("name") String name,

	@JsonProperty("residents") List<String> residents,

	@JsonProperty("terrain") String terrain
){

}