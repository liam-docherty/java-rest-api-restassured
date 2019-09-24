package todoist.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Investigate @JsonPropertyOrder, looks like it specifies the expected order
public class ProjectResponse extends Project {

    private int id;
    private int order;
    // TODO: Remove
    private String name;
    private int commentCount;

    public int getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    // TODO: Remove next 2 methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("comment_count")
    public int getCommentCount() {
        return commentCount;
    }

}
