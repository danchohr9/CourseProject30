package bg.tu_varna.sit.courseproject30.presentation.models;

import javafx.beans.property.*;

public class ProductViewModel {
    private IntegerProperty id;
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
    private StringProperty criteria_id;   //String, zashtoto criteria_id moje da e null ("") i ne e udachno da pishe 0
    private StringProperty criteria;
    private DoubleProperty currentPrice;
    private DoubleProperty depreciationGrowth;

    public ProductViewModel(
            int id, String  name, String description, String full_description, int type, int category_id,int quantity,
            Double rate,String date_of_registration, String date_of_transformation, Double price, int age, String criteria_id,
            String criteria, double currentPrice, double growth                ) {
        this.setId(id);
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
        this.setCriteria_id(criteria_id);
        this.setCriteria(criteria);
        this.setCurrentPrice(currentPrice);
        this.setDepreciationGrowth(growth);
    }

    public ProductViewModel(ProductViewModel pvm) {
        id = pvm.idProperty();
        name = pvm.nameProperty();
        description = pvm.descriptionProperty();
        full_description = pvm.full_descriptionProperty();
        type = pvm.typeProperty();
        category_id = pvm.category_idProperty();
        quantity = pvm.quantityProperty();
        rate_of_depreciation = pvm.rate_of_depreciationProperty();
        date_of_registration = pvm.date_of_registrationProperty();
        date_of_transformation = pvm.date_of_transformationProperty();
        price = pvm.priceProperty();
        age = pvm.ageProperty();
        criteria_id = pvm.criteria_idProperty();
        criteria = pvm.criteriaProperty();
        currentPrice = pvm.currentPriceProperty();
        depreciationGrowth = pvm.depreciationGrowthProperty();
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
    public IntegerProperty idProperty() {
        return id;
    }
    public StringProperty criteria_idProperty() {
        return criteria_id;
    }
    public StringProperty criteriaProperty() {
        return criteria;
    }
    public DoubleProperty currentPriceProperty(){return currentPrice;}
    public DoubleProperty depreciationGrowthProperty(){return depreciationGrowth;}

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
    public int getId(){return id.get();}
    public String getCriteria_id(){return criteria_id.get();}
    public String getCriteria(){return criteria.get();}
    public double getCurrentPrice(){return currentPrice.get();}
    public double getDepreciationGrowth(){return depreciationGrowth.get();}

    public void setRate_of_depreciation(Double rate) {
        this.rate_of_depreciation = new SimpleDoubleProperty();
        this.rate_of_depreciation.set(rate);
    }
    public void setPrice(Double price) {
        this.price = new SimpleDoubleProperty();
        this.price.set(price);
    }
    public void setId(int id) {
        this.id = new SimpleIntegerProperty();
        this.id.set(id);
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
    public void setCriteria_id(String criteria_id) {
        this.criteria_id = new SimpleStringProperty();
        this.criteria_id.set(criteria_id);
    }
    public void setCriteria(String criteria) {
        this.criteria = new SimpleStringProperty();
        this.criteria.set(criteria);
    }
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = new SimpleDoubleProperty();
        this.currentPrice.set(currentPrice);
    }
    public void setDepreciationGrowth(double depreciationGrowth) {
        this.depreciationGrowth = new SimpleDoubleProperty();
        this.depreciationGrowth.set(depreciationGrowth);
    }
}
