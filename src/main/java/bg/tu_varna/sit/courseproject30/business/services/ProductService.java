package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.data.entities.Category;
import bg.tu_varna.sit.courseproject30.data.entities.Client;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.ScrapCriteria;
import bg.tu_varna.sit.courseproject30.data.repositorities.CategoryRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ProductRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ScrapCriteriaRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.ProductViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ScrapCriteriaViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    public Product depreciate(Product product){
        int y = product.getAge();
        double rate = product.getRate_of_depreciation(), growth = product.getDeprGrowth(), price = product.getPrice();
        for(int i=0;i<y;i++){
            price = price - (price*rate/100);
            rate = rate+growth;
        }
        product.setRate_of_depreciation(rate);
        product.setCurrentPrice(price);
        return product;
    }

    public String createProduct(Product newProduct){
            newProduct = depreciate(newProduct);
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
                                u.getAge(),
                                u.getCriteria() == null ? null : u.getCriteria().getId().toString(),
                                u.getCriteria() == null ? null : u.getCriteria().toString(),
                                u.getCurrentPrice(),
                                u.getDeprGrowth()
                        )).collect(Collectors.toList()));
    }
    public ObservableList<ProductViewModel> searchProducts(String name, Date dateFrom, Date dateTo) {
        List<Product> products = repository.searchProducts(name,dateFrom,dateTo);
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
                                u.getAge(),
                                u.getCriteria() == null ? null : u.getCriteria().getId().toString(),
                                u.getCriteria() == null ? null : u.getCriteria().toString(),
                                u.getCurrentPrice(),
                                u.getDeprGrowth()
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
                                u.getAge(),
                                u.getCriteria() == null ? null : u.getCriteria().getId().toString(),
                                u.getCriteria() == null ? null : u.getCriteria().toString(),
                                u.getCurrentPrice(),
                                u.getDeprGrowth()

                        )).collect(Collectors.toList()));
    }

    public ObservableList<ProductViewModel> getMAProducts() {
        List<Product> products = repository.getMAProducts();
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
                                u.getAge(),
                                u.getCriteria() == null ? null : u.getCriteria().getId().toString(),
                                u.getCriteria() == null ? null : u.getCriteria().toString(),
                                u.getCurrentPrice(),
                                u.getDeprGrowth()
                        )).collect(Collectors.toList()));
    }

    public ObservableList<ProductViewModel> getDMAProducts() {
        List<Product> products = repository.getDMAProducts();
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
                                u.getAge(),
                                u.getCriteria() == null ? null : u.getCriteria().getId().toString(),
                                u.getCriteria() == null ? null : u.getCriteria().toString(),
                                u.getCurrentPrice(),
                                u.getDeprGrowth()
                        )).collect(Collectors.toList()));
    }

    public ScrapCriteria findCrit(long id){
        ScrapCriteriaRepository criteriaRepository = ScrapCriteriaRepository.getInstance();
        return criteriaRepository.getById(id);
    }

    public Product transform(Product product){
        double percentageDecreased = -100*((product.getCurrentPrice()-product.getPrice())/product.getPrice());
        System.out.println(percentageDecreased);
        if(product.getCriteria().getYears() <= product.getAge() && product.getCriteria().getDepreciation() <= product.getRate_of_depreciation()
        && product.getCriteria().getPriceDrop() <= percentageDecreased){
            product.setType(0);
            Date date = new Date();
            product.setDate_of_transformation(date);
            System.out.println("The product was transformed to MA");    //v izvestie
        }
        return product;
    }

    public void addCriteriaToProduct(ScrapCriteriaViewModel criteriaModel, ProductViewModel productModel){
        Product product = repository.getById(productModel.getId());
        ScrapCriteria criteria = this.findCrit(criteriaModel.getId());

        product.setCriteria(criteria);
        product = transform(product);
        repository.update(product);
    }
    public int getTotalProducts(){
        Long count = repository.getTotalProducts();
        return count.intValue();
    }

}
