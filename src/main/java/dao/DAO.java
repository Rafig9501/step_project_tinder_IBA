package dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    int create(T t);
    Optional<T> get(String id);
    int delete(T t);
    List<T> getAll();
}
