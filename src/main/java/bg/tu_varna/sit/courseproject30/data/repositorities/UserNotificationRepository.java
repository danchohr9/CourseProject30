package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import bg.tu_varna.sit.courseproject30.data.entities.UserNotification;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserNotificationRepository implements DAORepository<UserNotification> {

    private static final Logger log = Logger.getLogger(UserNotificationRepository.class);

    public static UserNotificationRepository getInstance() { return UserNotificationRepository.UserNotificationRepositoryHolder.INSTANCE;}

    private static class UserNotificationRepositoryHolder {
        public static final UserNotificationRepository INSTANCE = new UserNotificationRepository();
    }

    @Override
    public void save(UserNotification obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("UserNotification saved successfully");
        } catch (Exception ex) {
            log.error("UserNotification save error " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(UserNotification obj) {

    }

    @Override
    public void delete(UserNotification obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = "DELETE UserNotification un WHERE un.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", obj.getId());
            int result = query.executeUpdate();
            if (result > 0 ) {
                log.info("UserNotification was successfully removed.");
            }
        }catch (Exception ex){
            log.error("Delete UserNotification error: "+ex.getMessage());
        }finally {
            transaction.commit();
            session.close();
            //Connection.openSessionClose();           // pri zatvarqne dava greshka, akso iskame da se log out-nem
        }
    }

    public void deleteAllOfUser(User user){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = "DELETE UserNotification un WHERE un.user = :user";
            Query query = session.createQuery(hql);
            query.setParameter("user", user);
            int result = query.executeUpdate();
            if (result > 0 ) {
                log.info("UserNotification was successfully removed.");
            }
        }catch (Exception ex){
            log.error("Delete UserNotification error: "+ex.getMessage());
        }finally {
            transaction.commit();
            session.close();
            //Connection.openSessionClose();           // pri zatvarqne dava greshka, akso iskame da se log out-nem
        }
    }

    @Override
    public Optional<UserNotification> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UserNotification> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<UserNotification> userNotifications = new LinkedList<>();

        try {
            String jpql = "SELECT un FROM UserNotification un";
            userNotifications.addAll(session.createQuery(jpql, UserNotification.class).getResultList());
            log.info("Get all UserNotifications");

        } catch (Exception ex) {
            log.error("Get UserNotification error: " + ex.getMessage());
        } finally {
            transaction.commit();
            //Connection.openSessionClose();
        }
        System.out.println("First " + userNotifications);

        return userNotifications;
    }

    public List<UserNotification> getAllOfUser(String username){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<UserNotification> userNotifications = new LinkedList<>();

        try {
            String jpql = "SELECT un FROM UserNotification un WHERE un.user.username = :username";
            Query query = session.createQuery(jpql, UserNotification.class);
            query.setParameter("username",username);
            userNotifications.addAll(query.getResultList());
            log.info("Get all UserNotifications");

        } catch (Exception ex) {
            log.error("Get UserNotification error: " + ex.getMessage());
        } finally {
            transaction.commit();
            //Connection.openSessionClose();
        }
        System.out.println("First " + userNotifications);

        return userNotifications;
    }
}
