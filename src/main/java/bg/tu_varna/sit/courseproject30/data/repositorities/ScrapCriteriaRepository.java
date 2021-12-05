package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.ScrapCriteria;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        }
    }

    @Override
    public void update(ScrapCriteria obj) {

    }

    @Override
    public void delete(ScrapCriteria obj) {

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
            //Connection.openSessionClose();           // pri zatvarqne dava greshka, akso iskame da se log out-nem
        }

        return criteria;
    }

}
