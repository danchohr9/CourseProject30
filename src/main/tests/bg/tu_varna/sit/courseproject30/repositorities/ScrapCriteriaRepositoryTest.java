package bg.tu_varna.sit.courseproject30.repositorities;

import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.Category;
import bg.tu_varna.sit.courseproject30.data.entities.ScrapCriteria;
import bg.tu_varna.sit.courseproject30.data.repositorities.ScrapCriteriaRepository;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScrapCriteriaRepositoryTest {
    private ScrapCriteriaRepository repo;

    @BeforeEach
    void setUp() {
        PropertyConfigurator.configure(HelloApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        repo = ScrapCriteriaRepository.getInstance();
    }

    @Test
    @Order(1)
    void getInstance() {
        ScrapCriteriaRepository Repository = new ScrapCriteriaRepository();
        assertEquals(Repository.getClass(),repo.getClass());
    }

    @Test
    @Order(2)
    void save() {
        ScrapCriteria criteria = new ScrapCriteria();
        criteria.setYears(2);
        criteria.setDepreciation(10.0);
        criteria.setPriceDrop(5.0);
        repo.save(criteria);
    }

    @Test
    @Order(3)
    void update() {
        ScrapCriteria criteria = new ScrapCriteria();
        criteria.setId((long) repo.latestInsertedId());
        criteria.setDepreciation(10.0);
        criteria.setPriceDrop(5.0);
        criteria.setYears(6);
        repo.update(criteria);
    }

    @Test
    @Order(4)
    void getById() {
        ScrapCriteria criteria = repo.getById(repo.latestInsertedId());
        assertEquals(6, criteria.getYears());
    }

    @Test
    @Order(5)
    void getAll() {
        List<ScrapCriteria> categories = repo.getAll();
        boolean recordFound = false;
        for (ScrapCriteria category : categories) {
            if (category.getYears() == 6 && category.getDepreciation() == 10.0 && category.getPriceDrop() == 5.0) {
                recordFound = true;
                return;
            }
        }
        assertTrue(recordFound);
    }

    @Test
    @Order(6)
    void getCriteria() {
        ScrapCriteria criteria = repo.getCriteria(6,5.0,10.0);
        assertEquals(6,criteria.getYears());
    }

    @Test
    @Order(7)
    void delete() {
        ScrapCriteria criteria = repo.getById(repo.latestInsertedId());
        repo.delete(criteria);
    }



}