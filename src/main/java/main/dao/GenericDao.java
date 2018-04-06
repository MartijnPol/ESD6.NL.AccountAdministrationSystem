package main.dao;

import main.domain.BaseEntity;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Thom van de Pas on 8-3-2018
 */
public interface GenericDao<T> {

    void deleteById(Long id);

    void delete(T t);

    T findById(Long id);

    List<T> findAll();

    T oneResult(TypedQuery<T> query);

    <T extends BaseEntity> T createOrUpdate(T t);

}
