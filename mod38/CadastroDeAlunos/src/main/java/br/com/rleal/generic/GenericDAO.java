package br.com.rleal.generic;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDAO<T extends Serializable> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> clazz;

    public GenericDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void salvar(T entity) {
        em.persist(entity);
    }

    public void atualizar(T entity) {
        em.merge(entity);
    }

    public void excluir(T entity) {
        em.remove(em.merge(entity));
    }

    public T buscarPorId(Object id) {
        return em.find(clazz, id);
    }

    public List<T> buscarTodos() {
        return em.createQuery("from " + clazz.getName(), clazz).getResultList();
    }
}