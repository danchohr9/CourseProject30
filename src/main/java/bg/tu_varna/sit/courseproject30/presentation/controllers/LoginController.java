package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.business.services.UserService;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.presentation.models.TaskListViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.URL;

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

    private UserViewModel user;
    private final UserService service = UserService.getInstance();
    ObservableList<UserViewModel> userViewModels = service.getAllTask();
    boolean state=false;
    Stage stage = new Stage();

    public LoginController(Stage stage){
        this.stage = stage;
    }

    public void signInBtOnAction(ActionEvent actionEvent){

        if(usernameTF.getText().isBlank() == false && passwordPF.getText().isBlank() == false){
            validateLogin();
        }else{
            infoLbl.setText("Please enter username and password.");
        }
    }

    public void validateLogin(){
        user = new UserViewModel(usernameTF.getText(),passwordPF.getText());
        for (UserViewModel u:userViewModels){
            if(u.equals(user)){
                state=true;
                user.setEmail(u.getEmail());
                user.setRole(u.getRole());
                changeScene();
                break;
            }
        }
        if(state==false) infoLbl.setText("Invalid credentials.");
    }


    public void changeScene(){

        try{
            stage.close();
            Stage stage2 = new Stage();
            URL path = getClass().getResource(Constants.View.MENU_VIEW);
            FXMLLoader fxmlLoader = new FXMLLoader(path);
            fxmlLoader.setController(new MenuController(stage2,user));
            Parent root = fxmlLoader.load();
//            URL path = getClass().getResource(Constants.View.MENU_VIEW);
//            Parent root = FXMLLoader.load(path);

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage2.setTitle(Constants.Values.Title);
            stage2.setScene(scene);
            stage2.setResizable(false);
            stage2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
