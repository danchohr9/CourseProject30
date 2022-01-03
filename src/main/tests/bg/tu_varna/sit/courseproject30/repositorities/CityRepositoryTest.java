package bg.tu_varna.sit.courseproject30.repositorities;

import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.business.services.CategoryService;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.City;
import bg.tu_varna.sit.courseproject30.data.repositorities.CityRepository;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CityRepositoryTest {
    private static CityRepository repo;

    @BeforeAll
    public static void setup() {
        PropertyConfigurator.configure(HelloApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        repo = CityRepository.getInstance();
    }

    @Test
    @Order(1)
    void getInstance() {
        CityRepository Repository = new CityRepository();
        assertEquals(Repository.getClass(),repo.getClass());
    }

    @Test
    @Order(2)
    void save() {
        City city = new City();
        city.setName("Test");
        repo.save(city);
        City savedCity = repo.getCityByName("Test");
        assertEquals("Test", savedCity.getName());
    }
    @Test
    @Order(3)
    void getCityByName() {
        City city = repo.getCityByName("Test");
        assertNotNull(city);
    }
    @Test
    @Order(4)
    void getAll() {
        List<City> list = repo.getAll();
        assertTrue(list.size() > 0);
    }

}