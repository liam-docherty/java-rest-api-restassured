package todoist;

import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import todoist.entities.ProjectRequest;
import todoist.entities.ProjectResponse;

import java.math.BigInteger;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class BaseTest {

    private static final String BASE_ENDPOINT = "https://api.todoist.com/rest/v1";
    private static final String PROJECTS_ENDPOINT = BASE_ENDPOINT + "/projects";
    private static final String TASKS_ENDPOINT = BASE_ENDPOINT + "/tasks";
    // TODO: Hide token
    private static final String API_TOKEN = "Bearer a72e91d0ab45952b4a22ac4aef77041daf569a9b";
    private static final Header AUTHORIZATION_HEADER = new Header("Authorization", API_TOKEN);
    private static final ContentType CONTENT_TYPE = ContentType.JSON;
    // This is required because the todoist API will reject the call if the 'charset=UTF-8' is appended
    private static final RestAssuredConfig CONFIGURATION = RestAssuredConfig.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

    public static String getProjectsEndpoint() {
        return PROJECTS_ENDPOINT;
    }
    public static String getProjectsEndpoint(BigInteger id) {
        return PROJECTS_ENDPOINT + "/" + id.toString();
    }
    public static String getTasksEndpoint() {
        return TASKS_ENDPOINT;
    }
    public static Header getAuthorizationHeader() { return AUTHORIZATION_HEADER; }
    public static ContentType getContentType() { return CONTENT_TYPE; }
    public static RestAssuredConfig getConfiguration() { return CONFIGURATION; }

    public static Response createProject(ProjectRequest payload) {

        Response response =
                given().
                        config(CONFIGURATION).
                        header(AUTHORIZATION_HEADER).
                        contentType(CONTENT_TYPE).
                        body(payload).
                when().
                        post(getProjectsEndpoint());

        return response;

    }

    public static Response retrieveProject(BigInteger id) {

        Response response =

                given().
                        header(AUTHORIZATION_HEADER).
                when().
                        get(getProjectsEndpoint(id));

        return response;

    }

    public static Response retrieveAllProjects() {

        Response response =

                given().
                        header(AUTHORIZATION_HEADER).
                when().
                        get(getProjectsEndpoint());

        return response;

    }

    public static Response updateProject(BigInteger id, ProjectRequest payload) {

        Response response =

                given().
                        config(CONFIGURATION).
                        header(AUTHORIZATION_HEADER).
                        contentType(CONTENT_TYPE).
                        body(payload).
                when().
                        post(getProjectsEndpoint(id));

        return response;

    }

    public static Response deleteProject(BigInteger id) {

        Response response =

                given().
                        header(AUTHORIZATION_HEADER).
                when().
                        delete(getProjectsEndpoint(id));

        return response;

    }

    public static String generateStringWithRandomUuid(String prefix) { return prefix + " " + java.util.UUID.randomUUID(); }

    public static String generateStringWithSpecialCharacters(String prefix) { return prefix + " " + "!£$%^&*(){}:'|/,.~`"; }

    public static ProjectResponse retrieveProjectFromProjectResponseArray(ProjectResponse[] projectArray, Number id) {

        ProjectResponse projectResponse = null;
        for (int i = 0; i < projectArray.length; i++) {
            if (id.equals(projectArray[i].getId())) {
                projectResponse = projectArray[i];
            }
        }
        return projectResponse;

    }

}
