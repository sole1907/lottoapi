/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbc.arsenallotto.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Noble
 */
@Entity
@Table(name = "m_itms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MItms.findAll", query = "SELECT m FROM MItms m"),
    @NamedQuery(name = "MItms.findByTid", query = "SELECT m FROM MItms m WHERE m.tid = :tid"),
    @NamedQuery(name = "MItms.findByMnme", query = "SELECT m FROM MItms m WHERE m.mnme = :mnme"),
    @NamedQuery(name = "MItms.findByIDsc", query = "SELECT m FROM MItms m WHERE m.iDsc = :iDsc"),
    @NamedQuery(name = "MItms.findByIUrl", query = "SELECT m FROM MItms m WHERE m.iUrl = :iUrl"),
    @NamedQuery(name = "MItms.findByAGid", query = "SELECT m FROM MItms m WHERE m.aGid = :aGid"),
    @NamedQuery(name = "MItms.findByCByy", query = "SELECT m FROM MItms m WHERE m.cByy = :cByy"),
    @NamedQuery(name = "MItms.findByUDte", query = "SELECT m FROM MItms m WHERE m.uDte = :uDte"),
    @NamedQuery(name = "MItms.findByADte", query = "SELECT m FROM MItms m WHERE m.aDte = :aDte")})
public class MItms implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tid")
    private Long tid;
    @Size(max = 248)
    @Column(name = "M_nme")
    private String mnme;
    @Size(max = 248)
    @Column(name = "i_dsc")
    private String iDsc;
    @Size(max = 248)
    @Column(name = "i_url")
    private String iUrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a_gid")
    private long aGid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "c_byy")
    private long cByy;
    @Column(name = "u_dte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uDte;
    @Column(name = "a_dte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aDte;
    @JoinColumn(name = "i_sts", referencedColumnName = "TID")
    @ManyToOne(optional = false)
    private Sts iSts;
    @JoinColumn(name = "a_byy", referencedColumnName = "tid")
    @ManyToOne
    private Prle aByy;
    @JoinColumn(name = "u_byy", referencedColumnName = "tid")
    @ManyToOne
    private Prle uByy;
    @JoinColumn(name = "p_mid", referencedColumnName = "tid")
    @ManyToOne(optional = false)
    private MGrps pMid;

    public MItms() {
    }

    public MItms(Long tid) {
        this.tid = tid;
    }

    public MItms(Long tid, long aGid, long cByy) {
        this.tid = tid;
        this.aGid = aGid;
        this.cByy = cByy;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getMnme() {
        return mnme;
    }

    public void setMnme(String mnme) {
        this.mnme = mnme;
    }

    public String getIDsc() {
        return iDsc;
    }

    public void setIDsc(String iDsc) {
        this.iDsc = iDsc;
    }

    public String getIUrl() {
        return iUrl;
    }

    public void setIUrl(String iUrl) {
        this.iUrl = iUrl;
    }

    public long getAGid() {
        return aGid;
    }

    public void setAGid(long aGid) {
        this.aGid = aGid;
    }

    public long getCByy() {
        return cByy;
    }

    public void setCByy(long cByy) {
        this.cByy = cByy;
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

    public Sts getISts() {
        return iSts;
    }

    public void setISts(Sts iSts) {
        this.iSts = iSts;
    }

    public Prle getAByy() {
        return aByy;
    }

    public void setAByy(Prle aByy) {
        this.aByy = aByy;
    }

    public Prle getUByy() {
        return uByy;
    }

    public void setUByy(Prle uByy) {
        this.uByy = uByy;
    }

    public MGrps getPMid() {
        return pMid;
    }

    public void setPMid(MGrps pMid) {
        this.pMid = pMid;
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
        if (!(object instanceof MItms)) {
            return false;
        }
        MItms other = (MItms) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.MItms[ tid=" + tid + " ]";
    }
    
}
