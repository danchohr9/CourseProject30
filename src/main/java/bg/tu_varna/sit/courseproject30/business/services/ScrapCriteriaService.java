package bg.tu_varna.sit.courseproject30.business.services;


import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.ScrapCriteria;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import bg.tu_varna.sit.courseproject30.data.repositorities.ProductRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ScrapCriteriaRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.ScrapCriteriaViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.stream.Collectors;


public class ScrapCriteriaService{
    private final ScrapCriteriaRepository repository = ScrapCriteriaRepository.getInstance();

    public static ScrapCriteriaService getInstance() {
        return ScrapCriteriaService.ScrapCriteriaHolder.INSTANCE;
    }

    List<ScrapCriteria> criteria = repository.getAll();

    private static class ScrapCriteriaHolder {
        public static final ScrapCriteriaService INSTANCE = new ScrapCriteriaService();
    }

    public String createCriteria(int years, double priceDrop, double depreciation, ObservableList<ScrapCriteriaViewModel> criteriaViewModels){
        ScrapCriteriaViewModel newCriteria = new ScrapCriteriaViewModel(0,years, priceDrop, depreciation);
        if(years!=0 && priceDrop!=0 && depreciation!=0) {
            if (years > 100 || priceDrop > 1000) return "Years/months out of limit.";
            if(depreciation > 75) return "Depreciation is too much.";
            if(repository.getCriteria(years,priceDrop,depreciation)==null){
                ScrapCriteria scrapCriteria = new ScrapCriteria(0L,years,priceDrop,depreciation);
                repository.save(scrapCriteria);
                criteria.add(scrapCriteria);
                return "Scrap criteria successfully added.";
            }else return "Criteria already exists.";
        }else{
            return "Please fill each tab.";
        }
    }

    public void updateCriteria(ScrapCriteria obj){
        repository.update(obj);
    }
    public void removeCriteria(ScrapCriteriaViewModel criteriaModel){
        ScrapCriteria criteriaToDelete = new ScrapCriteria((long) criteriaModel.getId(), criteriaModel.getYears(), criteriaModel.getPriceDrop(),
                criteriaModel.getDepreciation());
        ProductRepository productRepository = ProductRepository.getInstance();
        List<Product> products = productRepository.getByCriteria(criteriaModel.getId());
        if(products!=null) {
            for (Product product:products) {
                product.setCriteria(null);
                productRepository.update(product);
            }
        }
        repository.delete(criteriaToDelete);
        criteria.removeAll(criteria);
        criteria = repository.getAll();
    }


    public ObservableList<ScrapCriteriaViewModel> getAllCriteria() {

        return FXCollections.observableList(
                criteria
                        .stream()
                        .map(c -> new ScrapCriteriaViewModel(
                                c.getId().intValue(),
                                c.getYears(),
                                c.getPriceDrop(),
                                c.getDepreciation()
                        )).collect(Collectors.toList()));
    }

}
