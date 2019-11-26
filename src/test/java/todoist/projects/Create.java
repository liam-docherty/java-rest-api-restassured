package todoist.projects;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import todoist.entities.ProjectRequest;
import todoist.entities.ProjectResponse;

import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static todoist.BaseTest.*;

public class Create {

    // TODO: This can be turned into a parameterised test
    @Test
    public void createProjectSuccess() {

        ProjectRequest payload = new ProjectRequest(generateUniqueString("CreateProjectSuccess"));
        Response response = createProject(payload);

        ProjectResponse responseBody = response.getBody().as(ProjectResponse.class);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.contentType(), is(ContentType.JSON.toString()));
        assertThat(responseBody.getId(), is(greaterThan(BigInteger.ZERO)));
        assertThat(responseBody.getName(), is(payload.getName()));
        assertThat(responseBody.getCommentCount(), is(0));
        assertThat(responseBody.getOrder(), is(greaterThanOrEqualTo(0)));

        deleteProject(responseBody.getId());

    }

    // TODO: Add tests with special characters
    // TODO: Add max characters tests


}
