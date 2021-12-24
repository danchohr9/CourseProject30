package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.ProductService;
import bg.tu_varna.sit.courseproject30.business.services.ScrapCriteriaService;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.presentation.models.ProductViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ScrapCriteriaViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class AddCritToProdController extends Controller{

    @FXML
    private TableView<ScrapCriteriaViewModel> criteriaTable = new TableView<>();
    @FXML
    private TableColumn<ScrapCriteriaViewModel, String> yearsColumn;
    @FXML
    private TableColumn<ScrapCriteriaViewModel, String> monthsColumn;
    @FXML
    private TableColumn<ScrapCriteriaViewModel, String> deprColumn;

    @FXML
    private Button addToProductBt;
    @FXML
    private TableColumn<ProductViewModel, String> dateOfRegCol;
    @FXML
    private TableColumn<ProductViewModel, String> ageCol;
    @FXML
    private TableColumn<ProductViewModel, String> priceCol;
    @FXML
    private TableColumn<ProductViewModel, String> criteriaCol;
    @FXML
    private TableView<ProductViewModel> productsTable;
    @FXML
    private TableColumn<ProductViewModel, String> nameCol;

    public Label alerBoxLabel;
    public Pane alerBoxPane;

    private ProductService productService;
    private ObservableList<ProductViewModel> productViewModels;
    private ScrapCriteriaService scrapCriteriaService;
    private ObservableList<ScrapCriteriaViewModel> criteriaViewModels;
    private Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    public void addBtOnAction(ActionEvent actionEvent){
        alerBoxPane.getStyleClass().removeAll("alert-danger");
        alerBoxLabel.setText("");
        if(productsTable.getSelectionModel().getSelectedItem()==null || criteriaTable.getSelectionModel().getSelectedItem()==null) {
            alerBoxPane.getStyleClass().add("alert-danger");
            alerBoxLabel.setText("You must select a product and a criteria.");
        }else{
            alert.setContentText("Are you sure you want to proceed? Criteria can not be changed if transformations occur.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    ProductViewModel selectedProduct = new ProductViewModel(productsTable.getSelectionModel().getSelectedItem());
                    ScrapCriteriaViewModel selectedCriteria = new ScrapCriteriaViewModel(criteriaTable.getSelectionModel().getSelectedItem());
                    productService.addCriteriaToProduct(selectedCriteria,selectedProduct);
                    initializeProductTable();
                }
            });
        }
    }

    public void initializeProductTable(){
        productService = ProductService.getInstance();
        productViewModels = productService.getDMAProducts();
        productsTable.setItems(productViewModels);

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        dateOfRegCol.setCellValueFactory(cellData -> cellData.getValue().date_of_registrationProperty());
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asString());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().currentPriceProperty().asString());
        criteriaCol.setCellValueFactory(cellData -> cellData.getValue().criteriaProperty());
    }

    public void criteriaBtOnAction(ActionEvent actionEvent){
        loadStage(Constants.View.CRITERIA_VIEW,actionEvent);
    }

    public void initialize(){
        initializeProductTable();

        scrapCriteriaService = ScrapCriteriaService.getInstance();
        criteriaViewModels = scrapCriteriaService.getAllCriteria();
        criteriaTable.setItems(criteriaViewModels);

        yearsColumn.setCellValueFactory(cellData -> cellData.getValue().yearsProperty().asString());
        monthsColumn.setCellValueFactory(cellData -> cellData.getValue().priceDropProperty().asString());
        deprColumn.setCellValueFactory(cellData -> cellData.getValue().depreciationProperty().asString());

    }

}
