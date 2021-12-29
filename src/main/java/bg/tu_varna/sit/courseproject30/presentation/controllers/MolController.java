package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.UserService;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MolController extends Controller{

    public Button saveBt;
    public Button createBt;
    public Button editBt;
    public Button deleteBt;
    public TableView<UserViewModel> MolTable;
    public TableColumn<UserViewModel, String> usernameTc;
    public TableColumn<UserViewModel, String> emailTc;
    public Label operationLabel;
    @FXML
    private Label registerLbl;

    @FXML
    private Label warningLbl;

    @FXML
    private TextField usernameTf;

    @FXML
    private TextField passwordTf;

    @FXML
    private TextField emailTf;

    @FXML
    private Button registerBt;

    private UserService service;

    public MolController() {
        service = new UserService();
    }
    ObservableList<UserViewModel> userViewModels;

    private UserViewModel selectedUser = null;

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        warningLbl.setText("");
        if (mouseEvent.getSource() == saveBt && selectedUser == null) {
            warningLbl.setText(service.registerUser(usernameTf.getText(),passwordTf.getText(),emailTf.getText()));
        }
        if (mouseEvent.getSource() == saveBt && selectedUser != null) {
            User editedUser = new User();
            editedUser.setEmail(emailTf.getText());
            editedUser.setUsername(usernameTf.getText());
            editedUser.setPassword(passwordTf.getText());
            editedUser.setId((long) selectedUser.getId());
            warningLbl.setText(service.update(editedUser));
        }
        if (mouseEvent.getSource() == createBt) {
            operationLabel.setText("Добавяне на МОЛ:");
            selectedUser = null;
            usernameTf.setText("");
            passwordTf.setText("");
            emailTf.setText("");
            return;
        }
        if (mouseEvent.getSource() == editBt && MolTable.getSelectionModel().getSelectedItem() != null ) {
            operationLabel.setText("Редактиране на потребител:");
            selectedUser = MolTable.getSelectionModel().getSelectedItem();
            usernameTf.setText(selectedUser.getUsername());
            emailTf.setText(selectedUser.getEmail());
            return;
        }
        if (mouseEvent.getSource() == deleteBt && MolTable.getSelectionModel().getSelectedItem() != null ) {
            service.delete(MolTable.getSelectionModel().getSelectedItem());
        }
        initialize();
    }
    public void registerBtOnAction(ActionEvent actionEvent){
        warningLbl.setText(service.registerUser(usernameTf.getText(),passwordTf.getText(),emailTf.getText()));
//        if(warningLbl.getText().equals("User successfully registered.")) warningLbl.setTextFill(Color.GREEN);       //pravi i po-natatuchnite suobshteniq zeleni
    }

    public void  initialize(){
        userViewModels = service.getAll();
        MolTable.setItems(userViewModels);
        usernameTc.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        emailTc.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
    }
}
