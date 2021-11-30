package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.URL;
import org.kordamp.bootstrapfx.BootstrapFX;
public class MenuController extends Controller{


    @FXML
    private Label welcomeLbl;

    @FXML
    private Button addMolBt;

    @FXML
    private Button homeBt;

    @FXML
    private Button addProductBt;

    @FXML
    private Button logOutBt;

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == logOutBt) {
            loadStage(Constants.View.LOGIN_VIEW,mouseEvent);
        } else if(mouseEvent.getSource() == addProductBt){
            loadStage(Constants.View.PRODUCTS_VIEW,mouseEvent);
        }else if(mouseEvent.getSource() == homeBt){
            loadStage(Constants.View.MENU_VIEW,mouseEvent);
        }
    }
    public void  initialize(){
        welcomeLbl.setText("Welcome, "+user.getEmail());
        if(user.getRole()==1) addMolBt.setVisible(true);
    }
}
