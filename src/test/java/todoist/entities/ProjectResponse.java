package todoist.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "order", "name", "comment_count"})
public class ProjectResponse extends Project {

    private Number id;
    private int order;
    private int commentCount;

    @JsonProperty("id")
    public Number getId() {
        return id;
    }

    @JsonProperty("order")
    public int getOrder() {
        return order;
    }

    @JsonProperty("comment_count")
    public int getCommentCount() {
        return commentCount;
    }

}
