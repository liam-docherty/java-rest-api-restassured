package todoist.projects;

import io.restassured.http.Header;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static todoist.BaseTest.*;

public class Get {

    @Test
    public void retrieveAllProjects() {

        Header header = new Header("Authorization", getApiToken());

        given().
                header(header).
                log().all().
        when().
                get("https://api.todoist.com/rest/v1/projects").
        then().
                log().body().
                assertThat().statusCode(200);

    }

}
