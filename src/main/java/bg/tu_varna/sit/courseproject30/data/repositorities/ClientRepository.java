package bg.tu_varna.sit.courseproject30.data.repositorities;

import bg.tu_varna.sit.courseproject30.data.access.Connection;
import bg.tu_varna.sit.courseproject30.data.entities.City;
import bg.tu_varna.sit.courseproject30.data.entities.Client;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

    }

    @Override
    public void delete(Client obj) {

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
}
