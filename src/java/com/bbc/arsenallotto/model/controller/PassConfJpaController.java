/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.model.controller;

import com.bbc.arsenallotto.model.PassConf;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.bbc.arsenallotto.model.Prle;
import com.bbc.arsenallotto.model.Sts;
import com.bbc.arsenallotto.model.Rolap;
import com.bbc.arsenallotto.model.controller.exceptions.IllegalOrphanException;
import com.bbc.arsenallotto.model.controller.exceptions.NonexistentEntityException;
import com.bbc.arsenallotto.model.controller.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Soul
 */
public class PassConfJpaController implements Serializable {

    public PassConfJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PassConf passConf) throws RollbackFailureException, Exception {
        if (passConf.getRolapCollection() == null) {
            passConf.setRolapCollection(new ArrayList<Rolap>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Prle CByy = passConf.getCByy();
            if (CByy != null) {
                CByy = em.getReference(CByy.getClass(), CByy.getTid());
                passConf.setCByy(CByy);
            }
            Prle UByy = passConf.getUByy();
            if (UByy != null) {
                UByy = em.getReference(UByy.getClass(), UByy.getTid());
                passConf.setUByy(UByy);
            }
            Sts sts = passConf.getSts();
            if (sts != null) {
                sts = em.getReference(sts.getClass(), sts.getTid());
                passConf.setSts(sts);
            }
            Collection<Rolap> attachedRolapCollection = new ArrayList<Rolap>();
            for (Rolap rolapCollectionRolapToAttach : passConf.getRolapCollection()) {
                rolapCollectionRolapToAttach = em.getReference(rolapCollectionRolapToAttach.getClass(), rolapCollectionRolapToAttach.getTid());
                attachedRolapCollection.add(rolapCollectionRolapToAttach);
            }
            passConf.setRolapCollection(attachedRolapCollection);
            em.persist(passConf);
            if (CByy != null) {
                CByy.getPassConfCollection().add(passConf);
                CByy = em.merge(CByy);
            }
            if (UByy != null) {
                UByy.getPassConfCollection().add(passConf);
                UByy = em.merge(UByy);
            }
            if (sts != null) {
                sts.getPassConfCollection().add(passConf);
                sts = em.merge(sts);
            }
            for (Rolap rolapCollectionRolap : passConf.getRolapCollection()) {
                PassConf oldPGrpOfRolapCollectionRolap = rolapCollectionRolap.getPGrp();
                rolapCollectionRolap.setPGrp(passConf);
                rolapCollectionRolap = em.merge(rolapCollectionRolap);
                if (oldPGrpOfRolapCollectionRolap != null) {
                    oldPGrpOfRolapCollectionRolap.getRolapCollection().remove(rolapCollectionRolap);
                    oldPGrpOfRolapCollectionRolap = em.merge(oldPGrpOfRolapCollectionRolap);
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

    public void edit(PassConf passConf) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PassConf persistentPassConf = em.find(PassConf.class, passConf.getTid());
            Prle CByyOld = persistentPassConf.getCByy();
            Prle CByyNew = passConf.getCByy();
            Prle UByyOld = persistentPassConf.getUByy();
            Prle UByyNew = passConf.getUByy();
            Sts stsOld = persistentPassConf.getSts();
            Sts stsNew = passConf.getSts();
            Collection<Rolap> rolapCollectionOld = persistentPassConf.getRolapCollection();
            Collection<Rolap> rolapCollectionNew = passConf.getRolapCollection();
            List<String> illegalOrphanMessages = null;
            for (Rolap rolapCollectionOldRolap : rolapCollectionOld) {
                if (!rolapCollectionNew.contains(rolapCollectionOldRolap)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Rolap " + rolapCollectionOldRolap + " since its PGrp field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (CByyNew != null) {
                CByyNew = em.getReference(CByyNew.getClass(), CByyNew.getTid());
                passConf.setCByy(CByyNew);
            }
            if (UByyNew != null) {
                UByyNew = em.getReference(UByyNew.getClass(), UByyNew.getTid());
                passConf.setUByy(UByyNew);
            }
            if (stsNew != null) {
                stsNew = em.getReference(stsNew.getClass(), stsNew.getTid());
                passConf.setSts(stsNew);
            }
            Collection<Rolap> attachedRolapCollectionNew = new ArrayList<Rolap>();
            for (Rolap rolapCollectionNewRolapToAttach : rolapCollectionNew) {
                rolapCollectionNewRolapToAttach = em.getReference(rolapCollectionNewRolapToAttach.getClass(), rolapCollectionNewRolapToAttach.getTid());
                attachedRolapCollectionNew.add(rolapCollectionNewRolapToAttach);
            }
            rolapCollectionNew = attachedRolapCollectionNew;
            passConf.setRolapCollection(rolapCollectionNew);
            passConf = em.merge(passConf);
            if (CByyOld != null && !CByyOld.equals(CByyNew)) {
                CByyOld.getPassConfCollection().remove(passConf);
                CByyOld = em.merge(CByyOld);
            }
            if (CByyNew != null && !CByyNew.equals(CByyOld)) {
                CByyNew.getPassConfCollection().add(passConf);
                CByyNew = em.merge(CByyNew);
            }
            if (UByyOld != null && !UByyOld.equals(UByyNew)) {
                UByyOld.getPassConfCollection().remove(passConf);
                UByyOld = em.merge(UByyOld);
            }
            if (UByyNew != null && !UByyNew.equals(UByyOld)) {
                UByyNew.getPassConfCollection().add(passConf);
                UByyNew = em.merge(UByyNew);
            }
            if (stsOld != null && !stsOld.equals(stsNew)) {
                stsOld.getPassConfCollection().remove(passConf);
                stsOld = em.merge(stsOld);
            }
            if (stsNew != null && !stsNew.equals(stsOld)) {
                stsNew.getPassConfCollection().add(passConf);
                stsNew = em.merge(stsNew);
            }
            for (Rolap rolapCollectionNewRolap : rolapCollectionNew) {
                if (!rolapCollectionOld.contains(rolapCollectionNewRolap)) {
                    PassConf oldPGrpOfRolapCollectionNewRolap = rolapCollectionNewRolap.getPGrp();
                    rolapCollectionNewRolap.setPGrp(passConf);
                    rolapCollectionNewRolap = em.merge(rolapCollectionNewRolap);
                    if (oldPGrpOfRolapCollectionNewRolap != null && !oldPGrpOfRolapCollectionNewRolap.equals(passConf)) {
                        oldPGrpOfRolapCollectionNewRolap.getRolapCollection().remove(rolapCollectionNewRolap);
                        oldPGrpOfRolapCollectionNewRolap = em.merge(oldPGrpOfRolapCollectionNewRolap);
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
                Long id = passConf.getTid();
                if (findPassConf(id) == null) {
                    throw new NonexistentEntityException("The passConf with id " + id + " no longer exists.");
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
            PassConf passConf;
            try {
                passConf = em.getReference(PassConf.class, id);
                passConf.getTid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The passConf with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Rolap> rolapCollectionOrphanCheck = passConf.getRolapCollection();
            for (Rolap rolapCollectionOrphanCheckRolap : rolapCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PassConf (" + passConf + ") cannot be destroyed since the Rolap " + rolapCollectionOrphanCheckRolap + " in its rolapCollection field has a non-nullable PGrp field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Prle CByy = passConf.getCByy();
            if (CByy != null) {
                CByy.getPassConfCollection().remove(passConf);
                CByy = em.merge(CByy);
            }
            Prle UByy = passConf.getUByy();
            if (UByy != null) {
                UByy.getPassConfCollection().remove(passConf);
                UByy = em.merge(UByy);
            }
            Sts sts = passConf.getSts();
            if (sts != null) {
                sts.getPassConfCollection().remove(passConf);
                sts = em.merge(sts);
            }
            em.remove(passConf);
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

    public List<PassConf> findPassConfEntities() {
        return findPassConfEntities(true, -1, -1);
    }

    public List<PassConf> findPassConfEntities(int maxResults, int firstResult) {
        return findPassConfEntities(false, maxResults, firstResult);
    }

    private List<PassConf> findPassConfEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PassConf.class));
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

    public PassConf findPassConf(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PassConf.class, id);
        } finally {
            em.close();
        }
    }

    public int getPassConfCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PassConf> rt = cq.from(PassConf.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
