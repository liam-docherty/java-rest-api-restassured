package todoist.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Project {

    private String name;

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

}
