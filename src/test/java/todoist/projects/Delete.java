package todoist.projects;

import io.restassured.http.Header;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static todoist.BaseTest.*;

public class Delete {

    @Test
    public void deleteProject() {

        // TODO: Common code, remove duplication
        // TODO: Remove logging once happy with tests
        Header authorization = new Header("Authorization", getApiToken());

        given().
                header(authorization).
                log().all().
        when().
                delete(getProjectsEndpoint("2218111869")).
        then().
                log().body().
                assertThat().statusCode(204);

    }

}
