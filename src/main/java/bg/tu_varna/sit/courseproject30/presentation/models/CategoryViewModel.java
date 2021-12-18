package bg.tu_varna.sit.courseproject30.presentation.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CategoryViewModel {
    private IntegerProperty id;
    StringProperty name;
    StringProperty description;

    public CategoryViewModel(int id,String name, String description){
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty();
        this.name.set(name);
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty();
        this.description.set(description);
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty();
        this.id.set(id);
    }

    public int getId() {
        return id.get();
    }
    public String getName() {
        return name.get();
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    @Override
    public String toString() {
        return getName();
    }
}
