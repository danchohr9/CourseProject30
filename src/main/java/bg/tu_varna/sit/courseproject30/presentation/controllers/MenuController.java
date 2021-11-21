package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Label welcomeLbl;

    @FXML
    private Button addMolBt;

    @FXML
    private Button addProductBt;

    private UserViewModel user;
    Stage stage;


    public MenuController(Stage stage2, UserViewModel user) {
        this.stage=stage2;
        this.user=user;
    }

    public void  initialize(){
        welcomeLbl.setText("Welcome, \n"+user.getEmail());
        if(user.getRole()==1) addMolBt.setVisible(true);
    }
}
