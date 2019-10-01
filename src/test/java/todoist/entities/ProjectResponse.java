package todoist.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Investigate @JsonPropertyOrder, looks like it specifies the expected order
public class ProjectResponse extends Project {

    private Number id;
    private Number order;
    private int commentCount;

    public Number getId() {
        return id;
    }

    public Number getOrder() {
        return order;
    }

    @JsonProperty("comment_count")
    public int getCommentCount() {
        return commentCount;
    }

}
