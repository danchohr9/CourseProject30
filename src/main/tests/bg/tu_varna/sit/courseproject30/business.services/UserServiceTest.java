package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import bg.tu_varna.sit.courseproject30.data.repositorities.UserRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.collections.ObservableList;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    UserService service;
    UserRepository repository;
    @BeforeEach
    void setUp() {
        PropertyConfigurator.configure(HelloApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        service = UserService.getInstance();
        repository = UserRepository.getInstance();
    }

    @Test
    @Order(1)
    void getAll() {
        List<User> users = repository.getAll();
        assertEquals(users.size(),service.getAll().size());
        assertEquals(users.get(0).getId(), service.getAll().get(0).getId());
        assertEquals(users.get(users.size()-1).getId(), service.getAll().get(service.getAll().size()-1).getId());
    }

    @Test
    @Order(4)
    void validateLogin() {
        ObservableList<UserViewModel> allUsers = service.getAll();
        UserViewModel userViewModel = new UserViewModel(repository.getLastInserted().getUsername(),repository.getLastInserted().getPassword());
        boolean check = service.validateLogin(userViewModel,allUsers);
        assertTrue(check);
        userViewModel.setUsername("wrongUsername");
        userViewModel.setPassword("wrongPass");
        check = service.validateLogin(userViewModel,allUsers);
        assertFalse(check);
    }

    @Test
    @Order(2)
    void registerUser() {
        assertEquals("Username is already taken",service.registerUser(repository.getLastInserted().getUsername(),"sdsada","sdasd"));
        assertEquals("Username is not acceptable.",service.registerUser("+-+dasj__","sdsada","sdasd"));
        assertEquals("Password is too short.",service.registerUser("test","a","sdasd"));
        assertEquals("Password uses disallowed characters.",service.registerUser("test","+sad-++","sdasd"));
        assertEquals("Invalid Email",service.registerUser("testUser1","testPass","abvdask"));
        assertEquals("Please fill each tab.",service.registerUser("","a","sdasd"));
        assertEquals("User successfully registered.",service.registerUser("testUser1","testPass","testUser1@abv.bg"));
    }

    @Test
    @Order(6)
    void validateEmail() {
        assertTrue(service.validateEmail("test@abv.bg"));
        assertFalse(service.validateEmail("++d9asd"));
    }

    @Test
    @Order(7)
    void validateUsername() {
        assertTrue(service.validateUsername("username1"));
        assertFalse(service.validateUsername("r-*asdda"));
    }

    @Test
    @Order(3)
    void update() {
        User user = repository.getUserByUsername(repository.getLastInserted().getUsername());
        user.setEmail("dasda123");
        assertEquals("Invalid Email",service.update(user));
        user.setPassword("*-/*2sad");
        assertEquals("Password uses disallowed characters.",service.update(user));
        user.setPassword("wqe");
        assertEquals("Password is too short.",service.update(user));
        user.setUsername("21sad--");
        assertEquals("Username is not acceptable.",service.update(user));
        user = repository.getUserByUsername(repository.getLastInserted().getUsername());
        user.setUsername("testUser1edit"); user.setPassword("testYser1password"); user.setEmail("user1Edit@abv.bg");
        assertEquals("User edited.",service.update(user));
    }

    @Test
    @Order(5)
    void delete() {
        User user = repository.getUserByUsername(repository.getLastInserted().getUsername());
        assertNotNull(user);
        UserViewModel toDelete = new UserViewModel(Math.toIntExact(user.getId()), user.getUsername(), user.getPassword(), user.getEmail(),user.getRole().getId());
        service.delete(toDelete);
        assertNull(repository.getUserByUsername(user.getUsername()));
    }


    @Test
    @Order(8)
    void validatePassword() {
        assertTrue(service.validatePassword("password1"));
        assertFalse(service.validatePassword("r-*asdda"));
    }

    @Test
    @Order(9)
    void getTotalUsers() {
        assertEquals(repository.getAll().size(),service.getTotalUsers());
    }
}