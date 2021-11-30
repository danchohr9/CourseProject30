package bg.tu_varna.sit.courseproject30.application;

import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.presentation.controllers.LoginController;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    private static UserViewModel CurrentUser;
    private static final Logger log = Logger.getLogger(HelloApplication.class);

    @Override
    public void start(Stage stage) throws IOException {

        PropertyConfigurator.configure(HelloApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));

        Parent root = FXMLLoader.load(getClass().getResource(Constants.View.LOGIN_VIEW));
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
/*
        URL path = getClass().getResource(Constants.View.LOGIN_VIEW);

        if(path != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(path);
            fxmlLoader.setController(new LoginController(stage));
            Parent root = fxmlLoader.load(); // = FXMLLoader.load(path);

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            stage.setTitle(Constants.Values.Title);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            log.error("Sorry, the main fxm could not be loaded.");
            System.exit(-1);
        }
*/
    }
    public static UserViewModel getUser() {
        return CurrentUser;
    }
    public static void setUser(UserViewModel user) {
        CurrentUser = user;
    }
    public static void main(String[] args) {
        launch();
    }
}