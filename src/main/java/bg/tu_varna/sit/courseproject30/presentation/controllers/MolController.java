package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MolController extends Controller{

    @FXML
    private Label registerLbl;

    @FXML
    private Label warningLbl;

    @FXML
    private TextField usernameTf;

    @FXML
    private TextField passwordTf;

    @FXML
    private TextField emailTf;

    @FXML
    private Button registerBt;

    private UserService service;

    public MolController() {
        service = new UserService();
    }

    public void registerBtOnAction(ActionEvent actionEvent){
        warningLbl.setText(service.registerUser(usernameTf.getText(),passwordTf.getText(),emailTf.getText()));
    }

    public void  initialize(){
    }
}
