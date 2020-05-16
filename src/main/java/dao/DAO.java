package dao;

import java.util.Optional;

public interface DAO<T> {

    int create(T t);
    Optional<T> get(String id);
    T delete(T t);
    T getAll();
}
