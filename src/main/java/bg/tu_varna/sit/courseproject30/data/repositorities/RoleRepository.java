package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.Roles;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RoleRepository implements DAORepository<Roles>{

    private static final Logger log = Logger.getLogger(RoleRepository.class);

    public static RoleRepository getInstance() { return RoleRepository.RoleRepositoryHolder.INSTANCE;}

    private static class RoleRepositoryHolder {
        public static final RoleRepository INSTANCE = new RoleRepository();
    }

    @Override
    public void save(Roles obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("User saved succesfully");
        } catch (Exception ex) {
            log.error("User save error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Roles obj) {

    }

    @Override
    public void delete(Roles obj) {

    }

    @Override
    public Optional<Roles> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Roles> getAll() {

        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Roles> users = new LinkedList<>();
        try {
            String jpql = "SELECT r FROM Roles r";
            users.addAll(session.createQuery(jpql, Roles.class).getResultList());
            log.info("Get all tasks");
        } catch (Exception ex) {
            log.error("Get Task error: " + ex.getMessage());
        } finally {
            transaction.commit();
            Connection.openSessionClose();
        }

        return users;
    }
}
