package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.CategoryService;
import bg.tu_varna.sit.courseproject30.business.services.ProductService;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.presentation.models.CategoryViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ProductViewModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class UpdateProductController extends AddProductController{
    public BorderPane borderPane;
    public Button editBtn;

    private ProductViewModel product;

    private final ProductService service = new ProductService();
    private final CategoryService categoryService  = new CategoryService();

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if(!this.validator()){
            return ;
        }
        if (mouseEvent.getSource() == editBtn) {
            Product editedProduct = getProductFromData();
            editedProduct.setId(product.getId());
            service.update(editedProduct);
            loadStage(Constants.View.PRODUCTS_VIEW,mouseEvent);
        }
    }
    public void  initializeProductData() {
        nameT.textProperty().setValue(product.getName());
        descriptionT.textProperty().setValue(product.getDescription());
        fullDescriptionTf.textProperty().setValue(product.getFull_description());
        quantityTf.textProperty().setValue(String.valueOf(product.getQuantity()));
        ageTf.textProperty().setValue(String.valueOf(product.getAge()));
        priceTf.textProperty().setValue(String.valueOf(product.getPrice()));
        rateTf.textProperty().setValue(String.valueOf(product.getRate_of_depreciation()));
        growthTf.textProperty().setValue(String.valueOf(product.getDepreciationGrowth()));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        registrationDateDp.setValue(LocalDate.parse(product.getDate_of_registration(),dateTimeFormatter));
        if(product.getDate_of_transformation() != null){
            transformationDateDp.setValue(LocalDate.parse(product.getDate_of_transformation(),dateTimeFormatter));
        }
        if(product.getType() == 1){
            typeCb.valueProperty().setValue("DMA");
        } else {
            typeCb.valueProperty().setValue("MA");
        }
    }


    public void  initialize(){
        borderPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if (oldScene == null && newScene != null) {
                // scene is set for the first time. Now its the time to listen stage changes.
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
                    if (oldWindow == null && newWindow != null) {
                        // stage is set. now is the right time to do whatever we need to the stage in the controller.
                        System.out.println("Loaded data:");
                        Object obj =((Stage) newWindow).getUserData();
                        System.out.println(obj);
                        this.product = ProductViewModel.class.cast(obj);
                        this.initializeProductData();
                    }
                });
            }
        });
        alertPane.setVisible(false);
        categoryService.getAllCategories();
        typeCb.getItems().addAll("DMA","MA");
        categoryCb.setItems(categoryService.getAllCategories());
    }
}
