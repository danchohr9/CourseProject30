package bg.tu_varna.sit.courseproject30.data.entities;

import javax.persistence.*;
import java.util.Date;

@Table(name = "javaproject.scrapcriteria_product")
@Entity
public class ScrapCriteriaProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "scrap_date", nullable = false)
    private Date scrap_date;

    @ManyToOne
    @JoinColumn(name = "criteria_id", nullable = false)
    private ScrapCriteria scrapCriteria;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getScrap_date() {
        return scrap_date;
    }

    public void setScrap_date(Date scrap_date) {
        this.scrap_date = scrap_date;
    }

    public ScrapCriteria getScrapCriteria() {
        return scrapCriteria;
    }

    public void setScrapCriteria(ScrapCriteria scrapCriteria) {
        this.scrapCriteria = scrapCriteria;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
