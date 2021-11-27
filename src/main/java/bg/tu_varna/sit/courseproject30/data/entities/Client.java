package bg.tu_varna.sit.courseproject30.data.entities;

import javax.persistence.*;
import java.util.Date;

@Table(name = "javaproject.client")
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_of_reg", nullable = false)
    private Date date_of_reg;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carton_id", nullable = false)
    private Carton carton;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_of_reg() {
        return date_of_reg;
    }

    public void setDate_of_reg(Date date_of_reg) {
        this.date_of_reg = date_of_reg;
    }

    public Carton getCarton() {
        return carton;
    }

    public void setCarton(Carton carton) {
        this.carton = carton;
    }
}
