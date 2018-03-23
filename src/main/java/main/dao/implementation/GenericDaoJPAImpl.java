package main.dao.implementation;

import main.dao.GenericDao;
import main.dao.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Thom van de Pas on 1-3-2018
 */
@Stateless
@JPA
public abstract class GenericDaoJPAImpl<T> implements GenericDao<T> {

    @PersistenceContext(unitName = "AccountAdministrationPU")
    protected EntityManager entityManager;

    private Class<T> type;

    public GenericDaoJPAImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    public T create(T t) {
        this.entityManager.persist(t);
        return t;
    }

    public T update(T t) {
        return this.entityManager.merge(t);
    }

    public void deleteById(Long id) {
        this.entityManager.remove(findById(id));
    }

    public void delete(T t) {
        this.entityManager.remove(this.entityManager.merge(t));
    }

    public T findById(Long id) {
        return (T) this.entityManager.find(type, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("SELECT t FROM " + type.getSimpleName() + " t", type).getResultList();
    }

    public T oneResult(TypedQuery<T> query) {
        query.setMaxResults(1);
        return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
    }


    //<editor-fold desc="Getters/Setters">
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    //</editor-fold>
}
