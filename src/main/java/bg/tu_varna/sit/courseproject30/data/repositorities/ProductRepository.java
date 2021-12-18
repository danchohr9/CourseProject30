package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.*;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements DAORepository<Product>{

    private static final Logger log = Logger.getLogger(ProductRepository.class);

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
            log.info("Product saved successfully");
        } catch (Exception ex) {
            log.error("Product save error" + ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
        }
    }
    public void updateWithQuantity(Product obj) {
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
    public void update(Product obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        Product product = (Product)session.get(Product.class, obj.getId());
        product.setName(obj.getName());
        product.setDescription(obj.getDescription());
        product.setFull_description(obj.getFull_description());
        product.setCategory(obj.getCategory());
        product.setPrice(obj.getPrice());
        product.setType(obj.getType());
        product.setAge(obj.getAge());
        product.setQuantity(obj.getQuantity());
        product.setRate_of_depreciation(obj.getRate_of_depreciation());
        product.setDate_of_transformation(obj.getDate_of_transformation());
        product.setDate_of_registration(obj.getDate_of_registration());

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
    public void delete(Product obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        Product product = (Product)session.get(Product.class, obj.getId());
        session.delete(product);

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
    public Product findById(Long id){
        Session session = Connection.openSession();
        Product cat = (Product) session.load(Product.class, id);
        session.close();
        return cat;
    }
    //TODO:Merge findById and getById in one
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
