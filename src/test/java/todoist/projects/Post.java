package todoist.projects;

import io.restassured.http.ContentType;
import org.junit.Test;
import todoist.BaseTest;

import static io.restassured.RestAssured.given;

public class Post {

    @Test
    public void createProject() {

        String json = "{\"name\": \"deleteme\"}";

        given().
                header("Authorization", BaseTest.getApiToken()).
                contentType(ContentType.JSON).
                body(json).
                log().all().
        when().
                post("https://api.todoist.com/rest/v1/projects").
        then().
                log().body().
                assertThat().statusCode(201);

    }

}
