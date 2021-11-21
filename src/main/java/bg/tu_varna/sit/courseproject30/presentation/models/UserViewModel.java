package bg.tu_varna.sit.courseproject30.presentation.models;

import java.util.Comparator;

public class UserViewModel{
    private String username;
    private String password;
    private String email;
    private int role;

    public UserViewModel(String username,String password){
        this.username = username;
        this.password = password;
    }


    public UserViewModel(String username, String password, String email, int role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
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

    @Override
    public String toString() {
        return  String.format("%s | %s", username ,email);
    }

    public boolean equals(Object o) {
        return username.equals(((UserViewModel)o).username) && password.equals(((UserViewModel)o).password);
    }

}
