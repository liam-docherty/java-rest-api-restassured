package todoist.projects;

import io.restassured.http.Header;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static todoist.BaseTest.*;

public class Delete {

    @Test
    public void deleteProject() {

        // TODO: Common code, remove duplication
        // TODO: Hardcoded url, remove duplication
        // TODO: Remove logging once happy with tests
        Header header = new Header("Authorization", getApiToken());

        given().
                header(header).
                log().all().
        when().
                delete("https://api.todoist.com/rest/v1/projects/2217677524").
        then().
                log().body().
                assertThat().statusCode(204);

    }

}
