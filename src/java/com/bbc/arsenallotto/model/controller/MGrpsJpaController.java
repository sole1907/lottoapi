/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbc.arsenallotto.model.controller;

import com.bbc.arsenallotto.model.MGrps;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.bbc.arsenallotto.model.Prle;
import com.bbc.arsenallotto.model.Sts;
import com.bbc.arsenallotto.model.MItms;
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
 * @author Noble
 */
public class MGrpsJpaController implements Serializable {

    public MGrpsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MGrps MGrps) throws RollbackFailureException, Exception {
        if (MGrps.getMItmsCollection() == null) {
            MGrps.setMItmsCollection(new ArrayList<MItms>());
        }
        if (MGrps.getUserMgrpsCollection() == null) {
            MGrps.setUserMgrpsCollection(new ArrayList<UserMgrps>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Prle UByy = MGrps.getUByy();
            if (UByy != null) {
                UByy = em.getReference(UByy.getClass(), UByy.getTid());
                MGrps.setUByy(UByy);
            }
            Prle AByy = MGrps.getAByy();
            if (AByy != null) {
                AByy = em.getReference(AByy.getClass(), AByy.getTid());
                MGrps.setAByy(AByy);
            }
            Sts ISts = MGrps.getISts();
            if (ISts != null) {
                ISts = em.getReference(ISts.getClass(), ISts.getTid());
                MGrps.setISts(ISts);
            }
            Prle CByy = MGrps.getCByy();
            if (CByy != null) {
                CByy = em.getReference(CByy.getClass(), CByy.getTid());
                MGrps.setCByy(CByy);
            }
            Collection<MItms> attachedMItmsCollection = new ArrayList<MItms>();
            for (MItms MItmsCollectionMItmsToAttach : MGrps.getMItmsCollection()) {
                MItmsCollectionMItmsToAttach = em.getReference(MItmsCollectionMItmsToAttach.getClass(), MItmsCollectionMItmsToAttach.getTid());
                attachedMItmsCollection.add(MItmsCollectionMItmsToAttach);
            }
            MGrps.setMItmsCollection(attachedMItmsCollection);
            Collection<UserMgrps> attachedUserMgrpsCollection = new ArrayList<UserMgrps>();
            for (UserMgrps userMgrpsCollectionUserMgrpsToAttach : MGrps.getUserMgrpsCollection()) {
                userMgrpsCollectionUserMgrpsToAttach = em.getReference(userMgrpsCollectionUserMgrpsToAttach.getClass(), userMgrpsCollectionUserMgrpsToAttach.getTid());
                attachedUserMgrpsCollection.add(userMgrpsCollectionUserMgrpsToAttach);
            }
            MGrps.setUserMgrpsCollection(attachedUserMgrpsCollection);
            em.persist(MGrps);
            if (UByy != null) {
                UByy.getMGrpsCollection().add(MGrps);
                UByy = em.merge(UByy);
            }
            if (AByy != null) {
                AByy.getMGrpsCollection().add(MGrps);
                AByy = em.merge(AByy);
            }
            if (ISts != null) {
                ISts.getMGrpsCollection().add(MGrps);
                ISts = em.merge(ISts);
            }
            if (CByy != null) {
                CByy.getMGrpsCollection().add(MGrps);
                CByy = em.merge(CByy);
            }
            for (MItms MItmsCollectionMItms : MGrps.getMItmsCollection()) {
                MGrps oldPMidOfMItmsCollectionMItms = MItmsCollectionMItms.getPMid();
                MItmsCollectionMItms.setPMid(MGrps);
                MItmsCollectionMItms = em.merge(MItmsCollectionMItms);
                if (oldPMidOfMItmsCollectionMItms != null) {
                    oldPMidOfMItmsCollectionMItms.getMItmsCollection().remove(MItmsCollectionMItms);
                    oldPMidOfMItmsCollectionMItms = em.merge(oldPMidOfMItmsCollectionMItms);
                }
            }
            for (UserMgrps userMgrpsCollectionUserMgrps : MGrps.getUserMgrpsCollection()) {
                MGrps oldMGrpsOfUserMgrpsCollectionUserMgrps = userMgrpsCollectionUserMgrps.getMGrps();
                userMgrpsCollectionUserMgrps.setMGrps(MGrps);
                userMgrpsCollectionUserMgrps = em.merge(userMgrpsCollectionUserMgrps);
                if (oldMGrpsOfUserMgrpsCollectionUserMgrps != null) {
                    oldMGrpsOfUserMgrpsCollectionUserMgrps.getUserMgrpsCollection().remove(userMgrpsCollectionUserMgrps);
                    oldMGrpsOfUserMgrpsCollectionUserMgrps = em.merge(oldMGrpsOfUserMgrpsCollectionUserMgrps);
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

    public void edit(MGrps MGrps) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MGrps persistentMGrps = em.find(MGrps.class, MGrps.getTid());
            Prle UByyOld = persistentMGrps.getUByy();
            Prle UByyNew = MGrps.getUByy();
            Prle AByyOld = persistentMGrps.getAByy();
            Prle AByyNew = MGrps.getAByy();
            Sts IStsOld = persistentMGrps.getISts();
            Sts IStsNew = MGrps.getISts();
            Prle CByyOld = persistentMGrps.getCByy();
            Prle CByyNew = MGrps.getCByy();
            Collection<MItms> MItmsCollectionOld = persistentMGrps.getMItmsCollection();
            Collection<MItms> MItmsCollectionNew = MGrps.getMItmsCollection();
            Collection<UserMgrps> userMgrpsCollectionOld = persistentMGrps.getUserMgrpsCollection();
            Collection<UserMgrps> userMgrpsCollectionNew = MGrps.getUserMgrpsCollection();
            List<String> illegalOrphanMessages = null;
            for (MItms MItmsCollectionOldMItms : MItmsCollectionOld) {
                if (!MItmsCollectionNew.contains(MItmsCollectionOldMItms)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MItms " + MItmsCollectionOldMItms + " since its PMid field is not nullable.");
                }
            }
            for (UserMgrps userMgrpsCollectionOldUserMgrps : userMgrpsCollectionOld) {
                if (!userMgrpsCollectionNew.contains(userMgrpsCollectionOldUserMgrps)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserMgrps " + userMgrpsCollectionOldUserMgrps + " since its MGrps field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (UByyNew != null) {
                UByyNew = em.getReference(UByyNew.getClass(), UByyNew.getTid());
                MGrps.setUByy(UByyNew);
            }
            if (AByyNew != null) {
                AByyNew = em.getReference(AByyNew.getClass(), AByyNew.getTid());
                MGrps.setAByy(AByyNew);
            }
            if (IStsNew != null) {
                IStsNew = em.getReference(IStsNew.getClass(), IStsNew.getTid());
                MGrps.setISts(IStsNew);
            }
            if (CByyNew != null) {
                CByyNew = em.getReference(CByyNew.getClass(), CByyNew.getTid());
                MGrps.setCByy(CByyNew);
            }
            Collection<MItms> attachedMItmsCollectionNew = new ArrayList<MItms>();
            for (MItms MItmsCollectionNewMItmsToAttach : MItmsCollectionNew) {
                MItmsCollectionNewMItmsToAttach = em.getReference(MItmsCollectionNewMItmsToAttach.getClass(), MItmsCollectionNewMItmsToAttach.getTid());
                attachedMItmsCollectionNew.add(MItmsCollectionNewMItmsToAttach);
            }
            MItmsCollectionNew = attachedMItmsCollectionNew;
            MGrps.setMItmsCollection(MItmsCollectionNew);
            Collection<UserMgrps> attachedUserMgrpsCollectionNew = new ArrayList<UserMgrps>();
            for (UserMgrps userMgrpsCollectionNewUserMgrpsToAttach : userMgrpsCollectionNew) {
                userMgrpsCollectionNewUserMgrpsToAttach = em.getReference(userMgrpsCollectionNewUserMgrpsToAttach.getClass(), userMgrpsCollectionNewUserMgrpsToAttach.getTid());
                attachedUserMgrpsCollectionNew.add(userMgrpsCollectionNewUserMgrpsToAttach);
            }
            userMgrpsCollectionNew = attachedUserMgrpsCollectionNew;
            MGrps.setUserMgrpsCollection(userMgrpsCollectionNew);
            MGrps = em.merge(MGrps);
            if (UByyOld != null && !UByyOld.equals(UByyNew)) {
                UByyOld.getMGrpsCollection().remove(MGrps);
                UByyOld = em.merge(UByyOld);
            }
            if (UByyNew != null && !UByyNew.equals(UByyOld)) {
                UByyNew.getMGrpsCollection().add(MGrps);
                UByyNew = em.merge(UByyNew);
            }
            if (AByyOld != null && !AByyOld.equals(AByyNew)) {
                AByyOld.getMGrpsCollection().remove(MGrps);
                AByyOld = em.merge(AByyOld);
            }
            if (AByyNew != null && !AByyNew.equals(AByyOld)) {
                AByyNew.getMGrpsCollection().add(MGrps);
                AByyNew = em.merge(AByyNew);
            }
            if (IStsOld != null && !IStsOld.equals(IStsNew)) {
                IStsOld.getMGrpsCollection().remove(MGrps);
                IStsOld = em.merge(IStsOld);
            }
            if (IStsNew != null && !IStsNew.equals(IStsOld)) {
                IStsNew.getMGrpsCollection().add(MGrps);
                IStsNew = em.merge(IStsNew);
            }
            if (CByyOld != null && !CByyOld.equals(CByyNew)) {
                CByyOld.getMGrpsCollection().remove(MGrps);
                CByyOld = em.merge(CByyOld);
            }
            if (CByyNew != null && !CByyNew.equals(CByyOld)) {
                CByyNew.getMGrpsCollection().add(MGrps);
                CByyNew = em.merge(CByyNew);
            }
            for (MItms MItmsCollectionNewMItms : MItmsCollectionNew) {
                if (!MItmsCollectionOld.contains(MItmsCollectionNewMItms)) {
                    MGrps oldPMidOfMItmsCollectionNewMItms = MItmsCollectionNewMItms.getPMid();
                    MItmsCollectionNewMItms.setPMid(MGrps);
                    MItmsCollectionNewMItms = em.merge(MItmsCollectionNewMItms);
                    if (oldPMidOfMItmsCollectionNewMItms != null && !oldPMidOfMItmsCollectionNewMItms.equals(MGrps)) {
                        oldPMidOfMItmsCollectionNewMItms.getMItmsCollection().remove(MItmsCollectionNewMItms);
                        oldPMidOfMItmsCollectionNewMItms = em.merge(oldPMidOfMItmsCollectionNewMItms);
                    }
                }
            }
            for (UserMgrps userMgrpsCollectionNewUserMgrps : userMgrpsCollectionNew) {
                if (!userMgrpsCollectionOld.contains(userMgrpsCollectionNewUserMgrps)) {
                    MGrps oldMGrpsOfUserMgrpsCollectionNewUserMgrps = userMgrpsCollectionNewUserMgrps.getMGrps();
                    userMgrpsCollectionNewUserMgrps.setMGrps(MGrps);
                    userMgrpsCollectionNewUserMgrps = em.merge(userMgrpsCollectionNewUserMgrps);
                    if (oldMGrpsOfUserMgrpsCollectionNewUserMgrps != null && !oldMGrpsOfUserMgrpsCollectionNewUserMgrps.equals(MGrps)) {
                        oldMGrpsOfUserMgrpsCollectionNewUserMgrps.getUserMgrpsCollection().remove(userMgrpsCollectionNewUserMgrps);
                        oldMGrpsOfUserMgrpsCollectionNewUserMgrps = em.merge(oldMGrpsOfUserMgrpsCollectionNewUserMgrps);
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
                Long id = MGrps.getTid();
                if (findMGrps(id) == null) {
                    throw new NonexistentEntityException("The mGrps with id " + id + " no longer exists.");
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
            MGrps MGrps;
            try {
                MGrps = em.getReference(MGrps.class, id);
                MGrps.getTid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The MGrps with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<MItms> MItmsCollectionOrphanCheck = MGrps.getMItmsCollection();
            for (MItms MItmsCollectionOrphanCheckMItms : MItmsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MGrps (" + MGrps + ") cannot be destroyed since the MItms " + MItmsCollectionOrphanCheckMItms + " in its MItmsCollection field has a non-nullable PMid field.");
            }
            Collection<UserMgrps> userMgrpsCollectionOrphanCheck = MGrps.getUserMgrpsCollection();
            for (UserMgrps userMgrpsCollectionOrphanCheckUserMgrps : userMgrpsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MGrps (" + MGrps + ") cannot be destroyed since the UserMgrps " + userMgrpsCollectionOrphanCheckUserMgrps + " in its userMgrpsCollection field has a non-nullable MGrps field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Prle UByy = MGrps.getUByy();
            if (UByy != null) {
                UByy.getMGrpsCollection().remove(MGrps);
                UByy = em.merge(UByy);
            }
            Prle AByy = MGrps.getAByy();
            if (AByy != null) {
                AByy.getMGrpsCollection().remove(MGrps);
                AByy = em.merge(AByy);
            }
            Sts ISts = MGrps.getISts();
            if (ISts != null) {
                ISts.getMGrpsCollection().remove(MGrps);
                ISts = em.merge(ISts);
            }
            Prle CByy = MGrps.getCByy();
            if (CByy != null) {
                CByy.getMGrpsCollection().remove(MGrps);
                CByy = em.merge(CByy);
            }
            em.remove(MGrps);
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

    public List<MGrps> findMGrpsEntities() {
        return findMGrpsEntities(true, -1, -1);
    }

    public List<MGrps> findMGrpsEntities(int maxResults, int firstResult) {
        return findMGrpsEntities(false, maxResults, firstResult);
    }

    private List<MGrps> findMGrpsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MGrps.class));
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

    public MGrps findMGrps(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MGrps.class, id);
        } finally {
            em.close();
        }
    }

    public int getMGrpsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MGrps> rt = cq.from(MGrps.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
