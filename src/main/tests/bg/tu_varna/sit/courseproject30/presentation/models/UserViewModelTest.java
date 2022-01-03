package bg.tu_varna.sit.courseproject30.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserViewModelTest {

    UserViewModel model;
    @BeforeEach
    void setUp() {
        model = new UserViewModel(2,"testUser","testPass","test@abv.bg",2);
    }

    @Test
    void setId() {
        model.setId(3);
        assertEquals(3,model.getId());
    }

    @Test
    void getId() {
        assertEquals(2,model.getId());
    }

    @Test
    void getUsername() {
        assertEquals("testUser",model.getUsername());
    }

    @Test
    void getEmail() {
        assertEquals("test@abv.bg",model.getEmail());
    }

    @Test
    void setEmail() {
        model.setEmail("changed@gmail.com");
        assertEquals("changed@gmail.com",model.getEmail());
    }

    @Test
    void getRole() {
        assertEquals(2,model.getRole());
    }

    @Test
    void setRole() {
        model.setRole(1);
        assertEquals(1,model.getRole());
    }

    @Test
    void usernameProperty() {
        assertEquals("testUser",model.usernameProperty().get());
    }

    @Test
    void emailProperty() {
        assertEquals("test@abv.bg",model.emailProperty().get());
    }

    @Test
    void setUsername() {
        model.setUsername("changedUser");
        assertEquals("changedUser",model.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("testPass",model.getPassword());
    }

    @Test
    void setPassword() {
        model.setPassword("changedPass");
        assertEquals("changedPass", model.getPassword());
    }

    @Test
    void testEquals() {
    }
}