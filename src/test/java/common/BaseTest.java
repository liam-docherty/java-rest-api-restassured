package common;

import io.restassured.http.Header;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class BaseTest {

    private static final String BASE_ENDPOINT = "https://restful-booker.herokuapp.com/";
    private static final String BOOKING_ENDPOINT = BASE_ENDPOINT + "booking/";
    private static final String PING_ENDPOINT = BASE_ENDPOINT + "ping/";
    private static final String APPLICATION_JSON = "application/json";

    public static String getBookingEndpoint() {
        return BOOKING_ENDPOINT;
    }

    public static String getBookingEndpoint(int id) {
        return BOOKING_ENDPOINT + id;
    }

    public static String getPingEndpoint() {
        return PING_ENDPOINT;
    }

    public static Header setAcceptApplicationJsonHeader() {
        return new Header("accept", APPLICATION_JSON);
    }

    public static void confirmStatusCode(Response response, Integer statusCode) {
        assertThat(response.getStatusCode(), is(statusCode));
    }

}

