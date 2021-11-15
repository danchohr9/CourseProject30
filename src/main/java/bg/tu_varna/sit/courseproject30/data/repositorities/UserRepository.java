package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserRepository implements DAORepository<User> {

    private static final Logger log = Logger.getLogger(UserRepository.class);

    private static UserRepository getInstance() { return UserRepository.UserRepositoryHolder.INSTANCE;}

    private static class UserRepositoryHolder {
        public static final UserRepository INSTANCE = new UserRepository();
    }

    @Override
    public void save(User obj) {
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
    public void update(User obj) {

    }

    @Override
    public void delete(User obj) {

    }

    @Override
    public Optional<User> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }


}