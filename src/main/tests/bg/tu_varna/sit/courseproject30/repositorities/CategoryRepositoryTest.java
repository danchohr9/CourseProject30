package bg.tu_varna.sit.courseproject30.repositorities;

import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.Category;
import bg.tu_varna.sit.courseproject30.data.repositorities.CategoryRepository;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryRepositoryTest {
    private static CategoryRepository repo;

    @BeforeAll
    public static void setup() {
        PropertyConfigurator.configure(HelloApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        repo = CategoryRepository.getInstance();
    }

    @Test
    @Order(1)
    void getInstance() {
        CategoryRepository Repository = new CategoryRepository();
        assertEquals(Repository.getClass(),repo.getClass());
    }

    @Test
    @Order(2)
    @DisplayName("Testing if creating a category works.")
    void save() {
        Category category = new Category();
        category.setDescription("test");
        category.setName("test");
        repo.save(category);
    }

    @Test
    @Order(3)
    @DisplayName("Testing if editing a category works.")
    void update() {
        Category category = new Category();
        category.setId((long) repo.latestInsertedId());
        category.setName("tests");
        repo.update(category);
    }

    @Test
    @Order(4)
    @DisplayName("Testing if finding a category by id works.")
    void getById() {
        Category category = repo.findById((long) repo.latestInsertedId());
        assertEquals("tests", category.getName());
    }

    @Test
    @Order(5)
    @DisplayName("Testing if finding all categories works.")
    void getAll() {
        List<Category> categories = repo.getAll();
        boolean recordFound = false;

        for (Category category : categories) {
            if (Objects.equals(category.getName(), "tests")) {
                recordFound = true;
                return;
            }
        }
        assertTrue(recordFound);
    }

    @Test
    @Order(6)
    @DisplayName("Testing if deleting a category works.")
    void delete() {
        Category category = new Category();
        category.setId((long) repo.latestInsertedId());
        repo.delete(category);
    }
}