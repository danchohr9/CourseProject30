package bg.tu_varna.sit.courseproject30.presentation.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ScrapViewModel {
    private IntegerProperty id;
    private StringProperty scrapDate;
    private IntegerProperty productId;
    private StringProperty product;
    private IntegerProperty quantity;

    public ScrapViewModel(int id, String scrapDate, int productId, String product, int quantity) {
        this.setId(id);
        this.setScrapDate(scrapDate);
        this.setProductId(productId);
        this.setProduct(product);
        this.setQuantity(quantity);
    }

    public ScrapViewModel(ScrapViewModel svm){
        id = svm.id;
        scrapDate = svm.scrapDate;
        productId = svm.productId;
        product = svm.product;
        quantity = svm.quantity;
    }

    public int getId() {
        return id.get();
    }
    public IntegerProperty idProperty() {
        return id;
    }
    public String getScrapDate() {
        return scrapDate.get();
    }
    public StringProperty scrapDateProperty() {
        return scrapDate;
    }
    public int getProductId() {
        return productId.get();
    }
    public IntegerProperty productIdProperty() {
        return productId;
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

    public void setId(int id) {
        this.id = new SimpleIntegerProperty();
        this.id.set(id);
    }

    public void setScrapDate(String scrapDate) {
        this.scrapDate = new SimpleStringProperty();
        this.scrapDate.set(scrapDate);
    }

    public void setProductId(int productId) {
        this.productId = new SimpleIntegerProperty();
        this.productId.set(productId);
    }

    public void setProduct(String product) {
        this.product = new SimpleStringProperty();
        this.product.set(product);
    }

    public void setQuantity(int quantity) {
        this.quantity = new SimpleIntegerProperty();
        this.quantity.set(quantity);
    }
}
