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
@Table(name = "rolap")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolap.findAll", query = "SELECT r FROM Rolap r"),
    @NamedQuery(name = "Rolap.findByTid", query = "SELECT r FROM Rolap r WHERE r.tid = :tid"),
    @NamedQuery(name = "Rolap.findByRNme", query = "SELECT r FROM Rolap r WHERE r.rNme = :rNme"),
    @NamedQuery(name = "Rolap.findByIDsc", query = "SELECT r FROM Rolap r WHERE r.iDsc = :iDsc"),
    @NamedQuery(name = "Rolap.findByOne", query = "SELECT r FROM Rolap r WHERE r.one = :one"),
    @NamedQuery(name = "Rolap.findByTwo", query = "SELECT r FROM Rolap r WHERE r.two = :two"),
    @NamedQuery(name = "Rolap.findByThr", query = "SELECT r FROM Rolap r WHERE r.thr = :thr"),
    @NamedQuery(name = "Rolap.findByFur", query = "SELECT r FROM Rolap r WHERE r.fur = :fur"),
    @NamedQuery(name = "Rolap.findByFve", query = "SELECT r FROM Rolap r WHERE r.fve = :fve"),
    @NamedQuery(name = "Rolap.findBySix", query = "SELECT r FROM Rolap r WHERE r.six = :six"),
    @NamedQuery(name = "Rolap.findBySvn", query = "SELECT r FROM Rolap r WHERE r.svn = :svn"),
    @NamedQuery(name = "Rolap.findByStt", query = "SELECT r FROM Rolap r WHERE r.stt = :stt"),
    @NamedQuery(name = "Rolap.findByStp", query = "SELECT r FROM Rolap r WHERE r.stp = :stp"),
    @NamedQuery(name = "Rolap.findByCRid", query = "SELECT r FROM Rolap r WHERE r.cRid = :cRid"),
    @NamedQuery(name = "Rolap.findByCCrt", query = "SELECT r FROM Rolap r WHERE r.cCrt = :cCrt"),
    @NamedQuery(name = "Rolap.findByCUpd", query = "SELECT r FROM Rolap r WHERE r.cUpd = :cUpd"),
    @NamedQuery(name = "Rolap.findByCPst", query = "SELECT r FROM Rolap r WHERE r.cPst = :cPst"),
    @NamedQuery(name = "Rolap.findByCDel", query = "SELECT r FROM Rolap r WHERE r.cDel = :cDel"),
    @NamedQuery(name = "Rolap.findByCDte", query = "SELECT r FROM Rolap r WHERE r.cDte = :cDte"),
    @NamedQuery(name = "Rolap.findByUDte", query = "SELECT r FROM Rolap r WHERE r.uDte = :uDte"),
    @NamedQuery(name = "Rolap.findByADte", query = "SELECT r FROM Rolap r WHERE r.aDte = :aDte"),
    @NamedQuery(name = "Rolap.findByFrmMm", query = "SELECT r FROM Rolap r WHERE r.frmMm = :frmMm"),
    @NamedQuery(name = "Rolap.findByFrmHh", query = "SELECT r FROM Rolap r WHERE r.frmHh = :frmHh"),
    @NamedQuery(name = "Rolap.findByToHh", query = "SELECT r FROM Rolap r WHERE r.toHh = :toHh"),
    @NamedQuery(name = "Rolap.findByToMm", query = "SELECT r FROM Rolap r WHERE r.toMm = :toMm")})
