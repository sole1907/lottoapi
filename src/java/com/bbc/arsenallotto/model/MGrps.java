/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbc.arsenallotto.model;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "m_grps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MGrps.findAll", query = "SELECT m FROM MGrps m"),
    @NamedQuery(name = "MGrps.findByTid", query = "SELECT m FROM MGrps m WHERE m.tid = :tid"),
    @NamedQuery(name = "MGrps.findByGNme", query = "SELECT m FROM MGrps m WHERE m.gNme = :gNme"),
    @NamedQuery(name = "MGrps.findByIDsc", query = "SELECT m FROM MGrps m WHERE m.iDsc = :iDsc"),
    @NamedQuery(name = "MGrps.findByIPth", query = "SELECT m FROM MGrps m WHERE m.iPth = :iPth"),
    @NamedQuery(name = "MGrps.findByAGid", query = "SELECT m FROM MGrps m WHERE m.aGid = :aGid"),
    @NamedQuery(name = "MGrps.findByUDte", query = "SELECT m FROM MGrps m WHERE m.uDte = :uDte"),
    @NamedQuery(name = "MGrps.findByADte", query = "SELECT m FROM MGrps m WHERE m.aDte = :aDte"),
    @NamedQuery(name = "MGrps.findByCDte", query = "SELECT m FROM MGrps m WHERE m.cDte = :cDte"),
    @NamedQuery(name = "MGrps.findByParentId", query = "SELECT m FROM MGrps m WHERE m.parentId = :parentId"),
    @NamedQuery(name = "MGrps.findByIsParent", query = "SELECT m FROM MGrps m WHERE m.isParent = :isParent")})
public class MGrps implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tid")
    private Long tid;
    @Size(max = 248)
    @Column(name = "g_nme")
    private String gNme;
    @Size(max = 248)
    @Column(name = "i_dsc")
    private String iDsc;
    @Size(max = 248)
    @Column(name = "i_pth")
    private String iPth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a_gid")
    private long aGid;
    @Column(name = "u_dte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uDte;
    @Column(name = "a_dte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aDte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "c_dte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cDte;
    @Column(name = "parent_id")
    private BigInteger parentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "is_parent")
    private String isParent;
    @JoinColumn(name = "u_byy", referencedColumnName = "tid")
    @ManyToOne
    private Prle uByy;
    @JoinColumn(name = "a_byy", referencedColumnName = "tid")
    @ManyToOne
    private Prle aByy;
    @JoinColumn(name = "i_sts", referencedColumnName = "TID")
    @ManyToOne
    private Sts iSts;
    @JoinColumn(name = "c_byy", referencedColumnName = "tid")
    @ManyToOne(optional = false)
    private Prle cByy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pMid")
    private Collection<MItms> mItmsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mGrps")
    private Collection<UserMgrps> userMgrpsCollection;

    public MGrps() {
    }

    public MGrps(Long tid) {
        this.tid = tid;
    }

    public MGrps(Long tid, long aGid, Date cDte, String isParent) {
        this.tid = tid;
        this.aGid = aGid;
        this.cDte = cDte;
        this.isParent = isParent;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getGNme() {
        return gNme;
    }

    public void setGNme(String gNme) {
        this.gNme = gNme;
    }

    public String getIDsc() {
        return iDsc;
    }

    public void setIDsc(String iDsc) {
        this.iDsc = iDsc;
    }

    public String getIPth() {
        return iPth;
    }

    public void setIPth(String iPth) {
        this.iPth = iPth;
    }

    public long getAGid() {
        return aGid;
    }

    public void setAGid(long aGid) {
        this.aGid = aGid;
    }

    public Date getUDte() {
        return uDte;
    }

    public void setUDte(Date uDte) {
        this.uDte = uDte;
    }

    public Date getADte() {
        return aDte;
    }

    public void setADte(Date aDte) {
        this.aDte = aDte;
    }

    public Date getCDte() {
        return cDte;
    }

    public void setCDte(Date cDte) {
        this.cDte = cDte;
    }

    public BigInteger getParentId() {
        return parentId;
    }

    public void setParentId(BigInteger parentId) {
        this.parentId = parentId;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public Prle getUByy() {
        return uByy;
    }

    public void setUByy(Prle uByy) {
        this.uByy = uByy;
    }

    public Prle getAByy() {
        return aByy;
    }

    public void setAByy(Prle aByy) {
        this.aByy = aByy;
    }

    public Sts getISts() {
        return iSts;
    }

    public void setISts(Sts iSts) {
        this.iSts = iSts;
    }

    public Prle getCByy() {
        return cByy;
    }

    public void setCByy(Prle cByy) {
        this.cByy = cByy;
    }

    @XmlTransient
    public Collection<MItms> getMItmsCollection() {
        return mItmsCollection;
    }

    public void setMItmsCollection(Collection<MItms> mItmsCollection) {
        this.mItmsCollection = mItmsCollection;
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
        if (!(object instanceof MGrps)) {
            return false;
        }
        MGrps other = (MGrps) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.MGrps[ tid=" + tid + " ]";
    }
    
}
