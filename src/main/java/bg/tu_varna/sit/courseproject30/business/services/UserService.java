package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.data.entities.Roles;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import bg.tu_varna.sit.courseproject30.data.repositorities.UserRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository repository = UserRepository.getInstance();

    public static UserService getInstance() {
        return UserService.UserServiceHolder.INSTANCE;
    }

    private static class UserServiceHolder {
        public static final UserService INSTANCE = new UserService();
    }

    public ObservableList<UserViewModel> getAllTask() {
        List<User> users = repository.getAll();

        return FXCollections.observableList(
                users
                        .stream()
                        .map(u -> new UserViewModel(
                                u.getUsername(),
                                u.getPassword(),
                                u.getEmail(),
                                u.getRole().getId()
                        )).collect(Collectors.toList()));
    }


    public static boolean validateLogin(UserViewModel user, ObservableList<UserViewModel> allUsers){
        boolean state=false;
        for (UserViewModel u:allUsers){
            if(u.equals(user)){
                state=true;
                user.setEmail(u.getEmail());
                user.setRole(u.getRole());
                return state;
            }
        }
        return state;
    }

    public String registerUser(String username, String password, String email){
        User newUser = new User();
        Roles role = new Roles();
        if(!username.isBlank() && !password.isBlank() && !email.isBlank()){
            if(repository.getUserByUsername(username).getUsername().equals(username)){
                return "Username is already taken";
            }
            if(email.indexOf('@')!=-1){
//                newUser.setUsername(username);
//                newUser.setPassword(password);
//                newUser.setEmail(email);
            }else return "Incorrect email.";
        }else{
            return "Please fill each tab.";
        }
        return "";
    }

}
