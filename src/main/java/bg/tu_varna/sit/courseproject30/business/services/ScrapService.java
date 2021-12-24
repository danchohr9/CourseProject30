package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.ProductClient;
import bg.tu_varna.sit.courseproject30.data.entities.Scrap;
import bg.tu_varna.sit.courseproject30.data.repositorities.ClientProductRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ProductRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ScrapRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.ScrapViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ToScrapViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ScrapService {
    public static ScrapService getInstance() {
        return ScrapService.ScrapServiceHolder.INSTANCE;
    }
    public final ScrapRepository scrapRepository = ScrapRepository.getInstance();
    public final ClientProductRepository clientProductRepository = ClientProductRepository.getInstance();
    public final ProductRepository productRepository = ProductRepository.getInstance();
    private static class ScrapServiceHolder {
        public static final ScrapService INSTANCE = new ScrapService();
    }

    public ObservableList<ScrapViewModel> getProducts() {
        List<Scrap> scraps = scrapRepository.getAll();

        return FXCollections.observableList(
                scraps
                        .stream()
                        .map(u -> new ScrapViewModel(
                                Math.toIntExact(u.getId()),
                                u.getScrap_date().toString(),
                                u.getProduct().getId(),
                                u.getProduct().getName(),
                                u.getQuantity()
                        )).collect(Collectors.toList()));
    }

    public void ScrapProduct(ToScrapViewModel toScrap){
        Date date = new Date();
        Scrap newScrap;
        if(toScrap.getIdProductClient()!=null){
            ProductClient toEdit = clientProductRepository.getById(Integer.parseInt(toScrap.getIdProductClient()));
            toEdit.setQuantity(0);
            toEdit.setDate_removed(date);
            clientProductRepository.update(toEdit);
            newScrap = new Scrap(date, toScrap.getQuantity(), toEdit.getProduct());
        }else{
            Product toEdit = productRepository.getById(toScrap.getIdProduct());
            toEdit.setQuantity(0);
            productRepository.update(toEdit);
            newScrap = new Scrap(date, toScrap.getQuantity(), toEdit);
        }
        scrapRepository.save(newScrap);
    }
}
