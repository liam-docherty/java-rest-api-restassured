package todoist.tasks;

import io.restassured.http.Header;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static todoist.BaseTest.*;

public class Get {

    @Test
    public void retrieveAllActiveTasks() {

        // TODO: Common header code, remove duplication
        // TODO: Remove logging once happy with tests
        Header authorization = getAuthorizationHeader();

        given().
                header(authorization).
                log().all().
        when().
                get(getTasksEndpoint()).
        then().
                log().body().
                assertThat().statusCode(200);

    }

}
