package todoist.projects;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Test;
import todoist.entities.ProjectResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static todoist.BaseTest.*;

public class Delete {

    @Test
    public void deleteProject() {

        ProjectResponse newProject = setupProject("Project " + java.util.UUID.randomUUID());
        String id = newProject.getId().toString();

        // TODO: Common code, remove duplication
        // TODO: Remove logging once happy with tests
        Header authorization = new Header("Authorization", getApiToken());

        Response response =

        given().
                header(authorization).
        when().
                delete(getProjectsEndpoint(id));

        assertThat(response.getStatusCode(), is(204));
        assertThat(response.getBody().print(), is(""));

        Response getResponse = retrieveProject(id);
        assertThat(getResponse.getStatusCode(), is(404));

    }

}
