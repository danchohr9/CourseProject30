package bg.tu_varna.sit.courseproject30.presentation.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ScrapCriteriaViewModel {
    private IntegerProperty years;
    private IntegerProperty months;
    private IntegerProperty depreciation;

    public ScrapCriteriaViewModel(int years, int months, int depreciation) {
        this.setYears(years);
        this.setMonths(months);
        this.setDepreciation(depreciation);
    }

    public ScrapCriteriaViewModel(ScrapCriteriaViewModel criteria) {
        years = criteria.yearsProperty();
        months = criteria.monthsProperty();
        depreciation = criteria.depreciationProperty();
    }


    public int getYears() {
        return years.get();
    }
    public IntegerProperty yearsProperty() {
        return years;
    }
    public int getMonths() {
        return months.get();
    }
    public IntegerProperty monthsProperty() {
        return months;
    }
    public int getDepreciation() {
        return depreciation.get();
    }
    public IntegerProperty depreciationProperty() {
        return depreciation;
    }


    public void setYears(int years) {
        this.years = new SimpleIntegerProperty();
        this.years.set(years);
    }
    public void setMonths(int months) {
        this.months = new SimpleIntegerProperty();
        this.months.set(months);
    }
    public void setDepreciation(int depreciation) {
        this.depreciation = new SimpleIntegerProperty();
        this.depreciation.set(depreciation);
    }

    @Override
    public String toString() {
        return  String.format("%s | %s | %s", years ,months,depreciation);
    }

    public boolean equals(Object o) {
        return years==((ScrapCriteriaViewModel)o).years &&
                months==((ScrapCriteriaViewModel)o).months &&
                depreciation==((ScrapCriteriaViewModel)o).depreciation;
    }

}
