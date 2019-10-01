package todoist;

import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import todoist.entities.ProjectRequest;
import todoist.entities.ProjectResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

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

    // TODO: This should accept the equivalent of an interface. The tests themselves should set up the payload
    // TODO: Setup project needs to return the entire response
    public static ProjectResponse setupProject(String name) {

        // TODO: This is duplication of POST test, remove duplication
        Header authorization = new Header("Authorization", getApiToken());
        ProjectRequest payload = new ProjectRequest(name);

        Response response =

                given().
                        // TODO: See if this config option can be moved to a Request Specification
                        // This is required because the todoist API will reject the call if the 'charset=UTF-8' is appended
                                config(RestAssuredConfig.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                        header(authorization).
                        contentType(ContentType.JSON).
                        body(payload).
                        when().
                        post(getProjectsEndpoint());

        return response.getBody().as(ProjectResponse.class);

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
