package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.ProductClient;
import bg.tu_varna.sit.courseproject30.data.repositorities.ClientProductRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ProductRepository;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToScrapSeviceTest {

    ProductRepository productRepository;
    ClientProductRepository clientProductRepository;
    ToScrapSevice sevice;
    @BeforeEach
    void setUp() {
        PropertyConfigurator.configure(HelloApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        productRepository = ProductRepository.getInstance();
        clientProductRepository = ClientProductRepository.getInstance();
        sevice = ToScrapSevice.getInstance();
    }

    @Test
    void getProducts() {
        List<Product> products = productRepository.getMAProducts();
        assertEquals(products.size(),sevice.getProducts().size());
        assertEquals(products.get(0).getId(),sevice.getProducts().get(0).getIdProduct());
        assertEquals(products.get(products.size()-1).getId(),sevice.getProducts().get(sevice.getProducts().size()-1).getIdProduct());
    }

    @Test
    void getProductsFromClients() {
        List<ProductClient> products = clientProductRepository.getMAProducts();
        assertEquals(products.size(),sevice.getProductsFromClients().size());
        assertEquals(products.get(0).getId(),Integer.parseInt(sevice.getProductsFromClients().get(0).getIdProductClient()));
        assertEquals(products.get(products.size() - 1).getId(),Integer.parseInt(sevice.getProductsFromClients().get(sevice.getProductsFromClients().size() - 1).getIdProductClient()));
    }
}