package todoist.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigInteger;

@JsonPropertyOrder({"id", "order", "name", "comment_count"})
public class ProjectResponse extends Project {

    private BigInteger id;
    private int order;
    private int commentCount;

    public BigInteger getId() {
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
