package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.ProductService;
import bg.tu_varna.sit.courseproject30.business.services.ScrapCriteriaService;
import bg.tu_varna.sit.courseproject30.presentation.models.ProductViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ScrapCriteriaViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AddCritToProdController {

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
    private TableView<ProductViewModel> productsTable;
    @FXML
    private TableColumn<ProductViewModel, String> nameCol;

    private ProductService productService;
    private ObservableList<ProductViewModel> productViewModels;
    private ScrapCriteriaService scrapCriteriaService;
    private ObservableList<ScrapCriteriaViewModel> criteriaViewModels;

    public void addBtOnAction(ActionEvent actionEvent){

    }

    public void initialize(){
        productService = ProductService.getInstance();
        productViewModels = productService.getAllProducts();
        productsTable.setItems(productViewModels);

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        dateOfRegCol.setCellValueFactory(cellData -> cellData.getValue().date_of_registrationProperty());
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asString());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString());

        scrapCriteriaService = ScrapCriteriaService.getInstance();
        criteriaViewModels = scrapCriteriaService.getAllCriteria();
        criteriaTable.setItems(criteriaViewModels);

        yearsColumn.setCellValueFactory(cellData -> cellData.getValue().yearsProperty().asString());
        monthsColumn.setCellValueFactory(cellData -> cellData.getValue().monthsProperty().asString());
        deprColumn.setCellValueFactory(cellData -> cellData.getValue().depreciationProperty().asString());

    }

}
