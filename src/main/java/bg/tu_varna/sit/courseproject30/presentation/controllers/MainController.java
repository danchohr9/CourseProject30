package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.ClientService;
import bg.tu_varna.sit.courseproject30.business.services.ProductService;
import bg.tu_varna.sit.courseproject30.business.services.UserService;
import javafx.scene.control.Label;

public class MainController extends Controller {
    public Label productsNumber;
    public Label UsersNumber;
    public Label ClientsNumber;

    private final ProductService service =  ProductService.getInstance();
    private final UserService UserService =  bg.tu_varna.sit.courseproject30.business.services.UserService.getInstance();
    private final ClientService ClientsService =  ClientService.getInstance();

    public void  initialize(){
        int totalProducts = service.getTotalProducts();
        int totalUsers = UserService.getTotalUsers();
        int totalClients = ClientsService.getTotalClients();

        productsNumber.setText(Integer.toString(totalProducts));
        UsersNumber.setText(Integer.toString(totalUsers));
        ClientsNumber.setText(Integer.toString(totalClients));
    }
}