package bg.tu_varna.sit.courseproject30.data.entities;

import javax.persistence.*;
import java.util.Date;

@Table(name = "javaproject.product_carton")
@Entity
public class ProductCarton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToOne
    @JoinColumn(name = "carton_id", nullable = false)
    private Carton carton;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "date_added", nullable = false)
    private Date date_added;

    @Column(name = "date_removed", nullable = false)
    private Date date_removed;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Carton getCarton() {
        return carton;
    }

    public void setCarton(Carton carton) {
        this.carton = carton;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate_added() {
        return date_added;
    }

    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }

    public Date getDate_removed() {
        return date_removed;
    }

    public void setDate_removed(Date date_removed) {
        this.date_removed = date_removed;
    }
}
