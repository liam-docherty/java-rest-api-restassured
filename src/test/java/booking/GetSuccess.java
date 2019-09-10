package booking;

import io.restassured.response.Response;
import org.junit.Test;

import static common.BaseTest.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GetSuccess {

    @Test
    public void getAllBookingsReturnsSuccessCode() {
        Response response = given().get(getBookingEndpoint());
        confirmStatusCode(response, 200);
    }

    @Test
    public void getBookingsByIdReturnsSuccessCode() {
        // TODO: Build common getBookingsById
        Response response = given()
                .header(setAcceptApplicationJsonHeader())
                .get(getBookingEndpoint(1));

        confirmStatusCode(response, 200);
    }

    @Test
    public void getBookingsByIdContainsBreakfast() {
        // TODO: Build common getBookingsById
        Response response = given()
                .header(setAcceptApplicationJsonHeader())
                .get(getBookingEndpoint(1));

        Booking responseBody = response.as(Booking.class);
        String additionalNeeds = responseBody.getAdditionalNeeds();

        assertThat(additionalNeeds, is("Breakfast"));
    }

}
