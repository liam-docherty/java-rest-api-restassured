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

        int existingProjectCount = getExistingProjectCount();

        // TODO: Common header code, remove duplication
        Header authorization = new Header("Authorization", getApiToken());

        // TODO: Replace all of these random guid generators with a method in base test
        Response newProjectResponse = setupProject("Project " + java.util.UUID.randomUUID());
        ProjectResponse newProject = newProjectResponse.getBody().as(ProjectResponse.class);
        String id = newProject.getId().toString();

        Response response =

                given().
                        header(authorization).
                when().
                        get(getProjectsEndpoint());

        ProjectResponse[] responseBody = response.getBody().as(ProjectResponse[].class);

        // Works assuming new project is added to end of list
        // TODO: Should instead find the matching ID in the array
        ProjectResponse newProjectInResponseBody = responseBody[existingProjectCount];

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.contentType(), is(ContentType.JSON.toString()));
        assertThat(responseBody.length, is(existingProjectCount + 1));
        assertThat(newProjectInResponseBody.getId(), is(newProject.getId()));
        assertThat(newProjectInResponseBody.getName(), is(newProject.getName()));
        assertThat(newProjectInResponseBody.getCommentCount(), is(newProject.getCommentCount()));
        assertThat(newProjectInResponseBody.getOrder(), is(newProject.getOrder()));

        teardownProject(id);

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
