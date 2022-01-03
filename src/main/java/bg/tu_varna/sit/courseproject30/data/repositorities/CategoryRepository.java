package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.Category;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.ScrapCriteria;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CategoryRepository implements DAORepository<Category> {
    private static final Logger log = Logger.getLogger(UserRepository.class);

    public static CategoryRepository getInstance() { return CategoryRepository.ProductRepositoryHolder.INSTANCE;}

    private static class ProductRepositoryHolder {
        public static final CategoryRepository INSTANCE = new CategoryRepository();
    }

    public void save(Category obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
        } catch (Exception ex) {
        } finally {
            transaction.commit();
        }
    }

    public void update(Category obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        Category category = (Category)session.get(Category.class, obj.getId());
        category.setName(obj.getName());
        category.setDescription(obj.getDescription());

        try{
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Category obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        Category category = (Category)session.get(Category.class, obj.getId());
        session.delete(category);

        try{
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Category> getByIg(Long id) {
        return Optional.empty();
    }
    public Category findById(Long id){
        Session session = Connection.openSession();
        Category cat = (Category) session.load(Category.class, id);
        return cat;
    }

/*
    Not used and not working
    public Category find(int id){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = "SELECT c FROM Category c where  c.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            List results = query.getResultList();
            System.out.println("Getting category");
            if (results != null && !results.isEmpty()) {
                log.info("Get Category");
                System.out.println("Getting category success");

                return (Category) results.get(0);
            }
            log.info("Category not found");
        }catch (Exception ex){
            log.error("Get criteria error: "+ex.getMessage());
        }finally {
            transaction.commit();
            session.close();
//            Connection.openSessionClose();           // pri zatvarqne dava greshka, akso iskame da se log out-nem
        }
        return new Category();
    }

 */
    public List<Category> getAll() {

        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Category> categories = new LinkedList<>();

        try {
            String jpql = "SELECT p FROM Category p";
            categories.addAll(session.createQuery(jpql, Category.class).getResultList());
            log.info("Get all Categories");

        } catch (Exception ex) {
            log.error("Get User error: " + ex.getMessage());
        } finally {
            transaction.commit();
            //Connection.openSessionClose();           // pri zatvarqne dava greshka, akso iskame da se log out-nem
        }
        System.out.println("First " + categories);
        return categories;
    }
    public int latestInsertedId(){
        Session session = Connection.openSession();
        int id = (Integer) session.createSQLQuery("SELECT c.id FROM Category c ORDER BY c.id DESC LIMIT 1;").getSingleResult();
        session.close();
        return id;
    }
}
