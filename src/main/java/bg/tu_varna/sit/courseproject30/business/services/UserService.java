package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.data.entities.Roles;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import bg.tu_varna.sit.courseproject30.data.repositorities.UserRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository repository = UserRepository.getInstance();

    public static UserService getInstance() {
        return UserService.UserServiceHolder.INSTANCE;
    }

    private static class UserServiceHolder {
        public static final UserService INSTANCE = new UserService();
    }

    public ObservableList<UserViewModel> getAll() {
        List<User> users = repository.getAll();

        return FXCollections.observableList(
                users
                        .stream()
                        .map(u -> new UserViewModel(
                                Math.toIntExact(u.getId()),
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
        Roles role = new Roles(2);
        if(!username.isBlank() && !password.isBlank() && !email.isBlank()){
            if(repository.getUserByUsername(username)!=null){
                return "Username is already taken";
            }
            if(!validateUsername(username)){
                return "Username is not acceptable.";
            }
            if(password.length()<4) {
                return "Password is too short.";
            }
            if (!validatePassword(password)) {
                return "Password uses disallowed characters.";
            }

            if(validateEmail(email)){
                newUser.setUsername(username);
                newUser.setPassword(password);
                newUser.setEmail(email);
                newUser.setRole(role);
                repository.save(newUser);
                return "User successfully registered.";
            }else return "Incorrect email.";
        }else{
            return "Please fill each tab.";
        }
    }

    public boolean validateEmail(String email){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher matcher = pattern.matcher(email);
        if(matcher.find() && matcher.group().equals(email)){
            return true;
        }else{
            return false;
        }
    }

    public boolean validateUsername(String username){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*");
        Matcher matcher = pattern.matcher(username);
        if(matcher.find() && matcher.group().equals(username)){
            return true;
        }else{
            return false;
        }
    }

    public void delete(UserViewModel obj){
        User user = new User();
        user.setId((long) obj.getId());
        repository.delete(user);
    }
    //TODO:Duplication. Maybe one function for validation.
    public String update(User user){
        if(!validateUsername(user.getUsername())){
            return "Username is not acceptable.";
        }
        if(user.getPassword().length()<4) {
            return "Password is too short.";
        }
        if (!validatePassword(user.getPassword())) {
            return "Password uses disallowed characters.";
        }
        if (!validateEmail(user.getEmail())){
            return "Invalid Email";
        }
        repository.update(user);
        return "User edited.";
    }
    public boolean validatePassword(String password){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(password);
        if(matcher.find() && matcher.group().equals(password)){
            return true;
        }else{
            return false;
        }
    }
    public int getTotalUsers(){
        Long count = repository.getTotalUsers();
        return count.intValue();
    }

}
