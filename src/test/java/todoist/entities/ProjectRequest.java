package todoist.entities;

public class ProjectRequest {

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectRequest(String name) {
        this.setName(name);
    }

}
