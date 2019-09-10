package booking;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: I feel like I'd like this in a separate folder from my tests
public class BookingDates {

    @JsonProperty
    private String checkin;
    @JsonProperty
    private String checkout;

}
