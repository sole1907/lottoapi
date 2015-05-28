/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbc.arsenallotto.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Noble
 */
@Entity
@Table(name = "sts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sts.findAll", query = "SELECT s FROM Sts s"),
    @NamedQuery(name = "Sts.findByTid", query = "SELECT s FROM Sts s WHERE s.tid = :tid"),
    @NamedQuery(name = "Sts.findByIDsc", query = "SELECT s FROM Sts s WHERE s.iDsc = :iDsc"),
    @NamedQuery(name = "Sts.findByCDte", query = "SELECT s FROM Sts s WHERE s.cDte = :cDte"),
    @NamedQuery(name = "Sts.findByUDte", query = "SELECT s FROM Sts s WHERE s.uDte = :uDte"),
    @NamedQuery(name = "Sts.findBySNme", query = "SELECT s FROM Sts s WHERE s.sNme = :sNme")})
public class Sts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TID")
    private Long tid;
    @Size(max = 250)
    @Column(name = "I_DSC")
    private String iDsc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "C_DTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cDte;
    @Column(name = "U_DTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uDte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "S_NME")
    private String sNme;
    @OneToMany(mappedBy = "iSts")
    private Collection<Rolap> rolapCollection;
    @OneToMany(mappedBy = "sts")
    private Collection<PassConf> passConfCollection;
    @OneToMany(mappedBy = "sts")
    private Collection<BaLottoConfig> baLottoConfigCollection;
    @OneToMany(mappedBy = "iSts")
    private Collection<MGrps> mGrpsCollection;
    @OneToMany(mappedBy = "sts")
    private Collection<BaLottoMember> baLottoMemberCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iSts")
    private Collection<MItms> mItmsCollection;
    @OneToMany(mappedBy = "iSts")
    private Collection<Prle> prleCollection;
    @JoinColumn(name = "C_BYY", referencedColumnName = "tid")
    @ManyToOne(optional = false)
    private Prle cByy;
    @JoinColumn(name = "U_BYY", referencedColumnName = "tid")
    @ManyToOne
    private Prle uByy;
    @OneToMany(mappedBy = "sts")
    private Collection<BaLottoChannel> baLottoChannelCollection;
    @OneToMany(mappedBy = "status")
    private Collection<BaLottoEntry> baLottoEntryCollection;
    @OneToMany(mappedBy = "sts")
    private Collection<BaLottoAgent> baLottoAgentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sts")
    private Collection<UserMgrps> userMgrpsCollection;

    public Sts() {
    }

    public Sts(Long tid) {
        this.tid = tid;
    }

    public Sts(Long tid, Date cDte, String sNme) {
        this.tid = tid;
        this.cDte = cDte;
        this.sNme = sNme;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getIDsc() {
        return iDsc;
    }

    public void setIDsc(String iDsc) {
        this.iDsc = iDsc;
    }

    public Date getCDte() {
        return cDte;
    }

    public void setCDte(Date cDte) {
        this.cDte = cDte;
    }

    public Date getUDte() {
        return uDte;
    }

    public void setUDte(Date uDte) {
        this.uDte = uDte;
    }

    public String getSNme() {
        return sNme;
    }

    public void setSNme(String sNme) {
        this.sNme = sNme;
    }

    @XmlTransient
    public Collection<Rolap> getRolapCollection() {
        return rolapCollection;
    }

    public void setRolapCollection(Collection<Rolap> rolapCollection) {
        this.rolapCollection = rolapCollection;
    }

    @XmlTransient
    public Collection<PassConf> getPassConfCollection() {
        return passConfCollection;
    }

    public void setPassConfCollection(Collection<PassConf> passConfCollection) {
        this.passConfCollection = passConfCollection;
    }

    @XmlTransient
    public Collection<BaLottoConfig> getBaLottoConfigCollection() {
        return baLottoConfigCollection;
    }

    public void setBaLottoConfigCollection(Collection<BaLottoConfig> baLottoConfigCollection) {
        this.baLottoConfigCollection = baLottoConfigCollection;
    }

    @XmlTransient
    public Collection<MGrps> getMGrpsCollection() {
        return mGrpsCollection;
    }

    public void setMGrpsCollection(Collection<MGrps> mGrpsCollection) {
        this.mGrpsCollection = mGrpsCollection;
    }

    @XmlTransient
    public Collection<BaLottoMember> getBaLottoMemberCollection() {
        return baLottoMemberCollection;
    }

    public void setBaLottoMemberCollection(Collection<BaLottoMember> baLottoMemberCollection) {
        this.baLottoMemberCollection = baLottoMemberCollection;
    }

    @XmlTransient
    public Collection<MItms> getMItmsCollection() {
        return mItmsCollection;
    }

    public void setMItmsCollection(Collection<MItms> mItmsCollection) {
        this.mItmsCollection = mItmsCollection;
    }

    @XmlTransient
    public Collection<Prle> getPrleCollection() {
        return prleCollection;
    }

    public void setPrleCollection(Collection<Prle> prleCollection) {
        this.prleCollection = prleCollection;
    }

    public Prle getCByy() {
        return cByy;
    }

    public void setCByy(Prle cByy) {
        this.cByy = cByy;
    }

    public Prle getUByy() {
        return uByy;
    }

    public void setUByy(Prle uByy) {
        this.uByy = uByy;
    }

    @XmlTransient
    public Collection<BaLottoChannel> getBaLottoChannelCollection() {
        return baLottoChannelCollection;
    }

    public void setBaLottoChannelCollection(Collection<BaLottoChannel> baLottoChannelCollection) {
        this.baLottoChannelCollection = baLottoChannelCollection;
    }

    @XmlTransient
    public Collection<BaLottoEntry> getBaLottoEntryCollection() {
        return baLottoEntryCollection;
    }

    public void setBaLottoEntryCollection(Collection<BaLottoEntry> baLottoEntryCollection) {
        this.baLottoEntryCollection = baLottoEntryCollection;
    }

    @XmlTransient
    public Collection<BaLottoAgent> getBaLottoAgentCollection() {
        return baLottoAgentCollection;
    }

    public void setBaLottoAgentCollection(Collection<BaLottoAgent> baLottoAgentCollection) {
        this.baLottoAgentCollection = baLottoAgentCollection;
    }

    @XmlTransient
    public Collection<UserMgrps> getUserMgrpsCollection() {
        return userMgrpsCollection;
    }

    public void setUserMgrpsCollection(Collection<UserMgrps> userMgrpsCollection) {
        this.userMgrpsCollection = userMgrpsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tid != null ? tid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sts)) {
            return false;
        }
        Sts other = (Sts) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.Sts[ tid=" + tid + " ]";
    }
    
}
