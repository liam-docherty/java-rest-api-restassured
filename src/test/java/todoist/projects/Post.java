package todoist.projects;

import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static todoist.BaseTest.*;

public class Post {

    @Test
    public void createProject() {

        // TODO: Common header code, remove duplication
        // TODO: Hardcoded url, remove duplication
        // TODO: Remove logging once happy with tests
        Header authorization = new Header("Authorization", getApiToken());
        String payload = "{\"name\": \"deleteme\"}";

        given().
                // This is required because the todoist API will reject the call if the 'charset=UTF-8' is appended
                config(RestAssuredConfig.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                header(authorization).
                contentType(ContentType.JSON).
                body(payload).
                log().all().
        when().
                post("https://api.todoist.com/rest/v1/projects").
        then().
                log().body().
                assertThat().statusCode(200);

    }

}
