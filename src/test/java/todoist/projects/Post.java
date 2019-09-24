package todoist.projects;

import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Test;
import todoist.entities.ProjectRequest;
import todoist.entities.ProjectResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static todoist.BaseTest.*;

public class Post {

    @Test
    public void createProjectSuccess() {

        // TODO: Common header code, remove duplication
        // TODO: Remove logging once happy with tests
        Header authorization = new Header("Authorization", getApiToken());
        ProjectRequest payload = new ProjectRequest("deleteme-serialization");

        Response response =

        given().
                // This is required because the todoist API will reject the call if the 'charset=UTF-8' is appended
                config(RestAssuredConfig.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                header(authorization).
                contentType(ContentType.JSON).
                body(payload).
                log().all().
        when().
                post(getProjectsEndpoint());

        assertThat(response.getStatusCode(), is(200));
        // TODO: Replace hardcoded value
        assertThat(response.contentType(), is("application/json"));

        ProjectResponse body = response.getBody().as(ProjectResponse.class);
        System.out.println("ID = " + body.getId());
        System.out.println("Name = " + body.getName());
        System.out.println("Order = " + body.getOrder());
        System.out.println("Comment Count = " + body.getCommentCount());

    }

}
