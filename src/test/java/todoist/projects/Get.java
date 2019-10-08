package todoist.projects;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Test;
import todoist.entities.ProjectResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static todoist.BaseTest.*;

public class Get {

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

        Response newProjectResponse = setupProject("Project " + java.util.UUID.randomUUID());
        ProjectResponse newProject = newProjectResponse.getBody().as(ProjectResponse.class);
        String id = newProject.getId().toString();

        Header authorization = new Header("Authorization", getApiToken());

        Response response =

            given().
                    header(authorization).
            when().
                    get(getProjectsEndpoint(id));

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
