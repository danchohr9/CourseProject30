package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.business.services.NotificationService;
import bg.tu_varna.sit.courseproject30.presentation.models.NotificationViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class NotificationsController extends Controller{

    @FXML
    private ListView<NotificationViewModel> listView;
    @FXML
    private Button deleteBt;
    @FXML
    private Button deleteAllBt;
    @FXML
    private Label noNotificationsLbl;
    @FXML
    private Label notificationsLbl;

    private NotificationService service = NotificationService.getInstance();
    private ObservableList<NotificationViewModel> notificationViewModels;
    NotificationViewModel toRemove;

    public void deleteBtOnAction(){
        if(listView.getSelectionModel().getSelectedItem() != null) {
            toRemove = new NotificationViewModel(listView.getSelectionModel().getSelectedItem());
            notificationViewModels.remove(toRemove);
            service.deleteUserNotification(toRemove);
            listView.getItems().removeAll(toRemove);
            initializeListView();
        }
    }

    public void deleteAllBtOnAction(ActionEvent actionEvent){
        service.deleteAllNotificationsOfUser(user);
        initializeListView();
    }

    public void initializeListView(){
        notificationViewModels = service.getAllOfUser(user);
        listView.setItems(notificationViewModels);
        checkNotifications();
    }

    public void checkNotifications(){
        if(listView.getItems().isEmpty()){
            listView.setVisible(false);
            noNotificationsLbl.setVisible(true);
            notificationsLbl.setVisible(false);
            deleteBt.setVisible(false);
            deleteAllBt.setVisible(false);
        }
    }

    public void initialize(){
        initializeListView();
        noNotificationsLbl.setVisible(false);
        checkNotifications();
    }
}
