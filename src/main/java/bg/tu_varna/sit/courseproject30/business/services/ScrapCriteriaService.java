package bg.tu_varna.sit.courseproject30.business.services;


import bg.tu_varna.sit.courseproject30.data.entities.ScrapCriteria;
import bg.tu_varna.sit.courseproject30.data.entities.User;
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

    public String createCriteria(int years, int months, int depreciation){
        ScrapCriteriaViewModel newCriteria = new ScrapCriteriaViewModel(years, months, depreciation);
        if(years!=0 && months!=0 && depreciation!=0) {
            if (years > 100 || months > 1000) return "Years/months out of limit.";
            if(depreciation > 75) return "Depreciation is too much.";
            if(!checkIfExists(newCriteria)){
                ScrapCriteria scrapCriteria = new ScrapCriteria(years,months,depreciation);
                repository.save(scrapCriteria);
                criteria.add(scrapCriteria);
                return "Scrap criteria successfully added.";
            }else return "Criteria already exists.";
        }else{
            return "Please fill each tab.";
        }
    }


    public ObservableList<ScrapCriteriaViewModel> getAllCriteria() {
        //List<ScrapCriteria> criteria = repository.getAll();       //posle da dobavqm novite sled suzdavaneto

        return FXCollections.observableList(
                criteria
                        .stream()
                        .map(c -> new ScrapCriteriaViewModel(
                                c.getYears(),
                                c.getMonth(),
                                c.getDepreciation()
                        )).collect(Collectors.toList()));
    }

    public boolean checkIfExists(ScrapCriteriaViewModel criteria){
        boolean state=false;
        ObservableList<ScrapCriteriaViewModel> allCriteria = this.getAllCriteria();
        for (ScrapCriteriaViewModel c:allCriteria){
            if(c.equals(criteria)){
                state=true;
                return state;
            }
        }
        return state;
    }
}
