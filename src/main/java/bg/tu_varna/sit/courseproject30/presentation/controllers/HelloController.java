package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.TaskService;
import bg.tu_varna.sit.courseproject30.presentation.models.HelloModel;
import bg.tu_varna.sit.courseproject30.presentation.models.TaskListViewModel;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class HelloController implements EventHandler<MouseEvent> {
    private final TaskService service = TaskService.getInstance();

    @FXML
    private Label welcomeText;

    @FXML
    private Button helloButton;

    @FXML
    private ListView<TaskListViewModel> listView;

    private final HelloModel model;

    public HelloController() {
        this.model = new HelloModel();
    }

    @FXML
    public void handle(MouseEvent mouseEvent){
        welcomeText.setText(model.getWelcomeMessage());
        ObservableList<TaskListViewModel> taskViewModels = service.getAllTask();
        listView.setItems(taskViewModels);
    }

    @FXML
    private void initialize(){
        helloButton.setOnMouseClicked(this::handle);
    }
}