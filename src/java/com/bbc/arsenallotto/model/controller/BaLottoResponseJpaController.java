/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.model.controller;

import com.bbc.arsenallotto.model.BaLottoResponse;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.bbc.arsenallotto.model.Prle;
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
public class BaLottoResponseJpaController implements Serializable {

    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;
    private EntityManager em = null;

    public BaLottoResponseJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }

    public BaLottoResponseJpaController(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEntityManager() {
        if (em == null) {
            em = emf.createEntityManager();
        }

        return em;
    }

    public void create(BaLottoResponse baLottoResponse) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            if (utx != null) {
                utx.begin();
            }
            em = getEntityManager();
            Prle CByy = baLottoResponse.getCByy();
            if (CByy != null) {
                CByy = em.getReference(CByy.getClass(), CByy.getTid());
                baLottoResponse.setCByy(CByy);
            }
            em.persist(baLottoResponse);
            if (CByy != null) {
                CByy.getBaLottoResponseCollection().add(baLottoResponse);
                CByy = em.merge(CByy);
            }
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
            if (em != null && emf != null) {
                em.close();
            }
        }
    }

    public void edit(BaLottoResponse baLottoResponse) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            if (utx != null) {
                utx.begin();
            }
            em = getEntityManager();
            BaLottoResponse persistentBaLottoResponse = em.find(BaLottoResponse.class, baLottoResponse.getId());
            Prle CByyOld = persistentBaLottoResponse.getCByy();
            Prle CByyNew = baLottoResponse.getCByy();
            if (CByyNew != null) {
                CByyNew = em.getReference(CByyNew.getClass(), CByyNew.getTid());
                baLottoResponse.setCByy(CByyNew);
            }
            baLottoResponse = em.merge(baLottoResponse);
            if (CByyOld != null && !CByyOld.equals(CByyNew)) {
                CByyOld.getBaLottoResponseCollection().remove(baLottoResponse);
                CByyOld = em.merge(CByyOld);
            }
            if (CByyNew != null && !CByyNew.equals(CByyOld)) {
                CByyNew.getBaLottoResponseCollection().add(baLottoResponse);
                CByyNew = em.merge(CByyNew);
            }
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
                Integer id = baLottoResponse.getId();
                if (findBaLottoResponse(id) == null) {
                    throw new NonexistentEntityException("The baLottoResponse with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null && emf != null) {
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
            BaLottoResponse baLottoResponse;
            try {
                baLottoResponse = em.getReference(BaLottoResponse.class, id);
                baLottoResponse.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baLottoResponse with id " + id + " no longer exists.", enfe);
            }
            Prle CByy = baLottoResponse.getCByy();
            if (CByy != null) {
                CByy.getBaLottoResponseCollection().remove(baLottoResponse);
                CByy = em.merge(CByy);
            }
            em.remove(baLottoResponse);
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
            if (em != null && emf != null) {
                em.close();
            }
        }
    }

    public List<BaLottoResponse> findBaLottoResponseEntities() {
        return findBaLottoResponseEntities(true, -1, -1);
    }

    public List<BaLottoResponse> findBaLottoResponseEntities(int maxResults, int firstResult) {
        return findBaLottoResponseEntities(false, maxResults, firstResult);
    }

    private List<BaLottoResponse> findBaLottoResponseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BaLottoResponse.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            if (em != null && emf != null) {
                em.close();
            }
        }
    }

    public BaLottoResponse findBaLottoResponse(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BaLottoResponse.class, id);
        } finally {
            if (em != null && emf != null) {
                em.close();
            }
        }
    }

    public int getBaLottoResponseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BaLottoResponse> rt = cq.from(BaLottoResponse.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            if (em != null && emf != null) {
                em.close();
            }
        }
    }

}
