package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.data.entities.Category;
import bg.tu_varna.sit.courseproject30.data.repositorities.CategoryRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.CategoryViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {
    public static CategoryService getInstance() {
        return CategoryService.ProductServiceHolder.INSTANCE;
    }
    public final CategoryRepository repository = CategoryRepository.getInstance();

    private static class ProductServiceHolder {
        public static final CategoryService INSTANCE = new CategoryService();
    }
    public Category findById(Long id){
        return repository.findById(id);
    }
    public void delete(CategoryViewModel cat){
        Category category = new Category();
        category.setId((long) cat.getId());
        repository.delete(category);
    }
    public void edit(Category cat){
        repository.update(cat);
    }
    public boolean createCategory(String name, String description){
        if (name == null || description == null || name.equals("")){
            return false;
        }
        Category category = new Category();
        category.setName(name);
        if(description.length() != 0){
            category.setDescription(description);
        } else {
            category.setDescription(null);
        }
        repository.save(category);
        return true;
    }
    public List<Category> getAll() {
        return repository.getAll();
    }
    public ObservableList<CategoryViewModel> getAllCategories() {
        List<Category> categories = repository.getAll();
        System.out.println(categories.toString());

        return FXCollections.observableList(
                categories
                        .stream()
                        .map(u -> new CategoryViewModel(
                                Integer.parseInt(u.getId().toString()),
                                u.getName(),
                                u.getDescription()
                        )).collect(Collectors.toList()));
    }
    public int latestInsertedId(){
        return repository.latestInsertedId();
    }
}
