package bg.tu_varna.sit.courseproject30.data.entities;

import javax.persistence.*;
import java.util.Set;

@Table(name = "javaproject.city")
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "city")
    private Set<Carton> cartonSet;

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

    public Set<Carton> getCartonSet() {
        return cartonSet;
    }

    public void setCartonSet(Set<Carton> cartonSet) {
        this.cartonSet = cartonSet;
    }



    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cartonSet=" + cartonSet +
                '}';
    }
}
