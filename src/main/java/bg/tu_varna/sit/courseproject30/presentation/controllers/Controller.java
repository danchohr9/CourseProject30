package bg.tu_varna.sit.courseproject30.presentation.controllers;

import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class Controller {
    protected UserViewModel user;
    Controller(){
        user = HelloApplication.getUser();
    }
    protected void loadStage(String fxml, ActionEvent event) {
        try {
            Stage oldStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            oldStage.close();
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();

            Scene scene = new Scene(root);
            stage.setTitle("Dashboard");
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
