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
import com.bbc.arsenallotto.model.BaLottoMember;
import com.bbc.arsenallotto.model.Sts;
import com.bbc.arsenallotto.model.BaLottoAgent;
import com.bbc.arsenallotto.model.BaLottoChannel;
import com.bbc.arsenallotto.model.BaLottoEntry;
import com.bbc.arsenallotto.model.BaLottoEntryDetails;
import com.bbc.arsenallotto.model.controller.exceptions.IllegalOrphanException;
import com.bbc.arsenallotto.model.controller.exceptions.NonexistentEntityException;
import com.bbc.arsenallotto.model.controller.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
 * @author Soul
 */
public class BaLottoEntryJpaController implements Serializable {

    public BaLottoEntryJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }

    public BaLottoEntryJpaController(EntityManager em) {
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

    public BaLottoEntry create(BaLottoEntry baLottoEntry) throws RollbackFailureException, Exception {
        if (baLottoEntry.getBaLottoEntryDetailsCollection() == null) {
            baLottoEntry.setBaLottoEntryDetailsCollection(new ArrayList<BaLottoEntryDetails>());
        }
        EntityManager em = null;
        try {
            if (utx != null) {
                utx.begin();
            }
            em = getEntityManager();
            BaLottoMember memberId = baLottoEntry.getMemberId();
            if (memberId != null) {
                memberId = em.getReference(memberId.getClass(), memberId.getId());
                baLottoEntry.setMemberId(memberId);
            }
            Sts status = baLottoEntry.getStatus();
            if (status != null) {
                status = em.getReference(status.getClass(), status.getTid());
                baLottoEntry.setStatus(status);
            }
            BaLottoAgent agentId = baLottoEntry.getAgentId();
            if (agentId != null) {
                agentId = em.getReference(agentId.getClass(), agentId.getId());
                baLottoEntry.setAgentId(agentId);
            }
            BaLottoChannel channelId = baLottoEntry.getChannelId();
            if (channelId != null) {
                channelId = em.getReference(channelId.getClass(), channelId.getId());
                baLottoEntry.setChannelId(channelId);
            }
            Collection<BaLottoEntryDetails> attachedBaLottoEntryDetailsCollection = new ArrayList<BaLottoEntryDetails>();
            for (BaLottoEntryDetails baLottoEntryDetailsCollectionBaLottoEntryDetailsToAttach : baLottoEntry.getBaLottoEntryDetailsCollection()) {
                if (baLottoEntryDetailsCollectionBaLottoEntryDetailsToAttach.getId() != null) {
                    baLottoEntryDetailsCollectionBaLottoEntryDetailsToAttach = em.getReference(baLottoEntryDetailsCollectionBaLottoEntryDetailsToAttach.getClass(), baLottoEntryDetailsCollectionBaLottoEntryDetailsToAttach.getId());
                    attachedBaLottoEntryDetailsCollection.add(baLottoEntryDetailsCollectionBaLottoEntryDetailsToAttach);
                } else {
                    attachedBaLottoEntryDetailsCollection.add(baLottoEntryDetailsCollectionBaLottoEntryDetailsToAttach);
                }
            }
            baLottoEntry.setBaLottoEntryDetailsCollection(attachedBaLottoEntryDetailsCollection);
            em.persist(baLottoEntry);
            if (memberId != null) {
                memberId.getBaLottoEntryCollection().add(baLottoEntry);
                memberId = em.merge(memberId);
            }
            if (status != null) {
                status.getBaLottoEntryCollection().add(baLottoEntry);
                status = em.merge(status);
            }
            if (agentId != null) {
                agentId.getBaLottoEntryCollection().add(baLottoEntry);
                agentId = em.merge(agentId);
            }
            if (channelId != null) {
                channelId.getBaLottoEntryCollection().add(baLottoEntry);
                channelId = em.merge(channelId);
            }
            for (Iterator<BaLottoEntryDetails> it = baLottoEntry.getBaLottoEntryDetailsCollection().iterator(); it.hasNext();) {
                BaLottoEntryDetails baLottoEntryDetailsCollectionBaLottoEntryDetails = it.next();
                //BaLottoEntry oldEntryIdOfBaLottoEntryDetailsCollectionBaLottoEntryDetails = baLottoEntryDetailsCollectionBaLottoEntryDetails.getEntryId();
                baLottoEntryDetailsCollectionBaLottoEntryDetails.setEntryId(baLottoEntry);
                baLottoEntryDetailsCollectionBaLottoEntryDetails = em.merge(baLottoEntryDetailsCollectionBaLottoEntryDetails);
                /*if (oldEntryIdOfBaLottoEntryDetailsCollectionBaLottoEntryDetails != null) {
                 oldEntryIdOfBaLottoEntryDetailsCollectionBaLottoEntryDetails.getBaLottoEntryDetailsCollection().remove(baLottoEntryDetailsCollectionBaLottoEntryDetails);
                 oldEntryIdOfBaLottoEntryDetailsCollectionBaLottoEntryDetails = em.merge(oldEntryIdOfBaLottoEntryDetailsCollectionBaLottoEntryDetails);
                 }*/
            }
            /*for (BaLottoEntryDetails baLottoEntryDetailsCollectionBaLottoEntryDetails : baLottoEntry.getBaLottoEntryDetailsCollection()) {
             BaLottoEntry oldEntryIdOfBaLottoEntryDetailsCollectionBaLottoEntryDetails = baLottoEntryDetailsCollectionBaLottoEntryDetails.getEntryId();
             baLottoEntryDetailsCollectionBaLottoEntryDetails.setEntryId(baLottoEntry);
             baLottoEntryDetailsCollectionBaLottoEntryDetails = em.merge(baLottoEntryDetailsCollectionBaLottoEntryDetails);
             /*if (oldEntryIdOfBaLottoEntryDetailsCollectionBaLottoEntryDetails != null) {
             oldEntryIdOfBaLottoEntryDetailsCollectionBaLottoEntryDetails.getBaLottoEntryDetailsCollection().remove(baLottoEntryDetailsCollectionBaLottoEntryDetails);
             oldEntryIdOfBaLottoEntryDetailsCollectionBaLottoEntryDetails = em.merge(oldEntryIdOfBaLottoEntryDetailsCollectionBaLottoEntryDetails);
             }
             }*/
            if (utx != null) {
                utx.commit();
            }
            //created = true;
        } catch (Exception ex) {
            baLottoEntry = null;
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

        return baLottoEntry;
    }

    public void edit(BaLottoEntry baLottoEntry) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            if (utx != null) {
                utx.begin();
            }
            em = getEntityManager();
            BaLottoEntry persistentBaLottoEntry = em.find(BaLottoEntry.class, baLottoEntry.getId());
            BaLottoMember memberIdOld = persistentBaLottoEntry.getMemberId();
            BaLottoMember memberIdNew = baLottoEntry.getMemberId();
            Sts statusOld = persistentBaLottoEntry.getStatus();
            Sts statusNew = baLottoEntry.getStatus();
            BaLottoAgent agentIdOld = persistentBaLottoEntry.getAgentId();
            BaLottoAgent agentIdNew = baLottoEntry.getAgentId();
            BaLottoChannel channelIdOld = persistentBaLottoEntry.getChannelId();
            BaLottoChannel channelIdNew = baLottoEntry.getChannelId();
            Collection<BaLottoEntryDetails> baLottoEntryDetailsCollectionOld = persistentBaLottoEntry.getBaLottoEntryDetailsCollection();
            Collection<BaLottoEntryDetails> baLottoEntryDetailsCollectionNew = baLottoEntry.getBaLottoEntryDetailsCollection();
            List<String> illegalOrphanMessages = null;
            /*for (BaLottoEntryDetails baLottoEntryDetailsCollectionOldBaLottoEntryDetails : baLottoEntryDetailsCollectionOld) {
             if (!baLottoEntryDetailsCollectionNew.contains(baLottoEntryDetailsCollectionOldBaLottoEntryDetails)) {
             if (illegalOrphanMessages == null) {
             illegalOrphanMessages = new ArrayList<String>();
             }
             illegalOrphanMessages.add("You must retain BaLottoEntryDetails " + baLottoEntryDetailsCollectionOldBaLottoEntryDetails + " since its entryId field is not nullable.");
             }
             }*/
            if (illegalOrphanMessages
                    != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (memberIdNew
                    != null) {
                memberIdNew = em.getReference(memberIdNew.getClass(), memberIdNew.getId());
                baLottoEntry.setMemberId(memberIdNew);
            }
            if (statusNew
                    != null) {
                statusNew = em.getReference(statusNew.getClass(), statusNew.getTid());
                baLottoEntry.setStatus(statusNew);
            }
            if (agentIdNew
                    != null) {
                agentIdNew = em.getReference(agentIdNew.getClass(), agentIdNew.getId());
                baLottoEntry.setAgentId(agentIdNew);
            }
            if (channelIdNew
                    != null) {
                channelIdNew = em.getReference(channelIdNew.getClass(), channelIdNew.getId());
                baLottoEntry.setChannelId(channelIdNew);
            }
            Collection<BaLottoEntryDetails> attachedBaLottoEntryDetailsCollectionNew = new ArrayList<BaLottoEntryDetails>();
            /*for (BaLottoEntryDetails baLottoEntryDetailsCollectionNewBaLottoEntryDetailsToAttach : baLottoEntryDetailsCollectionNew) {
             baLottoEntryDetailsCollectionNewBaLottoEntryDetailsToAttach = em.getReference(baLottoEntryDetailsCollectionNewBaLottoEntryDetailsToAttach.getClass(), baLottoEntryDetailsCollectionNewBaLottoEntryDetailsToAttach.getId());
             attachedBaLottoEntryDetailsCollectionNew.add(baLottoEntryDetailsCollectionNewBaLottoEntryDetailsToAttach);
             }*/
            baLottoEntryDetailsCollectionNew = attachedBaLottoEntryDetailsCollectionNew;

            baLottoEntry.setBaLottoEntryDetailsCollection(baLottoEntryDetailsCollectionNew);
            baLottoEntry = em.merge(baLottoEntry);
            if (memberIdOld
                    != null && !memberIdOld.equals(memberIdNew)) {
                memberIdOld.getBaLottoEntryCollection().remove(baLottoEntry);
                memberIdOld = em.merge(memberIdOld);
            }
            if (memberIdNew
                    != null && !memberIdNew.equals(memberIdOld)) {
                memberIdNew.getBaLottoEntryCollection().add(baLottoEntry);
                memberIdNew = em.merge(memberIdNew);
            }
            if (statusOld
                    != null && !statusOld.equals(statusNew)) {
                statusOld.getBaLottoEntryCollection().remove(baLottoEntry);
                statusOld = em.merge(statusOld);
            }
            if (statusNew
                    != null && !statusNew.equals(statusOld)) {
                statusNew.getBaLottoEntryCollection().add(baLottoEntry);
                statusNew = em.merge(statusNew);
            }
            if (agentIdOld
                    != null && !agentIdOld.equals(agentIdNew)) {
                agentIdOld.getBaLottoEntryCollection().remove(baLottoEntry);
                agentIdOld = em.merge(agentIdOld);
            }
            if (agentIdNew
                    != null && !agentIdNew.equals(agentIdOld)) {
                agentIdNew.getBaLottoEntryCollection().add(baLottoEntry);
                agentIdNew = em.merge(agentIdNew);
            }
            if (channelIdOld
                    != null && !channelIdOld.equals(channelIdNew)) {
                channelIdOld.getBaLottoEntryCollection().remove(baLottoEntry);
                channelIdOld = em.merge(channelIdOld);
            }
            if (channelIdNew
                    != null && !channelIdNew.equals(channelIdOld)) {
                channelIdNew.getBaLottoEntryCollection().add(baLottoEntry);
                channelIdNew = em.merge(channelIdNew);
            }
            for (BaLottoEntryDetails baLottoEntryDetailsCollectionNewBaLottoEntryDetails : baLottoEntryDetailsCollectionNew) {
                if (!baLottoEntryDetailsCollectionOld.contains(baLottoEntryDetailsCollectionNewBaLottoEntryDetails)) {
                    BaLottoEntry oldEntryIdOfBaLottoEntryDetailsCollectionNewBaLottoEntryDetails = baLottoEntryDetailsCollectionNewBaLottoEntryDetails.getEntryId();
                    baLottoEntryDetailsCollectionNewBaLottoEntryDetails.setEntryId(baLottoEntry);
                    baLottoEntryDetailsCollectionNewBaLottoEntryDetails = em.merge(baLottoEntryDetailsCollectionNewBaLottoEntryDetails);
                    if (oldEntryIdOfBaLottoEntryDetailsCollectionNewBaLottoEntryDetails != null && !oldEntryIdOfBaLottoEntryDetailsCollectionNewBaLottoEntryDetails.equals(baLottoEntry)) {
                        oldEntryIdOfBaLottoEntryDetailsCollectionNewBaLottoEntryDetails.getBaLottoEntryDetailsCollection().remove(baLottoEntryDetailsCollectionNewBaLottoEntryDetails);
                        oldEntryIdOfBaLottoEntryDetailsCollectionNewBaLottoEntryDetails = em.merge(oldEntryIdOfBaLottoEntryDetailsCollectionNewBaLottoEntryDetails);
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
                Integer id = baLottoEntry.getId();
                if (findBaLottoEntry(id) == null) {
                    throw new NonexistentEntityException("The baLottoEntry with id " + id + " no longer exists.");
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
            BaLottoEntry baLottoEntry;

            try {
                baLottoEntry = em.getReference(BaLottoEntry.class, id);
                baLottoEntry.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baLottoEntry with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<BaLottoEntryDetails> baLottoEntryDetailsCollectionOrphanCheck = baLottoEntry.getBaLottoEntryDetailsCollection();
            for (BaLottoEntryDetails baLottoEntryDetailsCollectionOrphanCheckBaLottoEntryDetails : baLottoEntryDetailsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BaLottoEntry (" + baLottoEntry + ") cannot be destroyed since the BaLottoEntryDetails " + baLottoEntryDetailsCollectionOrphanCheckBaLottoEntryDetails + " in its baLottoEntryDetailsCollection field has a non-nullable entryId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            BaLottoMember memberId = baLottoEntry.getMemberId();
            if (memberId != null) {
                memberId.getBaLottoEntryCollection().remove(baLottoEntry);
                memberId = em.merge(memberId);
            }
            Sts status = baLottoEntry.getStatus();
            if (status != null) {
                status.getBaLottoEntryCollection().remove(baLottoEntry);
                status = em.merge(status);
            }
            BaLottoAgent agentId = baLottoEntry.getAgentId();
            if (agentId != null) {
                agentId.getBaLottoEntryCollection().remove(baLottoEntry);
                agentId = em.merge(agentId);
            }
            BaLottoChannel channelId = baLottoEntry.getChannelId();
            if (channelId != null) {
                channelId.getBaLottoEntryCollection().remove(baLottoEntry);
                channelId = em.merge(channelId);
            }
            em.remove(baLottoEntry);
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

    public List<BaLottoEntry> findBaLottoEntryEntities() {
        return findBaLottoEntryEntities(true, -1, -1);
    }

    public List<BaLottoEntry> findBaLottoEntryEntities(int maxResults, int firstResult) {
        return findBaLottoEntryEntities(false, maxResults, firstResult);
    }

    private List<BaLottoEntry> findBaLottoEntryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq
                    .select(cq.from(BaLottoEntry.class
                            ));
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

    public BaLottoEntry findBaLottoEntry(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(BaLottoEntry.class, id);
        } finally {
            if (em != null && emf != null) {
                em.close();
            }
        }
    }

    public int getBaLottoEntryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BaLottoEntry> rt = cq.from(BaLottoEntry.class
            );
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            if (em != null && emf != null) {
                em.close();
            }
        }
    }

    public BaLottoEntry isDuplicate(Integer agentId, String reference) {
        BaLottoEntry entry = null;

        EntityManager em = getEntityManager();
        TypedQuery<BaLottoEntry> queryAgentReference = em.createNamedQuery("BaLottoEntry.findAgentReference", BaLottoEntry.class);
//set dynamic data for query
        queryAgentReference.setParameter("agentId", agentId);
        queryAgentReference.setParameter("reference", reference);
        try {
//execute query and get results
            entry = queryAgentReference.getSingleResult();
        } catch (NoResultException nre) {
            entry = null;
            Logger.getLogger(BaLottoEntryJpaController.class.getName()).log(Level.INFO, null, nre);
        } finally {
            if (em != null && emf != null) {
                em.close();
            }
        }

        return entry;
    }
}
