package bg.tu_varna.sit.courseproject30.presentation.models;

public class ScrapCriteriaViewModel {
    private int years;
    private int months;
    private int depreciation;

    public ScrapCriteriaViewModel(int years, int months, int depreciation) {
        this.years = years;
        this.months = months;
        this.depreciation = depreciation;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(int depreciation) {
        this.depreciation = depreciation;
    }

    @Override
    public String toString() {
        return  String.format("%s | %s | %s", years ,months,depreciation);
    }

    public boolean equals(Object o) {
        return (years==((ScrapCriteriaViewModel)o).years && months==((ScrapCriteriaViewModel)o).months &&
                depreciation==((ScrapCriteriaViewModel)o).depreciation);
    }

}
