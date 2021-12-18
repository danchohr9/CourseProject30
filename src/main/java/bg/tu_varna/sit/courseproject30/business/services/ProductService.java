package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.data.entities.Category;
import bg.tu_varna.sit.courseproject30.data.entities.Client;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.repositorities.CategoryRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ProductRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.ProductViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductService {
    public static ProductService getInstance() {
        return ProductService.ProductServiceHolder.INSTANCE;
    }
    public final ProductRepository repository = ProductRepository.getInstance();
    public final CategoryRepository categoryRepository = CategoryRepository.getInstance();
    private static class ProductServiceHolder {
        public static final ProductService INSTANCE = new ProductService();
    }
    public String createProduct(Product newProduct){

            repository.save(newProduct);
            return "successfully added.";
    }
    public void delete(ProductViewModel product){
        Product product1 = new Product();
        product1.setId(product.getId());
        repository.delete(product1);
    }
    public void update(Product product){
        repository.update(product);
    }
    public Product findById(Long id){
        return repository.findById(id);
    }

    public ObservableList<ProductViewModel> getAllProducts() {
        List<Product> products = repository.getAll();
        System.out.println(products.toString());

        return FXCollections.observableList(
                products
                        .stream()
                        .map(u -> new ProductViewModel(
                                Math.toIntExact(u.getId()),
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

    public ObservableList<ProductViewModel> getAvailableProducts() {
        List<Product> products = repository.getAvailableProducts();
        System.out.println(products.toString());

        return FXCollections.observableList(
                products
                        .stream()
                        .map(u -> new ProductViewModel(
                                Math.toIntExact(u.getId()),
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
