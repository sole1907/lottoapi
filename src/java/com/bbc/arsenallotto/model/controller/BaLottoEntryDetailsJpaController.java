/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.model.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.bbc.arsenallotto.model.BaLottoEntry;
import com.bbc.arsenallotto.model.BaLottoEntryDetails;
import com.bbc.arsenallotto.model.controller.exceptions.NonexistentEntityException;
import com.bbc.arsenallotto.model.controller.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Soul
 */
public class BaLottoEntryDetailsJpaController implements Serializable {

    public BaLottoEntryDetailsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BaLottoEntryDetails baLottoEntryDetails) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BaLottoEntry entryId = baLottoEntryDetails.getEntryId();
            if (entryId != null) {
                entryId = em.getReference(entryId.getClass(), entryId.getId());
                baLottoEntryDetails.setEntryId(entryId);
            }
            em.persist(baLottoEntryDetails);
            if (entryId != null) {
                entryId.getBaLottoEntryDetailsCollection().add(baLottoEntryDetails);
                entryId = em.merge(entryId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
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

    public void edit(BaLottoEntryDetails baLottoEntryDetails) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BaLottoEntryDetails persistentBaLottoEntryDetails = em.find(BaLottoEntryDetails.class, baLottoEntryDetails.getId());
            BaLottoEntry entryIdOld = persistentBaLottoEntryDetails.getEntryId();
            BaLottoEntry entryIdNew = baLottoEntryDetails.getEntryId();
            if (entryIdNew != null) {
                entryIdNew = em.getReference(entryIdNew.getClass(), entryIdNew.getId());
                baLottoEntryDetails.setEntryId(entryIdNew);
            }
            baLottoEntryDetails = em.merge(baLottoEntryDetails);
            if (entryIdOld != null && !entryIdOld.equals(entryIdNew)) {
                entryIdOld.getBaLottoEntryDetailsCollection().remove(baLottoEntryDetails);
                entryIdOld = em.merge(entryIdOld);
            }
            if (entryIdNew != null && !entryIdNew.equals(entryIdOld)) {
                entryIdNew.getBaLottoEntryDetailsCollection().add(baLottoEntryDetails);
                entryIdNew = em.merge(entryIdNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = baLottoEntryDetails.getId();
                if (findBaLottoEntryDetails(id) == null) {
                    throw new NonexistentEntityException("The baLottoEntryDetails with id " + id + " no longer exists.");
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
            utx.begin();
            em = getEntityManager();
            BaLottoEntryDetails baLottoEntryDetails;
            try {
                baLottoEntryDetails = em.getReference(BaLottoEntryDetails.class, id);
                baLottoEntryDetails.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baLottoEntryDetails with id " + id + " no longer exists.", enfe);
            }
            BaLottoEntry entryId = baLottoEntryDetails.getEntryId();
            if (entryId != null) {
                entryId.getBaLottoEntryDetailsCollection().remove(baLottoEntryDetails);
                entryId = em.merge(entryId);
            }
            em.remove(baLottoEntryDetails);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
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

    public List<BaLottoEntryDetails> findBaLottoEntryDetailsEntities() {
        return findBaLottoEntryDetailsEntities(true, -1, -1);
    }

    public List<BaLottoEntryDetails> findBaLottoEntryDetailsEntities(int maxResults, int firstResult) {
        return findBaLottoEntryDetailsEntities(false, maxResults, firstResult);
    }

    private List<BaLottoEntryDetails> findBaLottoEntryDetailsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BaLottoEntryDetails.class));
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

    public BaLottoEntryDetails findBaLottoEntryDetails(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BaLottoEntryDetails.class, id);
        } finally {
            em.close();
        }
    }

    public int getBaLottoEntryDetailsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BaLottoEntryDetails> rt = cq.from(BaLottoEntryDetails.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
