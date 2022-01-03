package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.ScrapService;
import bg.tu_varna.sit.courseproject30.presentation.models.ScrapViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ScrapQueryController{

    public Button searchBt;
    public DatePicker dateToDp;
    public DatePicker dateFromDp;
    public TextField searchNameTf;

    ObservableList<ScrapViewModel> scrapViewModels;
    private ScrapService scrapService;

    @FXML
    private TableView<ScrapViewModel> scrappedTable = new TableView<>();
    @FXML
    private TableColumn<ScrapViewModel, String> productCol1;
    @FXML
    private TableColumn<ScrapViewModel, String> dateScrapped;
    @FXML
    private TableColumn<ScrapViewModel, Integer> quantityCol1;

    public void searchBtOnAction(){
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
        scrappedTable.setItems(scrapService.searchScraps(searchNameTf.getText(),dateFrm,dateT));
    }

    public void  initialize(){
        scrapService = ScrapService.getInstance();
        scrapViewModels = scrapService.getProducts();
        scrappedTable.setItems(scrapViewModels);
        productCol1.setCellValueFactory(cellData -> cellData.getValue().productProperty());
        quantityCol1.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        dateScrapped.setCellValueFactory(cellData -> cellData.getValue().scrapDateProperty());
    }
}
