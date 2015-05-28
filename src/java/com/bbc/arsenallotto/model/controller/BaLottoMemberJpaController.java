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
import com.bbc.arsenallotto.model.BaLottoEntry;
import com.bbc.arsenallotto.model.BaLottoMember;
import com.bbc.arsenallotto.model.controller.exceptions.IllegalOrphanException;
import com.bbc.arsenallotto.model.controller.exceptions.NonexistentEntityException;
import com.bbc.arsenallotto.model.controller.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

/**
 *
 * @author Noble
 */
public class BaLottoMemberJpaController implements Serializable {

    public BaLottoMemberJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }

    public BaLottoMemberJpaController(EntityManager em) {
        this.em = em;
    }

    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;
    private EntityManager em = null;

    public EntityManager getEntityManager() {
        if (em == null) {
            em = emf.createEntityManager();
        }

        return em;
    }

    public void create(BaLottoMember baLottoMember) throws RollbackFailureException, Exception {
        if (baLottoMember.getBaLottoEntryCollection() == null) {
            baLottoMember.setBaLottoEntryCollection(new ArrayList<BaLottoEntry>());
        }
        EntityManager em = null;
        try {
            if (utx != null) {
                utx.begin();
            }
            em = getEntityManager();
            Sts sts = baLottoMember.getSts();
            if (sts != null) {
                sts = em.getReference(sts.getClass(), sts.getTid());
                baLottoMember.setSts(sts);
            }
            Prle CByy = baLottoMember.getCByy();
            if (CByy != null) {
                CByy = em.getReference(CByy.getClass(), CByy.getTid());
                baLottoMember.setCByy(CByy);
            }
            Collection<BaLottoEntry> attachedBaLottoEntryCollection = new ArrayList<BaLottoEntry>();
            for (BaLottoEntry baLottoEntryCollectionBaLottoEntryToAttach : baLottoMember.getBaLottoEntryCollection()) {
                baLottoEntryCollectionBaLottoEntryToAttach = em.getReference(baLottoEntryCollectionBaLottoEntryToAttach.getClass(), baLottoEntryCollectionBaLottoEntryToAttach.getId());
                attachedBaLottoEntryCollection.add(baLottoEntryCollectionBaLottoEntryToAttach);
            }
            baLottoMember.setBaLottoEntryCollection(attachedBaLottoEntryCollection);
            em.persist(baLottoMember);
            if (sts != null) {
                sts.getBaLottoMemberCollection().add(baLottoMember);
                sts = em.merge(sts);
            }
            if (CByy != null) {
                CByy.getBaLottoMemberCollection().add(baLottoMember);
                CByy = em.merge(CByy);
            }
            for (BaLottoEntry baLottoEntryCollectionBaLottoEntry : baLottoMember.getBaLottoEntryCollection()) {
                BaLottoMember oldMemberIdOfBaLottoEntryCollectionBaLottoEntry = baLottoEntryCollectionBaLottoEntry.getMemberId();
                baLottoEntryCollectionBaLottoEntry.setMemberId(baLottoMember);
                baLottoEntryCollectionBaLottoEntry = em.merge(baLottoEntryCollectionBaLottoEntry);
                if (oldMemberIdOfBaLottoEntryCollectionBaLottoEntry != null) {
                    oldMemberIdOfBaLottoEntryCollectionBaLottoEntry.getBaLottoEntryCollection().remove(baLottoEntryCollectionBaLottoEntry);
                    oldMemberIdOfBaLottoEntryCollectionBaLottoEntry = em.merge(oldMemberIdOfBaLottoEntryCollectionBaLottoEntry);
                }
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

    public void edit(BaLottoMember baLottoMember) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            if (utx != null) {
                utx.begin();
            }
            em = getEntityManager();
            BaLottoMember persistentBaLottoMember = em.find(BaLottoMember.class, baLottoMember.getId());
            Sts stsOld = persistentBaLottoMember.getSts();
            Sts stsNew = baLottoMember.getSts();
            Prle CByyOld = persistentBaLottoMember.getCByy();
            Prle CByyNew = baLottoMember.getCByy();
            Collection<BaLottoEntry> baLottoEntryCollectionOld = persistentBaLottoMember.getBaLottoEntryCollection();
            Collection<BaLottoEntry> baLottoEntryCollectionNew = baLottoMember.getBaLottoEntryCollection();
            List<String> illegalOrphanMessages = null;
            for (BaLottoEntry baLottoEntryCollectionOldBaLottoEntry : baLottoEntryCollectionOld) {
                if (!baLottoEntryCollectionNew.contains(baLottoEntryCollectionOldBaLottoEntry)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BaLottoEntry " + baLottoEntryCollectionOldBaLottoEntry + " since its memberId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (stsNew != null) {
                stsNew = em.getReference(stsNew.getClass(), stsNew.getTid());
                baLottoMember.setSts(stsNew);
            }
            if (CByyNew != null) {
                CByyNew = em.getReference(CByyNew.getClass(), CByyNew.getTid());
                baLottoMember.setCByy(CByyNew);
            }
            Collection<BaLottoEntry> attachedBaLottoEntryCollectionNew = new ArrayList<BaLottoEntry>();
            for (BaLottoEntry baLottoEntryCollectionNewBaLottoEntryToAttach : baLottoEntryCollectionNew) {
                baLottoEntryCollectionNewBaLottoEntryToAttach = em.getReference(baLottoEntryCollectionNewBaLottoEntryToAttach.getClass(), baLottoEntryCollectionNewBaLottoEntryToAttach.getId());
                attachedBaLottoEntryCollectionNew.add(baLottoEntryCollectionNewBaLottoEntryToAttach);
            }
            baLottoEntryCollectionNew = attachedBaLottoEntryCollectionNew;
            baLottoMember.setBaLottoEntryCollection(baLottoEntryCollectionNew);
            baLottoMember = em.merge(baLottoMember);
            if (stsOld != null && !stsOld.equals(stsNew)) {
                stsOld.getBaLottoMemberCollection().remove(baLottoMember);
                stsOld = em.merge(stsOld);
            }
            if (stsNew != null && !stsNew.equals(stsOld)) {
                stsNew.getBaLottoMemberCollection().add(baLottoMember);
                stsNew = em.merge(stsNew);
            }
            if (CByyOld != null && !CByyOld.equals(CByyNew)) {
                CByyOld.getBaLottoMemberCollection().remove(baLottoMember);
                CByyOld = em.merge(CByyOld);
            }
            if (CByyNew != null && !CByyNew.equals(CByyOld)) {
                CByyNew.getBaLottoMemberCollection().add(baLottoMember);
                CByyNew = em.merge(CByyNew);
            }
            for (BaLottoEntry baLottoEntryCollectionNewBaLottoEntry : baLottoEntryCollectionNew) {
                if (!baLottoEntryCollectionOld.contains(baLottoEntryCollectionNewBaLottoEntry)) {
                    BaLottoMember oldMemberIdOfBaLottoEntryCollectionNewBaLottoEntry = baLottoEntryCollectionNewBaLottoEntry.getMemberId();
                    baLottoEntryCollectionNewBaLottoEntry.setMemberId(baLottoMember);
                    baLottoEntryCollectionNewBaLottoEntry = em.merge(baLottoEntryCollectionNewBaLottoEntry);
                    if (oldMemberIdOfBaLottoEntryCollectionNewBaLottoEntry != null && !oldMemberIdOfBaLottoEntryCollectionNewBaLottoEntry.equals(baLottoMember)) {
                        oldMemberIdOfBaLottoEntryCollectionNewBaLottoEntry.getBaLottoEntryCollection().remove(baLottoEntryCollectionNewBaLottoEntry);
                        oldMemberIdOfBaLottoEntryCollectionNewBaLottoEntry = em.merge(oldMemberIdOfBaLottoEntryCollectionNewBaLottoEntry);
                    }
                }
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
                Integer id = baLottoMember.getId();
                if (findBaLottoMember(id) == null) {
                    throw new NonexistentEntityException("The baLottoMember with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null && emf != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            if (utx != null) {
                utx.begin();
            }
            em = getEntityManager();
            BaLottoMember baLottoMember;
            try {
                baLottoMember = em.getReference(BaLottoMember.class, id);
                baLottoMember.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baLottoMember with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<BaLottoEntry> baLottoEntryCollectionOrphanCheck = baLottoMember.getBaLottoEntryCollection();
            for (BaLottoEntry baLottoEntryCollectionOrphanCheckBaLottoEntry : baLottoEntryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BaLottoMember (" + baLottoMember + ") cannot be destroyed since the BaLottoEntry " + baLottoEntryCollectionOrphanCheckBaLottoEntry + " in its baLottoEntryCollection field has a non-nullable memberId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Sts sts = baLottoMember.getSts();
            if (sts != null) {
                sts.getBaLottoMemberCollection().remove(baLottoMember);
                sts = em.merge(sts);
            }
            Prle CByy = baLottoMember.getCByy();
            if (CByy != null) {
                CByy.getBaLottoMemberCollection().remove(baLottoMember);
                CByy = em.merge(CByy);
            }
            em.remove(baLottoMember);
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

    public List<BaLottoMember> findBaLottoMemberEntities() {
        return findBaLottoMemberEntities(true, -1, -1);
    }

    public List<BaLottoMember> findBaLottoMemberEntities(int maxResults, int firstResult) {
        return findBaLottoMemberEntities(false, maxResults, firstResult);
    }

    private List<BaLottoMember> findBaLottoMemberEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BaLottoMember.class));
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

    public BaLottoMember findBaLottoMember(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BaLottoMember.class, id);
        } finally {
            if (em != null && emf != null) {
                em.close();
            }
        }
    }

    public int getBaLottoMemberCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BaLottoMember> rt = cq.from(BaLottoMember.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            if (em != null && emf != null) {
                em.close();
            }
        }
    }

    public BaLottoMember findByMobile(String mobile) {
        BaLottoMember member = null;

        EntityManager em = getEntityManager();
        TypedQuery<BaLottoMember> queryByMobile = em.createNamedQuery("BaLottoMember.findByMobileNumber", BaLottoMember.class);
//set dynamic data for query
        queryByMobile.setParameter("mobileNumber", mobile);
        try {
//execute query and get results
            member = queryByMobile.getSingleResult();
        } catch (NoResultException nre) {
            member = null;
            Logger.getLogger(BaLottoMemberJpaController.class.getName()).log(Level.INFO, null, nre);
        } finally {
            if (em != null && emf != null) {
                em.close();
            }
        }

        return member;
    }
}
