package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.ProductService;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.presentation.models.ProductViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ProductController extends Controller{

    public Button editProductBt;
    public Button deleteProductBt;
    public Label alerBoxLabel;
    public Pane alerBoxPane;
    public Button searchBt;
    public DatePicker dateToDp;
    public DatePicker dateFromDp;
    public TextField searchNameTf;
    @FXML
    private Button addProductBt;
    @FXML
    public TableColumn<ProductViewModel, String> typeCol;
    @FXML
    private TableView<ProductViewModel> productsTable;
    @FXML
    private TableColumn<ProductViewModel, String> nameCol;
    @FXML
    private TableColumn<ProductViewModel, String> descrCol;
    @FXML
    private TableColumn<ProductViewModel, String> fullDescrCol;

    @FXML
    private TableColumn<ProductViewModel, String> rateOfDepCol;
    @FXML
    private TableColumn<ProductViewModel, String> dateOfRegCol;
    @FXML
    private TableColumn<ProductViewModel, String> ageCol;
    @FXML
    private TableColumn<ProductViewModel, String> priceCol;
    @FXML
    private TableColumn<ProductViewModel, String> dateOfTransCol;

    private ProductService service;

    ObservableList<ProductViewModel> productViewModels;


    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        alerBoxPane.getStyleClass().removeAll("alert-danger");
        alerBoxLabel.setText("");
        if (mouseEvent.getSource() == addProductBt) {
            loadStage(Constants.View.ADD_PRODUCT_VIEW,mouseEvent);
        }
        if (mouseEvent.getSource() == editProductBt && productsTable.getSelectionModel().getSelectedItem() != null) {
            this.userData = productsTable.getSelectionModel().getSelectedItem();
            loadStage(Constants.View.EDIT_PRODUCT_VIEW,mouseEvent);
        }
        if (mouseEvent.getSource() == deleteProductBt && productsTable.getSelectionModel().getSelectedItem() != null) {
            service.delete(productsTable.getSelectionModel().getSelectedItem());
            initialize();
        }
        if (mouseEvent.getSource() == searchBt) {
            Date dateT = null;
            Date dateFrm = null;

            if(dateToDp.getValue() != null){
                LocalDate dateTo = dateToDp.getValue();
                Instant instant = Instant.from(dateTo.atStartOfDay(ZoneId.systemDefault()));
                dateT = Date.from(instant);
            }

            if(dateFromDp.getValue() != null){
                LocalDate dateFrom = dateFromDp.getValue();
                Instant instant2 = Instant.from(dateFrom.atStartOfDay(ZoneId.systemDefault()));
                dateFrm = Date.from(instant2);
            }
            productsTable.setItems(service.searchProducts(searchNameTf.getText(),dateFrm,dateT));
            return;
        }
        alerBoxPane.getStyleClass().add("alert-danger");
        alerBoxLabel.setText("You must select a product.");
    }
    public void  initialize(){
        service = ProductService.getInstance();
        productViewModels = service.getAllProducts();
        productsTable.setItems(productViewModels);

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        descrCol.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        fullDescrCol.setCellValueFactory(cellData -> cellData.getValue().full_descriptionProperty());
        rateOfDepCol.setCellValueFactory(cellData -> cellData.getValue().rate_of_depreciationProperty().asString());
        dateOfRegCol.setCellValueFactory(cellData -> cellData.getValue().date_of_registrationProperty());
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asString());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().currentPriceProperty().asString());
        dateOfTransCol.setCellValueFactory(cellData -> cellData.getValue().date_of_transformationProperty());
        typeCol.setCellValueFactory(cellData -> cellData.getValue().getTypeName());
    }
}
