package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.Category;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {
    private static CategoryService service;

    @BeforeAll
    public static void setup() {
        PropertyConfigurator.configure(HelloApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        service = CategoryService.getInstance();
    }

    @Test
    void getInstance() {
        CategoryService service = new CategoryService();
        CategoryService service1 = CategoryService.getInstance();
        assertEquals(service.getClass(),service1.getClass());
    }

    @Test
    @DisplayName("Testing if creating category work with invalid data.")
    void createCategory() {
        boolean result = service.createCategory(null,null);
        assertFalse(result);
        result = service.createCategory("","");
        assertFalse(result);
    }

    @Test
    @DisplayName("Testing if getting all categories work.")
    void getAll() {
        PropertyConfigurator.configure(HelloApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        CategoryService service = CategoryService.getInstance();
        List<Category> list = service.getAll();
        Assertions.assertFalse(list.isEmpty());
    }

}