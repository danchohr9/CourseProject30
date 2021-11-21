package bg.tu_varna.sit.courseproject30.data.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "library.product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "full_description", nullable = false)
    private String full_description;

    @Column(name = "rate_of_depreciation", nullable = false)
    private double rate_of_depreciation;

    @Column(name = "date_of_registration", nullable = false)
    private Date date_of_registration;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "date_of_transformation", nullable = false)
    private Date date_of_transformation;

    @Column(name = "type", nullable = false)
    private int type;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @OneToOne(mappedBy = "product")
    private ScrapCriteriaProduct scrapCriteriaProduct;

    @OneToMany(mappedBy = "product")
    private Set<ProductCarton> productCartonSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFull_description() {
        return full_description;
    }

    public void setFull_description(String full_description) {
        this.full_description = full_description;
    }

    public double getRate_of_depreciation() {
        return rate_of_depreciation;
    }

    public void setRate_of_depreciation(double rate_of_depreciation) {
        this.rate_of_depreciation = rate_of_depreciation;
    }

    public Date getDate_of_registration() {
        return date_of_registration;
    }

    public void setDate_of_registration(Date date_of_registration) {
        this.date_of_registration = date_of_registration;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate_of_transformation() {
        return date_of_transformation;
    }

    public void setDate_of_transformation(Date date_of_transformation) {
        this.date_of_transformation = date_of_transformation;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ScrapCriteriaProduct getScrapCriteriaProduct() {
        return scrapCriteriaProduct;
    }

    public void setScrapCriteriaProduct(ScrapCriteriaProduct scrapCriteriaProduct) {
        this.scrapCriteriaProduct = scrapCriteriaProduct;
    }

    public Set<ProductCarton> getProductCartonSet() {
        return productCartonSet;
    }

    public void setProductCartonSet(Set<ProductCarton> productCartonSet) {
        this.productCartonSet = productCartonSet;
    }
}
