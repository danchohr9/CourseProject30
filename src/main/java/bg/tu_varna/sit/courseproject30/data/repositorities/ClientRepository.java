package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.*;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.*;

public class ClientRepository implements DAORepository<Client>{

    private static final Logger log = Logger.getLogger(ClientRepository.class);

    public static ClientRepository getInstance() { return ClientRepository.ClientRepositoryHolder.INSTANCE;}

    private static class ClientRepositoryHolder {
        public static final ClientRepository INSTANCE = new ClientRepository();
    }

    @Override
    public void save(Client obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Client saved successfully");
        } catch (Exception ex) {
            log.error("Client save error " + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Client obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        Client client = (Client)session.get(Client.class, obj.getId());
        client.setFirst_name(obj.getFirst_name());
        client.setLast_name(obj.getLast_name());
        client.setEgn(obj.getEgn());
        client.setCity(obj.getCity());
        client.setAddress(obj.getAddress());
        client.setPhone(obj.getPhone());

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
    public void delete(Client obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        Client client = (Client)session.get(Client.class, obj.getId());
        session.delete(client);

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
    public Optional<Client> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Client> getAll() {

        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Client> clients = new LinkedList<>();
        try {
            String jpql = "SELECT c FROM Client c";
            clients.addAll(session.createQuery(jpql, Client.class).getResultList());
            log.info("Get all clients");
        } catch (Exception ex) {
            log.error("Get Client error: " + ex.getMessage());
        } finally {
            transaction.commit();
            //Connection.openSessionClose();
        }

        return clients;
    }

    public Client getById(int id){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Client client = null;
        try {
            String hql = "SELECT c FROM Client c where  c.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                client = (Client) results.get(0);
                log.info("Get client");
            }
        }catch (Exception ex){
            log.error("Get client error: "+ex.getMessage());
        }
        return client;
    }
    public Long getTotalClients(){
        Session session = Connection.openSession();
        return (long)session.createQuery("SELECT COUNT(e) FROM Client e").getSingleResult();
    }

    public List<Client> searchClients(String name, Date dateFrom, Date dateTo) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Client> clients = new LinkedList<>();

        if(Objects.equals(name, "") && dateFrom == null && dateTo == null){
            return  clients;
        }
        if(dateFrom != null && dateTo != null){
            String hql = "SELECT p FROM Client p " + "WHERE ((p.first_name LIKE :name) OR (p.last_name LIKE :name)) and (p.register_date BETWEEN :stDate AND :edDate)";
            Query query = session.createQuery(hql);
            query.setParameter("name", "%"+name+"%");
            query.setParameter("stDate",dateFrom);
            query.setParameter("edDate",dateTo);
            clients = query.getResultList();
        } else if(dateFrom != null && dateTo == null){
            String hql = "SELECT p FROM Client p " + "WHERE ((p.first_name LIKE :name) OR (p.last_name LIKE :name)) and (p.register_date > :stDate)";
            Query query = session.createQuery(hql);
            query.setParameter("name", "%"+name+"%");
            query.setParameter("stDate",dateFrom);
            clients = query.getResultList();
        } else if(dateFrom == null && dateTo != null){
            String hql = "SELECT p FROM Client p " + "WHERE ((p.first_name LIKE :name) OR (p.last_name LIKE :name)) and (p.register_date < :stDate)";
            Query query = session.createQuery(hql);
            query.setParameter("name", "%"+name+"%");
            query.setParameter("stDate",dateTo);
            clients = query.getResultList();
        }  else if(dateFrom == null && dateTo == null){
            String hql = "SELECT p FROM Client p " + "WHERE p.first_name LIKE :name OR p.last_name LIKE :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", "%"+name+"%");
            clients = query.getResultList();
        }

        try {
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return clients;
    }

    public Client getLastInserted(){
        Client client = null;
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String hql = " FROM Client c WHERE c.id = (SELECT MAX(c2.id) FROM Client c2)";
            Query query = session.createQuery(hql);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                client = (Client) results.get(0);
                log.info("Get Client");
            }
        }catch (Exception ex){
            log.error("Get Client error: "+ex.getMessage());
        }finally {
            transaction.commit();
//            Connection.openSessionClose();
        }
        return client;
    }
}
