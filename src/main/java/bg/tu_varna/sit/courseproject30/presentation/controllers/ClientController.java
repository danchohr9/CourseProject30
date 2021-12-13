package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.ClientService;
import bg.tu_varna.sit.courseproject30.business.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ClientController {

    @FXML
    private Label warningLbl;

    @FXML
    private TextField nameTf;

    @FXML
    private TextField surnameTf;

    @FXML
    private TextField phoneTf;

    @FXML
    private TextField addressTf;

    @FXML
    private TextField egnTf;

    @FXML
    private TextField cityTf;

    @FXML
    private Button addBt;

    private ClientService service;

    public void addBtOnAction(ActionEvent actionEvent){
        warningLbl.setText(service.registerClient(nameTf.getText(), surnameTf.getText(), phoneTf.getText(), addressTf.getText(),
                egnTf.getText(), cityTf.getText()));

    }

    public void initialize(){
        service = ClientService.getInstance();
    }
}
