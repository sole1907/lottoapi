/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.model.controller;

import com.bbc.arsenallotto.model.BaLottoChannel;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.bbc.arsenallotto.model.Sts;
import com.bbc.arsenallotto.model.Prle;
import com.bbc.arsenallotto.model.BaLottoEntry;
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
public class BaLottoChannelJpaController implements Serializable {

    public BaLottoChannelJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }

    public BaLottoChannelJpaController(EntityManager em) {
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

    public void create(BaLottoChannel baLottoChannel) throws RollbackFailureException, Exception {
        if (baLottoChannel.getBaLottoEntryCollection() == null) {
            baLottoChannel.setBaLottoEntryCollection(new ArrayList<BaLottoEntry>());
        }
        EntityManager em = null;
        try {
            if (utx != null) {
                utx.begin();
            }
            em = getEntityManager();
            Sts sts = baLottoChannel.getSts();
            if (sts != null) {
                sts = em.getReference(sts.getClass(), sts.getTid());
                baLottoChannel.setSts(sts);
            }
            Prle CByy = baLottoChannel.getCByy();
            if (CByy != null) {
                CByy = em.getReference(CByy.getClass(), CByy.getTid());
                baLottoChannel.setCByy(CByy);
            }
            Collection<BaLottoEntry> attachedBaLottoEntryCollection = new ArrayList<BaLottoEntry>();
            for (BaLottoEntry baLottoEntryCollectionBaLottoEntryToAttach : baLottoChannel.getBaLottoEntryCollection()) {
                baLottoEntryCollectionBaLottoEntryToAttach = em.getReference(baLottoEntryCollectionBaLottoEntryToAttach.getClass(), baLottoEntryCollectionBaLottoEntryToAttach.getId());
                attachedBaLottoEntryCollection.add(baLottoEntryCollectionBaLottoEntryToAttach);
            }
            baLottoChannel.setBaLottoEntryCollection(attachedBaLottoEntryCollection);
            em.persist(baLottoChannel);
            if (sts != null) {
                sts.getBaLottoChannelCollection().add(baLottoChannel);
                sts = em.merge(sts);
            }
            if (CByy != null) {
                CByy.getBaLottoChannelCollection().add(baLottoChannel);
                CByy = em.merge(CByy);
            }
            for (BaLottoEntry baLottoEntryCollectionBaLottoEntry : baLottoChannel.getBaLottoEntryCollection()) {
                BaLottoChannel oldChannelIdOfBaLottoEntryCollectionBaLottoEntry = baLottoEntryCollectionBaLottoEntry.getChannelId();
                baLottoEntryCollectionBaLottoEntry.setChannelId(baLottoChannel);
                baLottoEntryCollectionBaLottoEntry = em.merge(baLottoEntryCollectionBaLottoEntry);
                if (oldChannelIdOfBaLottoEntryCollectionBaLottoEntry != null) {
                    oldChannelIdOfBaLottoEntryCollectionBaLottoEntry.getBaLottoEntryCollection().remove(baLottoEntryCollectionBaLottoEntry);
                    oldChannelIdOfBaLottoEntryCollectionBaLottoEntry = em.merge(oldChannelIdOfBaLottoEntryCollectionBaLottoEntry);
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

    public void edit(BaLottoChannel baLottoChannel) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            if (utx != null) {
                utx.begin();
            }
            em = getEntityManager();
            BaLottoChannel persistentBaLottoChannel = em.find(BaLottoChannel.class, baLottoChannel.getId());
            Sts stsOld = persistentBaLottoChannel.getSts();
            Sts stsNew = baLottoChannel.getSts();
            Prle CByyOld = persistentBaLottoChannel.getCByy();
            Prle CByyNew = baLottoChannel.getCByy();
            Collection<BaLottoEntry> baLottoEntryCollectionOld = persistentBaLottoChannel.getBaLottoEntryCollection();
            Collection<BaLottoEntry> baLottoEntryCollectionNew = baLottoChannel.getBaLottoEntryCollection();
            List<String> illegalOrphanMessages = null;
            for (BaLottoEntry baLottoEntryCollectionOldBaLottoEntry : baLottoEntryCollectionOld) {
                if (!baLottoEntryCollectionNew.contains(baLottoEntryCollectionOldBaLottoEntry)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BaLottoEntry " + baLottoEntryCollectionOldBaLottoEntry + " since its channelId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (stsNew != null) {
                stsNew = em.getReference(stsNew.getClass(), stsNew.getTid());
                baLottoChannel.setSts(stsNew);
            }
            if (CByyNew != null) {
                CByyNew = em.getReference(CByyNew.getClass(), CByyNew.getTid());
                baLottoChannel.setCByy(CByyNew);
            }
            Collection<BaLottoEntry> attachedBaLottoEntryCollectionNew = new ArrayList<BaLottoEntry>();
            for (BaLottoEntry baLottoEntryCollectionNewBaLottoEntryToAttach : baLottoEntryCollectionNew) {
                baLottoEntryCollectionNewBaLottoEntryToAttach = em.getReference(baLottoEntryCollectionNewBaLottoEntryToAttach.getClass(), baLottoEntryCollectionNewBaLottoEntryToAttach.getId());
                attachedBaLottoEntryCollectionNew.add(baLottoEntryCollectionNewBaLottoEntryToAttach);
            }
            baLottoEntryCollectionNew = attachedBaLottoEntryCollectionNew;
            baLottoChannel.setBaLottoEntryCollection(baLottoEntryCollectionNew);
            baLottoChannel = em.merge(baLottoChannel);
            if (stsOld != null && !stsOld.equals(stsNew)) {
                stsOld.getBaLottoChannelCollection().remove(baLottoChannel);
                stsOld = em.merge(stsOld);
            }
            if (stsNew != null && !stsNew.equals(stsOld)) {
                stsNew.getBaLottoChannelCollection().add(baLottoChannel);
                stsNew = em.merge(stsNew);
            }
            if (CByyOld != null && !CByyOld.equals(CByyNew)) {
                CByyOld.getBaLottoChannelCollection().remove(baLottoChannel);
                CByyOld = em.merge(CByyOld);
            }
            if (CByyNew != null && !CByyNew.equals(CByyOld)) {
                CByyNew.getBaLottoChannelCollection().add(baLottoChannel);
                CByyNew = em.merge(CByyNew);
            }
            for (BaLottoEntry baLottoEntryCollectionNewBaLottoEntry : baLottoEntryCollectionNew) {
                if (!baLottoEntryCollectionOld.contains(baLottoEntryCollectionNewBaLottoEntry)) {
                    BaLottoChannel oldChannelIdOfBaLottoEntryCollectionNewBaLottoEntry = baLottoEntryCollectionNewBaLottoEntry.getChannelId();
                    baLottoEntryCollectionNewBaLottoEntry.setChannelId(baLottoChannel);
                    baLottoEntryCollectionNewBaLottoEntry = em.merge(baLottoEntryCollectionNewBaLottoEntry);
                    if (oldChannelIdOfBaLottoEntryCollectionNewBaLottoEntry != null && !oldChannelIdOfBaLottoEntryCollectionNewBaLottoEntry.equals(baLottoChannel)) {
                        oldChannelIdOfBaLottoEntryCollectionNewBaLottoEntry.getBaLottoEntryCollection().remove(baLottoEntryCollectionNewBaLottoEntry);
                        oldChannelIdOfBaLottoEntryCollectionNewBaLottoEntry = em.merge(oldChannelIdOfBaLottoEntryCollectionNewBaLottoEntry);
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
                Integer id = baLottoChannel.getId();
                if (findBaLottoChannel(id) == null) {
                    throw new NonexistentEntityException("The baLottoChannel with id " + id + " no longer exists.");
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
            BaLottoChannel baLottoChannel;
            try {
                baLottoChannel = em.getReference(BaLottoChannel.class, id);
                baLottoChannel.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baLottoChannel with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<BaLottoEntry> baLottoEntryCollectionOrphanCheck = baLottoChannel.getBaLottoEntryCollection();
            for (BaLottoEntry baLottoEntryCollectionOrphanCheckBaLottoEntry : baLottoEntryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BaLottoChannel (" + baLottoChannel + ") cannot be destroyed since the BaLottoEntry " + baLottoEntryCollectionOrphanCheckBaLottoEntry + " in its baLottoEntryCollection field has a non-nullable channelId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Sts sts = baLottoChannel.getSts();
            if (sts != null) {
                sts.getBaLottoChannelCollection().remove(baLottoChannel);
                sts = em.merge(sts);
            }
            Prle CByy = baLottoChannel.getCByy();
            if (CByy != null) {
                CByy.getBaLottoChannelCollection().remove(baLottoChannel);
                CByy = em.merge(CByy);
            }
            em.remove(baLottoChannel);
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

    public List<BaLottoChannel> findBaLottoChannelEntities() {
        return findBaLottoChannelEntities(true, -1, -1);
    }

    public List<BaLottoChannel> findBaLottoChannelEntities(int maxResults, int firstResult) {
        return findBaLottoChannelEntities(false, maxResults, firstResult);
    }

    private List<BaLottoChannel> findBaLottoChannelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BaLottoChannel.class));
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

    public BaLottoChannel findBaLottoChannel(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BaLottoChannel.class, id);
        } finally {
            if (em != null && emf != null) {
                em.close();
            }
        }
    }

    public int getBaLottoChannelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BaLottoChannel> rt = cq.from(BaLottoChannel.class);
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
