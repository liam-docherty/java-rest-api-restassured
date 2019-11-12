package todoist.projects;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import todoist.entities.ProjectRequest;
import todoist.entities.ProjectResponse;

import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static todoist.BaseTest.*;

public class Read {

    @Test
    public void retrieveAllProjectsSuccess() {

        ProjectRequest postPayload1 = new ProjectRequest(generateUniqueString("RetrieveAllProjectsSuccess1"));
        Response postResponse1 = createProject(postPayload1);

        ProjectResponse postResponseBody1 = postResponse1.getBody().as(ProjectResponse.class);
        BigInteger id1 = postResponseBody1.getId();

        ProjectRequest postPayload2 = new ProjectRequest(generateUniqueString("RetrieveAllProjectsSuccess2"));
        Response postResponse2 = createProject(postPayload2);

        ProjectResponse postResponseBody2 = postResponse2.getBody().as(ProjectResponse.class);
        BigInteger id2 = postResponseBody2.getId();

        Response getResponse = retrieveAllProjects();

        ProjectResponse[] getResponseBody = getResponse.getBody().as(ProjectResponse[].class);

        ProjectResponse projectResponse1 = retrieveProjectFromProjectResponseArray(getResponseBody, id1);
        ProjectResponse projectResponse2 = retrieveProjectFromProjectResponseArray(getResponseBody, id2);

        assertThat(getResponse.getStatusCode(), is(200));
        assertThat(getResponse.contentType(), is(ContentType.JSON.toString()));
        assertThat(projectResponse1.getId(), is(id1));
        assertThat(projectResponse1.getName(), is(postResponseBody1.getName()));
        assertThat(projectResponse1.getCommentCount(), is(postResponseBody1.getCommentCount()));
        assertThat(projectResponse1.getOrder(), is(postResponseBody1.getOrder()));
        assertThat(projectResponse2.getId(), is(id2));
        assertThat(projectResponse2.getName(), is(postResponseBody2.getName()));
        assertThat(projectResponse2.getCommentCount(), is(postResponseBody2.getCommentCount()));
        assertThat(projectResponse2.getOrder(), is(postResponseBody2.getOrder()));

        deleteProject(id1);
        deleteProject(id2);

    }

    // TODO: This can be turned into a parameterised test
    @Test
    public void retrieveProjectSuccess() {

        ProjectRequest postPayload = new ProjectRequest(generateUniqueString("RetrieveProjectSuccess"));
        Response postResponse = createProject(postPayload);

        ProjectResponse postResponseBody = postResponse.getBody().as(ProjectResponse.class);
        BigInteger id = postResponseBody.getId();

        Response getResponse = retrieveProject(id);
        ProjectResponse getResponseBody = getResponse.getBody().as(ProjectResponse.class);

        assertThat(getResponse.getStatusCode(), is(200));
        assertThat(getResponse.contentType(), is(ContentType.JSON.toString()));
        assertThat(getResponseBody.getId(), is(postResponseBody.getId()));
        assertThat(getResponseBody.getName(), is(postResponseBody.getName()));
        assertThat(getResponseBody.getCommentCount(), is(postResponseBody.getCommentCount()));
        assertThat(getResponseBody.getOrder(), is(postResponseBody.getOrder()));

        deleteProject(id);

    }

}
