package todoist.projects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Project {

    private int id;
    private int order;
    private String name;
    private int commentCount;

    public int getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

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
