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
@Table(name = "pass_conf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PassConf.findAll", query = "SELECT p FROM PassConf p"),
    @NamedQuery(name = "PassConf.findByTid", query = "SELECT p FROM PassConf p WHERE p.tid = :tid"),
    @NamedQuery(name = "PassConf.findByNme", query = "SELECT p FROM PassConf p WHERE p.nme = :nme"),
    @NamedQuery(name = "PassConf.findByIDesc", query = "SELECT p FROM PassConf p WHERE p.iDesc = :iDesc"),
    @NamedQuery(name = "PassConf.findByCDte", query = "SELECT p FROM PassConf p WHERE p.cDte = :cDte"),
    @NamedQuery(name = "PassConf.findByUDte", query = "SELECT p FROM PassConf p WHERE p.uDte = :uDte")})
public class PassConf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tid")
    private Long tid;
    @Size(max = 50)
    @Column(name = "nme")
    private String nme;
    @Size(max = 248)
    @Column(name = "i_desc")
    private String iDesc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "c_dte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cDte;
    @Column(name = "u_dte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uDte;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pGrp")
    private Collection<Rolap> rolapCollection;
    @JoinColumn(name = "c_byy", referencedColumnName = "tid")
    @ManyToOne(optional = false)
    private Prle cByy;
    @JoinColumn(name = "u_byy", referencedColumnName = "tid")
    @ManyToOne
    private Prle uByy;
    @JoinColumn(name = "sts", referencedColumnName = "TID")
    @ManyToOne
    private Sts sts;

    public PassConf() {
    }

    public PassConf(Long tid) {
        this.tid = tid;
    }

    public PassConf(Long tid, Date cDte) {
        this.tid = tid;
        this.cDte = cDte;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getNme() {
        return nme;
    }

    public void setNme(String nme) {
        this.nme = nme;
    }

    public String getIDesc() {
        return iDesc;
    }

    public void setIDesc(String iDesc) {
        this.iDesc = iDesc;
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

    @XmlTransient
    public Collection<Rolap> getRolapCollection() {
        return rolapCollection;
    }

    public void setRolapCollection(Collection<Rolap> rolapCollection) {
        this.rolapCollection = rolapCollection;
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

    public Sts getSts() {
        return sts;
    }

    public void setSts(Sts sts) {
        this.sts = sts;
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
        if (!(object instanceof PassConf)) {
            return false;
        }
        PassConf other = (PassConf) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.PassConf[ tid=" + tid + " ]";
    }
    
}
