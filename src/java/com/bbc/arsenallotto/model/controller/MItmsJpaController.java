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
import com.bbc.arsenallotto.model.Sts;
import com.bbc.arsenallotto.model.Prle;
import com.bbc.arsenallotto.model.MGrps;
import com.bbc.arsenallotto.model.MItms;
import com.bbc.arsenallotto.model.controller.exceptions.NonexistentEntityException;
import com.bbc.arsenallotto.model.controller.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Noble
 */
public class MItmsJpaController implements Serializable {

    public MItmsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MItms MItms) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Sts ISts = MItms.getISts();
            if (ISts != null) {
                ISts = em.getReference(ISts.getClass(), ISts.getTid());
                MItms.setISts(ISts);
            }
            Prle AByy = MItms.getAByy();
            if (AByy != null) {
                AByy = em.getReference(AByy.getClass(), AByy.getTid());
                MItms.setAByy(AByy);
            }
            Prle UByy = MItms.getUByy();
            if (UByy != null) {
                UByy = em.getReference(UByy.getClass(), UByy.getTid());
                MItms.setUByy(UByy);
            }
            MGrps PMid = MItms.getPMid();
            if (PMid != null) {
                PMid = em.getReference(PMid.getClass(), PMid.getTid());
                MItms.setPMid(PMid);
            }
            em.persist(MItms);
            if (ISts != null) {
                ISts.getMItmsCollection().add(MItms);
                ISts = em.merge(ISts);
            }
            if (AByy != null) {
                AByy.getMItmsCollection().add(MItms);
                AByy = em.merge(AByy);
            }
            if (UByy != null) {
                UByy.getMItmsCollection().add(MItms);
                UByy = em.merge(UByy);
            }
            if (PMid != null) {
                PMid.getMItmsCollection().add(MItms);
                PMid = em.merge(PMid);
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

    public void edit(MItms MItms) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MItms persistentMItms = em.find(MItms.class, MItms.getTid());
            Sts IStsOld = persistentMItms.getISts();
            Sts IStsNew = MItms.getISts();
            Prle AByyOld = persistentMItms.getAByy();
            Prle AByyNew = MItms.getAByy();
            Prle UByyOld = persistentMItms.getUByy();
            Prle UByyNew = MItms.getUByy();
            MGrps PMidOld = persistentMItms.getPMid();
            MGrps PMidNew = MItms.getPMid();
            if (IStsNew != null) {
                IStsNew = em.getReference(IStsNew.getClass(), IStsNew.getTid());
                MItms.setISts(IStsNew);
            }
            if (AByyNew != null) {
                AByyNew = em.getReference(AByyNew.getClass(), AByyNew.getTid());
                MItms.setAByy(AByyNew);
            }
            if (UByyNew != null) {
                UByyNew = em.getReference(UByyNew.getClass(), UByyNew.getTid());
                MItms.setUByy(UByyNew);
            }
            if (PMidNew != null) {
                PMidNew = em.getReference(PMidNew.getClass(), PMidNew.getTid());
                MItms.setPMid(PMidNew);
            }
            MItms = em.merge(MItms);
            if (IStsOld != null && !IStsOld.equals(IStsNew)) {
                IStsOld.getMItmsCollection().remove(MItms);
                IStsOld = em.merge(IStsOld);
            }
            if (IStsNew != null && !IStsNew.equals(IStsOld)) {
                IStsNew.getMItmsCollection().add(MItms);
                IStsNew = em.merge(IStsNew);
            }
            if (AByyOld != null && !AByyOld.equals(AByyNew)) {
                AByyOld.getMItmsCollection().remove(MItms);
                AByyOld = em.merge(AByyOld);
            }
            if (AByyNew != null && !AByyNew.equals(AByyOld)) {
                AByyNew.getMItmsCollection().add(MItms);
                AByyNew = em.merge(AByyNew);
            }
            if (UByyOld != null && !UByyOld.equals(UByyNew)) {
                UByyOld.getMItmsCollection().remove(MItms);
                UByyOld = em.merge(UByyOld);
            }
            if (UByyNew != null && !UByyNew.equals(UByyOld)) {
                UByyNew.getMItmsCollection().add(MItms);
                UByyNew = em.merge(UByyNew);
            }
            if (PMidOld != null && !PMidOld.equals(PMidNew)) {
                PMidOld.getMItmsCollection().remove(MItms);
                PMidOld = em.merge(PMidOld);
            }
            if (PMidNew != null && !PMidNew.equals(PMidOld)) {
                PMidNew.getMItmsCollection().add(MItms);
                PMidNew = em.merge(PMidNew);
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
                Long id = MItms.getTid();
                if (findMItms(id) == null) {
                    throw new NonexistentEntityException("The mItms with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MItms MItms;
            try {
                MItms = em.getReference(MItms.class, id);
                MItms.getTid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The MItms with id " + id + " no longer exists.", enfe);
            }
            Sts ISts = MItms.getISts();
            if (ISts != null) {
                ISts.getMItmsCollection().remove(MItms);
                ISts = em.merge(ISts);
            }
            Prle AByy = MItms.getAByy();
            if (AByy != null) {
                AByy.getMItmsCollection().remove(MItms);
                AByy = em.merge(AByy);
            }
            Prle UByy = MItms.getUByy();
            if (UByy != null) {
                UByy.getMItmsCollection().remove(MItms);
                UByy = em.merge(UByy);
            }
            MGrps PMid = MItms.getPMid();
            if (PMid != null) {
                PMid.getMItmsCollection().remove(MItms);
                PMid = em.merge(PMid);
            }
            em.remove(MItms);
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

    public List<MItms> findMItmsEntities() {
        return findMItmsEntities(true, -1, -1);
    }

    public List<MItms> findMItmsEntities(int maxResults, int firstResult) {
        return findMItmsEntities(false, maxResults, firstResult);
    }

    private List<MItms> findMItmsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MItms.class));
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

    public MItms findMItms(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MItms.class, id);
        } finally {
            em.close();
        }
    }

    public int getMItmsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MItms> rt = cq.from(MItms.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
