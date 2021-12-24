package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.Scrap;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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



}
