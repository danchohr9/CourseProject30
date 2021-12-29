package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.ProductService;
import javafx.scene.control.Label;

public class MainController extends Controller {
    public Label productsNumber;

    private ProductService service =  ProductService.getInstance();
    public void  initialize(){
        Integer total = service.getTotalProducts();
        productsNumber.setText(total.toString());
    }
}
