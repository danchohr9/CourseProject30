package bg.tu_varna.sit.courseproject30.presentation.models;

import javafx.beans.property.*;

public class ProductViewModel {
    private StringProperty name;
    private StringProperty description;
    private StringProperty full_description;

    private DoubleProperty rate_of_depreciation;
    private StringProperty date_of_registration;
    private StringProperty date_of_transformation;
    private DoubleProperty price;
    private IntegerProperty age;

    private IntegerProperty type;
    private IntegerProperty category_id;
    private IntegerProperty quantity;


    public ProductViewModel(
            String  name, String description, String full_description, int type, int category_id,int quantity,
            Double rate,String date_of_registration, String date_of_transformation, Double price, int age
                            ) {
        this.setName(name);
        this.setDescription(description);
        this.setFull_description(full_description);

        this.setRate_of_depreciation(rate);
        this.setDate_of_registration(date_of_registration);
        this.setPrice(price);
        this.setDate_of_transformation(date_of_transformation);
        this.setAge(age);

        this.setQuantity(quantity);
        this.setType(type);
        this.setCategory_id(category_id);
    }

    public DoubleProperty rate_of_depreciationProperty() {
        return rate_of_depreciation;
    }
    public DoubleProperty priceProperty() {
        return price;
    }
    public StringProperty date_of_transformationProperty() {
            return date_of_transformation;
    }
    public IntegerProperty ageProperty() {
        return age;
    }
    public IntegerProperty category_idProperty() {
        return category_id;
    }
    public IntegerProperty quantityProperty() {
        return quantity;
    }
    public IntegerProperty typeProperty() {
        return type;
    }
    public StringProperty date_of_registrationProperty() {
        return date_of_registration;
    }
    public StringProperty descriptionProperty() {
        return description;
    }
    public StringProperty full_descriptionProperty() {
        return full_description;
    }
    public StringProperty nameProperty() {
        return name;
    }

    public double getRate_of_depreciation() {
        return rate_of_depreciation.get();
    }
    public double getPrice() {
        return price.get();
    }
    public int getAge() {
        return age.get();
    }
    public int getCategory_id() {
        return category_id.get();
    }
    public int getQuantity() {
        return quantity.get();
    }
    public int getType() {
        return type.get();
    }
    public String getDate_of_registration() {
        return date_of_registration.get();
    }
    public String getDate_of_transformation() {
        return date_of_transformation.get();
    }
    public String getDescription() {
        return description.get();
    }
    public String getFull_description() {
        return full_description.get();
    }
    public String getName() {
        return name.get();
    }

    public void setRate_of_depreciation(Double rate) {
        this.rate_of_depreciation = new SimpleDoubleProperty();
        this.rate_of_depreciation.set(rate);
    }
    public void setPrice(Double price) {
        this.price = new SimpleDoubleProperty();
        this.price.set(price);
    }
    public void setDate_of_registration(String date_of_registration) {
        this.date_of_registration = new SimpleStringProperty();
        this.date_of_registration.set(date_of_registration);
    }
    public void setDate_of_transformation(String date_of_transformation) {
        this.date_of_transformation = new SimpleStringProperty();
        this.date_of_transformation.set(date_of_transformation);
    }
    public void setDescription(String description) {
        this.description = new SimpleStringProperty();
        this.description.set(description);
    }
    public void setFull_description(String full_description) {
        this.full_description = new SimpleStringProperty();
        this.full_description.set(full_description);
    }
    public void setName(String name) {
        this.name = new SimpleStringProperty();
        this.name.set(name);
    }

    public void setQuantity(int quantity) {
        this.quantity = new SimpleIntegerProperty();
        this.quantity.set(quantity);
    }
    public void setType(int type) {
        this.type = new SimpleIntegerProperty();
        this.type.set(type);
    }
    public void setAge(int age) {
        this.age = new SimpleIntegerProperty();
        this.age.set(age);
    }
    public void setCategory_id(int category_id) {
        this.category_id = new SimpleIntegerProperty();
        this.category_id.set(category_id);
    }
}
