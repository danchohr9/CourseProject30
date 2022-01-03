package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.ProductClient;
import bg.tu_varna.sit.courseproject30.data.repositorities.ClientProductRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ProductRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.ToScrapViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class ToScrapSevice {
    public static ToScrapSevice getInstance() {
        return ToScrapSevice.ToScrapSeviceHolder.INSTANCE;
    }
    public final ProductRepository productRepository = ProductRepository.getInstance();
    public final ClientProductRepository clientProductRepository = ClientProductRepository.getInstance();

    private static class ToScrapSeviceHolder {
        public static final ToScrapSevice INSTANCE = new ToScrapSevice();
    }

    public ObservableList<ToScrapViewModel> getProducts() {
        List<Product> products = productRepository.getMAProducts();

        return FXCollections.observableList(
                products
                        .stream()
                        .map(u -> new ToScrapViewModel(
                                Math.toIntExact(u.getId()),
                                u.getName(),
                                u.getQuantity(),
                                null,
                                null
                        )).collect(Collectors.toList()));
    }

    public ObservableList<ToScrapViewModel> getProductsFromClients() {
        List<ProductClient> products = clientProductRepository.getMAProducts();

        return FXCollections.observableList(
                products
                        .stream()
                        .map(u -> new ToScrapViewModel(
                                u.getProduct().getId(),
                                u.getProduct().getName(),
                                u.getQuantity(),
                                String.valueOf(u.getId()),
                                u.getClient().getFirst_name()
                        )).collect(Collectors.toList()));
    }

}
