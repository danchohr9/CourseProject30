package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.data.entities.*;
import bg.tu_varna.sit.courseproject30.data.repositorities.ClientProductRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ProductRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ScrapRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.UserRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.ScrapViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.ToScrapViewModel;
import bg.tu_varna.sit.courseproject30.presentation.models.UserViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;
import java.util.stream.Collectors;

public class ScrapService {
    public static ScrapService getInstance() {
        return ScrapService.ScrapServiceHolder.INSTANCE;
    }
    public final ScrapRepository scrapRepository = ScrapRepository.getInstance();
    public final ClientProductRepository clientProductRepository = ClientProductRepository.getInstance();
    public final ProductRepository productRepository = ProductRepository.getInstance();
    private NotificationService notificationService = NotificationService.getInstance();
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

    public void ScrapProduct(ToScrapViewModel toScrap, UserViewModel user){
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
        String title = toScrap.getProduct() + " was scrapped";
        String message = toScrap.getProduct() + " was scrapped by user "+user.getEmail()+" on "+date.toString()+".";
        Notification notification = notificationService.createNotification(title,message,date);
        notificationService.sendNotifications(notification);
        scrapRepository.save(newScrap);
    }

}
