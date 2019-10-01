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

public class Get {

    // TODO: This should accept the equivalent of an interface. The tests themselves should set up the payload
    // TODO: Setup project needs to return the entire response
    public ProjectResponse setupProject(String name) {

        // TODO: This is duplication of POST test, remove duplication
        Header authorization = new Header("Authorization", getApiToken());
        ProjectRequest payload = new ProjectRequest(name);

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

        return response.getBody().as(ProjectResponse.class);

    }

    @Test
    public void retrieveAllProjectsSuccess() {

        // TODO: Common header code, remove duplication
        // TODO: Remove logging once happy with tests
        Header authorization = new Header("Authorization", getApiToken());

        given().
                header(authorization).
                log().all().
        when().
                get(getProjectsEndpoint()).
        then().
                log().body().
                assertThat().statusCode(200);

    }

    // TODO: This can be turned into a parameterised test
    @Test
    public void retrieveProjectSuccess() {

        ProjectResponse newProject = setupProject("Project " + java.util.UUID.randomUUID());

        Header authorization = new Header("Authorization", getApiToken());

        Response response =

            given().
                    header(authorization).
                    log().all().
            when().
                    get(getProjectsEndpoint(newProject.getId().toString()));

        ProjectResponse responseBody = response.getBody().as(ProjectResponse.class);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.contentType(), is(ContentType.JSON.toString()));
        assertThat(responseBody.getId(), is(newProject.getId()));
        assertThat(responseBody.getName(), is(newProject.getName()));
        assertThat(responseBody.getCommentCount(), is(newProject.getCommentCount()));
        assertThat(responseBody.getOrder(), is(newProject.getOrder()));

        teardownProject(newProject.getId().toString());

    }

}
