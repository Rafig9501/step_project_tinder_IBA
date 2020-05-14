package dao;

public interface DAO<T> {

    T create(T t);
    T get(int id);
    T delete(T t);
    T update(T t);
}
