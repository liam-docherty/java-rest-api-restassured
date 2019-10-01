package todoist;

import io.restassured.http.Header;

import static io.restassured.RestAssured.given;

public class BaseTest {

    private static final String BASE_ENDPOINT = "https://api.todoist.com/rest/v1";
    private static final String PROJECTS_ENDPOINT = BASE_ENDPOINT + "/projects";
    private static final String TASKS_ENDPOINT = BASE_ENDPOINT + "/tasks";
    private static final String API_TOKEN = "Bearer a72e91d0ab45952b4a22ac4aef77041daf569a9b";

    public static String getProjectsEndpoint() {
        return PROJECTS_ENDPOINT;
    }

    // TODO: Making this a string as unknown what length the ID will be
    public static String getProjectsEndpoint(String id) {
        return PROJECTS_ENDPOINT + "/" + id;
    }

    public static String getTasksEndpoint() {
        return TASKS_ENDPOINT;
    }

    public static String getApiToken() {
        return API_TOKEN;
    }

    // TODO: Is this the best place?
    public static void teardownProject(String id) {

        // TODO: Common header code, remove duplication
        Header authorization = new Header("Authorization", getApiToken());

        given().
                header(authorization).
                when().
                delete(getProjectsEndpoint(id));
    }

}
