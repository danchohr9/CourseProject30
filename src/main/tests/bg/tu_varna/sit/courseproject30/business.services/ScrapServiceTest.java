package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.Scrap;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import bg.tu_varna.sit.courseproject30.data.repositorities.ProductRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ScrapRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.UserRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.ToScrapViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ScrapServiceTest {
    public ScrapService service;
    public ScrapRepository repository;
    @BeforeEach
    void setUp() {
        PropertyConfigurator.configure(HelloApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        service = ScrapService.getInstance();
        repository = ScrapRepository.getInstance();
    }

    @Test
    void getProducts() {
        List<Scrap> scraps = repository.getAll();
        assertEquals(scraps.size(),service.getProducts().size());
    }

    @Test
    void scrapProduct() {
        ProductRepository productRepository = ProductRepository.getInstance();
        Product product = productRepository.getById(17);
        ToScrapViewModel toScrapViewModel = new ToScrapViewModel(product.getId(),product.getName(),product.getQuantity(),null,"test");
        UserRepository userRepository = UserRepository.getInstance();
        User user = userRepository.getUserByUsername("admin");
        UserViewModel userViewModel = new UserViewModel(Math.toIntExact(user.getId()),user.getUsername(),"test","test",1);
        service.ScrapProduct(toScrapViewModel,userViewModel);
        assertEquals(product.getId(),repository.getLastInserted().getProduct().getId());
        assertEquals(product.getQuantity(),repository.getLastInserted().getQuantity());
        assertEquals(0,productRepository.getById(17).getQuantity());
        Product product1 = repository.getLastInserted().getProduct();
        product1.setQuantity(product.getQuantity());
        productRepository.update(product1);
        repository.delete(repository.getLastInserted());
    }

    @Test
    void searchScraps() {
        Calendar myCal = Calendar.getInstance();
        myCal.set(Calendar.YEAR, 2021);
        myCal.set(Calendar.MONTH, 11);
        myCal.set(Calendar.DAY_OF_MONTH, 23);
        Date date1 = myCal.getTime();
        myCal.set(Calendar.YEAR, 2022);
        myCal.set(Calendar.MONTH, 0);
        myCal.set(Calendar.DAY_OF_MONTH, 23);
        Date date2 = myCal.getTime();
        System.out.println(date1.toString());
        List<Scrap> scraps = repository.searchScraps("Laptop Legion", date1, date2);
        assertEquals(scraps.get(0).getId(),service.searchScraps("Laptop Legion",date1, date2).get(0).getId());
    }
}