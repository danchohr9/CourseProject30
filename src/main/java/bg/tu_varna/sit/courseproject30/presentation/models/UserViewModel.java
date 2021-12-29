package bg.tu_varna.sit.courseproject30.presentation.models;

import javafx.beans.property.SimpleStringProperty;

import java.util.Comparator;

public class UserViewModel{
    private int id;
    private String username;
    private String password;
    private String email;
    private int role;

    public UserViewModel(String username,String password){
        this.username = username;
        this.password = password;
    }


    public UserViewModel(int id, String username, String password, String email, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    public SimpleStringProperty usernameProperty(){
        SimpleStringProperty username = new SimpleStringProperty();
        username.set(this.username);
        return username;
    }
    public SimpleStringProperty emailProperty(){
        SimpleStringProperty emailProperty = new SimpleStringProperty();
        emailProperty.set(this.email);
        return emailProperty;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object o) {
        return username.equals(((UserViewModel)o).username) && password.equals(((UserViewModel)o).password);
    }
}
