package ping;

import io.restassured.response.Response;
import org.junit.Test;

import static common.BaseTest.*;
import static io.restassured.RestAssured.given;

public class GetSuccess {

    @Test
    public void getHealthCheckReturnsSuccessCode() {
        Response response = given().get(getPingEndpoint());
        confirmStatusCode(response, 201);
    }

}
