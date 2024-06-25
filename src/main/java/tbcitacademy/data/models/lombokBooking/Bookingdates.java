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
@JsonPropertyOrder({"checkout", "checkin"})
@JsonIgnoreProperties({""})
public class Bookingdates{

	@JsonProperty("checkin")
	public String checkin;

	@JsonProperty("checkout")
	public String checkout;
}