package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.data.entities.Task;
import bg.tu_varna.sit.courseproject30.data.repositorities.TaskRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.TaskListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    private final TaskRepository repository = TaskRepository.getInstance();

    public static TaskService getInstance() {
        return TaskServiceHolder.INSTANCE;
    }

    private static class TaskServiceHolder {
        public static final TaskService INSTANCE = new TaskService();
    }

    public ObservableList<TaskListViewModel> getAllTask() {
        List<Task> tasks = repository.getAll();

        return FXCollections.observableList(
                tasks
                        .stream()
                        .map(t -> new TaskListViewModel(
                                t.getTitle(),
                                t.getDescription(),
                                t.getUser().getUsername()
                        )).collect(Collectors.toList()));
    }
}
