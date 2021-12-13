package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.Client;
import bg.tu_varna.sit.courseproject30.data.entities.ProductClient;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ClientProductRepository implements DAORepository<ProductClient>{
    private static final Logger log = Logger.getLogger(ClientProductRepository.class);

    public static ClientProductRepository getInstance() { return ClientProductRepository.ClientProductRepositoryHolder.INSTANCE;}

    private static class ClientProductRepositoryHolder {
        public static final ClientProductRepository INSTANCE = new ClientProductRepository();
    }

    @Override
    public void save(ProductClient obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("ProductClient saved successfully");
        } catch (Exception ex) {
            log.error("ProductClient save error " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(ProductClient obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "UPDATE ProductClient pc set pc.date_removed = : removed " + "WHERE pc.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("removed", obj.getDate_removed());
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
    public void delete(ProductClient obj) {

    }

    @Override
    public Optional<ProductClient> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ProductClient> getAll() {

        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<ProductClient> productClients = new LinkedList<>();
        try {
            String jpql = "SELECT pc FROM ProductClient pc";
            productClients.addAll(session.createQuery(jpql, ProductClient.class).getResultList());
            log.info("Get all ProductClients");
        } catch (Exception ex) {
            log.error("Get ProductClient error: " + ex.getMessage());
        } finally {
            transaction.commit();
            //Connection.openSessionClose();
        }

        return productClients;
    }

    public List<ProductClient> getClientProducts(int id) {

        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<ProductClient> productClients = new LinkedList<>();
        try {
            String jpql = "SELECT pc FROM ProductClient pc where pc.client.id = :id and pc.date_removed = null";
            Query query = session.createQuery(jpql, ProductClient.class);
            query.setParameter("id", id);
            productClients.addAll(query.getResultList());
            log.info("Get all ProductClients");
        } catch (Exception ex) {
            log.error("Get ProductClient error: " + ex.getMessage());
        } finally {
            transaction.commit();
            //Connection.openSessionClose();
        }
        return productClients;
    }

    public List<ProductClient> getAvailableProducts() {

        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<ProductClient> productClients = new LinkedList<>();
        try {
            String jpql = "SELECT pc FROM ProductClient pc where pc.date_added = :null";
            Query query = session.createQuery(jpql, ProductClient.class);
            query.setParameter("null", null);
            productClients.addAll(query.getResultList());
            log.info("Get all ProductClients");
        } catch (Exception ex) {
            log.error("Get ProductClient error: " + ex.getMessage());
        } finally {
            transaction.commit();
            //Connection.openSessionClose();
        }
        return productClients;
    }

    public ProductClient getById(int id){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        ProductClient productClient = null;
        try {
            String hql = "SELECT pc FROM ProductClient pc where  pc.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                productClient = (ProductClient) results.get(0);
                log.info("Get productClient");
            }
        }catch (Exception ex){
            log.error("Get productClient error: "+ex.getMessage());
        }
        return productClient;
    }
}
