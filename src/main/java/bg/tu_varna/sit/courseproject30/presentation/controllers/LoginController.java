package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.TaskService;
import bg.tu_varna.sit.courseproject30.presentation.models.TaskListViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;

public class LoginController{
//    private final TaskService service = TaskService.getInstance();

    @FXML
    private Label loginLbl;

    @FXML
    private Label infoLbl;

    @FXML
    private Label usernameLbl;

    @FXML
    private Label passwordLbl;

    @FXML
    private Button signInBt;

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private CheckBox showPassChB;

    public void signInBtOnAction(ActionEvent actionEvent){

        if(usernameTF.getText().isBlank() == false && passwordPF.getText().isBlank() == false){
            validateLogin();
        }else{
            infoLbl.setText("Please enter username and password.");
        }
    }

    public void validateLogin(){

    }
}
