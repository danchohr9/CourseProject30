package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.data.entities.Client;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.ProductClient;
import bg.tu_varna.sit.courseproject30.data.repositorities.ClientProductRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ClientRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ProductRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.ClientProductViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ClientViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ProductViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ClientProductService {
    private final ClientProductRepository clientProductRepository = ClientProductRepository.getInstance();
    ClientRepository clientRepository = ClientRepository.getInstance();
    ProductRepository productRepository = ProductRepository.getInstance();


    public static ClientProductService getInstance() {
        return ClientProductService.ClientProductServiceHolder.INSTANCE;
    }

    private static class ClientProductServiceHolder {
        public static final ClientProductService INSTANCE = new ClientProductService();
    }

    public ObservableList<ClientProductViewModel> getClientProducts(ClientViewModel cvm) {
        List<ProductClient> productClients = clientProductRepository.getClientProducts(cvm.getId());
        return FXCollections.observableList(
                productClients
                        .stream()
                        .map(pc -> new ClientProductViewModel(
                                pc.getId(),
                                pc.getProduct().getName(),
                                pc.getQuantity(),
                                pc.getDate_added().toString(),
                                pc.getDate_removed() == null ? null : pc.getDate_removed().toString()
                        )).collect(Collectors.toList()));
    }

    public Product findProduct(ProductViewModel product) {
        Product product1 = productRepository.getById(product.getId());
        return product1;
    }

    public Client findClient(ClientViewModel client) {
        Client client1;
        client1 = clientRepository.getById(client.getId());
        return client1;
    }

    public ProductClient findProductClient(ClientProductViewModel productClient) {
        ProductClient productClient1;
        productClient1 = clientProductRepository.getById(productClient.getId());
        return productClient1;
    }

    public void updateProduct(Product product) {
        productRepository.updateWithQuantity(product);
    }

    public void addProduct(ClientViewModel client, ProductViewModel product, int quantity) {
        Client client1 = findClient(client);
        Product product1 = findProduct(product);
        Date today = new Date();
        ProductClient productClient = new ProductClient(product1, client1, quantity, today);
        clientProductRepository.save(productClient);
        product1.setQuantity(product.getQuantity() - quantity);
        updateProduct(product1);
    }

    public void removeProduct(ClientProductViewModel productClient, int quantity) {
        ProductClient productClient1 = findProductClient(productClient);
        Date today = new Date();
        if(productClient.getQuantity() < quantity) quantity = productClient.getQuantity();
        if(productClient.getQuantity() > quantity){
            productClient1.setQuantity(productClient1.getQuantity() - quantity);
            clientProductRepository.updateQuantity(productClient1);
            ProductClient productClient2 = clientProductRepository.getById(productClient1.getId());
            productClient2.setQuantity(quantity);
            productClient2.setDate_removed(today);
            clientProductRepository.save(productClient2);

        }else if(productClient.getQuantity() == quantity){
            productClient1.setDate_removed(today);
            clientProductRepository.update(productClient1);
        }
        Product product = productClient1.getProduct();
        product.setQuantity(product.getQuantity() + quantity);
        updateProduct(product);
    }

    public ObservableList<ClientProductViewModel> searchProducts(String name, Date dateFrom, Date dateTo, ClientViewModel selectedClient) {
        List<ProductClient> productClients = clientProductRepository.searchClientProducts(name, dateFrom, dateTo, selectedClient.getId());

        return FXCollections.observableList(
                productClients
                        .stream()
                        .map(pc -> new ClientProductViewModel(
                                pc.getId(),
                                pc.getProduct().getName(),
                                pc.getQuantity(),
                                pc.getDate_added().toString(),
                                pc.getDate_removed() == null ? null : pc.getDate_removed().toString()
                        )).collect(Collectors.toList()));
    }
}
