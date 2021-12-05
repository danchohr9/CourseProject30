package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
}
