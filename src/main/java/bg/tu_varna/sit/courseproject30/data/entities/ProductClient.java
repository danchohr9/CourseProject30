package bg.tu_varna.sit.courseproject30.data.entities;

import javax.persistence.*;
import java.util.Date;

@Table(name = "javaproject.product_client")
@Entity
public class ProductClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "date_added", nullable = true)
    private Date date_added;

    @Column(name = "date_removed", nullable = true)
    private Date date_removed;

    public ProductClient(){

    }

    public ProductClient(Product product, Client client, int quantity, Date date_added) {
        this.product = product;
        this.client = client;
        this.quantity = quantity;
        this.date_added = date_added;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
