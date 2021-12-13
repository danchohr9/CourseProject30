package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.Client;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.ProductClient;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements DAORepository<Product>{

    private static final Logger log = Logger.getLogger(UserRepository.class);

    public static ProductRepository getInstance() { return ProductRepository.ProductRepositoryHolder.INSTANCE;}

    private static class ProductRepositoryHolder {
        public static final ProductRepository INSTANCE = new ProductRepository();
    }

    @Override
    public void save(Product obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
        } catch (Exception ex) {
        } finally {
            transaction.commit();
        }
    }


    public void update(Product obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "UPDATE Product p set p.quantity = : quantity " + "WHERE p.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("quantity", obj.getQuantity());
            query.setParameter("id", obj.getId());
            int result = query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product obj) {

    }

    @Override
    public Optional<Product> getByIg(Long id) {
        return Optional.empty();
    }

    public List<Product> getAll() {

        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Product> products = new LinkedList<>();

        try {
            String jpql = "SELECT p FROM Product p";
            products.addAll(session.createQuery(jpql, Product.class).getResultList());
            log.info("Get all Products");

        } catch (Exception ex) {
            log.error("Get User error: " + ex.getMessage());
        } finally {
            transaction.commit();
            //Connection.openSessionClose();           // pri zatvarqne dava greshka, akso iskame da se log out-nem
        }
        System.out.println("First " + products);

        return products;
    }

    public List<Product> getAvailableProducts(){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Product> products = new LinkedList<>();

        try {
            String jpql = "SELECT p FROM Product p where p.quantity != 0";   //ne znam zashto go dava za greshka kato raboti
            products.addAll(session.createQuery(jpql, Product.class).getResultList());
            log.info("Get available Products");

        } catch (Exception ex) {
            log.error("Get Products error: " + ex.getMessage());
        } finally {
            transaction.commit();
            //Connection.openSessionClose();           // pri zatvarqne dava greshka, akso iskame da se log out-nem
        }
        System.out.println("First " + products);

        return products;
    }

    public Product getById(int id){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = null;
        try {
            String hql = "SELECT p FROM Product p where  p.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                product = (Product) results.get(0);
                log.info("Get product");
            }
        }catch (Exception ex){
            log.error("Get product error: "+ex.getMessage());
        }
        return product;
    }
}
