package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.URL;

public class MenuController {

    @FXML
    private Label welcomeLbl;

    @FXML
    private Button addMolBt;

    @FXML
    private Button addProductBt;

    @FXML
    private Button logOutBt;

    private UserViewModel user;
    Stage stage;

    public void signOutBtOnAction(ActionEvent actionEvent) {
        changeScene();
    }

    public MenuController(Stage stage2, UserViewModel user) {
        this.stage=stage2;
        this.user=user;
    }

    public void changeScene(){
        try{
            stage.close();
            Stage stage2 = new Stage();
            URL path = getClass().getResource(Constants.View.LOGIN_VIEW);
            FXMLLoader fxmlLoader = new FXMLLoader(path);
            fxmlLoader.setController(new LoginController(stage2));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage2.setTitle(Constants.Values.Title);
            stage2.setScene(scene);
            stage2.setResizable(false);
            stage2.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void  initialize(){
        welcomeLbl.setText("Welcome, \n"+user.getEmail());
        if(user.getRole()==1) addMolBt.setVisible(true);
    }
}
