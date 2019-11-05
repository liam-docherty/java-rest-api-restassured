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

public class Read {

    @Test
    public void retrieveAllProjectsSuccess() {

        // TODO: Common header code, remove duplication
        Header authorization = new Header("Authorization", getApiToken());

        // TODO: Replace all of these random guid generators with a method in base test
        Response newProjectResponse1 = setupProject("RetrieveAllProjects1 " + java.util.UUID.randomUUID());
        ProjectResponse newProject1 = newProjectResponse1.getBody().as(ProjectResponse.class);
        Number id1 = newProject1.getId();

        Response newProjectResponse2 = setupProject("RetrieveAllProjects2 " + java.util.UUID.randomUUID());
        ProjectResponse newProject2 = newProjectResponse2.getBody().as(ProjectResponse.class);
        Number id2 = newProject2.getId();

        Response response =

                given().
                        header(authorization).
                when().
                        get(getProjectsEndpoint());

        ProjectResponse[] responseBody = response.getBody().as(ProjectResponse[].class);

        ProjectResponse projectResponse1 = retrieveProjectFromProjectResponseArray(responseBody, id1);
        ProjectResponse projectResponse2 = retrieveProjectFromProjectResponseArray(responseBody, id2);

        teardownProject(id1);
        teardownProject(id2);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.contentType(), is(ContentType.JSON.toString()));
        assertThat(projectResponse1.getId(), is(newProject1.getId()));
        assertThat(projectResponse1.getName(), is(newProject1.getName()));
        assertThat(projectResponse1.getCommentCount(), is(newProject1.getCommentCount()));
        assertThat(projectResponse1.getOrder(), is(newProject1.getOrder()));
        assertThat(projectResponse2.getId(), is(newProject2.getId()));
        assertThat(projectResponse2.getName(), is(newProject2.getName()));
        assertThat(projectResponse2.getCommentCount(), is(newProject2.getCommentCount()));
        assertThat(projectResponse2.getOrder(), is(newProject2.getOrder()));

    }

    // TODO: This can be turned into a parameterised test
    @Test
    public void retrieveProjectSuccess() {

        Response newProjectResponse = setupProject("RetrieveProjectSuccess " + java.util.UUID.randomUUID());
        ProjectResponse newProject = newProjectResponse.getBody().as(ProjectResponse.class);
        Number id = newProject.getId();

        Header authorization = new Header("Authorization", getApiToken());

        Response response =

            given().
                    header(authorization).
            when().
                    get(getProjectsEndpoint(id));

        ProjectResponse responseBody = response.getBody().as(ProjectResponse.class);

        teardownProject(id);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.contentType(), is(ContentType.JSON.toString()));
        assertThat(responseBody.getId(), is(newProject.getId()));
        assertThat(responseBody.getName(), is(newProject.getName()));
        assertThat(responseBody.getCommentCount(), is(newProject.getCommentCount()));
        assertThat(responseBody.getOrder(), is(newProject.getOrder()));

    }

}
