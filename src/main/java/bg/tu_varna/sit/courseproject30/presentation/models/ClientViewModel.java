package bg.tu_varna.sit.courseproject30.presentation.models;

import javafx.beans.property.*;

import java.util.Date;

public class ClientViewModel {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty phone;
    private StringProperty address;
    private StringProperty egn;
    private StringProperty register_date;
    private StringProperty city;

    public ClientViewModel(int id, String name, String phone, String address,
                           String egn, String register_date, String city) {
        this.setId(id);
        this.setName(name);
        this.setPhone(phone);
        this.setAddress(address);
        this.setEgn(egn);
        this.setRegister_date(register_date);
        this.setCity(city);
    }

    public ClientViewModel(){}

    public ClientViewModel(ClientViewModel client){
        id = client.idProperty();
        name = client.nameProperty();
        phone = client.phoneProperty();
        address = client.addressProperty();
        egn = client.egnProperty();
        register_date = client.registerDateProperty();
        city = client.cityProperty();
    }

    public IntegerProperty idProperty(){return id;}
    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty phoneProperty() {
        return phone;
    }
    public StringProperty addressProperty() {
        return address;
    }
    public StringProperty egnProperty() {
        return egn;
    }
    public StringProperty registerDateProperty() {
        return register_date;
    }
    public StringProperty cityProperty() {
        return city;
    }

    public int getId() {
        return id.get();
    }
    public String getName() {
        return name.get();
    }
    public String getPhone() {
        return phone.get();
    }
    public String getAddress() {
        return address.get();
    }
    public String getEgn() {
        return egn.get();
    }
    public String getRegister_date() {
        return register_date.get();
    }
    public String getCity() {
        return city.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty();
        this.id.set(id);
    }
    public void setName(String name) {
        this.name = new SimpleStringProperty();
        this.name.set(name);
    }
    public void setPhone(String phone) {
        this.phone = new SimpleStringProperty();
        this.phone.set(phone);
    }
    public void setAddress(String address) {
        this.address = new SimpleStringProperty();
        this.address.set(address);
    }
    public void setEgn(String egn) {
        this.egn = new SimpleStringProperty();
        this.egn.set(egn);
    }
    public void setRegister_date(String register_date) {
        this.register_date = new SimpleStringProperty();
        this.register_date.set(register_date);
    }
    public void setCity(String city) {
        this.city = new SimpleStringProperty();
        this.city.set(city);
    }
}
