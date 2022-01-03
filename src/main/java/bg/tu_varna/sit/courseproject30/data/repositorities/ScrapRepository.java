package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.Scrap;
import bg.tu_varna.sit.courseproject30.data.entities.ScrapCriteria;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        Scrap scrap = (Scrap) session.get(Scrap.class, obj.getId());
        session.delete(scrap);

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
    public Optional<Scrap> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Scrap> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Scrap> scraps = new LinkedList<>();
        Date date = new Date();
        System.out.println(date.toString());
        try {
            String jpql = "SELECT s FROM Scrap s";
            Query query = session.createQuery(jpql, Scrap.class);
            scraps.addAll(query.getResultList());
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

    public Scrap getLastInserted(){
        Scrap scrap = null;
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = " FROM Scrap s WHERE s.id = (SELECT MAX(s2.id) FROM Scrap s2)";
            Query query = session.createQuery(hql);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                scrap = (Scrap) results.get(0);
                log.info("Get scrap");
            }
        }catch (Exception ex){
            log.error("Get scrap error: "+ex.getMessage());
        }finally {
            transaction.commit();
//            Connection.openSessionClose();           // pri zatvarqne dava greshka, akso iskame da se log out-nem
        }
        return scrap;
    }
}

