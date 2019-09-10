package auth;

import io.restassured.response.Response;
import org.junit.Test;

import static common.BaseTest.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class PostSuccess {

    @Test
    public void postCreateTokenReturnsToken() {
        AuthRequest authRequest = new AuthRequest("admin", "password123");

        Response response = given()
                .body(authRequest)
                // TODO: This is a shortcut. Can possibly replace the method in base test
                .contentType("application/json")
                .post(getAuthEndpoint());

        String authResponse = response.getBody().print();

        assertThat(authResponse, containsString("token"));
    }

}
