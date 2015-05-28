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
import com.bbc.arsenallotto.model.Rolap;
import com.bbc.arsenallotto.model.Sts;
import java.util.ArrayList;
import java.util.Collection;
import com.bbc.arsenallotto.model.PassConf;
import com.bbc.arsenallotto.model.BaLottoConfig;
import com.bbc.arsenallotto.model.MGrps;
import com.bbc.arsenallotto.model.BaLottoMember;
import com.bbc.arsenallotto.model.MItms;
import com.bbc.arsenallotto.model.BaLottoChannel;
import com.bbc.arsenallotto.model.BaLottoResponse;
import com.bbc.arsenallotto.model.BaLottoAgent;
import com.bbc.arsenallotto.model.Prle;
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
public class PrleJpaController implements Serializable {

    public PrleJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prle prle) throws RollbackFailureException, Exception {
        if (prle.getRolapCollection() == null) {
            prle.setRolapCollection(new ArrayList<Rolap>());
        }
        if (prle.getRolapCollection1() == null) {
            prle.setRolapCollection1(new ArrayList<Rolap>());
        }
        if (prle.getRolapCollection2() == null) {
            prle.setRolapCollection2(new ArrayList<Rolap>());
        }
        if (prle.getPassConfCollection() == null) {
            prle.setPassConfCollection(new ArrayList<PassConf>());
        }
        if (prle.getPassConfCollection1() == null) {
            prle.setPassConfCollection1(new ArrayList<PassConf>());
        }
        if (prle.getBaLottoConfigCollection() == null) {
            prle.setBaLottoConfigCollection(new ArrayList<BaLottoConfig>());
        }
        if (prle.getMGrpsCollection() == null) {
            prle.setMGrpsCollection(new ArrayList<MGrps>());
        }
        if (prle.getMGrpsCollection1() == null) {
            prle.setMGrpsCollection1(new ArrayList<MGrps>());
        }
        if (prle.getMGrpsCollection2() == null) {
            prle.setMGrpsCollection2(new ArrayList<MGrps>());
        }
        if (prle.getBaLottoMemberCollection() == null) {
            prle.setBaLottoMemberCollection(new ArrayList<BaLottoMember>());
        }
        if (prle.getMItmsCollection() == null) {
            prle.setMItmsCollection(new ArrayList<MItms>());
        }
        if (prle.getMItmsCollection1() == null) {
            prle.setMItmsCollection1(new ArrayList<MItms>());
        }
        if (prle.getStsCollection() == null) {
            prle.setStsCollection(new ArrayList<Sts>());
        }
        if (prle.getStsCollection1() == null) {
            prle.setStsCollection1(new ArrayList<Sts>());
        }
        if (prle.getBaLottoChannelCollection() == null) {
            prle.setBaLottoChannelCollection(new ArrayList<BaLottoChannel>());
        }
        if (prle.getBaLottoResponseCollection() == null) {
            prle.setBaLottoResponseCollection(new ArrayList<BaLottoResponse>());
        }
        if (prle.getBaLottoAgentCollection() == null) {
            prle.setBaLottoAgentCollection(new ArrayList<BaLottoAgent>());
        }
        if (prle.getUserMgrpsCollection() == null) {
            prle.setUserMgrpsCollection(new ArrayList<UserMgrps>());
        }
        if (prle.getUserMgrpsCollection1() == null) {
            prle.setUserMgrpsCollection1(new ArrayList<UserMgrps>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Rolap RIdd = prle.getRIdd();
            if (RIdd != null) {
                RIdd = em.getReference(RIdd.getClass(), RIdd.getTid());
                prle.setRIdd(RIdd);
            }
            Sts ISts = prle.getISts();
            if (ISts != null) {
                ISts = em.getReference(ISts.getClass(), ISts.getTid());
                prle.setISts(ISts);
            }
            Collection<Rolap> attachedRolapCollection = new ArrayList<Rolap>();
            for (Rolap rolapCollectionRolapToAttach : prle.getRolapCollection()) {
                rolapCollectionRolapToAttach = em.getReference(rolapCollectionRolapToAttach.getClass(), rolapCollectionRolapToAttach.getTid());
                attachedRolapCollection.add(rolapCollectionRolapToAttach);
            }
            prle.setRolapCollection(attachedRolapCollection);
            Collection<Rolap> attachedRolapCollection1 = new ArrayList<Rolap>();
            for (Rolap rolapCollection1RolapToAttach : prle.getRolapCollection1()) {
                rolapCollection1RolapToAttach = em.getReference(rolapCollection1RolapToAttach.getClass(), rolapCollection1RolapToAttach.getTid());
                attachedRolapCollection1.add(rolapCollection1RolapToAttach);
            }
            prle.setRolapCollection1(attachedRolapCollection1);
            Collection<Rolap> attachedRolapCollection2 = new ArrayList<Rolap>();
            for (Rolap rolapCollection2RolapToAttach : prle.getRolapCollection2()) {
                rolapCollection2RolapToAttach = em.getReference(rolapCollection2RolapToAttach.getClass(), rolapCollection2RolapToAttach.getTid());
                attachedRolapCollection2.add(rolapCollection2RolapToAttach);
            }
            prle.setRolapCollection2(attachedRolapCollection2);
            Collection<PassConf> attachedPassConfCollection = new ArrayList<PassConf>();
            for (PassConf passConfCollectionPassConfToAttach : prle.getPassConfCollection()) {
                passConfCollectionPassConfToAttach = em.getReference(passConfCollectionPassConfToAttach.getClass(), passConfCollectionPassConfToAttach.getTid());
                attachedPassConfCollection.add(passConfCollectionPassConfToAttach);
            }
            prle.setPassConfCollection(attachedPassConfCollection);
            Collection<PassConf> attachedPassConfCollection1 = new ArrayList<PassConf>();
            for (PassConf passConfCollection1PassConfToAttach : prle.getPassConfCollection1()) {
                passConfCollection1PassConfToAttach = em.getReference(passConfCollection1PassConfToAttach.getClass(), passConfCollection1PassConfToAttach.getTid());
                attachedPassConfCollection1.add(passConfCollection1PassConfToAttach);
            }
            prle.setPassConfCollection1(attachedPassConfCollection1);
            Collection<BaLottoConfig> attachedBaLottoConfigCollection = new ArrayList<BaLottoConfig>();
            for (BaLottoConfig baLottoConfigCollectionBaLottoConfigToAttach : prle.getBaLottoConfigCollection()) {
                baLottoConfigCollectionBaLottoConfigToAttach = em.getReference(baLottoConfigCollectionBaLottoConfigToAttach.getClass(), baLottoConfigCollectionBaLottoConfigToAttach.getId());
                attachedBaLottoConfigCollection.add(baLottoConfigCollectionBaLottoConfigToAttach);
            }
            prle.setBaLottoConfigCollection(attachedBaLottoConfigCollection);
            Collection<MGrps> attachedMGrpsCollection = new ArrayList<MGrps>();
            for (MGrps MGrpsCollectionMGrpsToAttach : prle.getMGrpsCollection()) {
                MGrpsCollectionMGrpsToAttach = em.getReference(MGrpsCollectionMGrpsToAttach.getClass(), MGrpsCollectionMGrpsToAttach.getTid());
                attachedMGrpsCollection.add(MGrpsCollectionMGrpsToAttach);
            }
            prle.setMGrpsCollection(attachedMGrpsCollection);
            Collection<MGrps> attachedMGrpsCollection1 = new ArrayList<MGrps>();
            for (MGrps MGrpsCollection1MGrpsToAttach : prle.getMGrpsCollection1()) {
                MGrpsCollection1MGrpsToAttach = em.getReference(MGrpsCollection1MGrpsToAttach.getClass(), MGrpsCollection1MGrpsToAttach.getTid());
                attachedMGrpsCollection1.add(MGrpsCollection1MGrpsToAttach);
            }
            prle.setMGrpsCollection1(attachedMGrpsCollection1);
            Collection<MGrps> attachedMGrpsCollection2 = new ArrayList<MGrps>();
            for (MGrps MGrpsCollection2MGrpsToAttach : prle.getMGrpsCollection2()) {
                MGrpsCollection2MGrpsToAttach = em.getReference(MGrpsCollection2MGrpsToAttach.getClass(), MGrpsCollection2MGrpsToAttach.getTid());
                attachedMGrpsCollection2.add(MGrpsCollection2MGrpsToAttach);
            }
            prle.setMGrpsCollection2(attachedMGrpsCollection2);
            Collection<BaLottoMember> attachedBaLottoMemberCollection = new ArrayList<BaLottoMember>();
            for (BaLottoMember baLottoMemberCollectionBaLottoMemberToAttach : prle.getBaLottoMemberCollection()) {
                baLottoMemberCollectionBaLottoMemberToAttach = em.getReference(baLottoMemberCollectionBaLottoMemberToAttach.getClass(), baLottoMemberCollectionBaLottoMemberToAttach.getId());
                attachedBaLottoMemberCollection.add(baLottoMemberCollectionBaLottoMemberToAttach);
            }
            prle.setBaLottoMemberCollection(attachedBaLottoMemberCollection);
            Collection<MItms> attachedMItmsCollection = new ArrayList<MItms>();
            for (MItms MItmsCollectionMItmsToAttach : prle.getMItmsCollection()) {
                MItmsCollectionMItmsToAttach = em.getReference(MItmsCollectionMItmsToAttach.getClass(), MItmsCollectionMItmsToAttach.getTid());
                attachedMItmsCollection.add(MItmsCollectionMItmsToAttach);
            }
            prle.setMItmsCollection(attachedMItmsCollection);
            Collection<MItms> attachedMItmsCollection1 = new ArrayList<MItms>();
            for (MItms MItmsCollection1MItmsToAttach : prle.getMItmsCollection1()) {
                MItmsCollection1MItmsToAttach = em.getReference(MItmsCollection1MItmsToAttach.getClass(), MItmsCollection1MItmsToAttach.getTid());
                attachedMItmsCollection1.add(MItmsCollection1MItmsToAttach);
            }
            prle.setMItmsCollection1(attachedMItmsCollection1);
            Collection<Sts> attachedStsCollection = new ArrayList<Sts>();
            for (Sts stsCollectionStsToAttach : prle.getStsCollection()) {
                stsCollectionStsToAttach = em.getReference(stsCollectionStsToAttach.getClass(), stsCollectionStsToAttach.getTid());
                attachedStsCollection.add(stsCollectionStsToAttach);
            }
            prle.setStsCollection(attachedStsCollection);
            Collection<Sts> attachedStsCollection1 = new ArrayList<Sts>();
            for (Sts stsCollection1StsToAttach : prle.getStsCollection1()) {
                stsCollection1StsToAttach = em.getReference(stsCollection1StsToAttach.getClass(), stsCollection1StsToAttach.getTid());
                attachedStsCollection1.add(stsCollection1StsToAttach);
            }
            prle.setStsCollection1(attachedStsCollection1);
            Collection<BaLottoChannel> attachedBaLottoChannelCollection = new ArrayList<BaLottoChannel>();
            for (BaLottoChannel baLottoChannelCollectionBaLottoChannelToAttach : prle.getBaLottoChannelCollection()) {
                baLottoChannelCollectionBaLottoChannelToAttach = em.getReference(baLottoChannelCollectionBaLottoChannelToAttach.getClass(), baLottoChannelCollectionBaLottoChannelToAttach.getId());
                attachedBaLottoChannelCollection.add(baLottoChannelCollectionBaLottoChannelToAttach);
            }
            prle.setBaLottoChannelCollection(attachedBaLottoChannelCollection);
            Collection<BaLottoResponse> attachedBaLottoResponseCollection = new ArrayList<BaLottoResponse>();
            for (BaLottoResponse baLottoResponseCollectionBaLottoResponseToAttach : prle.getBaLottoResponseCollection()) {
                baLottoResponseCollectionBaLottoResponseToAttach = em.getReference(baLottoResponseCollectionBaLottoResponseToAttach.getClass(), baLottoResponseCollectionBaLottoResponseToAttach.getId());
                attachedBaLottoResponseCollection.add(baLottoResponseCollectionBaLottoResponseToAttach);
            }
            prle.setBaLottoResponseCollection(attachedBaLottoResponseCollection);
            Collection<BaLottoAgent> attachedBaLottoAgentCollection = new ArrayList<BaLottoAgent>();
            for (BaLottoAgent baLottoAgentCollectionBaLottoAgentToAttach : prle.getBaLottoAgentCollection()) {
                baLottoAgentCollectionBaLottoAgentToAttach = em.getReference(baLottoAgentCollectionBaLottoAgentToAttach.getClass(), baLottoAgentCollectionBaLottoAgentToAttach.getId());
                attachedBaLottoAgentCollection.add(baLottoAgentCollectionBaLottoAgentToAttach);
            }
            prle.setBaLottoAgentCollection(attachedBaLottoAgentCollection);
            Collection<UserMgrps> attachedUserMgrpsCollection = new ArrayList<UserMgrps>();
            for (UserMgrps userMgrpsCollectionUserMgrpsToAttach : prle.getUserMgrpsCollection()) {
                userMgrpsCollectionUserMgrpsToAttach = em.getReference(userMgrpsCollectionUserMgrpsToAttach.getClass(), userMgrpsCollectionUserMgrpsToAttach.getTid());
                attachedUserMgrpsCollection.add(userMgrpsCollectionUserMgrpsToAttach);
            }
            prle.setUserMgrpsCollection(attachedUserMgrpsCollection);
            Collection<UserMgrps> attachedUserMgrpsCollection1 = new ArrayList<UserMgrps>();
            for (UserMgrps userMgrpsCollection1UserMgrpsToAttach : prle.getUserMgrpsCollection1()) {
                userMgrpsCollection1UserMgrpsToAttach = em.getReference(userMgrpsCollection1UserMgrpsToAttach.getClass(), userMgrpsCollection1UserMgrpsToAttach.getTid());
                attachedUserMgrpsCollection1.add(userMgrpsCollection1UserMgrpsToAttach);
            }
            prle.setUserMgrpsCollection1(attachedUserMgrpsCollection1);
            em.persist(prle);
            if (RIdd != null) {
                Prle oldAByyOfRIdd = RIdd.getAByy();
                if (oldAByyOfRIdd != null) {
                    oldAByyOfRIdd.setRIdd(null);
                    oldAByyOfRIdd = em.merge(oldAByyOfRIdd);
                }
                RIdd.setAByy(prle);
                RIdd = em.merge(RIdd);
            }
            if (ISts != null) {
                ISts.getPrleCollection().add(prle);
                ISts = em.merge(ISts);
            }
            for (Rolap rolapCollectionRolap : prle.getRolapCollection()) {
                Prle oldAByyOfRolapCollectionRolap = rolapCollectionRolap.getAByy();
                rolapCollectionRolap.setAByy(prle);
                rolapCollectionRolap = em.merge(rolapCollectionRolap);
                if (oldAByyOfRolapCollectionRolap != null) {
                    oldAByyOfRolapCollectionRolap.getRolapCollection().remove(rolapCollectionRolap);
                    oldAByyOfRolapCollectionRolap = em.merge(oldAByyOfRolapCollectionRolap);
                }
            }
            for (Rolap rolapCollection1Rolap : prle.getRolapCollection1()) {
                Prle oldUByyOfRolapCollection1Rolap = rolapCollection1Rolap.getUByy();
                rolapCollection1Rolap.setUByy(prle);
                rolapCollection1Rolap = em.merge(rolapCollection1Rolap);
                if (oldUByyOfRolapCollection1Rolap != null) {
                    oldUByyOfRolapCollection1Rolap.getRolapCollection1().remove(rolapCollection1Rolap);
                    oldUByyOfRolapCollection1Rolap = em.merge(oldUByyOfRolapCollection1Rolap);
                }
            }
            for (Rolap rolapCollection2Rolap : prle.getRolapCollection2()) {
                Prle oldCByyOfRolapCollection2Rolap = rolapCollection2Rolap.getCByy();
                rolapCollection2Rolap.setCByy(prle);
                rolapCollection2Rolap = em.merge(rolapCollection2Rolap);
                if (oldCByyOfRolapCollection2Rolap != null) {
                    oldCByyOfRolapCollection2Rolap.getRolapCollection2().remove(rolapCollection2Rolap);
                    oldCByyOfRolapCollection2Rolap = em.merge(oldCByyOfRolapCollection2Rolap);
                }
            }
            for (PassConf passConfCollectionPassConf : prle.getPassConfCollection()) {
                Prle oldCByyOfPassConfCollectionPassConf = passConfCollectionPassConf.getCByy();
                passConfCollectionPassConf.setCByy(prle);
                passConfCollectionPassConf = em.merge(passConfCollectionPassConf);
                if (oldCByyOfPassConfCollectionPassConf != null) {
                    oldCByyOfPassConfCollectionPassConf.getPassConfCollection().remove(passConfCollectionPassConf);
                    oldCByyOfPassConfCollectionPassConf = em.merge(oldCByyOfPassConfCollectionPassConf);
                }
            }
            for (PassConf passConfCollection1PassConf : prle.getPassConfCollection1()) {
                Prle oldUByyOfPassConfCollection1PassConf = passConfCollection1PassConf.getUByy();
                passConfCollection1PassConf.setUByy(prle);
                passConfCollection1PassConf = em.merge(passConfCollection1PassConf);
                if (oldUByyOfPassConfCollection1PassConf != null) {
                    oldUByyOfPassConfCollection1PassConf.getPassConfCollection1().remove(passConfCollection1PassConf);
                    oldUByyOfPassConfCollection1PassConf = em.merge(oldUByyOfPassConfCollection1PassConf);
                }
            }
            for (BaLottoConfig baLottoConfigCollectionBaLottoConfig : prle.getBaLottoConfigCollection()) {
                Prle oldCByyOfBaLottoConfigCollectionBaLottoConfig = baLottoConfigCollectionBaLottoConfig.getCByy();
                baLottoConfigCollectionBaLottoConfig.setCByy(prle);
                baLottoConfigCollectionBaLottoConfig = em.merge(baLottoConfigCollectionBaLottoConfig);
                if (oldCByyOfBaLottoConfigCollectionBaLottoConfig != null) {
                    oldCByyOfBaLottoConfigCollectionBaLottoConfig.getBaLottoConfigCollection().remove(baLottoConfigCollectionBaLottoConfig);
                    oldCByyOfBaLottoConfigCollectionBaLottoConfig = em.merge(oldCByyOfBaLottoConfigCollectionBaLottoConfig);
                }
            }
            for (MGrps MGrpsCollectionMGrps : prle.getMGrpsCollection()) {
                Prle oldUByyOfMGrpsCollectionMGrps = MGrpsCollectionMGrps.getUByy();
                MGrpsCollectionMGrps.setUByy(prle);
                MGrpsCollectionMGrps = em.merge(MGrpsCollectionMGrps);
                if (oldUByyOfMGrpsCollectionMGrps != null) {
                    oldUByyOfMGrpsCollectionMGrps.getMGrpsCollection().remove(MGrpsCollectionMGrps);
                    oldUByyOfMGrpsCollectionMGrps = em.merge(oldUByyOfMGrpsCollectionMGrps);
                }
            }
            for (MGrps MGrpsCollection1MGrps : prle.getMGrpsCollection1()) {
                Prle oldAByyOfMGrpsCollection1MGrps = MGrpsCollection1MGrps.getAByy();
                MGrpsCollection1MGrps.setAByy(prle);
                MGrpsCollection1MGrps = em.merge(MGrpsCollection1MGrps);
                if (oldAByyOfMGrpsCollection1MGrps != null) {
                    oldAByyOfMGrpsCollection1MGrps.getMGrpsCollection1().remove(MGrpsCollection1MGrps);
                    oldAByyOfMGrpsCollection1MGrps = em.merge(oldAByyOfMGrpsCollection1MGrps);
                }
            }
            for (MGrps MGrpsCollection2MGrps : prle.getMGrpsCollection2()) {
                Prle oldCByyOfMGrpsCollection2MGrps = MGrpsCollection2MGrps.getCByy();
                MGrpsCollection2MGrps.setCByy(prle);
                MGrpsCollection2MGrps = em.merge(MGrpsCollection2MGrps);
                if (oldCByyOfMGrpsCollection2MGrps != null) {
                    oldCByyOfMGrpsCollection2MGrps.getMGrpsCollection2().remove(MGrpsCollection2MGrps);
                    oldCByyOfMGrpsCollection2MGrps = em.merge(oldCByyOfMGrpsCollection2MGrps);
                }
            }
            for (BaLottoMember baLottoMemberCollectionBaLottoMember : prle.getBaLottoMemberCollection()) {
                Prle oldCByyOfBaLottoMemberCollectionBaLottoMember = baLottoMemberCollectionBaLottoMember.getCByy();
                baLottoMemberCollectionBaLottoMember.setCByy(prle);
                baLottoMemberCollectionBaLottoMember = em.merge(baLottoMemberCollectionBaLottoMember);
                if (oldCByyOfBaLottoMemberCollectionBaLottoMember != null) {
                    oldCByyOfBaLottoMemberCollectionBaLottoMember.getBaLottoMemberCollection().remove(baLottoMemberCollectionBaLottoMember);
                    oldCByyOfBaLottoMemberCollectionBaLottoMember = em.merge(oldCByyOfBaLottoMemberCollectionBaLottoMember);
                }
            }
            for (MItms MItmsCollectionMItms : prle.getMItmsCollection()) {
                Prle oldAByyOfMItmsCollectionMItms = MItmsCollectionMItms.getAByy();
                MItmsCollectionMItms.setAByy(prle);
                MItmsCollectionMItms = em.merge(MItmsCollectionMItms);
                if (oldAByyOfMItmsCollectionMItms != null) {
                    oldAByyOfMItmsCollectionMItms.getMItmsCollection().remove(MItmsCollectionMItms);
                    oldAByyOfMItmsCollectionMItms = em.merge(oldAByyOfMItmsCollectionMItms);
                }
            }
            for (MItms MItmsCollection1MItms : prle.getMItmsCollection1()) {
                Prle oldUByyOfMItmsCollection1MItms = MItmsCollection1MItms.getUByy();
                MItmsCollection1MItms.setUByy(prle);
                MItmsCollection1MItms = em.merge(MItmsCollection1MItms);
                if (oldUByyOfMItmsCollection1MItms != null) {
                    oldUByyOfMItmsCollection1MItms.getMItmsCollection1().remove(MItmsCollection1MItms);
                    oldUByyOfMItmsCollection1MItms = em.merge(oldUByyOfMItmsCollection1MItms);
                }
            }
            for (Sts stsCollectionSts : prle.getStsCollection()) {
                Prle oldCByyOfStsCollectionSts = stsCollectionSts.getCByy();
                stsCollectionSts.setCByy(prle);
                stsCollectionSts = em.merge(stsCollectionSts);
                if (oldCByyOfStsCollectionSts != null) {
                    oldCByyOfStsCollectionSts.getStsCollection().remove(stsCollectionSts);
                    oldCByyOfStsCollectionSts = em.merge(oldCByyOfStsCollectionSts);
                }
            }
            for (Sts stsCollection1Sts : prle.getStsCollection1()) {
                Prle oldUByyOfStsCollection1Sts = stsCollection1Sts.getUByy();
                stsCollection1Sts.setUByy(prle);
                stsCollection1Sts = em.merge(stsCollection1Sts);
                if (oldUByyOfStsCollection1Sts != null) {
                    oldUByyOfStsCollection1Sts.getStsCollection1().remove(stsCollection1Sts);
                    oldUByyOfStsCollection1Sts = em.merge(oldUByyOfStsCollection1Sts);
                }
            }
            for (BaLottoChannel baLottoChannelCollectionBaLottoChannel : prle.getBaLottoChannelCollection()) {
                Prle oldCByyOfBaLottoChannelCollectionBaLottoChannel = baLottoChannelCollectionBaLottoChannel.getCByy();
                baLottoChannelCollectionBaLottoChannel.setCByy(prle);
                baLottoChannelCollectionBaLottoChannel = em.merge(baLottoChannelCollectionBaLottoChannel);
                if (oldCByyOfBaLottoChannelCollectionBaLottoChannel != null) {
                    oldCByyOfBaLottoChannelCollectionBaLottoChannel.getBaLottoChannelCollection().remove(baLottoChannelCollectionBaLottoChannel);
                    oldCByyOfBaLottoChannelCollectionBaLottoChannel = em.merge(oldCByyOfBaLottoChannelCollectionBaLottoChannel);
                }
            }
            for (BaLottoResponse baLottoResponseCollectionBaLottoResponse : prle.getBaLottoResponseCollection()) {
                Prle oldCByyOfBaLottoResponseCollectionBaLottoResponse = baLottoResponseCollectionBaLottoResponse.getCByy();
                baLottoResponseCollectionBaLottoResponse.setCByy(prle);
                baLottoResponseCollectionBaLottoResponse = em.merge(baLottoResponseCollectionBaLottoResponse);
                if (oldCByyOfBaLottoResponseCollectionBaLottoResponse != null) {
                    oldCByyOfBaLottoResponseCollectionBaLottoResponse.getBaLottoResponseCollection().remove(baLottoResponseCollectionBaLottoResponse);
                    oldCByyOfBaLottoResponseCollectionBaLottoResponse = em.merge(oldCByyOfBaLottoResponseCollectionBaLottoResponse);
                }
            }
            for (BaLottoAgent baLottoAgentCollectionBaLottoAgent : prle.getBaLottoAgentCollection()) {
                Prle oldCByyOfBaLottoAgentCollectionBaLottoAgent = baLottoAgentCollectionBaLottoAgent.getCByy();
                baLottoAgentCollectionBaLottoAgent.setCByy(prle);
                baLottoAgentCollectionBaLottoAgent = em.merge(baLottoAgentCollectionBaLottoAgent);
                if (oldCByyOfBaLottoAgentCollectionBaLottoAgent != null) {
                    oldCByyOfBaLottoAgentCollectionBaLottoAgent.getBaLottoAgentCollection().remove(baLottoAgentCollectionBaLottoAgent);
                    oldCByyOfBaLottoAgentCollectionBaLottoAgent = em.merge(oldCByyOfBaLottoAgentCollectionBaLottoAgent);
                }
            }
            for (UserMgrps userMgrpsCollectionUserMgrps : prle.getUserMgrpsCollection()) {
                Prle oldCByyOfUserMgrpsCollectionUserMgrps = userMgrpsCollectionUserMgrps.getCByy();
                userMgrpsCollectionUserMgrps.setCByy(prle);
                userMgrpsCollectionUserMgrps = em.merge(userMgrpsCollectionUserMgrps);
                if (oldCByyOfUserMgrpsCollectionUserMgrps != null) {
                    oldCByyOfUserMgrpsCollectionUserMgrps.getUserMgrpsCollection().remove(userMgrpsCollectionUserMgrps);
                    oldCByyOfUserMgrpsCollectionUserMgrps = em.merge(oldCByyOfUserMgrpsCollectionUserMgrps);
                }
            }
            for (UserMgrps userMgrpsCollection1UserMgrps : prle.getUserMgrpsCollection1()) {
                Prle oldUByyOfUserMgrpsCollection1UserMgrps = userMgrpsCollection1UserMgrps.getUByy();
                userMgrpsCollection1UserMgrps.setUByy(prle);
                userMgrpsCollection1UserMgrps = em.merge(userMgrpsCollection1UserMgrps);
                if (oldUByyOfUserMgrpsCollection1UserMgrps != null) {
                    oldUByyOfUserMgrpsCollection1UserMgrps.getUserMgrpsCollection1().remove(userMgrpsCollection1UserMgrps);
                    oldUByyOfUserMgrpsCollection1UserMgrps = em.merge(oldUByyOfUserMgrpsCollection1UserMgrps);
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

    public void edit(Prle prle) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Prle persistentPrle = em.find(Prle.class, prle.getTid());
            Rolap RIddOld = persistentPrle.getRIdd();
            Rolap RIddNew = prle.getRIdd();
            Sts IStsOld = persistentPrle.getISts();
            Sts IStsNew = prle.getISts();
            Collection<Rolap> rolapCollectionOld = persistentPrle.getRolapCollection();
            Collection<Rolap> rolapCollectionNew = prle.getRolapCollection();
            Collection<Rolap> rolapCollection1Old = persistentPrle.getRolapCollection1();
            Collection<Rolap> rolapCollection1New = prle.getRolapCollection1();
            Collection<Rolap> rolapCollection2Old = persistentPrle.getRolapCollection2();
            Collection<Rolap> rolapCollection2New = prle.getRolapCollection2();
            Collection<PassConf> passConfCollectionOld = persistentPrle.getPassConfCollection();
            Collection<PassConf> passConfCollectionNew = prle.getPassConfCollection();
            Collection<PassConf> passConfCollection1Old = persistentPrle.getPassConfCollection1();
            Collection<PassConf> passConfCollection1New = prle.getPassConfCollection1();
            Collection<BaLottoConfig> baLottoConfigCollectionOld = persistentPrle.getBaLottoConfigCollection();
            Collection<BaLottoConfig> baLottoConfigCollectionNew = prle.getBaLottoConfigCollection();
            Collection<MGrps> MGrpsCollectionOld = persistentPrle.getMGrpsCollection();
            Collection<MGrps> MGrpsCollectionNew = prle.getMGrpsCollection();
            Collection<MGrps> MGrpsCollection1Old = persistentPrle.getMGrpsCollection1();
            Collection<MGrps> MGrpsCollection1New = prle.getMGrpsCollection1();
            Collection<MGrps> MGrpsCollection2Old = persistentPrle.getMGrpsCollection2();
            Collection<MGrps> MGrpsCollection2New = prle.getMGrpsCollection2();
            Collection<BaLottoMember> baLottoMemberCollectionOld = persistentPrle.getBaLottoMemberCollection();
            Collection<BaLottoMember> baLottoMemberCollectionNew = prle.getBaLottoMemberCollection();
            Collection<MItms> MItmsCollectionOld = persistentPrle.getMItmsCollection();
            Collection<MItms> MItmsCollectionNew = prle.getMItmsCollection();
            Collection<MItms> MItmsCollection1Old = persistentPrle.getMItmsCollection1();
            Collection<MItms> MItmsCollection1New = prle.getMItmsCollection1();
            Collection<Sts> stsCollectionOld = persistentPrle.getStsCollection();
            Collection<Sts> stsCollectionNew = prle.getStsCollection();
            Collection<Sts> stsCollection1Old = persistentPrle.getStsCollection1();
            Collection<Sts> stsCollection1New = prle.getStsCollection1();
            Collection<BaLottoChannel> baLottoChannelCollectionOld = persistentPrle.getBaLottoChannelCollection();
            Collection<BaLottoChannel> baLottoChannelCollectionNew = prle.getBaLottoChannelCollection();
            Collection<BaLottoResponse> baLottoResponseCollectionOld = persistentPrle.getBaLottoResponseCollection();
            Collection<BaLottoResponse> baLottoResponseCollectionNew = prle.getBaLottoResponseCollection();
            Collection<BaLottoAgent> baLottoAgentCollectionOld = persistentPrle.getBaLottoAgentCollection();
            Collection<BaLottoAgent> baLottoAgentCollectionNew = prle.getBaLottoAgentCollection();
            Collection<UserMgrps> userMgrpsCollectionOld = persistentPrle.getUserMgrpsCollection();
            Collection<UserMgrps> userMgrpsCollectionNew = prle.getUserMgrpsCollection();
            Collection<UserMgrps> userMgrpsCollection1Old = persistentPrle.getUserMgrpsCollection1();
            Collection<UserMgrps> userMgrpsCollection1New = prle.getUserMgrpsCollection1();
            List<String> illegalOrphanMessages = null;
            for (PassConf passConfCollectionOldPassConf : passConfCollectionOld) {
                if (!passConfCollectionNew.contains(passConfCollectionOldPassConf)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PassConf " + passConfCollectionOldPassConf + " since its CByy field is not nullable.");
                }
            }
            for (MGrps MGrpsCollection2OldMGrps : MGrpsCollection2Old) {
                if (!MGrpsCollection2New.contains(MGrpsCollection2OldMGrps)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MGrps " + MGrpsCollection2OldMGrps + " since its CByy field is not nullable.");
                }
            }
            for (Sts stsCollectionOldSts : stsCollectionOld) {
                if (!stsCollectionNew.contains(stsCollectionOldSts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Sts " + stsCollectionOldSts + " since its CByy field is not nullable.");
                }
            }
            for (UserMgrps userMgrpsCollectionOldUserMgrps : userMgrpsCollectionOld) {
                if (!userMgrpsCollectionNew.contains(userMgrpsCollectionOldUserMgrps)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserMgrps " + userMgrpsCollectionOldUserMgrps + " since its CByy field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (RIddNew != null) {
                RIddNew = em.getReference(RIddNew.getClass(), RIddNew.getTid());
                prle.setRIdd(RIddNew);
            }
            if (IStsNew != null) {
                IStsNew = em.getReference(IStsNew.getClass(), IStsNew.getTid());
                prle.setISts(IStsNew);
            }
            Collection<Rolap> attachedRolapCollectionNew = new ArrayList<Rolap>();
            for (Rolap rolapCollectionNewRolapToAttach : rolapCollectionNew) {
                rolapCollectionNewRolapToAttach = em.getReference(rolapCollectionNewRolapToAttach.getClass(), rolapCollectionNewRolapToAttach.getTid());
                attachedRolapCollectionNew.add(rolapCollectionNewRolapToAttach);
            }
            rolapCollectionNew = attachedRolapCollectionNew;
            prle.setRolapCollection(rolapCollectionNew);
            Collection<Rolap> attachedRolapCollection1New = new ArrayList<Rolap>();
            for (Rolap rolapCollection1NewRolapToAttach : rolapCollection1New) {
                rolapCollection1NewRolapToAttach = em.getReference(rolapCollection1NewRolapToAttach.getClass(), rolapCollection1NewRolapToAttach.getTid());
                attachedRolapCollection1New.add(rolapCollection1NewRolapToAttach);
            }
            rolapCollection1New = attachedRolapCollection1New;
            prle.setRolapCollection1(rolapCollection1New);
            Collection<Rolap> attachedRolapCollection2New = new ArrayList<Rolap>();
            for (Rolap rolapCollection2NewRolapToAttach : rolapCollection2New) {
                rolapCollection2NewRolapToAttach = em.getReference(rolapCollection2NewRolapToAttach.getClass(), rolapCollection2NewRolapToAttach.getTid());
                attachedRolapCollection2New.add(rolapCollection2NewRolapToAttach);
            }
            rolapCollection2New = attachedRolapCollection2New;
            prle.setRolapCollection2(rolapCollection2New);
            Collection<PassConf> attachedPassConfCollectionNew = new ArrayList<PassConf>();
            for (PassConf passConfCollectionNewPassConfToAttach : passConfCollectionNew) {
                passConfCollectionNewPassConfToAttach = em.getReference(passConfCollectionNewPassConfToAttach.getClass(), passConfCollectionNewPassConfToAttach.getTid());
                attachedPassConfCollectionNew.add(passConfCollectionNewPassConfToAttach);
            }
            passConfCollectionNew = attachedPassConfCollectionNew;
            prle.setPassConfCollection(passConfCollectionNew);
            Collection<PassConf> attachedPassConfCollection1New = new ArrayList<PassConf>();
            for (PassConf passConfCollection1NewPassConfToAttach : passConfCollection1New) {
                passConfCollection1NewPassConfToAttach = em.getReference(passConfCollection1NewPassConfToAttach.getClass(), passConfCollection1NewPassConfToAttach.getTid());
                attachedPassConfCollection1New.add(passConfCollection1NewPassConfToAttach);
            }
            passConfCollection1New = attachedPassConfCollection1New;
            prle.setPassConfCollection1(passConfCollection1New);
            Collection<BaLottoConfig> attachedBaLottoConfigCollectionNew = new ArrayList<BaLottoConfig>();
            for (BaLottoConfig baLottoConfigCollectionNewBaLottoConfigToAttach : baLottoConfigCollectionNew) {
                baLottoConfigCollectionNewBaLottoConfigToAttach = em.getReference(baLottoConfigCollectionNewBaLottoConfigToAttach.getClass(), baLottoConfigCollectionNewBaLottoConfigToAttach.getId());
                attachedBaLottoConfigCollectionNew.add(baLottoConfigCollectionNewBaLottoConfigToAttach);
            }
            baLottoConfigCollectionNew = attachedBaLottoConfigCollectionNew;
            prle.setBaLottoConfigCollection(baLottoConfigCollectionNew);
            Collection<MGrps> attachedMGrpsCollectionNew = new ArrayList<MGrps>();
            for (MGrps MGrpsCollectionNewMGrpsToAttach : MGrpsCollectionNew) {
                MGrpsCollectionNewMGrpsToAttach = em.getReference(MGrpsCollectionNewMGrpsToAttach.getClass(), MGrpsCollectionNewMGrpsToAttach.getTid());
                attachedMGrpsCollectionNew.add(MGrpsCollectionNewMGrpsToAttach);
            }
            MGrpsCollectionNew = attachedMGrpsCollectionNew;
            prle.setMGrpsCollection(MGrpsCollectionNew);
            Collection<MGrps> attachedMGrpsCollection1New = new ArrayList<MGrps>();
            for (MGrps MGrpsCollection1NewMGrpsToAttach : MGrpsCollection1New) {
                MGrpsCollection1NewMGrpsToAttach = em.getReference(MGrpsCollection1NewMGrpsToAttach.getClass(), MGrpsCollection1NewMGrpsToAttach.getTid());
                attachedMGrpsCollection1New.add(MGrpsCollection1NewMGrpsToAttach);
            }
            MGrpsCollection1New = attachedMGrpsCollection1New;
            prle.setMGrpsCollection1(MGrpsCollection1New);
            Collection<MGrps> attachedMGrpsCollection2New = new ArrayList<MGrps>();
            for (MGrps MGrpsCollection2NewMGrpsToAttach : MGrpsCollection2New) {
                MGrpsCollection2NewMGrpsToAttach = em.getReference(MGrpsCollection2NewMGrpsToAttach.getClass(), MGrpsCollection2NewMGrpsToAttach.getTid());
                attachedMGrpsCollection2New.add(MGrpsCollection2NewMGrpsToAttach);
            }
            MGrpsCollection2New = attachedMGrpsCollection2New;
            prle.setMGrpsCollection2(MGrpsCollection2New);
            Collection<BaLottoMember> attachedBaLottoMemberCollectionNew = new ArrayList<BaLottoMember>();
            for (BaLottoMember baLottoMemberCollectionNewBaLottoMemberToAttach : baLottoMemberCollectionNew) {
                baLottoMemberCollectionNewBaLottoMemberToAttach = em.getReference(baLottoMemberCollectionNewBaLottoMemberToAttach.getClass(), baLottoMemberCollectionNewBaLottoMemberToAttach.getId());
                attachedBaLottoMemberCollectionNew.add(baLottoMemberCollectionNewBaLottoMemberToAttach);
            }
            baLottoMemberCollectionNew = attachedBaLottoMemberCollectionNew;
            prle.setBaLottoMemberCollection(baLottoMemberCollectionNew);
            Collection<MItms> attachedMItmsCollectionNew = new ArrayList<MItms>();
            for (MItms MItmsCollectionNewMItmsToAttach : MItmsCollectionNew) {
                MItmsCollectionNewMItmsToAttach = em.getReference(MItmsCollectionNewMItmsToAttach.getClass(), MItmsCollectionNewMItmsToAttach.getTid());
                attachedMItmsCollectionNew.add(MItmsCollectionNewMItmsToAttach);
            }
            MItmsCollectionNew = attachedMItmsCollectionNew;
            prle.setMItmsCollection(MItmsCollectionNew);
            Collection<MItms> attachedMItmsCollection1New = new ArrayList<MItms>();
            for (MItms MItmsCollection1NewMItmsToAttach : MItmsCollection1New) {
                MItmsCollection1NewMItmsToAttach = em.getReference(MItmsCollection1NewMItmsToAttach.getClass(), MItmsCollection1NewMItmsToAttach.getTid());
                attachedMItmsCollection1New.add(MItmsCollection1NewMItmsToAttach);
            }
            MItmsCollection1New = attachedMItmsCollection1New;
            prle.setMItmsCollection1(MItmsCollection1New);
            Collection<Sts> attachedStsCollectionNew = new ArrayList<Sts>();
            for (Sts stsCollectionNewStsToAttach : stsCollectionNew) {
                stsCollectionNewStsToAttach = em.getReference(stsCollectionNewStsToAttach.getClass(), stsCollectionNewStsToAttach.getTid());
                attachedStsCollectionNew.add(stsCollectionNewStsToAttach);
            }
            stsCollectionNew = attachedStsCollectionNew;
            prle.setStsCollection(stsCollectionNew);
            Collection<Sts> attachedStsCollection1New = new ArrayList<Sts>();
            for (Sts stsCollection1NewStsToAttach : stsCollection1New) {
                stsCollection1NewStsToAttach = em.getReference(stsCollection1NewStsToAttach.getClass(), stsCollection1NewStsToAttach.getTid());
                attachedStsCollection1New.add(stsCollection1NewStsToAttach);
            }
            stsCollection1New = attachedStsCollection1New;
            prle.setStsCollection1(stsCollection1New);
            Collection<BaLottoChannel> attachedBaLottoChannelCollectionNew = new ArrayList<BaLottoChannel>();
            for (BaLottoChannel baLottoChannelCollectionNewBaLottoChannelToAttach : baLottoChannelCollectionNew) {
                baLottoChannelCollectionNewBaLottoChannelToAttach = em.getReference(baLottoChannelCollectionNewBaLottoChannelToAttach.getClass(), baLottoChannelCollectionNewBaLottoChannelToAttach.getId());
                attachedBaLottoChannelCollectionNew.add(baLottoChannelCollectionNewBaLottoChannelToAttach);
            }
            baLottoChannelCollectionNew = attachedBaLottoChannelCollectionNew;
            prle.setBaLottoChannelCollection(baLottoChannelCollectionNew);
            Collection<BaLottoResponse> attachedBaLottoResponseCollectionNew = new ArrayList<BaLottoResponse>();
            for (BaLottoResponse baLottoResponseCollectionNewBaLottoResponseToAttach : baLottoResponseCollectionNew) {
                baLottoResponseCollectionNewBaLottoResponseToAttach = em.getReference(baLottoResponseCollectionNewBaLottoResponseToAttach.getClass(), baLottoResponseCollectionNewBaLottoResponseToAttach.getId());
                attachedBaLottoResponseCollectionNew.add(baLottoResponseCollectionNewBaLottoResponseToAttach);
            }
            baLottoResponseCollectionNew = attachedBaLottoResponseCollectionNew;
            prle.setBaLottoResponseCollection(baLottoResponseCollectionNew);
            Collection<BaLottoAgent> attachedBaLottoAgentCollectionNew = new ArrayList<BaLottoAgent>();
            for (BaLottoAgent baLottoAgentCollectionNewBaLottoAgentToAttach : baLottoAgentCollectionNew) {
                baLottoAgentCollectionNewBaLottoAgentToAttach = em.getReference(baLottoAgentCollectionNewBaLottoAgentToAttach.getClass(), baLottoAgentCollectionNewBaLottoAgentToAttach.getId());
                attachedBaLottoAgentCollectionNew.add(baLottoAgentCollectionNewBaLottoAgentToAttach);
            }
            baLottoAgentCollectionNew = attachedBaLottoAgentCollectionNew;
            prle.setBaLottoAgentCollection(baLottoAgentCollectionNew);
            Collection<UserMgrps> attachedUserMgrpsCollectionNew = new ArrayList<UserMgrps>();
            for (UserMgrps userMgrpsCollectionNewUserMgrpsToAttach : userMgrpsCollectionNew) {
                userMgrpsCollectionNewUserMgrpsToAttach = em.getReference(userMgrpsCollectionNewUserMgrpsToAttach.getClass(), userMgrpsCollectionNewUserMgrpsToAttach.getTid());
                attachedUserMgrpsCollectionNew.add(userMgrpsCollectionNewUserMgrpsToAttach);
            }
            userMgrpsCollectionNew = attachedUserMgrpsCollectionNew;
            prle.setUserMgrpsCollection(userMgrpsCollectionNew);
            Collection<UserMgrps> attachedUserMgrpsCollection1New = new ArrayList<UserMgrps>();
            for (UserMgrps userMgrpsCollection1NewUserMgrpsToAttach : userMgrpsCollection1New) {
                userMgrpsCollection1NewUserMgrpsToAttach = em.getReference(userMgrpsCollection1NewUserMgrpsToAttach.getClass(), userMgrpsCollection1NewUserMgrpsToAttach.getTid());
                attachedUserMgrpsCollection1New.add(userMgrpsCollection1NewUserMgrpsToAttach);
            }
            userMgrpsCollection1New = attachedUserMgrpsCollection1New;
            prle.setUserMgrpsCollection1(userMgrpsCollection1New);
            prle = em.merge(prle);
            if (RIddOld != null && !RIddOld.equals(RIddNew)) {
                RIddOld.setAByy(null);
                RIddOld = em.merge(RIddOld);
            }
            if (RIddNew != null && !RIddNew.equals(RIddOld)) {
                Prle oldAByyOfRIdd = RIddNew.getAByy();
                if (oldAByyOfRIdd != null) {
                    oldAByyOfRIdd.setRIdd(null);
                    oldAByyOfRIdd = em.merge(oldAByyOfRIdd);
                }
                RIddNew.setAByy(prle);
                RIddNew = em.merge(RIddNew);
            }
            if (IStsOld != null && !IStsOld.equals(IStsNew)) {
                IStsOld.getPrleCollection().remove(prle);
                IStsOld = em.merge(IStsOld);
            }
            if (IStsNew != null && !IStsNew.equals(IStsOld)) {
                IStsNew.getPrleCollection().add(prle);
                IStsNew = em.merge(IStsNew);
            }
            for (Rolap rolapCollectionOldRolap : rolapCollectionOld) {
                if (!rolapCollectionNew.contains(rolapCollectionOldRolap)) {
                    rolapCollectionOldRolap.setAByy(null);
                    rolapCollectionOldRolap = em.merge(rolapCollectionOldRolap);
                }
            }
            for (Rolap rolapCollectionNewRolap : rolapCollectionNew) {
                if (!rolapCollectionOld.contains(rolapCollectionNewRolap)) {
                    Prle oldAByyOfRolapCollectionNewRolap = rolapCollectionNewRolap.getAByy();
                    rolapCollectionNewRolap.setAByy(prle);
                    rolapCollectionNewRolap = em.merge(rolapCollectionNewRolap);
                    if (oldAByyOfRolapCollectionNewRolap != null && !oldAByyOfRolapCollectionNewRolap.equals(prle)) {
                        oldAByyOfRolapCollectionNewRolap.getRolapCollection().remove(rolapCollectionNewRolap);
                        oldAByyOfRolapCollectionNewRolap = em.merge(oldAByyOfRolapCollectionNewRolap);
                    }
                }
            }
            for (Rolap rolapCollection1OldRolap : rolapCollection1Old) {
                if (!rolapCollection1New.contains(rolapCollection1OldRolap)) {
                    rolapCollection1OldRolap.setUByy(null);
                    rolapCollection1OldRolap = em.merge(rolapCollection1OldRolap);
                }
            }
            for (Rolap rolapCollection1NewRolap : rolapCollection1New) {
                if (!rolapCollection1Old.contains(rolapCollection1NewRolap)) {
                    Prle oldUByyOfRolapCollection1NewRolap = rolapCollection1NewRolap.getUByy();
                    rolapCollection1NewRolap.setUByy(prle);
                    rolapCollection1NewRolap = em.merge(rolapCollection1NewRolap);
                    if (oldUByyOfRolapCollection1NewRolap != null && !oldUByyOfRolapCollection1NewRolap.equals(prle)) {
                        oldUByyOfRolapCollection1NewRolap.getRolapCollection1().remove(rolapCollection1NewRolap);
                        oldUByyOfRolapCollection1NewRolap = em.merge(oldUByyOfRolapCollection1NewRolap);
                    }
                }
            }
            for (Rolap rolapCollection2OldRolap : rolapCollection2Old) {
                if (!rolapCollection2New.contains(rolapCollection2OldRolap)) {
                    rolapCollection2OldRolap.setCByy(null);
                    rolapCollection2OldRolap = em.merge(rolapCollection2OldRolap);
                }
            }
            for (Rolap rolapCollection2NewRolap : rolapCollection2New) {
                if (!rolapCollection2Old.contains(rolapCollection2NewRolap)) {
                    Prle oldCByyOfRolapCollection2NewRolap = rolapCollection2NewRolap.getCByy();
                    rolapCollection2NewRolap.setCByy(prle);
                    rolapCollection2NewRolap = em.merge(rolapCollection2NewRolap);
                    if (oldCByyOfRolapCollection2NewRolap != null && !oldCByyOfRolapCollection2NewRolap.equals(prle)) {
                        oldCByyOfRolapCollection2NewRolap.getRolapCollection2().remove(rolapCollection2NewRolap);
                        oldCByyOfRolapCollection2NewRolap = em.merge(oldCByyOfRolapCollection2NewRolap);
                    }
                }
            }
            for (PassConf passConfCollectionNewPassConf : passConfCollectionNew) {
                if (!passConfCollectionOld.contains(passConfCollectionNewPassConf)) {
                    Prle oldCByyOfPassConfCollectionNewPassConf = passConfCollectionNewPassConf.getCByy();
                    passConfCollectionNewPassConf.setCByy(prle);
                    passConfCollectionNewPassConf = em.merge(passConfCollectionNewPassConf);
                    if (oldCByyOfPassConfCollectionNewPassConf != null && !oldCByyOfPassConfCollectionNewPassConf.equals(prle)) {
                        oldCByyOfPassConfCollectionNewPassConf.getPassConfCollection().remove(passConfCollectionNewPassConf);
                        oldCByyOfPassConfCollectionNewPassConf = em.merge(oldCByyOfPassConfCollectionNewPassConf);
                    }
                }
            }
            for (PassConf passConfCollection1OldPassConf : passConfCollection1Old) {
                if (!passConfCollection1New.contains(passConfCollection1OldPassConf)) {
                    passConfCollection1OldPassConf.setUByy(null);
                    passConfCollection1OldPassConf = em.merge(passConfCollection1OldPassConf);
                }
            }
            for (PassConf passConfCollection1NewPassConf : passConfCollection1New) {
                if (!passConfCollection1Old.contains(passConfCollection1NewPassConf)) {
                    Prle oldUByyOfPassConfCollection1NewPassConf = passConfCollection1NewPassConf.getUByy();
                    passConfCollection1NewPassConf.setUByy(prle);
                    passConfCollection1NewPassConf = em.merge(passConfCollection1NewPassConf);
                    if (oldUByyOfPassConfCollection1NewPassConf != null && !oldUByyOfPassConfCollection1NewPassConf.equals(prle)) {
                        oldUByyOfPassConfCollection1NewPassConf.getPassConfCollection1().remove(passConfCollection1NewPassConf);
                        oldUByyOfPassConfCollection1NewPassConf = em.merge(oldUByyOfPassConfCollection1NewPassConf);
                    }
                }
            }
            for (BaLottoConfig baLottoConfigCollectionOldBaLottoConfig : baLottoConfigCollectionOld) {
                if (!baLottoConfigCollectionNew.contains(baLottoConfigCollectionOldBaLottoConfig)) {
                    baLottoConfigCollectionOldBaLottoConfig.setCByy(null);
                    baLottoConfigCollectionOldBaLottoConfig = em.merge(baLottoConfigCollectionOldBaLottoConfig);
                }
            }
            for (BaLottoConfig baLottoConfigCollectionNewBaLottoConfig : baLottoConfigCollectionNew) {
                if (!baLottoConfigCollectionOld.contains(baLottoConfigCollectionNewBaLottoConfig)) {
                    Prle oldCByyOfBaLottoConfigCollectionNewBaLottoConfig = baLottoConfigCollectionNewBaLottoConfig.getCByy();
                    baLottoConfigCollectionNewBaLottoConfig.setCByy(prle);
                    baLottoConfigCollectionNewBaLottoConfig = em.merge(baLottoConfigCollectionNewBaLottoConfig);
                    if (oldCByyOfBaLottoConfigCollectionNewBaLottoConfig != null && !oldCByyOfBaLottoConfigCollectionNewBaLottoConfig.equals(prle)) {
                        oldCByyOfBaLottoConfigCollectionNewBaLottoConfig.getBaLottoConfigCollection().remove(baLottoConfigCollectionNewBaLottoConfig);
                        oldCByyOfBaLottoConfigCollectionNewBaLottoConfig = em.merge(oldCByyOfBaLottoConfigCollectionNewBaLottoConfig);
                    }
                }
            }
            for (MGrps MGrpsCollectionOldMGrps : MGrpsCollectionOld) {
                if (!MGrpsCollectionNew.contains(MGrpsCollectionOldMGrps)) {
                    MGrpsCollectionOldMGrps.setUByy(null);
                    MGrpsCollectionOldMGrps = em.merge(MGrpsCollectionOldMGrps);
                }
            }
            for (MGrps MGrpsCollectionNewMGrps : MGrpsCollectionNew) {
                if (!MGrpsCollectionOld.contains(MGrpsCollectionNewMGrps)) {
                    Prle oldUByyOfMGrpsCollectionNewMGrps = MGrpsCollectionNewMGrps.getUByy();
                    MGrpsCollectionNewMGrps.setUByy(prle);
                    MGrpsCollectionNewMGrps = em.merge(MGrpsCollectionNewMGrps);
                    if (oldUByyOfMGrpsCollectionNewMGrps != null && !oldUByyOfMGrpsCollectionNewMGrps.equals(prle)) {
                        oldUByyOfMGrpsCollectionNewMGrps.getMGrpsCollection().remove(MGrpsCollectionNewMGrps);
                        oldUByyOfMGrpsCollectionNewMGrps = em.merge(oldUByyOfMGrpsCollectionNewMGrps);
                    }
                }
            }
            for (MGrps MGrpsCollection1OldMGrps : MGrpsCollection1Old) {
                if (!MGrpsCollection1New.contains(MGrpsCollection1OldMGrps)) {
                    MGrpsCollection1OldMGrps.setAByy(null);
                    MGrpsCollection1OldMGrps = em.merge(MGrpsCollection1OldMGrps);
                }
            }
            for (MGrps MGrpsCollection1NewMGrps : MGrpsCollection1New) {
                if (!MGrpsCollection1Old.contains(MGrpsCollection1NewMGrps)) {
                    Prle oldAByyOfMGrpsCollection1NewMGrps = MGrpsCollection1NewMGrps.getAByy();
                    MGrpsCollection1NewMGrps.setAByy(prle);
                    MGrpsCollection1NewMGrps = em.merge(MGrpsCollection1NewMGrps);
                    if (oldAByyOfMGrpsCollection1NewMGrps != null && !oldAByyOfMGrpsCollection1NewMGrps.equals(prle)) {
                        oldAByyOfMGrpsCollection1NewMGrps.getMGrpsCollection1().remove(MGrpsCollection1NewMGrps);
                        oldAByyOfMGrpsCollection1NewMGrps = em.merge(oldAByyOfMGrpsCollection1NewMGrps);
                    }
                }
            }
            for (MGrps MGrpsCollection2NewMGrps : MGrpsCollection2New) {
                if (!MGrpsCollection2Old.contains(MGrpsCollection2NewMGrps)) {
                    Prle oldCByyOfMGrpsCollection2NewMGrps = MGrpsCollection2NewMGrps.getCByy();
                    MGrpsCollection2NewMGrps.setCByy(prle);
                    MGrpsCollection2NewMGrps = em.merge(MGrpsCollection2NewMGrps);
                    if (oldCByyOfMGrpsCollection2NewMGrps != null && !oldCByyOfMGrpsCollection2NewMGrps.equals(prle)) {
                        oldCByyOfMGrpsCollection2NewMGrps.getMGrpsCollection2().remove(MGrpsCollection2NewMGrps);
                        oldCByyOfMGrpsCollection2NewMGrps = em.merge(oldCByyOfMGrpsCollection2NewMGrps);
                    }
                }
            }
            for (BaLottoMember baLottoMemberCollectionOldBaLottoMember : baLottoMemberCollectionOld) {
                if (!baLottoMemberCollectionNew.contains(baLottoMemberCollectionOldBaLottoMember)) {
                    baLottoMemberCollectionOldBaLottoMember.setCByy(null);
                    baLottoMemberCollectionOldBaLottoMember = em.merge(baLottoMemberCollectionOldBaLottoMember);
                }
            }
            for (BaLottoMember baLottoMemberCollectionNewBaLottoMember : baLottoMemberCollectionNew) {
                if (!baLottoMemberCollectionOld.contains(baLottoMemberCollectionNewBaLottoMember)) {
                    Prle oldCByyOfBaLottoMemberCollectionNewBaLottoMember = baLottoMemberCollectionNewBaLottoMember.getCByy();
                    baLottoMemberCollectionNewBaLottoMember.setCByy(prle);
                    baLottoMemberCollectionNewBaLottoMember = em.merge(baLottoMemberCollectionNewBaLottoMember);
                    if (oldCByyOfBaLottoMemberCollectionNewBaLottoMember != null && !oldCByyOfBaLottoMemberCollectionNewBaLottoMember.equals(prle)) {
                        oldCByyOfBaLottoMemberCollectionNewBaLottoMember.getBaLottoMemberCollection().remove(baLottoMemberCollectionNewBaLottoMember);
                        oldCByyOfBaLottoMemberCollectionNewBaLottoMember = em.merge(oldCByyOfBaLottoMemberCollectionNewBaLottoMember);
                    }
                }
            }
            for (MItms MItmsCollectionOldMItms : MItmsCollectionOld) {
                if (!MItmsCollectionNew.contains(MItmsCollectionOldMItms)) {
                    MItmsCollectionOldMItms.setAByy(null);
                    MItmsCollectionOldMItms = em.merge(MItmsCollectionOldMItms);
                }
            }
            for (MItms MItmsCollectionNewMItms : MItmsCollectionNew) {
                if (!MItmsCollectionOld.contains(MItmsCollectionNewMItms)) {
                    Prle oldAByyOfMItmsCollectionNewMItms = MItmsCollectionNewMItms.getAByy();
                    MItmsCollectionNewMItms.setAByy(prle);
                    MItmsCollectionNewMItms = em.merge(MItmsCollectionNewMItms);
                    if (oldAByyOfMItmsCollectionNewMItms != null && !oldAByyOfMItmsCollectionNewMItms.equals(prle)) {
                        oldAByyOfMItmsCollectionNewMItms.getMItmsCollection().remove(MItmsCollectionNewMItms);
                        oldAByyOfMItmsCollectionNewMItms = em.merge(oldAByyOfMItmsCollectionNewMItms);
                    }
                }
            }
            for (MItms MItmsCollection1OldMItms : MItmsCollection1Old) {
                if (!MItmsCollection1New.contains(MItmsCollection1OldMItms)) {
                    MItmsCollection1OldMItms.setUByy(null);
                    MItmsCollection1OldMItms = em.merge(MItmsCollection1OldMItms);
                }
            }
            for (MItms MItmsCollection1NewMItms : MItmsCollection1New) {
                if (!MItmsCollection1Old.contains(MItmsCollection1NewMItms)) {
                    Prle oldUByyOfMItmsCollection1NewMItms = MItmsCollection1NewMItms.getUByy();
                    MItmsCollection1NewMItms.setUByy(prle);
                    MItmsCollection1NewMItms = em.merge(MItmsCollection1NewMItms);
                    if (oldUByyOfMItmsCollection1NewMItms != null && !oldUByyOfMItmsCollection1NewMItms.equals(prle)) {
                        oldUByyOfMItmsCollection1NewMItms.getMItmsCollection1().remove(MItmsCollection1NewMItms);
                        oldUByyOfMItmsCollection1NewMItms = em.merge(oldUByyOfMItmsCollection1NewMItms);
                    }
                }
            }
            for (Sts stsCollectionNewSts : stsCollectionNew) {
                if (!stsCollectionOld.contains(stsCollectionNewSts)) {
                    Prle oldCByyOfStsCollectionNewSts = stsCollectionNewSts.getCByy();
                    stsCollectionNewSts.setCByy(prle);
                    stsCollectionNewSts = em.merge(stsCollectionNewSts);
                    if (oldCByyOfStsCollectionNewSts != null && !oldCByyOfStsCollectionNewSts.equals(prle)) {
                        oldCByyOfStsCollectionNewSts.getStsCollection().remove(stsCollectionNewSts);
                        oldCByyOfStsCollectionNewSts = em.merge(oldCByyOfStsCollectionNewSts);
                    }
                }
            }
            for (Sts stsCollection1OldSts : stsCollection1Old) {
                if (!stsCollection1New.contains(stsCollection1OldSts)) {
                    stsCollection1OldSts.setUByy(null);
                    stsCollection1OldSts = em.merge(stsCollection1OldSts);
                }
            }
            for (Sts stsCollection1NewSts : stsCollection1New) {
                if (!stsCollection1Old.contains(stsCollection1NewSts)) {
                    Prle oldUByyOfStsCollection1NewSts = stsCollection1NewSts.getUByy();
                    stsCollection1NewSts.setUByy(prle);
                    stsCollection1NewSts = em.merge(stsCollection1NewSts);
                    if (oldUByyOfStsCollection1NewSts != null && !oldUByyOfStsCollection1NewSts.equals(prle)) {
                        oldUByyOfStsCollection1NewSts.getStsCollection1().remove(stsCollection1NewSts);
                        oldUByyOfStsCollection1NewSts = em.merge(oldUByyOfStsCollection1NewSts);
                    }
                }
            }
            for (BaLottoChannel baLottoChannelCollectionOldBaLottoChannel : baLottoChannelCollectionOld) {
                if (!baLottoChannelCollectionNew.contains(baLottoChannelCollectionOldBaLottoChannel)) {
                    baLottoChannelCollectionOldBaLottoChannel.setCByy(null);
                    baLottoChannelCollectionOldBaLottoChannel = em.merge(baLottoChannelCollectionOldBaLottoChannel);
                }
            }
            for (BaLottoChannel baLottoChannelCollectionNewBaLottoChannel : baLottoChannelCollectionNew) {
                if (!baLottoChannelCollectionOld.contains(baLottoChannelCollectionNewBaLottoChannel)) {
                    Prle oldCByyOfBaLottoChannelCollectionNewBaLottoChannel = baLottoChannelCollectionNewBaLottoChannel.getCByy();
                    baLottoChannelCollectionNewBaLottoChannel.setCByy(prle);
                    baLottoChannelCollectionNewBaLottoChannel = em.merge(baLottoChannelCollectionNewBaLottoChannel);
                    if (oldCByyOfBaLottoChannelCollectionNewBaLottoChannel != null && !oldCByyOfBaLottoChannelCollectionNewBaLottoChannel.equals(prle)) {
                        oldCByyOfBaLottoChannelCollectionNewBaLottoChannel.getBaLottoChannelCollection().remove(baLottoChannelCollectionNewBaLottoChannel);
                        oldCByyOfBaLottoChannelCollectionNewBaLottoChannel = em.merge(oldCByyOfBaLottoChannelCollectionNewBaLottoChannel);
                    }
                }
            }
            for (BaLottoResponse baLottoResponseCollectionOldBaLottoResponse : baLottoResponseCollectionOld) {
                if (!baLottoResponseCollectionNew.contains(baLottoResponseCollectionOldBaLottoResponse)) {
                    baLottoResponseCollectionOldBaLottoResponse.setCByy(null);
                    baLottoResponseCollectionOldBaLottoResponse = em.merge(baLottoResponseCollectionOldBaLottoResponse);
                }
            }
            for (BaLottoResponse baLottoResponseCollectionNewBaLottoResponse : baLottoResponseCollectionNew) {
                if (!baLottoResponseCollectionOld.contains(baLottoResponseCollectionNewBaLottoResponse)) {
                    Prle oldCByyOfBaLottoResponseCollectionNewBaLottoResponse = baLottoResponseCollectionNewBaLottoResponse.getCByy();
                    baLottoResponseCollectionNewBaLottoResponse.setCByy(prle);
                    baLottoResponseCollectionNewBaLottoResponse = em.merge(baLottoResponseCollectionNewBaLottoResponse);
                    if (oldCByyOfBaLottoResponseCollectionNewBaLottoResponse != null && !oldCByyOfBaLottoResponseCollectionNewBaLottoResponse.equals(prle)) {
                        oldCByyOfBaLottoResponseCollectionNewBaLottoResponse.getBaLottoResponseCollection().remove(baLottoResponseCollectionNewBaLottoResponse);
                        oldCByyOfBaLottoResponseCollectionNewBaLottoResponse = em.merge(oldCByyOfBaLottoResponseCollectionNewBaLottoResponse);
                    }
                }
            }
            for (BaLottoAgent baLottoAgentCollectionOldBaLottoAgent : baLottoAgentCollectionOld) {
                if (!baLottoAgentCollectionNew.contains(baLottoAgentCollectionOldBaLottoAgent)) {
                    baLottoAgentCollectionOldBaLottoAgent.setCByy(null);
                    baLottoAgentCollectionOldBaLottoAgent = em.merge(baLottoAgentCollectionOldBaLottoAgent);
                }
            }
            for (BaLottoAgent baLottoAgentCollectionNewBaLottoAgent : baLottoAgentCollectionNew) {
                if (!baLottoAgentCollectionOld.contains(baLottoAgentCollectionNewBaLottoAgent)) {
                    Prle oldCByyOfBaLottoAgentCollectionNewBaLottoAgent = baLottoAgentCollectionNewBaLottoAgent.getCByy();
                    baLottoAgentCollectionNewBaLottoAgent.setCByy(prle);
                    baLottoAgentCollectionNewBaLottoAgent = em.merge(baLottoAgentCollectionNewBaLottoAgent);
                    if (oldCByyOfBaLottoAgentCollectionNewBaLottoAgent != null && !oldCByyOfBaLottoAgentCollectionNewBaLottoAgent.equals(prle)) {
                        oldCByyOfBaLottoAgentCollectionNewBaLottoAgent.getBaLottoAgentCollection().remove(baLottoAgentCollectionNewBaLottoAgent);
                        oldCByyOfBaLottoAgentCollectionNewBaLottoAgent = em.merge(oldCByyOfBaLottoAgentCollectionNewBaLottoAgent);
                    }
                }
            }
            for (UserMgrps userMgrpsCollectionNewUserMgrps : userMgrpsCollectionNew) {
                if (!userMgrpsCollectionOld.contains(userMgrpsCollectionNewUserMgrps)) {
                    Prle oldCByyOfUserMgrpsCollectionNewUserMgrps = userMgrpsCollectionNewUserMgrps.getCByy();
                    userMgrpsCollectionNewUserMgrps.setCByy(prle);
                    userMgrpsCollectionNewUserMgrps = em.merge(userMgrpsCollectionNewUserMgrps);
                    if (oldCByyOfUserMgrpsCollectionNewUserMgrps != null && !oldCByyOfUserMgrpsCollectionNewUserMgrps.equals(prle)) {
                        oldCByyOfUserMgrpsCollectionNewUserMgrps.getUserMgrpsCollection().remove(userMgrpsCollectionNewUserMgrps);
                        oldCByyOfUserMgrpsCollectionNewUserMgrps = em.merge(oldCByyOfUserMgrpsCollectionNewUserMgrps);
                    }
                }
            }
            for (UserMgrps userMgrpsCollection1OldUserMgrps : userMgrpsCollection1Old) {
                if (!userMgrpsCollection1New.contains(userMgrpsCollection1OldUserMgrps)) {
                    userMgrpsCollection1OldUserMgrps.setUByy(null);
                    userMgrpsCollection1OldUserMgrps = em.merge(userMgrpsCollection1OldUserMgrps);
                }
            }
            for (UserMgrps userMgrpsCollection1NewUserMgrps : userMgrpsCollection1New) {
                if (!userMgrpsCollection1Old.contains(userMgrpsCollection1NewUserMgrps)) {
                    Prle oldUByyOfUserMgrpsCollection1NewUserMgrps = userMgrpsCollection1NewUserMgrps.getUByy();
                    userMgrpsCollection1NewUserMgrps.setUByy(prle);
                    userMgrpsCollection1NewUserMgrps = em.merge(userMgrpsCollection1NewUserMgrps);
                    if (oldUByyOfUserMgrpsCollection1NewUserMgrps != null && !oldUByyOfUserMgrpsCollection1NewUserMgrps.equals(prle)) {
                        oldUByyOfUserMgrpsCollection1NewUserMgrps.getUserMgrpsCollection1().remove(userMgrpsCollection1NewUserMgrps);
                        oldUByyOfUserMgrpsCollection1NewUserMgrps = em.merge(oldUByyOfUserMgrpsCollection1NewUserMgrps);
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
                Long id = prle.getTid();
                if (findPrle(id) == null) {
                    throw new NonexistentEntityException("The prle with id " + id + " no longer exists.");
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
            Prle prle;
            try {
                prle = em.getReference(Prle.class, id);
                prle.getTid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prle with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PassConf> passConfCollectionOrphanCheck = prle.getPassConfCollection();
            for (PassConf passConfCollectionOrphanCheckPassConf : passConfCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Prle (" + prle + ") cannot be destroyed since the PassConf " + passConfCollectionOrphanCheckPassConf + " in its passConfCollection field has a non-nullable CByy field.");
            }
            Collection<MGrps> MGrpsCollection2OrphanCheck = prle.getMGrpsCollection2();
            for (MGrps MGrpsCollection2OrphanCheckMGrps : MGrpsCollection2OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Prle (" + prle + ") cannot be destroyed since the MGrps " + MGrpsCollection2OrphanCheckMGrps + " in its MGrpsCollection2 field has a non-nullable CByy field.");
            }
            Collection<Sts> stsCollectionOrphanCheck = prle.getStsCollection();
            for (Sts stsCollectionOrphanCheckSts : stsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Prle (" + prle + ") cannot be destroyed since the Sts " + stsCollectionOrphanCheckSts + " in its stsCollection field has a non-nullable CByy field.");
            }
            Collection<UserMgrps> userMgrpsCollectionOrphanCheck = prle.getUserMgrpsCollection();
            for (UserMgrps userMgrpsCollectionOrphanCheckUserMgrps : userMgrpsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Prle (" + prle + ") cannot be destroyed since the UserMgrps " + userMgrpsCollectionOrphanCheckUserMgrps + " in its userMgrpsCollection field has a non-nullable CByy field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Rolap RIdd = prle.getRIdd();
            if (RIdd != null) {
                RIdd.setAByy(null);
                RIdd = em.merge(RIdd);
            }
            Sts ISts = prle.getISts();
            if (ISts != null) {
                ISts.getPrleCollection().remove(prle);
                ISts = em.merge(ISts);
            }
            Collection<Rolap> rolapCollection = prle.getRolapCollection();
            for (Rolap rolapCollectionRolap : rolapCollection) {
                rolapCollectionRolap.setAByy(null);
                rolapCollectionRolap = em.merge(rolapCollectionRolap);
            }
            Collection<Rolap> rolapCollection1 = prle.getRolapCollection1();
            for (Rolap rolapCollection1Rolap : rolapCollection1) {
                rolapCollection1Rolap.setUByy(null);
                rolapCollection1Rolap = em.merge(rolapCollection1Rolap);
            }
            Collection<Rolap> rolapCollection2 = prle.getRolapCollection2();
            for (Rolap rolapCollection2Rolap : rolapCollection2) {
                rolapCollection2Rolap.setCByy(null);
                rolapCollection2Rolap = em.merge(rolapCollection2Rolap);
            }
            Collection<PassConf> passConfCollection1 = prle.getPassConfCollection1();
            for (PassConf passConfCollection1PassConf : passConfCollection1) {
                passConfCollection1PassConf.setUByy(null);
                passConfCollection1PassConf = em.merge(passConfCollection1PassConf);
            }
            Collection<BaLottoConfig> baLottoConfigCollection = prle.getBaLottoConfigCollection();
            for (BaLottoConfig baLottoConfigCollectionBaLottoConfig : baLottoConfigCollection) {
                baLottoConfigCollectionBaLottoConfig.setCByy(null);
                baLottoConfigCollectionBaLottoConfig = em.merge(baLottoConfigCollectionBaLottoConfig);
            }
            Collection<MGrps> MGrpsCollection = prle.getMGrpsCollection();
            for (MGrps MGrpsCollectionMGrps : MGrpsCollection) {
                MGrpsCollectionMGrps.setUByy(null);
                MGrpsCollectionMGrps = em.merge(MGrpsCollectionMGrps);
            }
            Collection<MGrps> MGrpsCollection1 = prle.getMGrpsCollection1();
            for (MGrps MGrpsCollection1MGrps : MGrpsCollection1) {
                MGrpsCollection1MGrps.setAByy(null);
                MGrpsCollection1MGrps = em.merge(MGrpsCollection1MGrps);
            }
            Collection<BaLottoMember> baLottoMemberCollection = prle.getBaLottoMemberCollection();
            for (BaLottoMember baLottoMemberCollectionBaLottoMember : baLottoMemberCollection) {
                baLottoMemberCollectionBaLottoMember.setCByy(null);
                baLottoMemberCollectionBaLottoMember = em.merge(baLottoMemberCollectionBaLottoMember);
            }
            Collection<MItms> MItmsCollection = prle.getMItmsCollection();
            for (MItms MItmsCollectionMItms : MItmsCollection) {
                MItmsCollectionMItms.setAByy(null);
                MItmsCollectionMItms = em.merge(MItmsCollectionMItms);
            }
            Collection<MItms> MItmsCollection1 = prle.getMItmsCollection1();
            for (MItms MItmsCollection1MItms : MItmsCollection1) {
                MItmsCollection1MItms.setUByy(null);
                MItmsCollection1MItms = em.merge(MItmsCollection1MItms);
            }
            Collection<Sts> stsCollection1 = prle.getStsCollection1();
            for (Sts stsCollection1Sts : stsCollection1) {
                stsCollection1Sts.setUByy(null);
                stsCollection1Sts = em.merge(stsCollection1Sts);
            }
            Collection<BaLottoChannel> baLottoChannelCollection = prle.getBaLottoChannelCollection();
            for (BaLottoChannel baLottoChannelCollectionBaLottoChannel : baLottoChannelCollection) {
                baLottoChannelCollectionBaLottoChannel.setCByy(null);
                baLottoChannelCollectionBaLottoChannel = em.merge(baLottoChannelCollectionBaLottoChannel);
            }
            Collection<BaLottoResponse> baLottoResponseCollection = prle.getBaLottoResponseCollection();
            for (BaLottoResponse baLottoResponseCollectionBaLottoResponse : baLottoResponseCollection) {
                baLottoResponseCollectionBaLottoResponse.setCByy(null);
                baLottoResponseCollectionBaLottoResponse = em.merge(baLottoResponseCollectionBaLottoResponse);
            }
            Collection<BaLottoAgent> baLottoAgentCollection = prle.getBaLottoAgentCollection();
            for (BaLottoAgent baLottoAgentCollectionBaLottoAgent : baLottoAgentCollection) {
                baLottoAgentCollectionBaLottoAgent.setCByy(null);
                baLottoAgentCollectionBaLottoAgent = em.merge(baLottoAgentCollectionBaLottoAgent);
            }
            Collection<UserMgrps> userMgrpsCollection1 = prle.getUserMgrpsCollection1();
            for (UserMgrps userMgrpsCollection1UserMgrps : userMgrpsCollection1) {
                userMgrpsCollection1UserMgrps.setUByy(null);
                userMgrpsCollection1UserMgrps = em.merge(userMgrpsCollection1UserMgrps);
            }
            em.remove(prle);
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

    public List<Prle> findPrleEntities() {
        return findPrleEntities(true, -1, -1);
    }

    public List<Prle> findPrleEntities(int maxResults, int firstResult) {
        return findPrleEntities(false, maxResults, firstResult);
    }

    private List<Prle> findPrleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prle.class));
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

    public Prle findPrle(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prle.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prle> rt = cq.from(Prle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
