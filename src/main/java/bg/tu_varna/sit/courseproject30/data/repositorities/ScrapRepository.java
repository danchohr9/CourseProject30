package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.Scrap;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.*;

public class ScrapRepository implements DAORepository<Scrap>{

    private static final Logger log = Logger.getLogger(ScrapRepository.class);

    public static ScrapRepository getInstance() { return ScrapRepository.ScrapRepositoryHolder.INSTANCE;}

    @Override
    public void save(Scrap obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Scrap saved successfully");
        } catch (Exception ex) {
            log.error("Scrap save error " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Scrap obj) {

    }

    @Override
    public void delete(Scrap obj) {

    }

    @Override
    public Optional<Scrap> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Scrap> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Scrap> scraps = new LinkedList<>();

        try {
            String jpql = "SELECT s FROM Scrap s";
            scraps.addAll(session.createQuery(jpql, Scrap.class).getResultList());
            log.info("Get all Scraps");

        } catch (Exception ex) {
            log.error("Get Scrap error: " + ex.getMessage());
        } finally {
            transaction.commit();
            //Connection.openSessionClose();
        }
        System.out.println("First " + scraps);

        return scraps;
    }

    private static class ScrapRepositoryHolder {
        public static final ScrapRepository INSTANCE = new ScrapRepository();
    }

    public List<Scrap> searchScraps(String name, Date dateFrom, Date dateTo) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Scrap> scraps = new LinkedList<>();

        if(Objects.equals(name, "") && dateFrom == null && dateTo == null){
            return  scraps;
        }
        if(dateFrom != null && dateTo != null){
            String hql = "SELECT p FROM Scrap p " + "WHERE (p.product.name LIKE :name) and (p.scrap_date BETWEEN :stDate AND :edDate)";
            Query query = session.createQuery(hql);
            query.setParameter("name", "%"+name+"%");
            query.setParameter("stDate",dateFrom);
            query.setParameter("edDate",dateTo);
            scraps = query.getResultList();
        } else if(dateFrom != null && dateTo == null){
            String hql = "SELECT p FROM Scrap p " + "WHERE (p.product.name LIKE :name) and (p.scrap_date > :stDate)";
            Query query = session.createQuery(hql);
            query.setParameter("name", "%"+name+"%");
            query.setParameter("stDate",dateFrom);
            scraps = query.getResultList();
        } else if(dateFrom == null && dateTo != null){
            String hql = "SELECT p FROM Scrap p " + "WHERE (p.product.name LIKE :name) and (p.scrap_date < :stDate)";
            Query query = session.createQuery(hql);
            query.setParameter("name", "%"+name+"%");
            query.setParameter("stDate",dateTo);
            scraps = query.getResultList();
        }  else if(dateFrom == null && dateTo == null){
            String hql = "SELECT p FROM Scrap p " + "WHERE p.product.name LIKE :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", "%"+name+"%");
            scraps = query.getResultList();
        }

        try {
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return scraps;
    }

}

