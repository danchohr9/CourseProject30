package bg.tu_varna.sit.courseproject30.presentation.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NotificationViewModel {
    private IntegerProperty id;
    private StringProperty title;
    private StringProperty message;

    public NotificationViewModel(int id, String title, String message) {
        this.setId(id);
        this.setTitle(title);
        this.setMessage(message);
    }

    public NotificationViewModel(NotificationViewModel nvm){
        id = nvm.id;
        title = nvm.title;
        message = nvm.message;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty();
        this.id.set(id);
    }

    public void setTitle(String title) {
        this.title = new SimpleStringProperty();
        this.title.set(title);
    }

    public void setMessage(String message) {
        this.message = new SimpleStringProperty();
        this.message.set(message);
    }

    @Override
    public String toString() {
        return this.getTitle() + "\n" + this.getMessage();
    }

}

