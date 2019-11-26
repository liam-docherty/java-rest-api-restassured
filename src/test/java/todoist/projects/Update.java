package todoist.projects;

import io.restassured.response.Response;
import org.junit.Test;
import todoist.entities.ProjectRequest;
import todoist.entities.ProjectResponse;

import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static todoist.BaseTest.*;

public class Update {

    // TODO: This can be turned into a parameterised test
    @Test
    public void updateProjectSuccess() {

        ProjectRequest createPayload = new ProjectRequest(generateUniqueString("UpdateProjectSuccessCreate"));
        Response createResponse = createProject(createPayload);

        ProjectResponse createResponseBody = createResponse.getBody().as(ProjectResponse.class);
        BigInteger id = createResponseBody.getId();

        ProjectRequest updatePayload = new ProjectRequest(generateUniqueString("UpdateProjectSuccessUpdate"));

        Response updateResponse = updateProject(id, updatePayload);

        assertThat(updateResponse.getStatusCode(), is(204));
        assertThat(updateResponse.getBody().print(), is(""));

        Response retrieveResponse = retrieveProject(id);
        ProjectResponse retrieveResponseBody = retrieveResponse.getBody().as(ProjectResponse.class);

        assertThat(retrieveResponseBody.getId(), is(id));
        assertThat(retrieveResponseBody.getName(), is(updatePayload.getName()));
        assertThat(retrieveResponseBody.getCommentCount(), is(createResponseBody.getCommentCount()));
        assertThat(retrieveResponseBody.getOrder(), is(createResponseBody.getOrder()));

        deleteProject(id);

    }

}
