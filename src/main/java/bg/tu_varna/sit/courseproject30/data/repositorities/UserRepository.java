package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.Category;
import bg.tu_varna.sit.courseproject30.data.entities.Product;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements DAORepository<User> {

    private static final Logger log = Logger.getLogger(UserRepository.class);

    public static UserRepository getInstance() { return UserRepository.UserRepositoryHolder.INSTANCE;}

    private static class UserRepositoryHolder {
        public static final UserRepository INSTANCE = new UserRepository();
    }

    @Override
    public void save(User obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("User saved successfully");
        } catch (Exception ex) {
            log.error("User save error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(User obj) {

        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        User user = (User)session.get(User.class, obj.getId());
        user.setEmail(obj.getEmail());
        user.setPassword(obj.getPassword());
        user.setUsername(obj.getUsername());

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
    public void delete(User obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        User user = (User)session.get(User.class, obj.getId());
        session.delete(user);

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
    public Optional<User> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {

        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = new LinkedList<>();
        try {
            String jpql = "SELECT u FROM User u";
            users.addAll(session.createQuery(jpql, User.class).getResultList());
            log.info("Get all users");
        } catch (Exception ex) {
            log.error("Get User error: " + ex.getMessage());
        } finally {
            transaction.commit();
            //Connection.openSessionClose();           // pri zatvarqne dava greshka, akso iskame da se log out-nem
        }

        return users;
    }
    public User getUserByUsername(String username){
        User user = null;
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = " FROM User u WHERE u.username = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                user = (User) results.get(0);
                log.info("Get user");
            }
        }catch (Exception ex){
            log.error("Get user error: "+ex.getMessage());
        }
        return user;
    }
    public Long getTotalUsers(){
        Session session = Connection.openSession();
        return (long)session.createQuery("SELECT COUNT(e) FROM User e").getSingleResult();
    }

    public User getLastInserted(){
        User user = null;
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = " FROM User u WHERE u.id = (SELECT MAX(u2.id) FROM User u2)";
            Query query = session.createQuery(hql);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                user = (User) results.get(0);
                log.info("Get User");
            }
        }catch (Exception ex){
            log.error("Get User error: "+ex.getMessage());
        }finally {
            transaction.commit();
//            Connection.openSessionClose();
        }
        return user;
    }
}