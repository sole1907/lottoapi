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
import com.bbc.arsenallotto.model.Rolap;
import java.util.ArrayList;
import java.util.Collection;
import com.bbc.arsenallotto.model.PassConf;
import com.bbc.arsenallotto.model.BaLottoConfig;
import com.bbc.arsenallotto.model.MGrps;
import com.bbc.arsenallotto.model.BaLottoMember;
import com.bbc.arsenallotto.model.MItms;
import com.bbc.arsenallotto.model.BaLottoChannel;
import com.bbc.arsenallotto.model.BaLottoEntry;
import com.bbc.arsenallotto.model.BaLottoAgent;
import com.bbc.arsenallotto.model.Sts;
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
public class StsJpaController implements Serializable {

    public StsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sts sts) throws IllegalOrphanException, RollbackFailureException, Exception {
        if (sts.getRolapCollection() == null) {
            sts.setRolapCollection(new ArrayList<Rolap>());
        }
        if (sts.getPassConfCollection() == null) {
            sts.setPassConfCollection(new ArrayList<PassConf>());
        }
        if (sts.getBaLottoConfigCollection() == null) {
            sts.setBaLottoConfigCollection(new ArrayList<BaLottoConfig>());
        }
        if (sts.getMGrpsCollection() == null) {
            sts.setMGrpsCollection(new ArrayList<MGrps>());
        }
        if (sts.getBaLottoMemberCollection() == null) {
            sts.setBaLottoMemberCollection(new ArrayList<BaLottoMember>());
        }
        if (sts.getMItmsCollection() == null) {
            sts.setMItmsCollection(new ArrayList<MItms>());
        }
        if (sts.getPrleCollection() == null) {
            sts.setPrleCollection(new ArrayList<Prle>());
        }
        if (sts.getBaLottoChannelCollection() == null) {
            sts.setBaLottoChannelCollection(new ArrayList<BaLottoChannel>());
        }
        if (sts.getBaLottoEntryCollection() == null) {
            sts.setBaLottoEntryCollection(new ArrayList<BaLottoEntry>());
        }
        if (sts.getBaLottoAgentCollection() == null) {
            sts.setBaLottoAgentCollection(new ArrayList<BaLottoAgent>());
        }
        if (sts.getUserMgrpsCollection() == null) {
            sts.setUserMgrpsCollection(new ArrayList<UserMgrps>());
        }
        List<String> illegalOrphanMessages = null;
        Prle CByyOrphanCheck = sts.getCByy();
        if (CByyOrphanCheck != null) {
            Sts oldIStsOfCByy = CByyOrphanCheck.getISts();
            if (oldIStsOfCByy != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Prle " + CByyOrphanCheck + " already has an item of type Sts whose CByy column cannot be null. Please make another selection for the CByy field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Prle CByy = sts.getCByy();
            if (CByy != null) {
                CByy = em.getReference(CByy.getClass(), CByy.getTid());
                sts.setCByy(CByy);
            }
            Prle UByy = sts.getUByy();
            if (UByy != null) {
                UByy = em.getReference(UByy.getClass(), UByy.getTid());
                sts.setUByy(UByy);
            }
            Collection<Rolap> attachedRolapCollection = new ArrayList<Rolap>();
            for (Rolap rolapCollectionRolapToAttach : sts.getRolapCollection()) {
                rolapCollectionRolapToAttach = em.getReference(rolapCollectionRolapToAttach.getClass(), rolapCollectionRolapToAttach.getTid());
                attachedRolapCollection.add(rolapCollectionRolapToAttach);
            }
            sts.setRolapCollection(attachedRolapCollection);
            Collection<PassConf> attachedPassConfCollection = new ArrayList<PassConf>();
            for (PassConf passConfCollectionPassConfToAttach : sts.getPassConfCollection()) {
                passConfCollectionPassConfToAttach = em.getReference(passConfCollectionPassConfToAttach.getClass(), passConfCollectionPassConfToAttach.getTid());
                attachedPassConfCollection.add(passConfCollectionPassConfToAttach);
            }
            sts.setPassConfCollection(attachedPassConfCollection);
            Collection<BaLottoConfig> attachedBaLottoConfigCollection = new ArrayList<BaLottoConfig>();
            for (BaLottoConfig baLottoConfigCollectionBaLottoConfigToAttach : sts.getBaLottoConfigCollection()) {
                baLottoConfigCollectionBaLottoConfigToAttach = em.getReference(baLottoConfigCollectionBaLottoConfigToAttach.getClass(), baLottoConfigCollectionBaLottoConfigToAttach.getId());
                attachedBaLottoConfigCollection.add(baLottoConfigCollectionBaLottoConfigToAttach);
            }
            sts.setBaLottoConfigCollection(attachedBaLottoConfigCollection);
            Collection<MGrps> attachedMGrpsCollection = new ArrayList<MGrps>();
            for (MGrps MGrpsCollectionMGrpsToAttach : sts.getMGrpsCollection()) {
                MGrpsCollectionMGrpsToAttach = em.getReference(MGrpsCollectionMGrpsToAttach.getClass(), MGrpsCollectionMGrpsToAttach.getTid());
                attachedMGrpsCollection.add(MGrpsCollectionMGrpsToAttach);
            }
            sts.setMGrpsCollection(attachedMGrpsCollection);
            Collection<BaLottoMember> attachedBaLottoMemberCollection = new ArrayList<BaLottoMember>();
            for (BaLottoMember baLottoMemberCollectionBaLottoMemberToAttach : sts.getBaLottoMemberCollection()) {
                baLottoMemberCollectionBaLottoMemberToAttach = em.getReference(baLottoMemberCollectionBaLottoMemberToAttach.getClass(), baLottoMemberCollectionBaLottoMemberToAttach.getId());
                attachedBaLottoMemberCollection.add(baLottoMemberCollectionBaLottoMemberToAttach);
            }
            sts.setBaLottoMemberCollection(attachedBaLottoMemberCollection);
            Collection<MItms> attachedMItmsCollection = new ArrayList<MItms>();
            for (MItms MItmsCollectionMItmsToAttach : sts.getMItmsCollection()) {
                MItmsCollectionMItmsToAttach = em.getReference(MItmsCollectionMItmsToAttach.getClass(), MItmsCollectionMItmsToAttach.getTid());
                attachedMItmsCollection.add(MItmsCollectionMItmsToAttach);
            }
            sts.setMItmsCollection(attachedMItmsCollection);
            Collection<Prle> attachedPrleCollection = new ArrayList<Prle>();
            for (Prle prleCollectionPrleToAttach : sts.getPrleCollection()) {
                prleCollectionPrleToAttach = em.getReference(prleCollectionPrleToAttach.getClass(), prleCollectionPrleToAttach.getTid());
                attachedPrleCollection.add(prleCollectionPrleToAttach);
            }
            sts.setPrleCollection(attachedPrleCollection);
            Collection<BaLottoChannel> attachedBaLottoChannelCollection = new ArrayList<BaLottoChannel>();
            for (BaLottoChannel baLottoChannelCollectionBaLottoChannelToAttach : sts.getBaLottoChannelCollection()) {
                baLottoChannelCollectionBaLottoChannelToAttach = em.getReference(baLottoChannelCollectionBaLottoChannelToAttach.getClass(), baLottoChannelCollectionBaLottoChannelToAttach.getId());
                attachedBaLottoChannelCollection.add(baLottoChannelCollectionBaLottoChannelToAttach);
            }
            sts.setBaLottoChannelCollection(attachedBaLottoChannelCollection);
            Collection<BaLottoEntry> attachedBaLottoEntryCollection = new ArrayList<BaLottoEntry>();
            for (BaLottoEntry baLottoEntryCollectionBaLottoEntryToAttach : sts.getBaLottoEntryCollection()) {
                baLottoEntryCollectionBaLottoEntryToAttach = em.getReference(baLottoEntryCollectionBaLottoEntryToAttach.getClass(), baLottoEntryCollectionBaLottoEntryToAttach.getId());
                attachedBaLottoEntryCollection.add(baLottoEntryCollectionBaLottoEntryToAttach);
            }
            sts.setBaLottoEntryCollection(attachedBaLottoEntryCollection);
            Collection<BaLottoAgent> attachedBaLottoAgentCollection = new ArrayList<BaLottoAgent>();
            for (BaLottoAgent baLottoAgentCollectionBaLottoAgentToAttach : sts.getBaLottoAgentCollection()) {
                baLottoAgentCollectionBaLottoAgentToAttach = em.getReference(baLottoAgentCollectionBaLottoAgentToAttach.getClass(), baLottoAgentCollectionBaLottoAgentToAttach.getId());
                attachedBaLottoAgentCollection.add(baLottoAgentCollectionBaLottoAgentToAttach);
            }
            sts.setBaLottoAgentCollection(attachedBaLottoAgentCollection);
            Collection<UserMgrps> attachedUserMgrpsCollection = new ArrayList<UserMgrps>();
            for (UserMgrps userMgrpsCollectionUserMgrpsToAttach : sts.getUserMgrpsCollection()) {
                userMgrpsCollectionUserMgrpsToAttach = em.getReference(userMgrpsCollectionUserMgrpsToAttach.getClass(), userMgrpsCollectionUserMgrpsToAttach.getTid());
                attachedUserMgrpsCollection.add(userMgrpsCollectionUserMgrpsToAttach);
            }
            sts.setUserMgrpsCollection(attachedUserMgrpsCollection);
            em.persist(sts);
            if (CByy != null) {
                CByy.setISts(sts);
                CByy = em.merge(CByy);
            }
            if (UByy != null) {
                Sts oldIStsOfUByy = UByy.getISts();
                if (oldIStsOfUByy != null) {
                    oldIStsOfUByy.setUByy(null);
                    oldIStsOfUByy = em.merge(oldIStsOfUByy);
                }
                UByy.setISts(sts);
                UByy = em.merge(UByy);
            }
            for (Rolap rolapCollectionRolap : sts.getRolapCollection()) {
                Sts oldIStsOfRolapCollectionRolap = rolapCollectionRolap.getISts();
                rolapCollectionRolap.setISts(sts);
                rolapCollectionRolap = em.merge(rolapCollectionRolap);
                if (oldIStsOfRolapCollectionRolap != null) {
                    oldIStsOfRolapCollectionRolap.getRolapCollection().remove(rolapCollectionRolap);
                    oldIStsOfRolapCollectionRolap = em.merge(oldIStsOfRolapCollectionRolap);
                }
            }
            for (PassConf passConfCollectionPassConf : sts.getPassConfCollection()) {
                Sts oldStsOfPassConfCollectionPassConf = passConfCollectionPassConf.getSts();
                passConfCollectionPassConf.setSts(sts);
                passConfCollectionPassConf = em.merge(passConfCollectionPassConf);
                if (oldStsOfPassConfCollectionPassConf != null) {
                    oldStsOfPassConfCollectionPassConf.getPassConfCollection().remove(passConfCollectionPassConf);
                    oldStsOfPassConfCollectionPassConf = em.merge(oldStsOfPassConfCollectionPassConf);
                }
            }
            for (BaLottoConfig baLottoConfigCollectionBaLottoConfig : sts.getBaLottoConfigCollection()) {
                Sts oldStsOfBaLottoConfigCollectionBaLottoConfig = baLottoConfigCollectionBaLottoConfig.getSts();
                baLottoConfigCollectionBaLottoConfig.setSts(sts);
                baLottoConfigCollectionBaLottoConfig = em.merge(baLottoConfigCollectionBaLottoConfig);
                if (oldStsOfBaLottoConfigCollectionBaLottoConfig != null) {
                    oldStsOfBaLottoConfigCollectionBaLottoConfig.getBaLottoConfigCollection().remove(baLottoConfigCollectionBaLottoConfig);
                    oldStsOfBaLottoConfigCollectionBaLottoConfig = em.merge(oldStsOfBaLottoConfigCollectionBaLottoConfig);
                }
            }
            for (MGrps MGrpsCollectionMGrps : sts.getMGrpsCollection()) {
                Sts oldIStsOfMGrpsCollectionMGrps = MGrpsCollectionMGrps.getISts();
                MGrpsCollectionMGrps.setISts(sts);
                MGrpsCollectionMGrps = em.merge(MGrpsCollectionMGrps);
                if (oldIStsOfMGrpsCollectionMGrps != null) {
                    oldIStsOfMGrpsCollectionMGrps.getMGrpsCollection().remove(MGrpsCollectionMGrps);
                    oldIStsOfMGrpsCollectionMGrps = em.merge(oldIStsOfMGrpsCollectionMGrps);
                }
            }
            for (BaLottoMember baLottoMemberCollectionBaLottoMember : sts.getBaLottoMemberCollection()) {
                Sts oldStsOfBaLottoMemberCollectionBaLottoMember = baLottoMemberCollectionBaLottoMember.getSts();
                baLottoMemberCollectionBaLottoMember.setSts(sts);
                baLottoMemberCollectionBaLottoMember = em.merge(baLottoMemberCollectionBaLottoMember);
                if (oldStsOfBaLottoMemberCollectionBaLottoMember != null) {
                    oldStsOfBaLottoMemberCollectionBaLottoMember.getBaLottoMemberCollection().remove(baLottoMemberCollectionBaLottoMember);
                    oldStsOfBaLottoMemberCollectionBaLottoMember = em.merge(oldStsOfBaLottoMemberCollectionBaLottoMember);
                }
            }
            for (MItms MItmsCollectionMItms : sts.getMItmsCollection()) {
                Sts oldIStsOfMItmsCollectionMItms = MItmsCollectionMItms.getISts();
                MItmsCollectionMItms.setISts(sts);
                MItmsCollectionMItms = em.merge(MItmsCollectionMItms);
                if (oldIStsOfMItmsCollectionMItms != null) {
                    oldIStsOfMItmsCollectionMItms.getMItmsCollection().remove(MItmsCollectionMItms);
                    oldIStsOfMItmsCollectionMItms = em.merge(oldIStsOfMItmsCollectionMItms);
                }
            }
            for (Prle prleCollectionPrle : sts.getPrleCollection()) {
                Sts oldIStsOfPrleCollectionPrle = prleCollectionPrle.getISts();
                prleCollectionPrle.setISts(sts);
                prleCollectionPrle = em.merge(prleCollectionPrle);
                if (oldIStsOfPrleCollectionPrle != null) {
                    oldIStsOfPrleCollectionPrle.getPrleCollection().remove(prleCollectionPrle);
                    oldIStsOfPrleCollectionPrle = em.merge(oldIStsOfPrleCollectionPrle);
                }
            }
            for (BaLottoChannel baLottoChannelCollectionBaLottoChannel : sts.getBaLottoChannelCollection()) {
                Sts oldStsOfBaLottoChannelCollectionBaLottoChannel = baLottoChannelCollectionBaLottoChannel.getSts();
                baLottoChannelCollectionBaLottoChannel.setSts(sts);
                baLottoChannelCollectionBaLottoChannel = em.merge(baLottoChannelCollectionBaLottoChannel);
                if (oldStsOfBaLottoChannelCollectionBaLottoChannel != null) {
                    oldStsOfBaLottoChannelCollectionBaLottoChannel.getBaLottoChannelCollection().remove(baLottoChannelCollectionBaLottoChannel);
                    oldStsOfBaLottoChannelCollectionBaLottoChannel = em.merge(oldStsOfBaLottoChannelCollectionBaLottoChannel);
                }
            }
            for (BaLottoEntry baLottoEntryCollectionBaLottoEntry : sts.getBaLottoEntryCollection()) {
                Sts oldStatusOfBaLottoEntryCollectionBaLottoEntry = baLottoEntryCollectionBaLottoEntry.getStatus();
                baLottoEntryCollectionBaLottoEntry.setStatus(sts);
                baLottoEntryCollectionBaLottoEntry = em.merge(baLottoEntryCollectionBaLottoEntry);
                if (oldStatusOfBaLottoEntryCollectionBaLottoEntry != null) {
                    oldStatusOfBaLottoEntryCollectionBaLottoEntry.getBaLottoEntryCollection().remove(baLottoEntryCollectionBaLottoEntry);
                    oldStatusOfBaLottoEntryCollectionBaLottoEntry = em.merge(oldStatusOfBaLottoEntryCollectionBaLottoEntry);
                }
            }
            for (BaLottoAgent baLottoAgentCollectionBaLottoAgent : sts.getBaLottoAgentCollection()) {
                Sts oldStsOfBaLottoAgentCollectionBaLottoAgent = baLottoAgentCollectionBaLottoAgent.getSts();
                baLottoAgentCollectionBaLottoAgent.setSts(sts);
                baLottoAgentCollectionBaLottoAgent = em.merge(baLottoAgentCollectionBaLottoAgent);
                if (oldStsOfBaLottoAgentCollectionBaLottoAgent != null) {
                    oldStsOfBaLottoAgentCollectionBaLottoAgent.getBaLottoAgentCollection().remove(baLottoAgentCollectionBaLottoAgent);
                    oldStsOfBaLottoAgentCollectionBaLottoAgent = em.merge(oldStsOfBaLottoAgentCollectionBaLottoAgent);
                }
            }
            for (UserMgrps userMgrpsCollectionUserMgrps : sts.getUserMgrpsCollection()) {
                Sts oldStsOfUserMgrpsCollectionUserMgrps = userMgrpsCollectionUserMgrps.getSts();
                userMgrpsCollectionUserMgrps.setSts(sts);
                userMgrpsCollectionUserMgrps = em.merge(userMgrpsCollectionUserMgrps);
                if (oldStsOfUserMgrpsCollectionUserMgrps != null) {
                    oldStsOfUserMgrpsCollectionUserMgrps.getUserMgrpsCollection().remove(userMgrpsCollectionUserMgrps);
                    oldStsOfUserMgrpsCollectionUserMgrps = em.merge(oldStsOfUserMgrpsCollectionUserMgrps);
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

    public void edit(Sts sts) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Sts persistentSts = em.find(Sts.class, sts.getTid());
            Prle CByyOld = persistentSts.getCByy();
            Prle CByyNew = sts.getCByy();
            Prle UByyOld = persistentSts.getUByy();
            Prle UByyNew = sts.getUByy();
            Collection<Rolap> rolapCollectionOld = persistentSts.getRolapCollection();
            Collection<Rolap> rolapCollectionNew = sts.getRolapCollection();
            Collection<PassConf> passConfCollectionOld = persistentSts.getPassConfCollection();
            Collection<PassConf> passConfCollectionNew = sts.getPassConfCollection();
            Collection<BaLottoConfig> baLottoConfigCollectionOld = persistentSts.getBaLottoConfigCollection();
            Collection<BaLottoConfig> baLottoConfigCollectionNew = sts.getBaLottoConfigCollection();
            Collection<MGrps> MGrpsCollectionOld = persistentSts.getMGrpsCollection();
            Collection<MGrps> MGrpsCollectionNew = sts.getMGrpsCollection();
            Collection<BaLottoMember> baLottoMemberCollectionOld = persistentSts.getBaLottoMemberCollection();
            Collection<BaLottoMember> baLottoMemberCollectionNew = sts.getBaLottoMemberCollection();
            Collection<MItms> MItmsCollectionOld = persistentSts.getMItmsCollection();
            Collection<MItms> MItmsCollectionNew = sts.getMItmsCollection();
            Collection<Prle> prleCollectionOld = persistentSts.getPrleCollection();
            Collection<Prle> prleCollectionNew = sts.getPrleCollection();
            Collection<BaLottoChannel> baLottoChannelCollectionOld = persistentSts.getBaLottoChannelCollection();
            Collection<BaLottoChannel> baLottoChannelCollectionNew = sts.getBaLottoChannelCollection();
            Collection<BaLottoEntry> baLottoEntryCollectionOld = persistentSts.getBaLottoEntryCollection();
            Collection<BaLottoEntry> baLottoEntryCollectionNew = sts.getBaLottoEntryCollection();
            Collection<BaLottoAgent> baLottoAgentCollectionOld = persistentSts.getBaLottoAgentCollection();
            Collection<BaLottoAgent> baLottoAgentCollectionNew = sts.getBaLottoAgentCollection();
            Collection<UserMgrps> userMgrpsCollectionOld = persistentSts.getUserMgrpsCollection();
            Collection<UserMgrps> userMgrpsCollectionNew = sts.getUserMgrpsCollection();
            List<String> illegalOrphanMessages = null;
            if (CByyNew != null && !CByyNew.equals(CByyOld)) {
                Sts oldIStsOfCByy = CByyNew.getISts();
                if (oldIStsOfCByy != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Prle " + CByyNew + " already has an item of type Sts whose CByy column cannot be null. Please make another selection for the CByy field.");
                }
            }
            for (MItms MItmsCollectionOldMItms : MItmsCollectionOld) {
                if (!MItmsCollectionNew.contains(MItmsCollectionOldMItms)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MItms " + MItmsCollectionOldMItms + " since its ISts field is not nullable.");
                }
            }
            for (UserMgrps userMgrpsCollectionOldUserMgrps : userMgrpsCollectionOld) {
                if (!userMgrpsCollectionNew.contains(userMgrpsCollectionOldUserMgrps)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserMgrps " + userMgrpsCollectionOldUserMgrps + " since its sts field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (CByyNew != null) {
                CByyNew = em.getReference(CByyNew.getClass(), CByyNew.getTid());
                sts.setCByy(CByyNew);
            }
            if (UByyNew != null) {
                UByyNew = em.getReference(UByyNew.getClass(), UByyNew.getTid());
                sts.setUByy(UByyNew);
            }
            Collection<Rolap> attachedRolapCollectionNew = new ArrayList<Rolap>();
            for (Rolap rolapCollectionNewRolapToAttach : rolapCollectionNew) {
                rolapCollectionNewRolapToAttach = em.getReference(rolapCollectionNewRolapToAttach.getClass(), rolapCollectionNewRolapToAttach.getTid());
                attachedRolapCollectionNew.add(rolapCollectionNewRolapToAttach);
            }
            rolapCollectionNew = attachedRolapCollectionNew;
            sts.setRolapCollection(rolapCollectionNew);
            Collection<PassConf> attachedPassConfCollectionNew = new ArrayList<PassConf>();
            for (PassConf passConfCollectionNewPassConfToAttach : passConfCollectionNew) {
                passConfCollectionNewPassConfToAttach = em.getReference(passConfCollectionNewPassConfToAttach.getClass(), passConfCollectionNewPassConfToAttach.getTid());
                attachedPassConfCollectionNew.add(passConfCollectionNewPassConfToAttach);
            }
            passConfCollectionNew = attachedPassConfCollectionNew;
            sts.setPassConfCollection(passConfCollectionNew);
            Collection<BaLottoConfig> attachedBaLottoConfigCollectionNew = new ArrayList<BaLottoConfig>();
            for (BaLottoConfig baLottoConfigCollectionNewBaLottoConfigToAttach : baLottoConfigCollectionNew) {
                baLottoConfigCollectionNewBaLottoConfigToAttach = em.getReference(baLottoConfigCollectionNewBaLottoConfigToAttach.getClass(), baLottoConfigCollectionNewBaLottoConfigToAttach.getId());
                attachedBaLottoConfigCollectionNew.add(baLottoConfigCollectionNewBaLottoConfigToAttach);
            }
            baLottoConfigCollectionNew = attachedBaLottoConfigCollectionNew;
            sts.setBaLottoConfigCollection(baLottoConfigCollectionNew);
            Collection<MGrps> attachedMGrpsCollectionNew = new ArrayList<MGrps>();
            for (MGrps MGrpsCollectionNewMGrpsToAttach : MGrpsCollectionNew) {
                MGrpsCollectionNewMGrpsToAttach = em.getReference(MGrpsCollectionNewMGrpsToAttach.getClass(), MGrpsCollectionNewMGrpsToAttach.getTid());
                attachedMGrpsCollectionNew.add(MGrpsCollectionNewMGrpsToAttach);
            }
            MGrpsCollectionNew = attachedMGrpsCollectionNew;
            sts.setMGrpsCollection(MGrpsCollectionNew);
            Collection<BaLottoMember> attachedBaLottoMemberCollectionNew = new ArrayList<BaLottoMember>();
            for (BaLottoMember baLottoMemberCollectionNewBaLottoMemberToAttach : baLottoMemberCollectionNew) {
                baLottoMemberCollectionNewBaLottoMemberToAttach = em.getReference(baLottoMemberCollectionNewBaLottoMemberToAttach.getClass(), baLottoMemberCollectionNewBaLottoMemberToAttach.getId());
                attachedBaLottoMemberCollectionNew.add(baLottoMemberCollectionNewBaLottoMemberToAttach);
            }
            baLottoMemberCollectionNew = attachedBaLottoMemberCollectionNew;
            sts.setBaLottoMemberCollection(baLottoMemberCollectionNew);
            Collection<MItms> attachedMItmsCollectionNew = new ArrayList<MItms>();
            for (MItms MItmsCollectionNewMItmsToAttach : MItmsCollectionNew) {
                MItmsCollectionNewMItmsToAttach = em.getReference(MItmsCollectionNewMItmsToAttach.getClass(), MItmsCollectionNewMItmsToAttach.getTid());
                attachedMItmsCollectionNew.add(MItmsCollectionNewMItmsToAttach);
            }
            MItmsCollectionNew = attachedMItmsCollectionNew;
            sts.setMItmsCollection(MItmsCollectionNew);
            Collection<Prle> attachedPrleCollectionNew = new ArrayList<Prle>();
            for (Prle prleCollectionNewPrleToAttach : prleCollectionNew) {
                prleCollectionNewPrleToAttach = em.getReference(prleCollectionNewPrleToAttach.getClass(), prleCollectionNewPrleToAttach.getTid());
                attachedPrleCollectionNew.add(prleCollectionNewPrleToAttach);
            }
            prleCollectionNew = attachedPrleCollectionNew;
            sts.setPrleCollection(prleCollectionNew);
            Collection<BaLottoChannel> attachedBaLottoChannelCollectionNew = new ArrayList<BaLottoChannel>();
            for (BaLottoChannel baLottoChannelCollectionNewBaLottoChannelToAttach : baLottoChannelCollectionNew) {
                baLottoChannelCollectionNewBaLottoChannelToAttach = em.getReference(baLottoChannelCollectionNewBaLottoChannelToAttach.getClass(), baLottoChannelCollectionNewBaLottoChannelToAttach.getId());
                attachedBaLottoChannelCollectionNew.add(baLottoChannelCollectionNewBaLottoChannelToAttach);
            }
            baLottoChannelCollectionNew = attachedBaLottoChannelCollectionNew;
            sts.setBaLottoChannelCollection(baLottoChannelCollectionNew);
            Collection<BaLottoEntry> attachedBaLottoEntryCollectionNew = new ArrayList<BaLottoEntry>();
            for (BaLottoEntry baLottoEntryCollectionNewBaLottoEntryToAttach : baLottoEntryCollectionNew) {
                baLottoEntryCollectionNewBaLottoEntryToAttach = em.getReference(baLottoEntryCollectionNewBaLottoEntryToAttach.getClass(), baLottoEntryCollectionNewBaLottoEntryToAttach.getId());
                attachedBaLottoEntryCollectionNew.add(baLottoEntryCollectionNewBaLottoEntryToAttach);
            }
            baLottoEntryCollectionNew = attachedBaLottoEntryCollectionNew;
            sts.setBaLottoEntryCollection(baLottoEntryCollectionNew);
            Collection<BaLottoAgent> attachedBaLottoAgentCollectionNew = new ArrayList<BaLottoAgent>();
            for (BaLottoAgent baLottoAgentCollectionNewBaLottoAgentToAttach : baLottoAgentCollectionNew) {
                baLottoAgentCollectionNewBaLottoAgentToAttach = em.getReference(baLottoAgentCollectionNewBaLottoAgentToAttach.getClass(), baLottoAgentCollectionNewBaLottoAgentToAttach.getId());
                attachedBaLottoAgentCollectionNew.add(baLottoAgentCollectionNewBaLottoAgentToAttach);
            }
            baLottoAgentCollectionNew = attachedBaLottoAgentCollectionNew;
            sts.setBaLottoAgentCollection(baLottoAgentCollectionNew);
            Collection<UserMgrps> attachedUserMgrpsCollectionNew = new ArrayList<UserMgrps>();
            for (UserMgrps userMgrpsCollectionNewUserMgrpsToAttach : userMgrpsCollectionNew) {
                userMgrpsCollectionNewUserMgrpsToAttach = em.getReference(userMgrpsCollectionNewUserMgrpsToAttach.getClass(), userMgrpsCollectionNewUserMgrpsToAttach.getTid());
                attachedUserMgrpsCollectionNew.add(userMgrpsCollectionNewUserMgrpsToAttach);
            }
            userMgrpsCollectionNew = attachedUserMgrpsCollectionNew;
            sts.setUserMgrpsCollection(userMgrpsCollectionNew);
            sts = em.merge(sts);
            if (CByyOld != null && !CByyOld.equals(CByyNew)) {
                CByyOld.setISts(null);
                CByyOld = em.merge(CByyOld);
            }
            if (CByyNew != null && !CByyNew.equals(CByyOld)) {
                CByyNew.setISts(sts);
                CByyNew = em.merge(CByyNew);
            }
            if (UByyOld != null && !UByyOld.equals(UByyNew)) {
                UByyOld.setISts(null);
                UByyOld = em.merge(UByyOld);
            }
            if (UByyNew != null && !UByyNew.equals(UByyOld)) {
                Sts oldIStsOfUByy = UByyNew.getISts();
                if (oldIStsOfUByy != null) {
                    oldIStsOfUByy.setUByy(null);
                    oldIStsOfUByy = em.merge(oldIStsOfUByy);
                }
                UByyNew.setISts(sts);
                UByyNew = em.merge(UByyNew);
            }
            for (Rolap rolapCollectionOldRolap : rolapCollectionOld) {
                if (!rolapCollectionNew.contains(rolapCollectionOldRolap)) {
                    rolapCollectionOldRolap.setISts(null);
                    rolapCollectionOldRolap = em.merge(rolapCollectionOldRolap);
                }
            }
            for (Rolap rolapCollectionNewRolap : rolapCollectionNew) {
                if (!rolapCollectionOld.contains(rolapCollectionNewRolap)) {
                    Sts oldIStsOfRolapCollectionNewRolap = rolapCollectionNewRolap.getISts();
                    rolapCollectionNewRolap.setISts(sts);
                    rolapCollectionNewRolap = em.merge(rolapCollectionNewRolap);
                    if (oldIStsOfRolapCollectionNewRolap != null && !oldIStsOfRolapCollectionNewRolap.equals(sts)) {
                        oldIStsOfRolapCollectionNewRolap.getRolapCollection().remove(rolapCollectionNewRolap);
                        oldIStsOfRolapCollectionNewRolap = em.merge(oldIStsOfRolapCollectionNewRolap);
                    }
                }
            }
            for (PassConf passConfCollectionOldPassConf : passConfCollectionOld) {
                if (!passConfCollectionNew.contains(passConfCollectionOldPassConf)) {
                    passConfCollectionOldPassConf.setSts(null);
                    passConfCollectionOldPassConf = em.merge(passConfCollectionOldPassConf);
                }
            }
            for (PassConf passConfCollectionNewPassConf : passConfCollectionNew) {
                if (!passConfCollectionOld.contains(passConfCollectionNewPassConf)) {
                    Sts oldStsOfPassConfCollectionNewPassConf = passConfCollectionNewPassConf.getSts();
                    passConfCollectionNewPassConf.setSts(sts);
                    passConfCollectionNewPassConf = em.merge(passConfCollectionNewPassConf);
                    if (oldStsOfPassConfCollectionNewPassConf != null && !oldStsOfPassConfCollectionNewPassConf.equals(sts)) {
                        oldStsOfPassConfCollectionNewPassConf.getPassConfCollection().remove(passConfCollectionNewPassConf);
                        oldStsOfPassConfCollectionNewPassConf = em.merge(oldStsOfPassConfCollectionNewPassConf);
                    }
                }
            }
            for (BaLottoConfig baLottoConfigCollectionOldBaLottoConfig : baLottoConfigCollectionOld) {
                if (!baLottoConfigCollectionNew.contains(baLottoConfigCollectionOldBaLottoConfig)) {
                    baLottoConfigCollectionOldBaLottoConfig.setSts(null);
                    baLottoConfigCollectionOldBaLottoConfig = em.merge(baLottoConfigCollectionOldBaLottoConfig);
                }
            }
            for (BaLottoConfig baLottoConfigCollectionNewBaLottoConfig : baLottoConfigCollectionNew) {
                if (!baLottoConfigCollectionOld.contains(baLottoConfigCollectionNewBaLottoConfig)) {
                    Sts oldStsOfBaLottoConfigCollectionNewBaLottoConfig = baLottoConfigCollectionNewBaLottoConfig.getSts();
                    baLottoConfigCollectionNewBaLottoConfig.setSts(sts);
                    baLottoConfigCollectionNewBaLottoConfig = em.merge(baLottoConfigCollectionNewBaLottoConfig);
                    if (oldStsOfBaLottoConfigCollectionNewBaLottoConfig != null && !oldStsOfBaLottoConfigCollectionNewBaLottoConfig.equals(sts)) {
                        oldStsOfBaLottoConfigCollectionNewBaLottoConfig.getBaLottoConfigCollection().remove(baLottoConfigCollectionNewBaLottoConfig);
                        oldStsOfBaLottoConfigCollectionNewBaLottoConfig = em.merge(oldStsOfBaLottoConfigCollectionNewBaLottoConfig);
                    }
                }
            }
            for (MGrps MGrpsCollectionOldMGrps : MGrpsCollectionOld) {
                if (!MGrpsCollectionNew.contains(MGrpsCollectionOldMGrps)) {
                    MGrpsCollectionOldMGrps.setISts(null);
                    MGrpsCollectionOldMGrps = em.merge(MGrpsCollectionOldMGrps);
                }
            }
            for (MGrps MGrpsCollectionNewMGrps : MGrpsCollectionNew) {
                if (!MGrpsCollectionOld.contains(MGrpsCollectionNewMGrps)) {
                    Sts oldIStsOfMGrpsCollectionNewMGrps = MGrpsCollectionNewMGrps.getISts();
                    MGrpsCollectionNewMGrps.setISts(sts);
                    MGrpsCollectionNewMGrps = em.merge(MGrpsCollectionNewMGrps);
                    if (oldIStsOfMGrpsCollectionNewMGrps != null && !oldIStsOfMGrpsCollectionNewMGrps.equals(sts)) {
                        oldIStsOfMGrpsCollectionNewMGrps.getMGrpsCollection().remove(MGrpsCollectionNewMGrps);
                        oldIStsOfMGrpsCollectionNewMGrps = em.merge(oldIStsOfMGrpsCollectionNewMGrps);
                    }
                }
            }
            for (BaLottoMember baLottoMemberCollectionOldBaLottoMember : baLottoMemberCollectionOld) {
                if (!baLottoMemberCollectionNew.contains(baLottoMemberCollectionOldBaLottoMember)) {
                    baLottoMemberCollectionOldBaLottoMember.setSts(null);
                    baLottoMemberCollectionOldBaLottoMember = em.merge(baLottoMemberCollectionOldBaLottoMember);
                }
            }
            for (BaLottoMember baLottoMemberCollectionNewBaLottoMember : baLottoMemberCollectionNew) {
                if (!baLottoMemberCollectionOld.contains(baLottoMemberCollectionNewBaLottoMember)) {
                    Sts oldStsOfBaLottoMemberCollectionNewBaLottoMember = baLottoMemberCollectionNewBaLottoMember.getSts();
                    baLottoMemberCollectionNewBaLottoMember.setSts(sts);
                    baLottoMemberCollectionNewBaLottoMember = em.merge(baLottoMemberCollectionNewBaLottoMember);
                    if (oldStsOfBaLottoMemberCollectionNewBaLottoMember != null && !oldStsOfBaLottoMemberCollectionNewBaLottoMember.equals(sts)) {
                        oldStsOfBaLottoMemberCollectionNewBaLottoMember.getBaLottoMemberCollection().remove(baLottoMemberCollectionNewBaLottoMember);
                        oldStsOfBaLottoMemberCollectionNewBaLottoMember = em.merge(oldStsOfBaLottoMemberCollectionNewBaLottoMember);
                    }
                }
            }
            for (MItms MItmsCollectionNewMItms : MItmsCollectionNew) {
                if (!MItmsCollectionOld.contains(MItmsCollectionNewMItms)) {
                    Sts oldIStsOfMItmsCollectionNewMItms = MItmsCollectionNewMItms.getISts();
                    MItmsCollectionNewMItms.setISts(sts);
                    MItmsCollectionNewMItms = em.merge(MItmsCollectionNewMItms);
                    if (oldIStsOfMItmsCollectionNewMItms != null && !oldIStsOfMItmsCollectionNewMItms.equals(sts)) {
                        oldIStsOfMItmsCollectionNewMItms.getMItmsCollection().remove(MItmsCollectionNewMItms);
                        oldIStsOfMItmsCollectionNewMItms = em.merge(oldIStsOfMItmsCollectionNewMItms);
                    }
                }
            }
            for (Prle prleCollectionOldPrle : prleCollectionOld) {
                if (!prleCollectionNew.contains(prleCollectionOldPrle)) {
                    prleCollectionOldPrle.setISts(null);
                    prleCollectionOldPrle = em.merge(prleCollectionOldPrle);
                }
            }
            for (Prle prleCollectionNewPrle : prleCollectionNew) {
                if (!prleCollectionOld.contains(prleCollectionNewPrle)) {
                    Sts oldIStsOfPrleCollectionNewPrle = prleCollectionNewPrle.getISts();
                    prleCollectionNewPrle.setISts(sts);
                    prleCollectionNewPrle = em.merge(prleCollectionNewPrle);
                    if (oldIStsOfPrleCollectionNewPrle != null && !oldIStsOfPrleCollectionNewPrle.equals(sts)) {
                        oldIStsOfPrleCollectionNewPrle.getPrleCollection().remove(prleCollectionNewPrle);
                        oldIStsOfPrleCollectionNewPrle = em.merge(oldIStsOfPrleCollectionNewPrle);
                    }
                }
            }
            for (BaLottoChannel baLottoChannelCollectionOldBaLottoChannel : baLottoChannelCollectionOld) {
                if (!baLottoChannelCollectionNew.contains(baLottoChannelCollectionOldBaLottoChannel)) {
                    baLottoChannelCollectionOldBaLottoChannel.setSts(null);
                    baLottoChannelCollectionOldBaLottoChannel = em.merge(baLottoChannelCollectionOldBaLottoChannel);
                }
            }
            for (BaLottoChannel baLottoChannelCollectionNewBaLottoChannel : baLottoChannelCollectionNew) {
                if (!baLottoChannelCollectionOld.contains(baLottoChannelCollectionNewBaLottoChannel)) {
                    Sts oldStsOfBaLottoChannelCollectionNewBaLottoChannel = baLottoChannelCollectionNewBaLottoChannel.getSts();
                    baLottoChannelCollectionNewBaLottoChannel.setSts(sts);
                    baLottoChannelCollectionNewBaLottoChannel = em.merge(baLottoChannelCollectionNewBaLottoChannel);
                    if (oldStsOfBaLottoChannelCollectionNewBaLottoChannel != null && !oldStsOfBaLottoChannelCollectionNewBaLottoChannel.equals(sts)) {
                        oldStsOfBaLottoChannelCollectionNewBaLottoChannel.getBaLottoChannelCollection().remove(baLottoChannelCollectionNewBaLottoChannel);
                        oldStsOfBaLottoChannelCollectionNewBaLottoChannel = em.merge(oldStsOfBaLottoChannelCollectionNewBaLottoChannel);
                    }
                }
            }
            for (BaLottoEntry baLottoEntryCollectionOldBaLottoEntry : baLottoEntryCollectionOld) {
                if (!baLottoEntryCollectionNew.contains(baLottoEntryCollectionOldBaLottoEntry)) {
                    baLottoEntryCollectionOldBaLottoEntry.setStatus(null);
                    baLottoEntryCollectionOldBaLottoEntry = em.merge(baLottoEntryCollectionOldBaLottoEntry);
                }
            }
            for (BaLottoEntry baLottoEntryCollectionNewBaLottoEntry : baLottoEntryCollectionNew) {
                if (!baLottoEntryCollectionOld.contains(baLottoEntryCollectionNewBaLottoEntry)) {
                    Sts oldStatusOfBaLottoEntryCollectionNewBaLottoEntry = baLottoEntryCollectionNewBaLottoEntry.getStatus();
                    baLottoEntryCollectionNewBaLottoEntry.setStatus(sts);
                    baLottoEntryCollectionNewBaLottoEntry = em.merge(baLottoEntryCollectionNewBaLottoEntry);
                    if (oldStatusOfBaLottoEntryCollectionNewBaLottoEntry != null && !oldStatusOfBaLottoEntryCollectionNewBaLottoEntry.equals(sts)) {
                        oldStatusOfBaLottoEntryCollectionNewBaLottoEntry.getBaLottoEntryCollection().remove(baLottoEntryCollectionNewBaLottoEntry);
                        oldStatusOfBaLottoEntryCollectionNewBaLottoEntry = em.merge(oldStatusOfBaLottoEntryCollectionNewBaLottoEntry);
                    }
                }
            }
            for (BaLottoAgent baLottoAgentCollectionOldBaLottoAgent : baLottoAgentCollectionOld) {
                if (!baLottoAgentCollectionNew.contains(baLottoAgentCollectionOldBaLottoAgent)) {
                    baLottoAgentCollectionOldBaLottoAgent.setSts(null);
                    baLottoAgentCollectionOldBaLottoAgent = em.merge(baLottoAgentCollectionOldBaLottoAgent);
                }
            }
            for (BaLottoAgent baLottoAgentCollectionNewBaLottoAgent : baLottoAgentCollectionNew) {
                if (!baLottoAgentCollectionOld.contains(baLottoAgentCollectionNewBaLottoAgent)) {
                    Sts oldStsOfBaLottoAgentCollectionNewBaLottoAgent = baLottoAgentCollectionNewBaLottoAgent.getSts();
                    baLottoAgentCollectionNewBaLottoAgent.setSts(sts);
                    baLottoAgentCollectionNewBaLottoAgent = em.merge(baLottoAgentCollectionNewBaLottoAgent);
                    if (oldStsOfBaLottoAgentCollectionNewBaLottoAgent != null && !oldStsOfBaLottoAgentCollectionNewBaLottoAgent.equals(sts)) {
                        oldStsOfBaLottoAgentCollectionNewBaLottoAgent.getBaLottoAgentCollection().remove(baLottoAgentCollectionNewBaLottoAgent);
                        oldStsOfBaLottoAgentCollectionNewBaLottoAgent = em.merge(oldStsOfBaLottoAgentCollectionNewBaLottoAgent);
                    }
                }
            }
            for (UserMgrps userMgrpsCollectionNewUserMgrps : userMgrpsCollectionNew) {
                if (!userMgrpsCollectionOld.contains(userMgrpsCollectionNewUserMgrps)) {
                    Sts oldStsOfUserMgrpsCollectionNewUserMgrps = userMgrpsCollectionNewUserMgrps.getSts();
                    userMgrpsCollectionNewUserMgrps.setSts(sts);
                    userMgrpsCollectionNewUserMgrps = em.merge(userMgrpsCollectionNewUserMgrps);
                    if (oldStsOfUserMgrpsCollectionNewUserMgrps != null && !oldStsOfUserMgrpsCollectionNewUserMgrps.equals(sts)) {
                        oldStsOfUserMgrpsCollectionNewUserMgrps.getUserMgrpsCollection().remove(userMgrpsCollectionNewUserMgrps);
                        oldStsOfUserMgrpsCollectionNewUserMgrps = em.merge(oldStsOfUserMgrpsCollectionNewUserMgrps);
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
                Long id = sts.getTid();
                if (findSts(id) == null) {
                    throw new NonexistentEntityException("The sts with id " + id + " no longer exists.");
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
            Sts sts;
            try {
                sts = em.getReference(Sts.class, id);
                sts.getTid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sts with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<MItms> MItmsCollectionOrphanCheck = sts.getMItmsCollection();
            for (MItms MItmsCollectionOrphanCheckMItms : MItmsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sts (" + sts + ") cannot be destroyed since the MItms " + MItmsCollectionOrphanCheckMItms + " in its MItmsCollection field has a non-nullable ISts field.");
            }
            Collection<UserMgrps> userMgrpsCollectionOrphanCheck = sts.getUserMgrpsCollection();
            for (UserMgrps userMgrpsCollectionOrphanCheckUserMgrps : userMgrpsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sts (" + sts + ") cannot be destroyed since the UserMgrps " + userMgrpsCollectionOrphanCheckUserMgrps + " in its userMgrpsCollection field has a non-nullable sts field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Prle CByy = sts.getCByy();
            if (CByy != null) {
                CByy.setISts(null);
                CByy = em.merge(CByy);
            }
            Prle UByy = sts.getUByy();
            if (UByy != null) {
                UByy.setISts(null);
                UByy = em.merge(UByy);
            }
            Collection<Rolap> rolapCollection = sts.getRolapCollection();
            for (Rolap rolapCollectionRolap : rolapCollection) {
                rolapCollectionRolap.setISts(null);
                rolapCollectionRolap = em.merge(rolapCollectionRolap);
            }
            Collection<PassConf> passConfCollection = sts.getPassConfCollection();
            for (PassConf passConfCollectionPassConf : passConfCollection) {
                passConfCollectionPassConf.setSts(null);
                passConfCollectionPassConf = em.merge(passConfCollectionPassConf);
            }
            Collection<BaLottoConfig> baLottoConfigCollection = sts.getBaLottoConfigCollection();
            for (BaLottoConfig baLottoConfigCollectionBaLottoConfig : baLottoConfigCollection) {
                baLottoConfigCollectionBaLottoConfig.setSts(null);
                baLottoConfigCollectionBaLottoConfig = em.merge(baLottoConfigCollectionBaLottoConfig);
            }
            Collection<MGrps> MGrpsCollection = sts.getMGrpsCollection();
            for (MGrps MGrpsCollectionMGrps : MGrpsCollection) {
                MGrpsCollectionMGrps.setISts(null);
                MGrpsCollectionMGrps = em.merge(MGrpsCollectionMGrps);
            }
            Collection<BaLottoMember> baLottoMemberCollection = sts.getBaLottoMemberCollection();
            for (BaLottoMember baLottoMemberCollectionBaLottoMember : baLottoMemberCollection) {
                baLottoMemberCollectionBaLottoMember.setSts(null);
                baLottoMemberCollectionBaLottoMember = em.merge(baLottoMemberCollectionBaLottoMember);
            }
            Collection<Prle> prleCollection = sts.getPrleCollection();
            for (Prle prleCollectionPrle : prleCollection) {
                prleCollectionPrle.setISts(null);
                prleCollectionPrle = em.merge(prleCollectionPrle);
            }
            Collection<BaLottoChannel> baLottoChannelCollection = sts.getBaLottoChannelCollection();
            for (BaLottoChannel baLottoChannelCollectionBaLottoChannel : baLottoChannelCollection) {
                baLottoChannelCollectionBaLottoChannel.setSts(null);
                baLottoChannelCollectionBaLottoChannel = em.merge(baLottoChannelCollectionBaLottoChannel);
            }
            Collection<BaLottoEntry> baLottoEntryCollection = sts.getBaLottoEntryCollection();
            for (BaLottoEntry baLottoEntryCollectionBaLottoEntry : baLottoEntryCollection) {
                baLottoEntryCollectionBaLottoEntry.setStatus(null);
                baLottoEntryCollectionBaLottoEntry = em.merge(baLottoEntryCollectionBaLottoEntry);
            }
            Collection<BaLottoAgent> baLottoAgentCollection = sts.getBaLottoAgentCollection();
            for (BaLottoAgent baLottoAgentCollectionBaLottoAgent : baLottoAgentCollection) {
                baLottoAgentCollectionBaLottoAgent.setSts(null);
                baLottoAgentCollectionBaLottoAgent = em.merge(baLottoAgentCollectionBaLottoAgent);
            }
            em.remove(sts);
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

    public List<Sts> findStsEntities() {
        return findStsEntities(true, -1, -1);
    }

    public List<Sts> findStsEntities(int maxResults, int firstResult) {
        return findStsEntities(false, maxResults, firstResult);
    }

    private List<Sts> findStsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sts.class));
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

    public Sts findSts(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sts.class, id);
        } finally {
            em.close();
        }
    }

    public int getStsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sts> rt = cq.from(Sts.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
