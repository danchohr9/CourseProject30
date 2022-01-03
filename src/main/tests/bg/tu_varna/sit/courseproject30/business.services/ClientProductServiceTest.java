package bg.tu_varna.sit.courseproject30.business.services;


import bg.tu_varna.sit.courseproject30.application.HelloApplication;
import bg.tu_varna.sit.courseproject30.common.Constants;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.ProductClient;

import bg.tu_varna.sit.courseproject30.data.repositorities.ClientProductRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ClientRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ProductRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.ClientProductViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ClientViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ProductViewModel;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientProductServiceTest {
    ClientProductService service;
    ClientProductRepository repository;
    ProductRepository productRepository;
    ClientRepository clientRepository;
    @BeforeEach
    void setUp() {
        PropertyConfigurator.configure(HelloApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        service = ClientProductService.getInstance();
        repository = ClientProductRepository.getInstance();
        productRepository = ProductRepository.getInstance();
        clientRepository = ClientRepository.getInstance();
    }

    @Test
    @Order(1)
    void getClientProducts() {
        ClientViewModel client = new ClientViewModel();
        client.setId(clientRepository.getLastInserted().getId());
        List<ProductClient> productClients = repository.getClientProducts(client.getId());
        assertEquals(productClients.size(),service.getClientProducts(client).size());
        assertEquals(productClients.get(0).getId(), service.getClientProducts(client).get(0).getId());
        assertEquals(productClients.get(productClients.size()-1).getId(), service.getClientProducts(client).get(service.getClientProducts(client).size()-1).getId());
    }

    @Test
    @Order(2)
    void findProduct() {
        ProductViewModel productViewModel = new ProductViewModel();
        productViewModel.setId(20);
        assertEquals(productRepository.getById(productViewModel.getId()).getId(),service.findProduct(productViewModel).getId());
    }

    @Test
    @Order(3)
    void findClient() {
        ClientViewModel clientViewModel = new ClientViewModel();
        clientViewModel.setId(5);
        assertEquals(clientRepository.getById(clientViewModel.getId()).getId(),service.findClient(clientViewModel).getId());
    }

    @Test
    @Order(4)
    void findProductClient() {
        ClientProductViewModel model = new ClientProductViewModel();
        model.setId(4);
        assertEquals(repository.getById(model.getId()).getId(),service.findProductClient(model).getId());
    }

    @Test
    @Order(6)
    void updateProduct() {
        Product product = repository.getLastInserted().getProduct();
        int quantity = product.getQuantity();
        product.setQuantity(quantity+1);
        service.updateProduct(product);
        assertEquals(quantity+1,productRepository.getById(productRepository.getLastInserted().getId()).getQuantity());
        product.setQuantity(quantity);
        service.updateProduct(product);
        assertEquals(quantity,productRepository.getById(productRepository.getLastInserted().getId()).getQuantity());
    }

    @Test
    @Order(5)
    void addProduct() {
        ClientViewModel clientViewModel = new ClientViewModel();
        ProductViewModel productViewModel = new ProductViewModel();
        productViewModel.setId(productRepository.getLastInserted().getId());
        Product product = service.findProduct(productViewModel);
        int oldQuantity = product.getQuantity();
        productViewModel.setQuantity(oldQuantity);
        clientViewModel.setId(clientRepository.getLastInserted().getId());
        int quantity = 1;
        service.addProduct(clientViewModel,productViewModel,quantity);
        assertEquals(oldQuantity-quantity,(service.findProduct(productViewModel)).getQuantity());
    }

    @Test
    @Order(7)
    void removeProduct() {
        ClientProductViewModel clientProductViewModel = new ClientProductViewModel();
        clientProductViewModel.setId(repository.getLastInserted().getId());
        int quantity = 1;
        ProductClient productClient = service.findProductClient(clientProductViewModel);
        clientProductViewModel.setQuantity(productClient.getQuantity());
        int oldQuantity = productClient.getProduct().getQuantity();
        assertNull(repository.getById(productClient.getId()).getDate_removed());
        service.removeProduct(clientProductViewModel,quantity);
        assertNotNull(repository.getById(productClient.getId()).getDate_removed());
        assertEquals(oldQuantity+quantity,productRepository.getById(productClient.getProduct().getId()).getQuantity());
        repository.delete(repository.getLastInserted());
    }

    @Test
    @Order(8)
    void searchProducts() {
        Calendar myCal = Calendar.getInstance();
        myCal.set(Calendar.YEAR, 2021);
        myCal.set(Calendar.MONTH, 11);
        myCal.set(Calendar.DAY_OF_MONTH, 23);
        Date date1 = myCal.getTime();
        myCal.set(Calendar.YEAR, 2022);
        myCal.set(Calendar.MONTH, 0);
        myCal.set(Calendar.DAY_OF_MONTH, 23);
        Date date2 = myCal.getTime();
        ClientViewModel clientViewModel = new ClientViewModel();
        clientViewModel.setId(2);
        List<ProductClient> products = repository.searchClientProducts(repository.getLastInserted().getProduct().getName(), date1, date2,clientViewModel.getId());
        List<ClientProductViewModel> result = service.searchProducts(repository.getLastInserted().getProduct().getName(),date1, date2,clientViewModel);
        assertEquals(products.get(0).getId(),result.get(0).getId());
        assertEquals(products.size(),result.size());
        assertEquals(products.get(products.size()-1).getId(),result.get(result.size()-1).getId());
    }
}