public class Rolap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TID")
    private Long tid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "R_NME")
    private String rNme;
    @Size(max = 250)
    @Column(name = "I_DSC")
    private String iDsc;
    @Column(name = "ONE")
    private Boolean one;
    @Column(name = "TWO")
    private Boolean two;
    @Column(name = "THR")
    private Boolean thr;
    @Column(name = "FUR")
    private Boolean fur;
    @Column(name = "FVE")
    private Boolean fve;
    @Column(name = "SIX")
    private Boolean six;
    @Column(name = "SVN")
    private Boolean svn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "STT")
    private String stt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "STP")
    private String stp;
    @Column(name = "C_RID")
    private Boolean cRid;
    @Column(name = "C_CRT")
    private Boolean cCrt;
    @Column(name = "C_UPD")
    private Boolean cUpd;
    @Column(name = "C_PST")
    private Boolean cPst;
    @Column(name = "C_DEL")
    private Boolean cDel;
    @Column(name = "C_DTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cDte;
    @Column(name = "U_DTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uDte;
    @Column(name = "a_dte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aDte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "FRM_MM")
    private String frmMm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "FRM_HH")
    private String frmHh;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "TO_HH")
    private String toHh;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "TO_MM")
    private String toMm;
    @JoinColumn(name = "a_byy", referencedColumnName = "tid")
    @ManyToOne
    private Prle aByy;
    @JoinColumn(name = "U__BYY", referencedColumnName = "tid")
    @ManyToOne
    private Prle uByy;
    @JoinColumn(name = "I_STS", referencedColumnName = "TID")
    @ManyToOne
    private Sts iSts;
    @JoinColumn(name = "P_GRP", referencedColumnName = "tid")
    @ManyToOne(optional = false)
    private PassConf pGrp;
    @JoinColumn(name = "C_BYY", referencedColumnName = "tid")
    @ManyToOne
    private Prle cByy;
    @OneToMany(mappedBy = "rIdd")
    private Collection<Prle> prleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uRlp")
    private Collection<UserMgrps> userMgrpsCollection;

    public Rolap() {
    }

    public Rolap(Long tid) {
        this.tid = tid;
    }

    public Rolap(Long tid, String rNme, String stt, String stp, String frmMm, String frmHh, String toHh, String toMm) {
        this.tid = tid;
        this.rNme = rNme;
        this.stt = stt;
        this.stp = stp;
        this.frmMm = frmMm;
        this.frmHh = frmHh;
        this.toHh = toHh;
        this.toMm = toMm;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getRNme() {
        return rNme;
    }

    public void setRNme(String rNme) {
        this.rNme = rNme;
    }

    public String getIDsc() {
        return iDsc;
    }

    public void setIDsc(String iDsc) {
        this.iDsc = iDsc;
    }

    public Boolean getOne() {
        return one;
    }

    public void setOne(Boolean one) {
        this.one = one;
    }

    public Boolean getTwo() {
        return two;
    }

    public void setTwo(Boolean two) {
        this.two = two;
    }

    public Boolean getThr() {
        return thr;
    }

    public void setThr(Boolean thr) {
        this.thr = thr;
    }

    public Boolean getFur() {
        return fur;
    }

    public void setFur(Boolean fur) {
        this.fur = fur;
    }

    public Boolean getFve() {
        return fve;
    }

    public void setFve(Boolean fve) {
        this.fve = fve;
    }

    public Boolean getSix() {
        return six;
    }

    public void setSix(Boolean six) {
        this.six = six;
    }

    public Boolean getSvn() {
        return svn;
    }

    public void setSvn(Boolean svn) {
        this.svn = svn;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getStp() {
        return stp;
    }

    public void setStp(String stp) {
        this.stp = stp;
    }

    public Boolean getCRid() {
        return cRid;
    }

    public void setCRid(Boolean cRid) {
        this.cRid = cRid;
    }

    public Boolean getCCrt() {
        return cCrt;
    }

    public void setCCrt(Boolean cCrt) {
        this.cCrt = cCrt;
    }

    public Boolean getCUpd() {
        return cUpd;
    }

    public void setCUpd(Boolean cUpd) {
        this.cUpd = cUpd;
    }

    public Boolean getCPst() {
        return cPst;
    }

    public void setCPst(Boolean cPst) {
        this.cPst = cPst;
    }

    public Boolean getCDel() {
        return cDel;
    }

    public void setCDel(Boolean cDel) {
        this.cDel = cDel;
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

    public Date getADte() {
        return aDte;
    }

    public void setADte(Date aDte) {
        this.aDte = aDte;
    }

    public String getFrmMm() {
        return frmMm;
    }

    public void setFrmMm(String frmMm) {
        this.frmMm = frmMm;
    }

    public String getFrmHh() {
        return frmHh;
    }

    public void setFrmHh(String frmHh) {
        this.frmHh = frmHh;
    }

    public String getToHh() {
        return toHh;
    }

    public void setToHh(String toHh) {
        this.toHh = toHh;
    }

    public String getToMm() {
        return toMm;
    }

    public void setToMm(String toMm) {
        this.toMm = toMm;
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

    public Sts getISts() {
        return iSts;
    }

    public void setISts(Sts iSts) {
        this.iSts = iSts;
    }

    public PassConf getPGrp() {
        return pGrp;
    }

    public void setPGrp(PassConf pGrp) {
        this.pGrp = pGrp;
    }

    public Prle getCByy() {
        return cByy;
    }

    public void setCByy(Prle cByy) {
        this.cByy = cByy;
    }

    @XmlTransient
    public Collection<Prle> getPrleCollection() {
        return prleCollection;
    }

    public void setPrleCollection(Collection<Prle> prleCollection) {
        this.prleCollection = prleCollection;
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
        if (!(object instanceof Rolap)) {
            return false;
        }
        Rolap other = (Rolap) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.Rolap[ tid=" + tid + " ]";
    }
    
}
