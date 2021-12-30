package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.common.Constants;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MenuController extends Controller{


    @FXML
    private Label welcomeLbl;

    @FXML
    private Button addMolBt;

    @FXML
    private Button homeBt;

    @FXML
    private Button addProductBt;

    @FXML
    private Button addCriteriaBt;

    @FXML
    private Button addClientBt;

    @FXML
    private Button clientProductBt;

    @FXML
    private Button logOutBt;

    @FXML
    public Button categoriesBtn;

    @FXML
    public Button scrapBt;

    @FXML
    public Button notificationsBt;

    @FXML
    public ImageView notificationsImage;
    public Image image;


    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == logOutBt) {
            loadStage(Constants.View.LOGIN_VIEW,mouseEvent);
        } else if(mouseEvent.getSource() == addProductBt){
            loadStage(Constants.View.PRODUCTS_VIEW,mouseEvent);
        }else if(mouseEvent.getSource() == homeBt){
            loadStage(Constants.View.MENU_VIEW,mouseEvent);
        }else if(mouseEvent.getSource() == addMolBt){
            loadStage(Constants.View.MOL_VIEW,mouseEvent);
        }else if(mouseEvent.getSource() == addCriteriaBt){
            loadStage(Constants.View.ADD_CRITERIA_TO_PRODUCT,mouseEvent);
        }else if(mouseEvent.getSource() == addClientBt){
            loadStage(Constants.View.ADD_CLIENT_VIEW,mouseEvent);
        }else if(mouseEvent.getSource() == clientProductBt){
            loadStage(Constants.View.CLIENT_PRODUCT_VIEW,mouseEvent);
        }else if(mouseEvent.getSource() == categoriesBtn){
            loadStage(Constants.View.CATEGORY_VIEW,mouseEvent);
        }else if(mouseEvent.getSource() == scrapBt) {
            loadStage(Constants.View.SCRAP_VIEW, mouseEvent);
        }else if(mouseEvent.getSource() == notificationsBt) {
        loadStage(Constants.View.NOTIFICATIONS_VIEW, mouseEvent);
    }
    }

    public void  initialize(){
        welcomeLbl.setText("Welcome, "+user.getUsername());
        if(user.getRole()==1) addMolBt.setVisible(true);


        image = new Image(getClass().getResource(Constants.Images.NOTIFICATIONS_PICTURE).toExternalForm());
        notificationsImage.setImage(image);
        notificationsImage.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                notificationsBt.fire();
                event.consume();
            }
        });
    }
}
