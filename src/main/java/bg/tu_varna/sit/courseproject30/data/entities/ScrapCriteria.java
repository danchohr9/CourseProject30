package bg.tu_varna.sit.courseproject30.data.entities;

import javax.persistence.*;
import java.util.Set;

@Table(name = "javaproject.scrap_criteria")
@Entity
public class ScrapCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "years", nullable = false)
    private int years;

    @Column(name = "depreciation", nullable = false)
    private double depreciation;

    @Column(name = "price_drop", nullable = false)
    private double priceDrop;

    @OneToMany(mappedBy = "id")
    private Set<Product> productSet;

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

    public double getPriceDrop() {
        return priceDrop;
    }

    public void setPriceDrop(double priceDrop) {
        this.priceDrop = priceDrop;
    }

    public double getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(double depreciation) {
        this.depreciation = depreciation;
    }


    public ScrapCriteria(){
    }

    public ScrapCriteria(Long id, int years, double priceDrop, double depreciation) {
        this.id = id;
        this.years = years;
        this.priceDrop = priceDrop;
        this.depreciation = depreciation;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    @Override
    public String toString() {
        return years +
                "y, " + priceDrop +
                "%$, " + depreciation +
                '%';
    }
}
