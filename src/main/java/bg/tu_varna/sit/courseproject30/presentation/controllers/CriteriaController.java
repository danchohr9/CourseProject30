package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.ProductService;
import bg.tu_varna.sit.courseproject30.business.services.ScrapCriteriaService;

import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.ScrapCriteria;
import bg.tu_varna.sit.courseproject30.presentation.models.ProductViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ScrapCriteriaViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CriteriaController extends Controller{

    @FXML
    private Label titleLbl;

    @FXML
    private Label createCritLbl;

    @FXML
    private Label deleteCriteriaLbl;

    @FXML
    private Label warningLbl;

    @FXML
    private TextField yearsTf;

    @FXML
    private TextField monthsTf;

    @FXML
    private TextField depreciationTf;



    @FXML
    private TableView<ScrapCriteriaViewModel> criteriaTable = new TableView<>();
    @FXML
    private TableColumn<ScrapCriteriaViewModel, String> yearsColumn;
    @FXML
    private TableColumn<ScrapCriteriaViewModel, String> monthsColumn;
    @FXML
    private TableColumn<ScrapCriteriaViewModel, String> deprColumn;


    private ScrapCriteriaService service;
    ScrapCriteriaViewModel removedCriteria;
    ScrapCriteriaViewModel editedCriteria;

    ObservableList<ScrapCriteriaViewModel> criteriaViewModels;

//    public CriteriaController(){
//        service = new ScrapCriteriaService();
//    }


    public void saveBtOnAction(ActionEvent actionEvent) {

        if(editedCriteria != null){
            int y=0;
            double m=0,d=0;
            if(yearsTf.getLength()!=0) y = Integer.parseInt(yearsTf.getText());
            if(monthsTf.getLength()!=0) m = Double.parseDouble(monthsTf.getText());
            if(depreciationTf.getLength()!=0) d = Double.parseDouble(depreciationTf.getText());
            service.updateCriteria(new ScrapCriteria((long) editedCriteria.getId(),y,m,d));
            criteriaViewModels = service.getAllCriteria();
            criteriaTable.getItems().clear();
            criteriaTable.setItems(criteriaViewModels);
            return;
        }

        int y=0;
        double m=0,d=0;
        if(yearsTf.getLength()!=0) y = Integer.parseInt(yearsTf.getText());
        if(monthsTf.getLength()!=0) m = Double.parseDouble(monthsTf.getText());
        if(depreciationTf.getLength()!=0) d = Double.parseDouble(depreciationTf.getText());
        warningLbl.setText(service.createCriteria(y,m,d, criteriaViewModels));
        if(warningLbl.getText().equals("Scrap criteria successfully added.")) {
            ScrapCriteriaViewModel newCriteria = new ScrapCriteriaViewModel(0,y, m, d);
            criteriaViewModels.add(newCriteria);
        }
    }

    public void deleteBtOnAction(ActionEvent actionEvent){
        removedCriteria = new ScrapCriteriaViewModel(criteriaTable.getSelectionModel().getSelectedItem());
        criteriaViewModels.remove(removedCriteria);
        service.removeCriteria(removedCriteria);
        criteriaTable.getItems().removeAll(removedCriteria);
    }
    public void createBtOnAction(ActionEvent actionEvent){
        createCritLbl.setText("Creatе a new criteria");
        editedCriteria = null;
        yearsTf.setText("");
        monthsTf.setText("");
        depreciationTf.setText("");
    }
    public void editBtOnAction(ActionEvent actionEvent){

        editedCriteria = new ScrapCriteriaViewModel(criteriaTable.getSelectionModel().getSelectedItem());
        createCritLbl.setText("Edit Criteria");
        yearsTf.textProperty().setValue(String.valueOf(editedCriteria.getYears()));
        monthsTf.setText(String.valueOf(editedCriteria.getPriceDrop()));
        depreciationTf.setText(String.valueOf(editedCriteria.getDepreciation()));
    }
    public void initialize(){

        service = ScrapCriteriaService.getInstance();
        criteriaViewModels = service.getAllCriteria();
        criteriaTable.setItems(criteriaViewModels);

        yearsColumn.setCellValueFactory(cellData -> cellData.getValue().yearsProperty().asString());
        monthsColumn.setCellValueFactory(cellData -> cellData.getValue().priceDropProperty().asString());
        deprColumn.setCellValueFactory(cellData -> cellData.getValue().depreciationProperty().asString());

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
                if (!newValue.matches(".*\\\\d.*")) {
                    monthsTf.setText(newValue.replaceAll("[^\\d.]", ""));
                }
            }
        });

        depreciationTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {

                if (!newValue.matches(".*\\\\d.*")) {
                    depreciationTf.setText(newValue.replaceAll("[^\\d.]", ""));
                }
            }
        });
    }
}
