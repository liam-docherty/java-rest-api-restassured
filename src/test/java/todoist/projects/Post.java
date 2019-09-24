package todoist.projects;

import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.junit.Test;
import todoist.entities.ProjectRequest;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static todoist.BaseTest.*;

public class Post {

    @Test
    public void createProject() {

        // TODO: Common header code, remove duplication
        // TODO: Remove logging once happy with tests
        Header authorization = new Header("Authorization", getApiToken());
        ProjectRequest payload = new ProjectRequest("deleteme-serialization");

        given().
                // This is required because the todoist API will reject the call if the 'charset=UTF-8' is appended
                config(RestAssuredConfig.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                header(authorization).
                contentType(ContentType.JSON).
                body(payload).
                log().all().
        when().
                post(getProjectsEndpoint()).
        then().
                log().body().
                assertThat().statusCode(200);

    }

}
