package todoist.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Investigate @JsonPropertyOrder, looks like it specifies the expected order
public class ProjectResponse extends Project {

    private Number id;
    private int order;
    private int commentCount;

    public Number getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    @JsonProperty("comment_count")
    public int getCommentCount() {
        return commentCount;
    }

}
