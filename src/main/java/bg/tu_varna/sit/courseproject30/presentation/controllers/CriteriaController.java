package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.ScrapCriteriaService;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CriteriaController extends Controller{
    @FXML
    private Label titleLbl;

    @FXML
    private Label createCritLbl;

    @FXML
    private Label addToProdLbl;

    @FXML
    private Label warningLbl;

    @FXML
    private TextField yearsTf;

    @FXML
    private TextField monthsTf;

    @FXML
    private TextField depreciationTf;

    @FXML
    private Button createBt;

    private ScrapCriteriaService service;

    public CriteriaController(){
        service = new ScrapCriteriaService();
    }


    public void createBtOnAction(ActionEvent actionEvent) {
        int y=0,m=0,d=0;
        if(yearsTf.getLength()!=0) y = Integer.parseInt(yearsTf.getText());
        if(monthsTf.getLength()!=0) m = Integer.parseInt(monthsTf.getText());
        if(depreciationTf.getLength()!=0) d = Integer.parseInt(depreciationTf.getText());
        warningLbl.setText(service.createCriteria(y,m,d));
    }

    public void initialize(){
        yearsTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    yearsTf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        monthsTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    monthsTf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        depreciationTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    depreciationTf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
