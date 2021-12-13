package bg.tu_varna.sit.courseproject30.data.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "javaproject.client")
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "egn", nullable = false)
    private String egn;

    @Column(name = "register_date", nullable = false)
    private Date register_date;

    @OneToMany(mappedBy = "client")
    private Set<ProductClient> productClientSet;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public  Client(){

    }
    public Client(String first_name, String last_name, String phone, String address, String egn, Date register_date, City city) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.address = address;
        this.egn = egn;
        this.register_date = register_date;
        this.city = city;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }



}
