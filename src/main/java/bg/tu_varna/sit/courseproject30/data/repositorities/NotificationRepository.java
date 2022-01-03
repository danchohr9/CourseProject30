package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.Notification;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class NotificationRepository implements DAORepository<Notification>{
    private static final Logger log = Logger.getLogger(NotificationRepository.class);

    public static NotificationRepository getInstance() { return NotificationRepository.NotificationRepositoryHolder.INSTANCE;}

    private static class NotificationRepositoryHolder {
        public static final NotificationRepository INSTANCE = new NotificationRepository();
    }

    @Override
    public void save(Notification obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Notification saved successfully");
        } catch (Exception ex) {
            log.error("Notification save error " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Notification obj) {

    }

    @Override
    public void delete(Notification obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = "DELETE Notification un WHERE un.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", obj.getId());
            int result = query.executeUpdate();
            if (result > 0 ) {
                log.info("Notification was successfully removed.");
            }
        }catch (Exception ex){
            log.error("Delete Notification error: "+ex.getMessage());
        }finally {
            transaction.commit();
            session.close();
            //Connection.openSessionClose();           // pri zatvarqne dava greshka, akso iskame da se log out-nem
        }
    }

    @Override
    public Optional<Notification> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Notification> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Notification> notifications = new LinkedList<>();

        try {
            String jpql = "SELECT n FROM Notification n";
            notifications.addAll(session.createQuery(jpql, Notification.class).getResultList());
            log.info("Get all Notifications");

        } catch (Exception ex) {
            log.error("Get Notification error: " + ex.getMessage());
        } finally {
            transaction.commit();
            //Connection.openSessionClose();
        }
        System.out.println("First " + notifications);

        return notifications;
    }

    public Notification getById(long id){
        Notification notification = null;
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = " FROM Notification n WHERE n.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                notification = (Notification) results.get(0);
                log.info("Get notification");
            }
        }catch (Exception ex){
            log.error("Get notification error: "+ex.getMessage());
        }finally {
            transaction.commit();
//            Connection.openSessionClose();
        }
        return notification;
    }

    public Notification getLastInserted(){
        Notification notification = null;
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = " FROM Notification n WHERE n.id = (SELECT MAX(n2.id) FROM Notification n2)";
            Query query = session.createQuery(hql);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                notification = (Notification) results.get(0);
                log.info("Get notification");
            }
        }catch (Exception ex){
            log.error("Get notification error: "+ex.getMessage());
        }finally {
            transaction.commit();
//            Connection.openSessionClose();
        }
        return notification;
    }
}
