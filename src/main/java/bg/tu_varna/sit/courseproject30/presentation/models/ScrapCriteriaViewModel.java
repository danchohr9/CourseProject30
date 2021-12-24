package bg.tu_varna.sit.courseproject30.presentation.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ScrapCriteriaViewModel {
    private IntegerProperty id;
    private IntegerProperty years;
    private DoubleProperty priceDrop;
    private DoubleProperty depreciation;

    public ScrapCriteriaViewModel(int id, int years, double priceDrop, double depreciation) {
        this.setId(id);
        this.setYears(years);
        this.setPriceDrop(priceDrop);
        this.setDepreciation(depreciation);
    }

    public ScrapCriteriaViewModel(ScrapCriteriaViewModel criteria) {
        id = criteria.idProperty();
        years = criteria.yearsProperty();
        priceDrop = criteria.priceDropProperty();
        depreciation = criteria.depreciationProperty();
    }


    public int getYears() {
        return years.get();
    }
    public IntegerProperty yearsProperty() {
        return years;
    }
    public double getPriceDrop() {
        return priceDrop.get();
    }
    public DoubleProperty priceDropProperty() {
        return priceDrop;
    }
    public double getDepreciation() {
        return depreciation.get();
    }
    public DoubleProperty depreciationProperty() {
        return depreciation;
    }
    public int getId() {
        return id.get();
    }
    public IntegerProperty idProperty() {
        return id;
    }


    public void setYears(int years) {
        this.years = new SimpleIntegerProperty();
        this.years.set(years);
    }
    public void setPriceDrop(double priceDrop) {
        this.priceDrop = new SimpleDoubleProperty();
        this.priceDrop.set(priceDrop);
    }
    public void setDepreciation(double depreciation) {
        this.depreciation = new SimpleDoubleProperty();
        this.depreciation.set(depreciation);
    }
    public void setId(int id) {
        this.id = new SimpleIntegerProperty();
        this.id.set(id);
    }

    @Override
    public String toString() {
        return  String.format("%s | %s | %s", years ,priceDrop,depreciation);
    }

    public boolean equals(Object o) {
        return years==((ScrapCriteriaViewModel)o).years &&
                priceDrop==((ScrapCriteriaViewModel)o).priceDrop &&
                depreciation==((ScrapCriteriaViewModel)o).depreciation;
    }

}
