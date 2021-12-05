package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.ProductService;
import bg.tu_varna.sit.courseproject30.business.services.UserService;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.repositorities.ProductRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.UserRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.ProductViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ProductController extends Controller{

    @FXML
    private Button addProductBt;

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
        if (mouseEvent.getSource() == addProductBt) {
            loadStage(Constants.View.ADD_PRODUCT_VIEW,mouseEvent);
        }
    }
    public void  initialize(){
        service = ProductService.getInstance();
        productViewModels = service.getAllProducts();
        productsTable.setItems(productViewModels);

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        descrCol.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        fullDescrCol.setCellValueFactory(cellData -> cellData.getValue().full_descriptionProperty());

        rateOfDepCol.setCellValueFactory(cellData -> cellData.getValue().rate_of_depreciationProperty().asString());
        dateOfRegCol.setCellValueFactory(cellData -> cellData.getValue().full_descriptionProperty());
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asString());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asString());
        dateOfTransCol.setCellValueFactory(cellData -> cellData.getValue().date_of_transformationProperty());

    }
}
