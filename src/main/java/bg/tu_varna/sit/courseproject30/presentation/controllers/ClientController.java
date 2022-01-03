package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.ClientService;
import bg.tu_varna.sit.courseproject30.data.entities.City;
import bg.tu_varna.sit.courseproject30.data.entities.Client;
import bg.tu_varna.sit.courseproject30.presentation.models.CategoryViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ClientViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class ClientController {

    public Pane alerBoxPane;
    public Label alerBoxLabel;
    public TableView<ClientViewModel> clientsTable;
    public TableColumn<ClientViewModel, String> nameCol;
    public TableColumn<ClientViewModel, String> phoneCol;
    public TableColumn<ClientViewModel, String> addressCol;
    public TableColumn<ClientViewModel, String> egnCol;
    public TableColumn<ClientViewModel, String> cityCol;
    public Button createBtn;
    public Button editBtn;
    public Button deleteBtn;
    public Label titleLbl;
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
    ObservableList<ClientViewModel> clientViewModels;

    ClientViewModel selectedClient = null;

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        alerBoxPane.getStyleClass().removeAll("alert-warning");
        alerBoxPane.getStyleClass().removeAll("alert-info");
        alerBoxLabel.setText("");;

        if (mouseEvent.getSource() == addBt && selectedClient == null){
            alerBoxPane.getStyleClass().add("alert-warning");
            alerBoxLabel.setText(service.registerClient(nameTf.getText(), surnameTf.getText(), phoneTf.getText(), addressTf.getText(),
                    egnTf.getText(), cityTf.getText()));
        }
        if (mouseEvent.getSource() == addBt && selectedClient != null){
            Client client = new Client();
            client.setId(selectedClient.getId());
            client.setFirst_name(nameTf.getText());
            client.setLast_name(surnameTf.getText());
            client.setPhone(phoneTf.getText());
            client.setAddress(addressTf.getText());
            client.setEgn(egnTf.getText());
            client.setCity(service.checkCity(cityTf.getText()));
            service.edit(client);
            alerBoxPane.getStyleClass().add("alert-info");
            alerBoxLabel.setText("Client Edited.");
        }
        if (mouseEvent.getSource() == editBtn && clientsTable.getSelectionModel().getSelectedItem() != null){
            titleLbl.setText("Edit Client");
            selectedClient =  clientsTable.getSelectionModel().getSelectedItem();
            String[] splitStr = selectedClient.getName().split("\\s+");
            nameTf.setText(splitStr[0]);
            surnameTf.setText(splitStr[1]);
            phoneTf.setText(selectedClient.getPhone());
            addressTf.setText(selectedClient.getAddress());
            egnTf.setText(selectedClient.getEgn());
            cityTf.setText(selectedClient.getCity());
            return;
        }
        if (mouseEvent.getSource() == deleteBtn && clientsTable.getSelectionModel().getSelectedItem() != null){
            Client client = new Client();
            client.setId(clientsTable.getSelectionModel().getSelectedItem().getId());
            service.delete(client);
            alerBoxPane.getStyleClass().add("alert-info");
            alerBoxLabel.setText("Client Deleted.");
        }
        if (mouseEvent.getSource() == createBtn){
            selectedClient = null;
            titleLbl.setText("Add a new client");
            nameTf.setText("");
            surnameTf.setText("");
            phoneTf.setText("");
            addressTf.setText("");
            egnTf.setText("");
            cityTf.setText("");
            return;
        }
        initialize();
    }

    public void initialize(){
        service = ClientService.getInstance();
        clientViewModels = service.getAllClients();
        clientsTable.setItems(clientViewModels);
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        phoneCol.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        addressCol.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        egnCol.setCellValueFactory(cellData -> cellData.getValue().egnProperty());
        cityCol.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
    }
}
