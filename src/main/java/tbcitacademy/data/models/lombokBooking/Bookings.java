package tbcitacademy.data.models.lombokBooking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"firstname", "lastname", "totalprice", "bookingdates",
		"depositpaid", "additionalneeds", "saleprice", "passportno"})
@JsonIgnoreProperties({"saleprice"})
public class Bookings{

	@JsonProperty("firstname")
	public String firstname;

	@JsonProperty("additionalneeds")
	public String additionalneeds;

	@JsonProperty("bookingdates")
	public Bookingdates bookingdates;

	@JsonProperty("totalprice")
	public int totalprice;

	@JsonProperty("depositpaid")
	public boolean depositpaid;

	@JsonProperty("lastname")
	public String lastname;

	@JsonProperty("saleprice")
	public int saleprice;

	@JsonProperty("passportno")
	public String passportNo;
}