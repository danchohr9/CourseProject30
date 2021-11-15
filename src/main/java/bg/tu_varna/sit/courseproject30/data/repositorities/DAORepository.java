package bg.tu_varna.sit.courseproject30.data.repositorities;

import java.util.List;
import java.util.Optional;

public interface DAORepository<T> {
    void save(T obj);
    void update(T obj);
    void delete(T obj);
    Optional<T> getByIg(Long id);
    List<T> getAll();
}
