package bg.tu_varna.sit.courseproject30.presentation.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClientProductViewModel {

    IntegerProperty id;
    StringProperty product;
    IntegerProperty quantity;
    StringProperty dateAdded;
    StringProperty dateRemoved;

    public ClientProductViewModel() {

    }

    public int getId() {
        return id.get();
    }
    public IntegerProperty idProperty() {
        return id;
    }
    public String getProduct() {
        return product.get();
    }
    public StringProperty productProperty() {
        return product;
    }
    public int getQuantity() {
        return quantity.get();
    }
    public IntegerProperty quantityProperty() {
        return quantity;
    }
    public String getDateAdded() {
        return dateAdded.get();
    }
    public StringProperty dateAddedProperty() {
        return dateAdded;
    }
    public String getDateRemoved() {
        return dateRemoved.get();
    }
    public StringProperty dateRemovedProperty() {
        return dateRemoved;
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty();
        this.id.set(id);
    }

    public void setProduct(String product) {
        this.product = new SimpleStringProperty();
        this.product.set(product);
    }

    public void setQuantity(int quantity) {
        this.quantity = new SimpleIntegerProperty();
        this.quantity.set(quantity);
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = new SimpleStringProperty();
        this.dateAdded.set(dateAdded);
    }

    public void setDateRemoved(String dateRemoved) {
        this.dateRemoved = new SimpleStringProperty();
        this.dateRemoved.set(dateRemoved);
    }

    public ClientProductViewModel(int id, String product, int quantity, String dateAdded, String dateRemoved) {
        this.setId(id);
        this.setProduct(product);
        this.setQuantity(quantity);
        this.setDateAdded(dateAdded);
        this.setDateRemoved(dateRemoved);
    }

    public ClientProductViewModel(ClientProductViewModel cp){
        id = cp.idProperty();
        product = cp.productProperty();
        quantity = cp.quantityProperty();
        dateAdded = cp.dateAddedProperty();
        dateRemoved = cp.dateRemovedProperty();
    }

}
