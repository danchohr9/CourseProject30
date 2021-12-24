package bg.tu_varna.sit.courseproject30.presentation.models;

import javafx.beans.property.*;

public class ToScrapViewModel {
    private IntegerProperty idProduct;
    private StringProperty product;
    private IntegerProperty quantity;
    private StringProperty idProductClient; //String, zashtoto criteria_id moje da e null ("") i ne e udachno da pishe 0
    private StringProperty client;

    public ToScrapViewModel(int idProduct, String product, int quantity, String idProductClient, String client) {
        this.setIdProduct(idProduct);
        this.setProduct(product);
        this.setQuantity(quantity);
        this.setIdProductClient(idProductClient);
        this.setClient(client);
    }

    public ToScrapViewModel(ToScrapViewModel tsvm){
        idProduct = tsvm.idProduct;
        product = tsvm.product;
        quantity = tsvm.quantity;
        idProductClient = tsvm.idProductClient;
        client = tsvm.client;
    }

    public int getIdProduct() {
        return idProduct.get();
    }
    public IntegerProperty idProductProperty() {
        return idProduct;
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
    public String getIdProductClient() {
        return idProductClient.get();
    }
    public StringProperty idProductClientProperty() {
        return idProductClient;
    }
    public String getClient() {
        return client.get();
    }
    public StringProperty clientProperty() {
        return client;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = new SimpleIntegerProperty();
        this.idProduct.set(idProduct);
    }

    public void setProduct(String product) {
        this.product = new SimpleStringProperty();
        this.product.set(product);
    }

    public void setQuantity(int quantity) {
        this.quantity = new SimpleIntegerProperty();
        this.quantity.set(quantity);
    }

    public void setIdProductClient(String idProductClient) {
        this.idProductClient = new SimpleStringProperty();
        this.idProductClient.set(idProductClient);
    }

    public void setClient(String client) {
        this.client = new SimpleStringProperty();
        this.client.set(client);
    }
}
