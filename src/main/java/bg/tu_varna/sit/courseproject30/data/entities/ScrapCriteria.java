package bg.tu_varna.sit.courseproject30.data.entities;

import javax.persistence.*;
import java.util.Set;

@Table(name = "javaproject.scrap_criteria")
@Entity
public class ScrapCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tasks_id", nullable = false)
    private Long id;

    @Column(name = "years", nullable = false)
    private int years;

    @Column(name = "month", nullable = false)
    private int month;

    @Column(name = "depreciation", nullable = false)
    private int depreciation;

    @OneToMany(mappedBy = "scrapCriteria")
    private Set<ScrapCriteriaProduct> scrapCriteriaProductSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(int depreciation) {
        this.depreciation = depreciation;
    }

    public Set<ScrapCriteriaProduct> getScrapCriteriaProductSet() {
        return scrapCriteriaProductSet;
    }

    public void setScrapCriteriaProductSet(Set<ScrapCriteriaProduct> scrapCriteriaProductSet) {
        this.scrapCriteriaProductSet = scrapCriteriaProductSet;
    }
}
