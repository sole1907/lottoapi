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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Noble
 */
@Entity
@Table(name = "user_mgrps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserMgrps.findAll", query = "SELECT u FROM UserMgrps u"),
    @NamedQuery(name = "UserMgrps.findByTid", query = "SELECT u FROM UserMgrps u WHERE u.tid = :tid"),
    @NamedQuery(name = "UserMgrps.findByCDte", query = "SELECT u FROM UserMgrps u WHERE u.cDte = :cDte"),
    @NamedQuery(name = "UserMgrps.findByUDte", query = "SELECT u FROM UserMgrps u WHERE u.uDte = :uDte")})
public class UserMgrps implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tid")
    private Long tid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "c_dte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cDte;
    @Column(name = "u_dte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uDte;
    @JoinColumn(name = "sts", referencedColumnName = "TID")
    @ManyToOne(optional = false)
    private Sts sts;
    @JoinColumn(name = "c_byy", referencedColumnName = "tid")
    @ManyToOne(optional = false)
    private Prle cByy;
    @JoinColumn(name = "u_byy", referencedColumnName = "tid")
    @ManyToOne
    private Prle uByy;
    @JoinColumn(name = "u_rlp", referencedColumnName = "TID")
    @ManyToOne(optional = false)
    private Rolap uRlp;
    @JoinColumn(name = "m_grps", referencedColumnName = "tid")
    @ManyToOne(optional = false)
    private MGrps mGrps;

    public UserMgrps() {
    }

    public UserMgrps(Long tid) {
        this.tid = tid;
    }

    public UserMgrps(Long tid, Date cDte) {
        this.tid = tid;
        this.cDte = cDte;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
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

    public Sts getSts() {
        return sts;
    }

    public void setSts(Sts sts) {
        this.sts = sts;
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

    public Rolap getURlp() {
        return uRlp;
    }

    public void setURlp(Rolap uRlp) {
        this.uRlp = uRlp;
    }

    public MGrps getMGrps() {
        return mGrps;
    }

    public void setMGrps(MGrps mGrps) {
        this.mGrps = mGrps;
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
        if (!(object instanceof UserMgrps)) {
            return false;
        }
        UserMgrps other = (UserMgrps) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.UserMgrps[ tid=" + tid + " ]";
    }
    
}
