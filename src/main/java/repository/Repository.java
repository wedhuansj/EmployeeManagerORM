package repository;

import java.util.List;

public interface Repository<T> {
    void add(T t);
    void update(T t);
    void delete(String id, Class<T> clazz);
    T findById(String id, Class<T> clazz);
    List<T> findAll(Class<T> clazz);
}