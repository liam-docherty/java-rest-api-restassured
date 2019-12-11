package todoist.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Should this be an abstract class. I only want to instantiate the ProjectRequest or ProjectResponse classes
public class Project {

    private String name;

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    @JsonProperty("name")
    public void setName(String name) { this.name = name; }

}
