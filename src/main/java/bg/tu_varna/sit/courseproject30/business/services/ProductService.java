package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import bg.tu_varna.sit.courseproject30.data.repositorities.ProductRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.UserRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.ProductViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    public static ProductService getInstance() {
        return ProductService.ProductServiceHolder.INSTANCE;
    }
    public final ProductRepository repository = ProductRepository.getInstance();

    private static class ProductServiceHolder {
        public static final ProductService INSTANCE = new ProductService();
    }

    public ObservableList<ProductViewModel> getAllProducts() {
        List<Product> products = repository.getAll();
        System.out.println(products.toString());

        return FXCollections.observableList(
                products
                        .stream()
                        .map(u -> new ProductViewModel(
                                u.getName(),
                                u.getDescription(),
                                u.getFull_description(),
                                u.getType(),
                                Math.toIntExact(u.getCategory().getId()),
                                u.getQuantity(),
                                u.getRate_of_depreciation(),
                                u.getDate_of_registration().toString(),
                                u.getDate_of_transformation() == null ? null : u.getDate_of_transformation().toString(),
                                u.getPrice(),
                                u.getAge()

                        )).collect(Collectors.toList()));
    }
}
