package booking;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: I feel like I'd like this in a separate folder from my tests
public class Booking {

    @JsonProperty
    private String firstname;
    @JsonProperty
    private String lastname;
    @JsonProperty
    private int totalprice;
    @JsonProperty
    private boolean depositpaid;
    @JsonProperty
    private BookingDates bookingdates;
    @JsonProperty
    private String additionalneeds;

    public String getAdditionalNeeds() {
        return additionalneeds;
    }

}
