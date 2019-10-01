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

    public int getExistingProjectCount() {

        Header authorization = new Header("Authorization", getApiToken());

        // TODO: This will be common with the get all Projects tests, move to common area
        Response response =

            given().
                    header(authorization).
            when().
                    get(getProjectsEndpoint());

        ProjectResponse[] body = response.getBody().as(ProjectResponse[].class);

        return body.length;

    }

    // TODO: This can be turned into a parameterised test
    @Test
    public void createProjectSuccess() {

        int existingProjectCount = getExistingProjectCount();

        // TODO: Common header code, remove duplication
        // TODO: Remove logging once happy with tests
        Header authorization = new Header("Authorization", getApiToken());
        ProjectRequest payload = new ProjectRequest("deleteme-serialization");

        Response response =

            given().
                    // TODO: See if this config option can be moved to a Request Specification
                    // This is required because the todoist API will reject the call if the 'charset=UTF-8' is appended
                    config(RestAssuredConfig.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                    header(authorization).
                    contentType(ContentType.JSON).
                    body(payload).
            when().
                    post(getProjectsEndpoint());

        ProjectResponse responseBody = response.getBody().as(ProjectResponse.class);

        assertThat(response.getStatusCode(), is(200));
        // TODO: Replace hardcoded value
        assertThat(response.contentType(), is(ContentType.JSON.toString()));
        assertThat(responseBody.getName(), is(payload.getName()));
        assertThat(responseBody.getOrder(), is(existingProjectCount));
        assertThat(responseBody.getCommentCount(), is(0));

        teardownProject(responseBody.getId().toString());

    }

    // TODO: Add tests with special characters
    // TODO: Add max characters tests


}
