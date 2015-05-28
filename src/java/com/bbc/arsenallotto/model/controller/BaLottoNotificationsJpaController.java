/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.model.controller;

import com.bbc.arsenallotto.model.BaLottoNotifications;
import com.bbc.arsenallotto.model.controller.exceptions.NonexistentEntityException;
import com.bbc.arsenallotto.model.controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Soul
 */
public class BaLottoNotificationsJpaController implements Serializable {

    public BaLottoNotificationsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;
    private EntityManager em = null;

    public BaLottoNotificationsJpaController(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEntityManager() {
        if (em == null) {
            em = emf.createEntityManager();
        }

        return em;
    }

    public boolean create(BaLottoNotifications baLottoNotifications) throws RollbackFailureException, Exception {
        boolean created = false;

        EntityManager em = null;
        try {
            if (utx != null) {
                utx.begin();
            }
            em = getEntityManager();
            em.persist(baLottoNotifications);
            if (utx != null) {
                utx.commit();
            }
            created = true;
        } catch (Exception ex) {
            try {
                if (utx != null) {
                    utx.rollback();
                }
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } /*finally {
            if (em != null) {
                em.close();
            }
        }*/

        return created;
    }

    public void edit(BaLottoNotifications baLottoNotifications) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            if (utx != null) {
                utx.begin();
            }
            em = getEntityManager();
            baLottoNotifications = em.merge(baLottoNotifications);
            if (utx != null) {
                utx.commit();
            }
        } catch (Exception ex) {
            try {
                if (utx != null) {
                    utx.rollback();
                }
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = baLottoNotifications.getId();
                if (findBaLottoNotifications(id) == null) {
                    throw new NonexistentEntityException("The baLottoNotifications with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            if (utx != null) {
                utx.begin();
            }
            em = getEntityManager();
            BaLottoNotifications baLottoNotifications;
            try {
                baLottoNotifications = em.getReference(BaLottoNotifications.class, id);
                baLottoNotifications.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baLottoNotifications with id " + id + " no longer exists.", enfe);
            }
            em.remove(baLottoNotifications);
            if (utx != null) {
                utx.commit();
            }
        } catch (Exception ex) {
            try {
                if (utx != null) {
                    utx.rollback();
                }
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BaLottoNotifications> findBaLottoNotificationsEntities() {
        return findBaLottoNotificationsEntities(true, -1, -1);
    }

    public List<BaLottoNotifications> findBaLottoNotificationsEntities(int maxResults, int firstResult) {
        return findBaLottoNotificationsEntities(false, maxResults, firstResult);
    }

    private List<BaLottoNotifications> findBaLottoNotificationsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BaLottoNotifications.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BaLottoNotifications findBaLottoNotifications(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BaLottoNotifications.class, id);
        } finally {
            em.close();
        }
    }

    public int getBaLottoNotificationsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BaLottoNotifications> rt = cq.from(BaLottoNotifications.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public boolean isDuplicate(Short gameId, String drawDate) {
        boolean isDuplicate = false;
        EntityManager em = getEntityManager();
        TypedQuery<BaLottoNotifications> queryNotification = em.createNamedQuery("BaLottoNotifications.findDuplicate", BaLottoNotifications.class);
//set dynamic data for query
        queryNotification.setParameter("gameId", gameId);
        queryNotification.setParameter("drawDate", drawDate);
        
        try {
//execute query and get results
            queryNotification.getSingleResult();
            isDuplicate = true; 
        } catch (NoResultException nre) {
            Logger.getLogger(BaLottoNotificationsJpaController.class.getName()).log(Level.INFO, null, nre);
        } finally {
            if (em != null && emf != null) {
                em.close();
            }
        }

        return isDuplicate;
    }
}
