package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.ClientProductService;
import bg.tu_varna.sit.courseproject30.business.services.ClientService;
import bg.tu_varna.sit.courseproject30.business.services.ProductService;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.presentation.models.ClientProductViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ClientViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ProductViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ClientProductQueryController {

    public Button searchBt;
    public DatePicker dateToDp;
    public DatePicker dateFromDp;
    public TextField searchNameTf;
    public Button searchBt1;
    public DatePicker dateToDp1;
    public DatePicker dateFromDp1;
    public TextField searchNameTf1;

    private ClientService clientService = ClientService.getInstance();
    private ClientProductService clientProductService = ClientProductService.getInstance();
    ClientViewModel selectedClient = new ClientViewModel();
    ObservableList<ClientViewModel> clientViewModels;
    ObservableList<ClientProductViewModel> clientProductViewModels;

    @FXML
    private TableView<ClientViewModel> clientsTable = new TableView<>();
    @FXML
    private TableView<ClientProductViewModel> clientProductsTable = new TableView<>();

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


    public void initializeClientProductTable(){

        selectedClient = clientsTable.getSelectionModel().getSelectedItem();
        if(selectedClient!=null) {
            clientProductViewModels = clientProductService.getClientProducts(selectedClient);
            clientProductsTable.setItems(clientProductViewModels);

            productCol.setCellValueFactory(cellData -> cellData.getValue().productProperty());
            quantityCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
            dateAddedCol.setCellValueFactory(cellData -> cellData.getValue().dateAddedProperty());
        }
    }

    public void searchBtOnAction(ActionEvent actionEvent){
        //search clients
        if (actionEvent.getSource() == searchBt) {
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
            clientsTable.setItems(clientService.searchClients(searchNameTf.getText(),dateFrm,dateT));
        }
        //search products of client
        else if(actionEvent.getSource() == searchBt1){
            selectedClient = clientsTable.getSelectionModel().getSelectedItem();
            if(selectedClient!=null) {
                Date dateT = null;
                Date dateFrm = null;

                if(dateToDp1.getValue() != null){
                    LocalDate dateTo = dateToDp1.getValue();
                    Instant instant = Instant.from(dateTo.atStartOfDay(ZoneId.systemDefault()));
                    dateT = Date.from(instant);
                }

                if(dateFromDp1.getValue() != null){
                    LocalDate dateFrom = dateFromDp1.getValue();
                    Instant instant2 = Instant.from(dateFrom.atStartOfDay(ZoneId.systemDefault()));
                    dateFrm = Date.from(instant2);
                }
                clientProductsTable.setItems(clientProductService.searchProducts(searchNameTf1.getText(),dateFrm,dateT,selectedClient));
            }
        }

    }

    public void initialize(){
        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        phoneCol.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        addressCol.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        egnCol.setCellValueFactory(cellData -> cellData.getValue().egnProperty());
        cityCol.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        registeredCol.setCellValueFactory(cellData -> cellData.getValue().registerDateProperty());

        clientViewModels = clientService.getAllClients();
        clientsTable.setItems(clientViewModels);


        clientsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                initializeClientProductTable();
            }
        });
    }
}
