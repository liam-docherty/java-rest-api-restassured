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
import static todoist.BaseTest.teardownProject;

public class Update {

    // TODO: This can be turned into a parameterised test
    @Test
    public void updateProjectSuccess() {

        Response newProjectResponse = setupProject("RetrieveProjectSuccess " + java.util.UUID.randomUUID());
        ProjectResponse newProject = newProjectResponse.getBody().as(ProjectResponse.class);
        Number id = newProject.getId();

        // TODO: Common header code, remove duplication
        Header authorization = new Header("Authorization", getApiToken());
        ProjectRequest payload = new ProjectRequest("UpdateProjectSuccess " + java.util.UUID.randomUUID());

        Response postResponse =

                given().
                        // TODO: See if this config option can be moved to a Request Specification
                        // This is required because the todoist API will reject the call if the 'charset=UTF-8' is appended
                        config(RestAssuredConfig.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                        header(authorization).
                        contentType(ContentType.JSON).
                        body(payload).
                when().
                        post(getProjectsEndpoint(id));

        assertThat(postResponse.getStatusCode(), is(204));
        assertThat(postResponse.getBody().print(), is(""));

        Response getResponse =

                given().
                        header(authorization).
                when().
                        get(getProjectsEndpoint(id));

        ProjectResponse getResponseBody = getResponse.getBody().as(ProjectResponse.class);

        // TODO: Tidy up
//        assertThat(getResponseBody.getId(), is(newProject.getId()));
        System.out.println(getResponseBody.getName());
        assertThat(getResponseBody.getName(), is(payload.getName()));
//        assertThat(getResponseBody.getCommentCount(), is(newProject.getCommentCount()));
//        assertThat(getResponseBody.getOrder(), is(newProject.getOrder()));

        teardownProject(id);

    }

}
