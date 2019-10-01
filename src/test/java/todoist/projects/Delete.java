package todoist.projects;

import io.restassured.http.Header;
import org.junit.Test;
import todoist.entities.ProjectResponse;

import static io.restassured.RestAssured.given;
import static todoist.BaseTest.*;

public class Delete {

    @Test
    public void deleteProject() {

        ProjectResponse newProject = setupProject("Project " + java.util.UUID.randomUUID());

        // TODO: Common code, remove duplication
        // TODO: Remove logging once happy with tests
        Header authorization = new Header("Authorization", getApiToken());

        given().
                header(authorization).
                log().all().
        when().
                delete(getProjectsEndpoint(newProject.getId().toString())).
        then().
                log().body().
                assertThat().statusCode(204);

        // TODO: Check response is empty
        // TODO: Check that 404 is returned when using getProject

    }

}
