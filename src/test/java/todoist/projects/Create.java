package todoist.projects;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import todoist.entities.ProjectRequest;
import todoist.entities.ProjectResponse;

import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static todoist.BaseTest.*;

@RunWith(DataProviderRunner.class)
public class Create {

    private static String namePrefix = "CreateProjectSuccess";

    @DataProvider
    public static String[] projectNames() {

        // TODO: Add max characters example
        return new String[]{
                generateStringWithRandomUuid(namePrefix),
                generateStringWithSpecialCharacters(namePrefix)
        };

    }

    @Test
    @UseDataProvider("projectNames")
    public void createProjectSuccess(String name) {

        ProjectRequest payload = new ProjectRequest(name);
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

}
