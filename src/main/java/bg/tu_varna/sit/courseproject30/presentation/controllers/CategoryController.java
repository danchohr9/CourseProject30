package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.CategoryService;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.Category;
import bg.tu_varna.sit.courseproject30.presentation.models.CategoryViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class CategoryController {

    public TableColumn<CategoryViewModel, String> nameCol;
    public TableColumn<CategoryViewModel, String> descrCol;
    public TableView<CategoryViewModel> categoryTable;
    public Button saveBt;
    public Button createBt;
    public Button editBt;
    public Button deleteBt;
    public TextField nameTf;
    public TextField descrTf;
    public CategoryViewModel editedItem = null;
    public Label actionLabel;
    public Label alertLabel;
    public Pane alertPane;

    private CategoryService service;


    ObservableList<CategoryViewModel> categoryViewModels;

    private boolean categorySelected(){
        if(categoryTable.getSelectionModel().getSelectedItem() == null){
            alertPane.getStyleClass().add("alert-danger");
            alertLabel.setText("You must select category");;
            alertPane.setVisible(true);
            return false;
        }
        return true;
    }
    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        clearAlertBox();
        if (mouseEvent.getSource() == createBt ){
            editedItem = null;
            nameTf.setText("");
            descrTf.setText("");
            actionLabel.setText("Add Category");
            return;
        }
        if (mouseEvent.getSource() == editBt ){
            if(!categorySelected()){
                return;
            }
            CategoryViewModel category = categoryTable.getSelectionModel().getSelectedItem();
            editedItem = category;
            nameTf.setText(category.getName());
            descrTf.setText(category.getDescription());
            actionLabel.setText("Edit Category");
        }
        if (mouseEvent.getSource() == deleteBt) {
            if(!categorySelected()){
                return;
            }
            service.delete(categoryTable.getSelectionModel().getSelectedItem());
            initialize();
            return;
        }

        if(!validator()){
            return;
        }

        if (mouseEvent.getSource() == saveBt && editedItem == null){
            service.createCategory(nameTf.getText(),descrTf.getText());
        }
        if (mouseEvent.getSource() == saveBt && editedItem != null){
            Category category = new Category();
            category.setId((long) editedItem.getId());
            category.setName(nameTf.getText());
            category.setDescription(descrTf.getText());
            service.edit(category);
        }
        initialize();
    }

    private boolean validator(){
        if(nameTf.getText().length() == 0){
            alertPane.getStyleClass().add("alert-danger");
            alertLabel.setText("You must enter name");;
            alertPane.setVisible(true);
            return false;
        }
        clearAlertBox();
        return true;
    }
    public void clearAlertBox(){
        alertPane.getStyleClass().removeAll("alert-danger");
        alertLabel.setText("");;
        alertPane.setVisible(false);
    }
    public void  initialize(){
        service = CategoryService.getInstance();
        categoryViewModels = service.getAllCategories();

        categoryTable.setItems(categoryViewModels);
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        descrCol.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

    }
}
