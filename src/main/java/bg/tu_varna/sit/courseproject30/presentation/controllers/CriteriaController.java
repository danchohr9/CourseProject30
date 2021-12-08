package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.ProductService;
import bg.tu_varna.sit.courseproject30.business.services.ScrapCriteriaService;

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
    private Button createBt;

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
    ObservableList<ScrapCriteriaViewModel> criteriaViewModels;

//    public CriteriaController(){
//        service = new ScrapCriteriaService();
//    }


    public void createBtOnAction(ActionEvent actionEvent) {
        int y=0,m=0,d=0;
        if(yearsTf.getLength()!=0) y = Integer.parseInt(yearsTf.getText());
        if(monthsTf.getLength()!=0) m = Integer.parseInt(monthsTf.getText());
        if(depreciationTf.getLength()!=0) d = Integer.parseInt(depreciationTf.getText());
        warningLbl.setText(service.createCriteria(y,m,d, criteriaViewModels));
        if(warningLbl.getText().equals("Scrap criteria successfully added.")) {
            ScrapCriteriaViewModel newCriteria = new ScrapCriteriaViewModel(y, m, d);
            criteriaViewModels.add(newCriteria);
        }
    }

    public  void deleteBtOnAction(ActionEvent actionEvent){
        removedCriteria = new ScrapCriteriaViewModel(criteriaTable.getSelectionModel().getSelectedItem());
        criteriaViewModels.remove(removedCriteria);
        service.removeCriteria(removedCriteria);
        criteriaTable.getItems().removeAll(removedCriteria);
    }

    public void initialize(){

        service = ScrapCriteriaService.getInstance();
        criteriaViewModels = service.getAllCriteria();
        criteriaTable.setItems(criteriaViewModels);

        yearsColumn.setCellValueFactory(cellData -> cellData.getValue().yearsProperty().asString());
        monthsColumn.setCellValueFactory(cellData -> cellData.getValue().monthsProperty().asString());
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
