package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.ClientProductService;
import bg.tu_varna.sit.courseproject30.business.services.ClientService;
import bg.tu_varna.sit.courseproject30.business.services.ProductService;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.presentation.models.ClientProductViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ClientViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ProductViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Objects;

public class ClientProductController extends Controller{

    private ClientService clientService;
    private ClientProductService clientProductService;
    private ProductService productService;
    ObservableList<ClientViewModel> clientViewModels;
    ObservableList<ClientProductViewModel> clientProductViewModels1;
    ObservableList<ProductViewModel> clientProductViewModels2;
    ClientViewModel selectedClient = new ClientViewModel();

    @FXML
    private TableView<ClientViewModel> clientsTable = new TableView<>();
    @FXML
    private TableView<ClientProductViewModel> clientProductsTable = new TableView<>();
    @FXML
    private TableView<ProductViewModel> availableProductsTable = new TableView<>();

    @FXML
    private TableColumn<ClientViewModel, Integer> idCol;
    @FXML
    private TableColumn<ClientViewModel, String> nameCol;
    @FXML
    private TableColumn<ClientViewModel, String> phoneCol;
    @FXML
    private TableColumn<ClientViewModel, String> addressCol;
    @FXML
    private TableColumn<ClientViewModel, String> egnCol;
    @FXML
    private TableColumn<ClientViewModel, String> cityCol;
    @FXML
    private TableColumn<ClientViewModel, String> registeredCol;

    @FXML
    private TableColumn<ClientProductViewModel, String> productCol;
    @FXML
    private TableColumn<ClientProductViewModel, Integer> quantityCol;
    @FXML
    private TableColumn<ClientProductViewModel, String> dateAddedCol;

    @FXML
    private TableColumn<ProductViewModel, String> productCol1;
    @FXML
    private TableColumn<ProductViewModel, Integer> quantityCol1;

    @FXML
    private Label clientProductsLbl;
    @FXML
    private TextField quantityTf;


    public void initializeClientProductTable(){

        ClientViewModel selected = clientsTable.getSelectionModel().getSelectedItem();
        if(selected!=null) {
            clientProductsLbl.setText("Products of " + selected.getName());
            clientProductService = ClientProductService.getInstance();
            clientProductViewModels1 = clientProductService.getClientProducts(selected);
            clientProductsTable.setItems(clientProductViewModels1);

            productCol.setCellValueFactory(cellData -> cellData.getValue().productProperty());
            quantityCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
            dateAddedCol.setCellValueFactory(cellData -> cellData.getValue().dateAddedProperty());
        }
    }

    public void initializeProductTable(){
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        phoneCol.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        addressCol.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        egnCol.setCellValueFactory(cellData -> cellData.getValue().egnProperty());
        cityCol.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        registeredCol.setCellValueFactory(cellData -> cellData.getValue().registerDateProperty());

        productService = ProductService.getInstance();
        clientProductViewModels2 = productService.getAvailableProducts();
        availableProductsTable.setItems(clientProductViewModels2);
    }

    public void addBtOnAction(){
        selectedClient = clientsTable.getSelectionModel().getSelectedItem();
        ProductViewModel selectedProduct;
        if(availableProductsTable.getSelectionModel().getSelectedItem() != null) {
            selectedProduct = new ProductViewModel(availableProductsTable.getSelectionModel().getSelectedItem());
            int quantity = 1;
            if (selectedClient != null && selectedProduct != null && !quantityTf.getText().equals('0')) {
                if (!Objects.equals(quantityTf.getText(), "")) quantity = Integer.parseInt(quantityTf.getText());
                clientProductService.addProduct(selectedClient, selectedProduct, quantity);

                initializeClientProductTable();
                initializeProductTable();
            }
        }
    }

    public void removeBtOnAction(){
        if(clientsTable.getSelectionModel().getSelectedItem() != null) {
            selectedClient = new ClientViewModel(clientsTable.getSelectionModel().getSelectedItem());
            if(clientProductsTable.getSelectionModel().getSelectedItem() != null) {
                ClientProductViewModel selectedProduct = new ClientProductViewModel(clientProductsTable.getSelectionModel().getSelectedItem());
                int quantity = 1;
                if (selectedClient != null && selectedProduct != null && !quantityTf.getText().equals("0")) {
                    if (!Objects.equals(quantityTf.getText(), "")) quantity = Integer.parseInt(quantityTf.getText());
                    if (quantity > selectedProduct.getQuantity()) quantity = selectedProduct.getQuantity();
                    clientProductService.removeProduct(selectedProduct, quantity);

                    initializeClientProductTable();
                    initializeProductTable();
                }
            }
        }

    }

    public void queryBtOnAction(ActionEvent actionEvent){
        loadStage(Constants.View.PRODUCT_CLIENT_QUERY_VIEW, actionEvent);
    }

    public void initialize(){
        initializeProductTable();
        clientService = ClientService.getInstance();
        clientViewModels = clientService.getAllClients();
        clientsTable.setItems(clientViewModels);

        productCol1.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        quantityCol1.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        clientsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                initializeClientProductTable();
            }
        });

        quantityTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    quantityTf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

}
