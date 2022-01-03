package bg.tu_varna.sit.courseproject30.presentation.controllers;


import bg.tu_varna.sit.courseproject30.business.services.ScrapService;
import bg.tu_varna.sit.courseproject30.business.services.ToScrapSevice;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.presentation.models.ScrapViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ToScrapViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class ScrapController extends Controller{
    private ToScrapSevice toScrapSevice;
    private ScrapService scrapService;
    ObservableList<ToScrapViewModel> toScrapViewModels;
    ObservableList<ScrapViewModel> scrapViewModels;
    ToScrapViewModel toScrap;
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    public Label alerBoxLabel;
    public Pane alerBoxPane;

    @FXML
    private TableView<ToScrapViewModel> productsTable = new TableView<>();

    @FXML
    private TableColumn<ToScrapViewModel, String> productCol;
    @FXML
    private TableColumn<ToScrapViewModel, String> clientCol;
    @FXML
    private TableColumn<ToScrapViewModel, Integer> quantityCol;

    @FXML
    private TableView<ScrapViewModel> scrappedTable = new TableView<>();
    @FXML
    private TableColumn<ScrapViewModel, String> productCol1;
    @FXML
    private TableColumn<ScrapViewModel, String> dateScrapped;
    @FXML
    private TableColumn<ScrapViewModel, Integer> quantityCol1;


    public  void scrapBtOnAction(){
        alerBoxPane.getStyleClass().removeAll("alert-danger");
        alerBoxLabel.setText("");
        if(productsTable.getSelectionModel().getSelectedItem()==null) {
            alerBoxPane.getStyleClass().add("alert-danger");
            alerBoxLabel.setText("Please, select a product!");
        }else{
            alert.setContentText("Are you sure you want to proceed? There is no turning back once you scrap it.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    toScrap = new ToScrapViewModel(productsTable.getSelectionModel().getSelectedItem());
                    scrapService.ScrapProduct(toScrap, user);
                    initializeScrapped();
                    initializeProducts();
                }
            });
        }
    }

    public void queryBtOnAction(ActionEvent actionEvent){
        loadStage(Constants.View.SCRAP_QUERY_VIEW, actionEvent);
    }

    public void initializeScrapped(){
        //tuk trqbva da se redaktira da ne se pokazvat vsichki brakuvani produkti, a samo brakuvanite kum tozi moment (ili za denq?),
        //che inache stava kato nqkakva dulga spravka i e neudachno
        scrapService = ScrapService.getInstance();
        scrapViewModels = scrapService.getProducts();
        scrappedTable.setItems(scrapViewModels);
        productCol1.setCellValueFactory(cellData -> cellData.getValue().productProperty());
        quantityCol1.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        dateScrapped.setCellValueFactory(cellData -> cellData.getValue().scrapDateProperty());
    }

    public void initializeProducts(){
        toScrapSevice = ToScrapSevice.getInstance();
        toScrapViewModels = toScrapSevice.getProducts();
        toScrapViewModels.addAll(toScrapSevice.getProductsFromClients());
        productsTable.setItems(toScrapViewModels);
        productCol.setCellValueFactory(cellData -> cellData.getValue().productProperty());
        quantityCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        clientCol.setCellValueFactory(cellData -> cellData.getValue().clientProperty());
    }

    public void initialize(){
        initializeProducts();
        initializeScrapped();
    }


}
