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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.PropertyConfigurator;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
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
    private final UserService service;
    ObservableList<UserViewModel> userViewModels;
    Stage stage;

    public LoginController(){
        service = UserService.getInstance();
        userViewModels = service.getAllTask();
    }
    @FXML
    public void signInBtOnAction(ActionEvent actionEvent){

        if(!usernameTF.getText().isBlank() && !passwordPF.getText().isBlank()){
            Stage oldStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            oldStage.close();
            user = new UserViewModel(usernameTF.getText(),passwordPF.getText());
            if(UserService.validateLogin(user, userViewModels)){
                HelloApplication.setUser(user);
                try {
                    URL path = getClass().getResource(Constants.View.MENU_VIEW);
                    FXMLLoader fxmlLoader = new FXMLLoader(path);
                    Parent root = fxmlLoader.load();
                    Stage stage = new Stage();
                    /*
                    MenuController controller = fxmlLoader.getController();
                    controller.initData();
                    Could be useful
                     */
                    Scene scene = new Scene(root);
                    stage.setTitle("Dashboard");
                    scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                infoLbl.setText("Invalid credentials.");
            }
        }else{
            infoLbl.setText("Please enter username and password.");
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
