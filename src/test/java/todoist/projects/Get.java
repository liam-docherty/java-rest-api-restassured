package todoist.projects;

import io.restassured.http.Header;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static todoist.BaseTest.*;

public class Get {

    @Test
    public void retrieveAllProjects() {

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

}
