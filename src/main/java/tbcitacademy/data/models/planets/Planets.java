package tbcitacademy.data.models.planets;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



public record Planets(

	@JsonProperty("next") String next,

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") LocalDateTime created,

	@JsonProperty("previous") Object previous,

	@JsonProperty("count") int count,

	@JsonProperty("results") List<PlanetsResponse> results
	){

}