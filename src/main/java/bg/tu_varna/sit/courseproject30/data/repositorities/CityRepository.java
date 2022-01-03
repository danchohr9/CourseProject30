package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.City;
import bg.tu_varna.sit.courseproject30.data.entities.Roles;
import bg.tu_varna.sit.courseproject30.data.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CityRepository implements DAORepository<City>{

    private static final Logger log = Logger.getLogger(CityRepository.class);

    public static CityRepository getInstance() { return CityRepository.CityRepositoryHolder.INSTANCE;}

    private static class CityRepositoryHolder {
        public static final CityRepository INSTANCE = new CityRepository();
    }

    @Override
    public void save(City obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("City saved succesfully");
        } catch (Exception ex) {
            log.error("City save error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(City obj) {

    }

    @Override
    public void delete(City obj) {

    }

    @Override
    public Optional<City> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<City> getAll() {

        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<City> cities = new LinkedList<>();
        try {
            String jpql = "SELECT c FROM City c";
            cities.addAll(session.createQuery(jpql, City.class).getResultList());
            log.info("Get all cities");
        } catch (Exception ex) {
            log.error("Get City error: " + ex.getMessage());
        } finally {
            transaction.commit();
            Connection.openSessionClose();
        }

        return cities;
    }

    public City getCityByName(String cityStr){
        City city = null;
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = " FROM City c WHERE c.name = :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", cityStr);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                city = (City) results.get(0);
                log.info("Get city");
            }
        }catch (Exception ex){
            log.error("Get city error: "+ex.getMessage());
        }
        return city;
    }
    public int latestInsertedId(){
        Session session = Connection.openSession();
        int id = (Integer) session.createSQLQuery("SELECT c.id FROM City c ORDER BY c.id DESC LIMIT 1;").getSingleResult();
        session.close();
        return id;
    }
}
