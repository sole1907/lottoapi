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
import com.bbc.arsenallotto.model.Prle;
import com.bbc.arsenallotto.model.Sts;
import com.bbc.arsenallotto.model.PassConf;
import com.bbc.arsenallotto.model.Rolap;
import java.util.ArrayList;
import java.util.Collection;
import com.bbc.arsenallotto.model.UserMgrps;
import com.bbc.arsenallotto.model.controller.exceptions.IllegalOrphanException;
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
public class RolapJpaController implements Serializable {

    public RolapJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rolap rolap) throws RollbackFailureException, Exception {
        if (rolap.getPrleCollection() == null) {
            rolap.setPrleCollection(new ArrayList<Prle>());
        }
        if (rolap.getUserMgrpsCollection() == null) {
            rolap.setUserMgrpsCollection(new ArrayList<UserMgrps>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Prle AByy = rolap.getAByy();
            if (AByy != null) {
                AByy = em.getReference(AByy.getClass(), AByy.getTid());
                rolap.setAByy(AByy);
            }
            Prle UByy = rolap.getUByy();
            if (UByy != null) {
                UByy = em.getReference(UByy.getClass(), UByy.getTid());
                rolap.setUByy(UByy);
            }
            Sts ISts = rolap.getISts();
            if (ISts != null) {
                ISts = em.getReference(ISts.getClass(), ISts.getTid());
                rolap.setISts(ISts);
            }
            PassConf PGrp = rolap.getPGrp();
            if (PGrp != null) {
                PGrp = em.getReference(PGrp.getClass(), PGrp.getTid());
                rolap.setPGrp(PGrp);
            }
            Prle CByy = rolap.getCByy();
            if (CByy != null) {
                CByy = em.getReference(CByy.getClass(), CByy.getTid());
                rolap.setCByy(CByy);
            }
            Collection<Prle> attachedPrleCollection = new ArrayList<Prle>();
            for (Prle prleCollectionPrleToAttach : rolap.getPrleCollection()) {
                prleCollectionPrleToAttach = em.getReference(prleCollectionPrleToAttach.getClass(), prleCollectionPrleToAttach.getTid());
                attachedPrleCollection.add(prleCollectionPrleToAttach);
            }
            rolap.setPrleCollection(attachedPrleCollection);
            Collection<UserMgrps> attachedUserMgrpsCollection = new ArrayList<UserMgrps>();
            for (UserMgrps userMgrpsCollectionUserMgrpsToAttach : rolap.getUserMgrpsCollection()) {
                userMgrpsCollectionUserMgrpsToAttach = em.getReference(userMgrpsCollectionUserMgrpsToAttach.getClass(), userMgrpsCollectionUserMgrpsToAttach.getTid());
                attachedUserMgrpsCollection.add(userMgrpsCollectionUserMgrpsToAttach);
            }
            rolap.setUserMgrpsCollection(attachedUserMgrpsCollection);
            em.persist(rolap);
            if (AByy != null) {
                AByy.getRolapCollection().add(rolap);
                AByy = em.merge(AByy);
            }
            if (UByy != null) {
                UByy.getRolapCollection().add(rolap);
                UByy = em.merge(UByy);
            }
            if (ISts != null) {
                ISts.getRolapCollection().add(rolap);
                ISts = em.merge(ISts);
            }
            if (PGrp != null) {
                PGrp.getRolapCollection().add(rolap);
                PGrp = em.merge(PGrp);
            }
            if (CByy != null) {
                CByy.getRolapCollection().add(rolap);
                CByy = em.merge(CByy);
            }
            for (Prle prleCollectionPrle : rolap.getPrleCollection()) {
                Rolap oldRIddOfPrleCollectionPrle = prleCollectionPrle.getRIdd();
                prleCollectionPrle.setRIdd(rolap);
                prleCollectionPrle = em.merge(prleCollectionPrle);
                if (oldRIddOfPrleCollectionPrle != null) {
                    oldRIddOfPrleCollectionPrle.getPrleCollection().remove(prleCollectionPrle);
                    oldRIddOfPrleCollectionPrle = em.merge(oldRIddOfPrleCollectionPrle);
                }
            }
            for (UserMgrps userMgrpsCollectionUserMgrps : rolap.getUserMgrpsCollection()) {
                Rolap oldURlpOfUserMgrpsCollectionUserMgrps = userMgrpsCollectionUserMgrps.getURlp();
                userMgrpsCollectionUserMgrps.setURlp(rolap);
                userMgrpsCollectionUserMgrps = em.merge(userMgrpsCollectionUserMgrps);
                if (oldURlpOfUserMgrpsCollectionUserMgrps != null) {
                    oldURlpOfUserMgrpsCollectionUserMgrps.getUserMgrpsCollection().remove(userMgrpsCollectionUserMgrps);
                    oldURlpOfUserMgrpsCollectionUserMgrps = em.merge(oldURlpOfUserMgrpsCollectionUserMgrps);
                }
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

    public void edit(Rolap rolap) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Rolap persistentRolap = em.find(Rolap.class, rolap.getTid());
            Prle AByyOld = persistentRolap.getAByy();
            Prle AByyNew = rolap.getAByy();
            Prle UByyOld = persistentRolap.getUByy();
            Prle UByyNew = rolap.getUByy();
            Sts IStsOld = persistentRolap.getISts();
            Sts IStsNew = rolap.getISts();
            PassConf PGrpOld = persistentRolap.getPGrp();
            PassConf PGrpNew = rolap.getPGrp();
            Prle CByyOld = persistentRolap.getCByy();
            Prle CByyNew = rolap.getCByy();
            Collection<Prle> prleCollectionOld = persistentRolap.getPrleCollection();
            Collection<Prle> prleCollectionNew = rolap.getPrleCollection();
            Collection<UserMgrps> userMgrpsCollectionOld = persistentRolap.getUserMgrpsCollection();
            Collection<UserMgrps> userMgrpsCollectionNew = rolap.getUserMgrpsCollection();
            List<String> illegalOrphanMessages = null;
            for (UserMgrps userMgrpsCollectionOldUserMgrps : userMgrpsCollectionOld) {
                if (!userMgrpsCollectionNew.contains(userMgrpsCollectionOldUserMgrps)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserMgrps " + userMgrpsCollectionOldUserMgrps + " since its URlp field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (AByyNew != null) {
                AByyNew = em.getReference(AByyNew.getClass(), AByyNew.getTid());
                rolap.setAByy(AByyNew);
            }
            if (UByyNew != null) {
                UByyNew = em.getReference(UByyNew.getClass(), UByyNew.getTid());
                rolap.setUByy(UByyNew);
            }
            if (IStsNew != null) {
                IStsNew = em.getReference(IStsNew.getClass(), IStsNew.getTid());
                rolap.setISts(IStsNew);
            }
            if (PGrpNew != null) {
                PGrpNew = em.getReference(PGrpNew.getClass(), PGrpNew.getTid());
                rolap.setPGrp(PGrpNew);
            }
            if (CByyNew != null) {
                CByyNew = em.getReference(CByyNew.getClass(), CByyNew.getTid());
                rolap.setCByy(CByyNew);
            }
            Collection<Prle> attachedPrleCollectionNew = new ArrayList<Prle>();
            for (Prle prleCollectionNewPrleToAttach : prleCollectionNew) {
                prleCollectionNewPrleToAttach = em.getReference(prleCollectionNewPrleToAttach.getClass(), prleCollectionNewPrleToAttach.getTid());
                attachedPrleCollectionNew.add(prleCollectionNewPrleToAttach);
            }
            prleCollectionNew = attachedPrleCollectionNew;
            rolap.setPrleCollection(prleCollectionNew);
            Collection<UserMgrps> attachedUserMgrpsCollectionNew = new ArrayList<UserMgrps>();
            for (UserMgrps userMgrpsCollectionNewUserMgrpsToAttach : userMgrpsCollectionNew) {
                userMgrpsCollectionNewUserMgrpsToAttach = em.getReference(userMgrpsCollectionNewUserMgrpsToAttach.getClass(), userMgrpsCollectionNewUserMgrpsToAttach.getTid());
                attachedUserMgrpsCollectionNew.add(userMgrpsCollectionNewUserMgrpsToAttach);
            }
            userMgrpsCollectionNew = attachedUserMgrpsCollectionNew;
            rolap.setUserMgrpsCollection(userMgrpsCollectionNew);
            rolap = em.merge(rolap);
            if (AByyOld != null && !AByyOld.equals(AByyNew)) {
                AByyOld.getRolapCollection().remove(rolap);
                AByyOld = em.merge(AByyOld);
            }
            if (AByyNew != null && !AByyNew.equals(AByyOld)) {
                AByyNew.getRolapCollection().add(rolap);
                AByyNew = em.merge(AByyNew);
            }
            if (UByyOld != null && !UByyOld.equals(UByyNew)) {
                UByyOld.getRolapCollection().remove(rolap);
                UByyOld = em.merge(UByyOld);
            }
            if (UByyNew != null && !UByyNew.equals(UByyOld)) {
                UByyNew.getRolapCollection().add(rolap);
                UByyNew = em.merge(UByyNew);
            }
            if (IStsOld != null && !IStsOld.equals(IStsNew)) {
                IStsOld.getRolapCollection().remove(rolap);
                IStsOld = em.merge(IStsOld);
            }
            if (IStsNew != null && !IStsNew.equals(IStsOld)) {
                IStsNew.getRolapCollection().add(rolap);
                IStsNew = em.merge(IStsNew);
            }
            if (PGrpOld != null && !PGrpOld.equals(PGrpNew)) {
                PGrpOld.getRolapCollection().remove(rolap);
                PGrpOld = em.merge(PGrpOld);
            }
            if (PGrpNew != null && !PGrpNew.equals(PGrpOld)) {
                PGrpNew.getRolapCollection().add(rolap);
                PGrpNew = em.merge(PGrpNew);
            }
            if (CByyOld != null && !CByyOld.equals(CByyNew)) {
                CByyOld.getRolapCollection().remove(rolap);
                CByyOld = em.merge(CByyOld);
            }
            if (CByyNew != null && !CByyNew.equals(CByyOld)) {
                CByyNew.getRolapCollection().add(rolap);
                CByyNew = em.merge(CByyNew);
            }
            for (Prle prleCollectionOldPrle : prleCollectionOld) {
                if (!prleCollectionNew.contains(prleCollectionOldPrle)) {
                    prleCollectionOldPrle.setRIdd(null);
                    prleCollectionOldPrle = em.merge(prleCollectionOldPrle);
                }
            }
            for (Prle prleCollectionNewPrle : prleCollectionNew) {
                if (!prleCollectionOld.contains(prleCollectionNewPrle)) {
                    Rolap oldRIddOfPrleCollectionNewPrle = prleCollectionNewPrle.getRIdd();
                    prleCollectionNewPrle.setRIdd(rolap);
                    prleCollectionNewPrle = em.merge(prleCollectionNewPrle);
                    if (oldRIddOfPrleCollectionNewPrle != null && !oldRIddOfPrleCollectionNewPrle.equals(rolap)) {
                        oldRIddOfPrleCollectionNewPrle.getPrleCollection().remove(prleCollectionNewPrle);
                        oldRIddOfPrleCollectionNewPrle = em.merge(oldRIddOfPrleCollectionNewPrle);
                    }
                }
            }
            for (UserMgrps userMgrpsCollectionNewUserMgrps : userMgrpsCollectionNew) {
                if (!userMgrpsCollectionOld.contains(userMgrpsCollectionNewUserMgrps)) {
                    Rolap oldURlpOfUserMgrpsCollectionNewUserMgrps = userMgrpsCollectionNewUserMgrps.getURlp();
                    userMgrpsCollectionNewUserMgrps.setURlp(rolap);
                    userMgrpsCollectionNewUserMgrps = em.merge(userMgrpsCollectionNewUserMgrps);
                    if (oldURlpOfUserMgrpsCollectionNewUserMgrps != null && !oldURlpOfUserMgrpsCollectionNewUserMgrps.equals(rolap)) {
                        oldURlpOfUserMgrpsCollectionNewUserMgrps.getUserMgrpsCollection().remove(userMgrpsCollectionNewUserMgrps);
                        oldURlpOfUserMgrpsCollectionNewUserMgrps = em.merge(oldURlpOfUserMgrpsCollectionNewUserMgrps);
                    }
                }
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
                Long id = rolap.getTid();
                if (findRolap(id) == null) {
                    throw new NonexistentEntityException("The rolap with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Rolap rolap;
            try {
                rolap = em.getReference(Rolap.class, id);
                rolap.getTid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolap with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<UserMgrps> userMgrpsCollectionOrphanCheck = rolap.getUserMgrpsCollection();
            for (UserMgrps userMgrpsCollectionOrphanCheckUserMgrps : userMgrpsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rolap (" + rolap + ") cannot be destroyed since the UserMgrps " + userMgrpsCollectionOrphanCheckUserMgrps + " in its userMgrpsCollection field has a non-nullable URlp field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Prle AByy = rolap.getAByy();
            if (AByy != null) {
                AByy.getRolapCollection().remove(rolap);
                AByy = em.merge(AByy);
            }
            Prle UByy = rolap.getUByy();
            if (UByy != null) {
                UByy.getRolapCollection().remove(rolap);
                UByy = em.merge(UByy);
            }
            Sts ISts = rolap.getISts();
            if (ISts != null) {
                ISts.getRolapCollection().remove(rolap);
                ISts = em.merge(ISts);
            }
            PassConf PGrp = rolap.getPGrp();
            if (PGrp != null) {
                PGrp.getRolapCollection().remove(rolap);
                PGrp = em.merge(PGrp);
            }
            Prle CByy = rolap.getCByy();
            if (CByy != null) {
                CByy.getRolapCollection().remove(rolap);
                CByy = em.merge(CByy);
            }
            Collection<Prle> prleCollection = rolap.getPrleCollection();
            for (Prle prleCollectionPrle : prleCollection) {
                prleCollectionPrle.setRIdd(null);
                prleCollectionPrle = em.merge(prleCollectionPrle);
            }
            em.remove(rolap);
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

    public List<Rolap> findRolapEntities() {
        return findRolapEntities(true, -1, -1);
    }

    public List<Rolap> findRolapEntities(int maxResults, int firstResult) {
        return findRolapEntities(false, maxResults, firstResult);
    }

    private List<Rolap> findRolapEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rolap.class));
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

    public Rolap findRolap(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rolap.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolapCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rolap> rt = cq.from(Rolap.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
