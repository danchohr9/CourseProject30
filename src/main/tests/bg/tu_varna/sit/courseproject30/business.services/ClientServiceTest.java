package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.Category;
import bg.tu_varna.sit.courseproject30.presentation.models.ClientViewModel;
import javafx.collections.ObservableList;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientServiceTest {
    private static ClientService service;

    @BeforeAll
    public static void setup() {
        PropertyConfigurator.configure(HelloApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        service = ClientService.getInstance();
    }

    @Test
    @Order(1)
    void getInstance() {
        ClientService service = new ClientService();
        ClientService service1 = ClientService.getInstance();
        assertEquals(service.getClass(),service1.getClass());
    }

    @Test
    @Order(2)
    void testValidation(){
        assertEquals("Please fill all fields.",service.registerClient("","Name","0999445555","Address here","1234567890","Test"));
        assertEquals("Please fill all fields.",service.registerClient("Name","","0999445555","Address here","1234567890","Test"));
        assertEquals("Please fill all fields.",service.registerClient("Name","Name","","Address here","1234567890","Test"));
        assertEquals("Please fill all fields.",service.registerClient("Name","Name","0999445555","","1234567890","Test"));
        assertEquals("Please fill all fields.",service.registerClient("Name","Name","0999445555","Address here","","Test"));
        assertEquals("Please fill all fields.",service.registerClient("Name","Name","0999445555","Address here","1234567890",""));

        assertEquals("Wrong phone format.",service.registerClient("Name","Name","099944df555","Address here","1234567890","Test"));
        assertEquals("Wrong City format.",service.registerClient("Name","Name","0999445555","Address here","1234567890","Test6657"));
        assertEquals("Wrong EGN format.",service.registerClient("Name","Name","0999445555","Address here","1237890","Test"));
        assertEquals("Wrong name format.",service.registerClient("54676dfghg45456","Name","0999445555","Address here","1234567890","Test"));
        assertEquals("Wrong name format.",service.registerClient("Name","Name575680-=90","0999445555","Address here","1234567890","Test"));
    }

    @Test
    @Order(3)
    void registerClient() {
        service.registerClient("Name","Name","0999445555","Address here","1234567890","Test");
    }


    @Test
    @Order(4)
    void getAllClients() {
        ObservableList<ClientViewModel> clients = service.getAllClients();
        boolean recordFound = false;
        for (ClientViewModel client : clients) {
            if (Objects.equals(client.getPhone(), "0999445555")) {
                recordFound = true;
                return;
            }
        }
        assertTrue(recordFound);
    }

    @Test
    @Order(5)
    void getTotalClients() {
        assertTrue(service.getTotalClients() > 0);
    }

    @Test
    @Order(6)
    void searchClients() {
        Date today = new Date();
        today.setTime(0);
        ObservableList<ClientViewModel> client= service.searchClients("Name",today,null);
        assertFalse(client.isEmpty());
    }
}