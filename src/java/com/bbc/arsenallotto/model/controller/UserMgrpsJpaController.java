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
import com.bbc.arsenallotto.model.Rolap;
import com.bbc.arsenallotto.model.MGrps;
import com.bbc.arsenallotto.model.UserMgrps;
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
public class UserMgrpsJpaController implements Serializable {

    public UserMgrpsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserMgrps userMgrps) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Sts sts = userMgrps.getSts();
            if (sts != null) {
                sts = em.getReference(sts.getClass(), sts.getTid());
                userMgrps.setSts(sts);
            }
            Prle CByy = userMgrps.getCByy();
            if (CByy != null) {
                CByy = em.getReference(CByy.getClass(), CByy.getTid());
                userMgrps.setCByy(CByy);
            }
            Prle UByy = userMgrps.getUByy();
            if (UByy != null) {
                UByy = em.getReference(UByy.getClass(), UByy.getTid());
                userMgrps.setUByy(UByy);
            }
            Rolap URlp = userMgrps.getURlp();
            if (URlp != null) {
                URlp = em.getReference(URlp.getClass(), URlp.getTid());
                userMgrps.setURlp(URlp);
            }
            MGrps MGrps = userMgrps.getMGrps();
            if (MGrps != null) {
                MGrps = em.getReference(MGrps.getClass(), MGrps.getTid());
                userMgrps.setMGrps(MGrps);
            }
            em.persist(userMgrps);
            if (sts != null) {
                sts.getUserMgrpsCollection().add(userMgrps);
                sts = em.merge(sts);
            }
            if (CByy != null) {
                CByy.getUserMgrpsCollection().add(userMgrps);
                CByy = em.merge(CByy);
            }
            if (UByy != null) {
                UByy.getUserMgrpsCollection().add(userMgrps);
                UByy = em.merge(UByy);
            }
            if (URlp != null) {
                URlp.getUserMgrpsCollection().add(userMgrps);
                URlp = em.merge(URlp);
            }
            if (MGrps != null) {
                MGrps.getUserMgrpsCollection().add(userMgrps);
                MGrps = em.merge(MGrps);
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

    public void edit(UserMgrps userMgrps) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UserMgrps persistentUserMgrps = em.find(UserMgrps.class, userMgrps.getTid());
            Sts stsOld = persistentUserMgrps.getSts();
            Sts stsNew = userMgrps.getSts();
            Prle CByyOld = persistentUserMgrps.getCByy();
            Prle CByyNew = userMgrps.getCByy();
            Prle UByyOld = persistentUserMgrps.getUByy();
            Prle UByyNew = userMgrps.getUByy();
            Rolap URlpOld = persistentUserMgrps.getURlp();
            Rolap URlpNew = userMgrps.getURlp();
            MGrps MGrpsOld = persistentUserMgrps.getMGrps();
            MGrps MGrpsNew = userMgrps.getMGrps();
            if (stsNew != null) {
                stsNew = em.getReference(stsNew.getClass(), stsNew.getTid());
                userMgrps.setSts(stsNew);
            }
            if (CByyNew != null) {
                CByyNew = em.getReference(CByyNew.getClass(), CByyNew.getTid());
                userMgrps.setCByy(CByyNew);
            }
            if (UByyNew != null) {
                UByyNew = em.getReference(UByyNew.getClass(), UByyNew.getTid());
                userMgrps.setUByy(UByyNew);
            }
            if (URlpNew != null) {
                URlpNew = em.getReference(URlpNew.getClass(), URlpNew.getTid());
                userMgrps.setURlp(URlpNew);
            }
            if (MGrpsNew != null) {
                MGrpsNew = em.getReference(MGrpsNew.getClass(), MGrpsNew.getTid());
                userMgrps.setMGrps(MGrpsNew);
            }
            userMgrps = em.merge(userMgrps);
            if (stsOld != null && !stsOld.equals(stsNew)) {
                stsOld.getUserMgrpsCollection().remove(userMgrps);
                stsOld = em.merge(stsOld);
            }
            if (stsNew != null && !stsNew.equals(stsOld)) {
                stsNew.getUserMgrpsCollection().add(userMgrps);
                stsNew = em.merge(stsNew);
            }
            if (CByyOld != null && !CByyOld.equals(CByyNew)) {
                CByyOld.getUserMgrpsCollection().remove(userMgrps);
                CByyOld = em.merge(CByyOld);
            }
            if (CByyNew != null && !CByyNew.equals(CByyOld)) {
                CByyNew.getUserMgrpsCollection().add(userMgrps);
                CByyNew = em.merge(CByyNew);
            }
            if (UByyOld != null && !UByyOld.equals(UByyNew)) {
                UByyOld.getUserMgrpsCollection().remove(userMgrps);
                UByyOld = em.merge(UByyOld);
            }
            if (UByyNew != null && !UByyNew.equals(UByyOld)) {
                UByyNew.getUserMgrpsCollection().add(userMgrps);
                UByyNew = em.merge(UByyNew);
            }
            if (URlpOld != null && !URlpOld.equals(URlpNew)) {
                URlpOld.getUserMgrpsCollection().remove(userMgrps);
                URlpOld = em.merge(URlpOld);
            }
            if (URlpNew != null && !URlpNew.equals(URlpOld)) {
                URlpNew.getUserMgrpsCollection().add(userMgrps);
                URlpNew = em.merge(URlpNew);
            }
            if (MGrpsOld != null && !MGrpsOld.equals(MGrpsNew)) {
                MGrpsOld.getUserMgrpsCollection().remove(userMgrps);
                MGrpsOld = em.merge(MGrpsOld);
            }
            if (MGrpsNew != null && !MGrpsNew.equals(MGrpsOld)) {
                MGrpsNew.getUserMgrpsCollection().add(userMgrps);
                MGrpsNew = em.merge(MGrpsNew);
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
                Long id = userMgrps.getTid();
                if (findUserMgrps(id) == null) {
                    throw new NonexistentEntityException("The userMgrps with id " + id + " no longer exists.");
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
            UserMgrps userMgrps;
            try {
                userMgrps = em.getReference(UserMgrps.class, id);
                userMgrps.getTid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userMgrps with id " + id + " no longer exists.", enfe);
            }
            Sts sts = userMgrps.getSts();
            if (sts != null) {
                sts.getUserMgrpsCollection().remove(userMgrps);
                sts = em.merge(sts);
            }
            Prle CByy = userMgrps.getCByy();
            if (CByy != null) {
                CByy.getUserMgrpsCollection().remove(userMgrps);
                CByy = em.merge(CByy);
            }
            Prle UByy = userMgrps.getUByy();
            if (UByy != null) {
                UByy.getUserMgrpsCollection().remove(userMgrps);
                UByy = em.merge(UByy);
            }
            Rolap URlp = userMgrps.getURlp();
            if (URlp != null) {
                URlp.getUserMgrpsCollection().remove(userMgrps);
                URlp = em.merge(URlp);
            }
            MGrps MGrps = userMgrps.getMGrps();
            if (MGrps != null) {
                MGrps.getUserMgrpsCollection().remove(userMgrps);
                MGrps = em.merge(MGrps);
            }
            em.remove(userMgrps);
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

    public List<UserMgrps> findUserMgrpsEntities() {
        return findUserMgrpsEntities(true, -1, -1);
    }

    public List<UserMgrps> findUserMgrpsEntities(int maxResults, int firstResult) {
        return findUserMgrpsEntities(false, maxResults, firstResult);
    }

    private List<UserMgrps> findUserMgrpsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserMgrps.class));
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

    public UserMgrps findUserMgrps(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserMgrps.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserMgrpsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserMgrps> rt = cq.from(UserMgrps.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
