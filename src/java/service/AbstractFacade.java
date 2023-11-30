/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.ws.rs.core.Response;
import java.util.List;
import model.entities.Game;
import model.entities.Game.Console;
import model.entities.Game.Type;

/**
 *
 * @author crist
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public Response create(T entity) {
        try {
            getEntityManager().persist(entity);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();

        }
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        jakarta.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<Game> findWithType(String type) {
        TypedQuery<Game> query = (TypedQuery<Game>) getEntityManager().createNamedQuery("game.findByType").setParameter("type", type);
        return query.getResultList();
    }

    public List<Game> findWithConsole(String console) {
        TypedQuery<Game> query = (TypedQuery<Game>) getEntityManager().createNamedQuery("game.findByConsole").setParameter("console", console);
        return query.getResultList();
    }

    public List<Game> findWithTypeAndConsole(String type, String console) {
        TypedQuery<Game> query = (TypedQuery<Game>) getEntityManager().createNamedQuery("game.findByTypeAndConsole");
        query.setParameter("type", Type.valueOf(type));
        query.setParameter("console", Console.valueOf(console));
        return query.getResultList();
    }
}
