package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.ScrapCriteria;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import bg.tu_varna.sit.courseproject30.presentation.models.ScrapCriteriaViewModel;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ScrapCriteriaRepository implements DAORepository<ScrapCriteria> {
    private static final Logger log = Logger.getLogger(ScrapCriteriaRepository.class);

    public static ScrapCriteriaRepository getInstance() { return ScrapCriteriaRepository.ScrapCriteriaRepositoryHolder.INSTANCE;}

    private static class ScrapCriteriaRepositoryHolder {
        public static final ScrapCriteriaRepository INSTANCE = new ScrapCriteriaRepository();
    }

    public void save(ScrapCriteria obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Scrap criteria saved successfully");
        } catch (Exception ex) {
            log.error("Scrap criteria save error" + ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void update(ScrapCriteria obj) {

    }

    @Override
    public void delete(ScrapCriteria obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = "DELETE ScrapCriteria sc WHERE sc.years = :years AND sc.priceDrop = :priceDrop AND sc.depreciation = :depreciation";
            Query query = session.createQuery(hql);
            query.setParameter("years", obj.getYears());
            query.setParameter("priceDrop", obj.getPriceDrop());
            query.setParameter("depreciation", obj.getDepreciation());
            int result = query.executeUpdate();
            if (result > 0 ) {
                log.info("Criteria was successfully removed.");
            }
        }catch (Exception ex){
            log.error("Delete criteria error: "+ex.getMessage());
        }finally {
            transaction.commit();
            session.close();
            //Connection.openSessionClose();           // pri zatvarqne dava greshka, akso iskame da se log out-nem
        }
    }

    @Override
    public Optional<ScrapCriteria> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ScrapCriteria> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<ScrapCriteria> criteria = new LinkedList<>();
        try {
            String jpql = "SELECT c FROM ScrapCriteria c";
            criteria.addAll(session.createQuery(jpql, ScrapCriteria.class).getResultList());
            log.info("Get all criteria");
        } catch (Exception ex) {
            log.error("Get Criteria error: " + ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
//            Connection.openSessionClose();           // pri zatvarqne dava greshka, akso iskame da se log out-nem
        }

        return criteria;
    }

    public ScrapCriteria getCriteria(int years, double priceDrop, double depreciation){
        ScrapCriteria scrapCriteria = null;
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = " FROM ScrapCriteria sc WHERE sc.years = :years AND sc.priceDrop = :priceDrop AND sc.depreciation = :depreciation";
            Query query = session.createQuery(hql);
            query.setParameter("years", years);
            query.setParameter("priceDrop", priceDrop);
            query.setParameter("depreciation", depreciation);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                scrapCriteria = (ScrapCriteria) results.get(0);
                log.info("Get criteria");
            }
        }catch (Exception ex){
            log.error("Get criteria error: "+ex.getMessage());
        }finally {
        transaction.commit();
//            Connection.openSessionClose();           // pri zatvarqne dava greshka, akso iskame da se log out-nem
        }
        return scrapCriteria;
    }

    public ScrapCriteria getById(long id){
        ScrapCriteria scrapCriteria = null;
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = " FROM ScrapCriteria sc WHERE sc.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                scrapCriteria = (ScrapCriteria) results.get(0);
                log.info("Get criteria");
            }
        }catch (Exception ex){
            log.error("Get criteria error: "+ex.getMessage());
        }finally {
            transaction.commit();
//            Connection.openSessionClose();           // pri zatvarqne dava greshka, akso iskame da se log out-nem
        }
        return scrapCriteria;
    }

//    public ScrapCriteria findById(Long id){
//        Session session = Connection.openSession();
//        ScrapCriteria cat = (ScrapCriteria) session.load(ScrapCriteria.class, id);
//        session.close();
//        return cat;
//    }

}
