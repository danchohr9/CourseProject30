package bg.tu_varna.sit.courseproject30.presentation.models;

public class TaskListViewModel {
    private String summary;
    private String description;
    private String user;

    public TaskListViewModel(String summary, String description, String user) {
        this.summary = summary;
        this.description = description;
        this.user = user;
    }

    @Override
    public String toString() {
        return  String.format("%s | %s | %s", summary ,description,user);
    }
}


