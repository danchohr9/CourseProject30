package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.CategoryService;
import bg.tu_varna.sit.courseproject30.business.services.ProductService;
import bg.tu_varna.sit.courseproject30.data.entities.Category;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.presentation.models.CategoryViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddProductController extends Controller{
    public TextField quantityTf;
    public TextField ageTf;
    public TextField priceTf;
    public ComboBox typeCb;
    public ComboBox categoryCb;
    public TextField fullDescriptionTf;
    public DatePicker registrationDateDp;
    public DatePicker transformationDateDp;
    public Button createBtn;
    public Pane alertPane;
    public Label alertLabel;
    public TextField descriptionT;
    public TextField nameT;
    public TextField rateTf;
    public TextField growthTf;

    private final ProductService service = new ProductService();
    private final CategoryService categoryService  = new CategoryService();

    protected Product getProductFromData(){
        String name = nameT.getText();
        String descriptionTText = descriptionT.getText();
        String fullDescriptionTfText = fullDescriptionTf.getText();
        int quantity = Integer.parseInt(quantityTf.getText());
        int age = Integer.parseInt(ageTf.getText());
        double price = Double.parseDouble(priceTf.getText());
        double rate = Double.parseDouble(rateTf.getText());
        double growth = Double.parseDouble(growthTf.getText());
        int type = 0;
        if(typeCb.getValue() == "DMA"){
            type = 1;
        }
        CategoryViewModel cat = (CategoryViewModel) categoryCb.getSelectionModel().getSelectedItem();
        Category category =  categoryService.findById(Long.parseLong(String.valueOf(cat.getId())));

        LocalDate dateOfReg = registrationDateDp.getValue();
        LocalDate dateOfTran = transformationDateDp.getValue();
        Date date_of_reg;
        Date date_of_tran = null;

        if(dateOfReg == null){
            date_of_reg = new Date();
        } else {
            Instant instant = Instant.from(dateOfReg.atStartOfDay(ZoneId.systemDefault()));
            date_of_reg = Date.from(instant);
        }
        if(dateOfTran != null){
            Instant instant2 = Instant.from(dateOfTran.atStartOfDay(ZoneId.systemDefault()));
            date_of_tran = Date.from(instant2);
        }

        return new Product(name,descriptionTText,fullDescriptionTfText,rate,date_of_reg,age,price,
                date_of_tran,type,category,quantity, growth
        );
    }
    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {

        if (mouseEvent.getSource() == createBtn) {
            if(!this.validator()){
                return;
            }
            alertPane.getStyleClass().add("alert-success");
            String result = service.createProduct(getProductFromData());
            alertLabel.setText(result);;

        }
    }

    public boolean validator(){
        //TODO:Make better validation
        alertPane.getStyleClass().add("alert-danger");
        if(nameT.getLength() == 0){
            alertLabel.setText("You must enter name");;
            alertPane.setVisible(true); return false;
        }
        if(descriptionT.getLength() == 0){
            alertLabel.setText("You must enter description");;
            alertPane.setVisible(true); return false;
        }
        if(fullDescriptionTf.getLength() == 0){
            alertLabel.setText("You must enter full description");;
            alertPane.setVisible(true); return false;
        }
        if(priceTf.getLength() == 0){
            alertLabel.setText("Invalid Price");;
            alertPane.setVisible(true); return false;
        }
        if(ageTf.getLength() == 0){
            alertLabel.setText("Invalid Age");;
            alertPane.setVisible(true); return false;
        }
        if(quantityTf.getLength() == 0){
            alertLabel.setText("Invalid quantity");;
            alertPane.setVisible(true); return false;
        }
        if(typeCb.getValue() == null){  return false; }
        if(categoryCb.getValue() == null){
            alertLabel.setText("Select Category");;
            alertPane.setVisible(true); return false;
        }
        alertPane.getStyleClass().removeAll("alert-danger");
        return true;
    }

    @FXML
    public void  initialize(){
        alertPane.setVisible(false);
        categoryService.getAllCategories();
        typeCb.getItems().addAll("DMA","MA");
        typeCb.valueProperty().setValue("DMA");
        categoryCb.setItems(categoryService.getAllCategories());
    }
}
