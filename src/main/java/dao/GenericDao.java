package dao;

import java.util.List;

/**
 * @author Thom van de Pas on 8-3-2018
 */
public interface GenericDao<T> {

    T create(T t);

    T update(T t);

    void deleteById(Long id);

    void delete(T t);

    T findById(Long id);

    List<T> findAll();
}
