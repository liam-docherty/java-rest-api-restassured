package todoist.projects;

import io.restassured.response.Response;
import org.junit.Test;
import todoist.entities.ProjectRequest;
import todoist.entities.ProjectResponse;

import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static todoist.BaseTest.*;

public class Delete {

    @Test
    public void deleteProjectSuccess() {

        ProjectRequest postPayload = new ProjectRequest(generateStringWithRandomUuid("DeleteProjectSuccess"));
        Response postResponse = createProject(postPayload);

        ProjectResponse postResponseBody = postResponse.getBody().as(ProjectResponse.class);
        BigInteger id = postResponseBody.getId();

        Response deleteResponse = deleteProject(id);

        assertThat(deleteResponse.getStatusCode(), is(204));
        assertThat(deleteResponse.getBody().print(), is(""));

        Response getResponse = retrieveProject(id);

        assertThat(getResponse.getStatusCode(), is(404));

    }

}
