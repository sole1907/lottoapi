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
@Table(name = "prle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prle.findAll", query = "SELECT p FROM Prle p"),
    @NamedQuery(name = "Prle.findByTid", query = "SELECT p FROM Prle p WHERE p.tid = :tid"),
    @NamedQuery(name = "Prle.findByUNme", query = "SELECT p FROM Prle p WHERE p.uNme = :uNme"),
    @NamedQuery(name = "Prle.findByPWrd", query = "SELECT p FROM Prle p WHERE p.pWrd = :pWrd"),
    @NamedQuery(name = "Prle.findByFNme", query = "SELECT p FROM Prle p WHERE p.fNme = :fNme"),
    @NamedQuery(name = "Prle.findByMNme", query = "SELECT p FROM Prle p WHERE p.mNme = :mNme"),
    @NamedQuery(name = "Prle.findByLNme", query = "SELECT p FROM Prle p WHERE p.lNme = :lNme"),
    @NamedQuery(name = "Prle.findByLPch", query = "SELECT p FROM Prle p WHERE p.lPch = :lPch"),
    @NamedQuery(name = "Prle.findByBCde", query = "SELECT p FROM Prle p WHERE p.bCde = :bCde"),
    @NamedQuery(name = "Prle.findByPNum", query = "SELECT p FROM Prle p WHERE p.pNum = :pNum"),
    @NamedQuery(name = "Prle.findByCTry", query = "SELECT p FROM Prle p WHERE p.cTry = :cTry"),
    @NamedQuery(name = "Prle.findBySTte", query = "SELECT p FROM Prle p WHERE p.sTte = :sTte"),
    @NamedQuery(name = "Prle.findByADdy", query = "SELECT p FROM Prle p WHERE p.aDdy = :aDdy"),
    @NamedQuery(name = "Prle.findByEMal", query = "SELECT p FROM Prle p WHERE p.eMal = :eMal"),
    @NamedQuery(name = "Prle.findByCDte", query = "SELECT p FROM Prle p WHERE p.cDte = :cDte"),
    @NamedQuery(name = "Prle.findByCByy", query = "SELECT p FROM Prle p WHERE p.cByy = :cByy"),
    @NamedQuery(name = "Prle.findByUDte", query = "SELECT p FROM Prle p WHERE p.uDte = :uDte"),
    @NamedQuery(name = "Prle.findByUByy", query = "SELECT p FROM Prle p WHERE p.uByy = :uByy"),
    @NamedQuery(name = "Prle.findByADte", query = "SELECT p FROM Prle p WHERE p.aDte = :aDte"),
    @NamedQuery(name = "Prle.findByAByy", query = "SELECT p FROM Prle p WHERE p.aByy = :aByy"),
    @NamedQuery(name = "Prle.findByLLgn", query = "SELECT p FROM Prle p WHERE p.lLgn = :lLgn"),
    @NamedQuery(name = "Prle.findByPRty", query = "SELECT p FROM Prle p WHERE p.pRty = :pRty"),
    @NamedQuery(name = "Prle.findByPTyp", query = "SELECT p FROM Prle p WHERE p.pTyp = :pTyp"),
    @NamedQuery(name = "Prle.findByDefpass", query = "SELECT p FROM Prle p WHERE p.defpass = :defpass"),
    @NamedQuery(name = "Prle.findByProfileFullname", query = "SELECT p FROM Prle p WHERE p.profileFullname = :profileFullname"),
    @NamedQuery(name = "Prle.findByExtId", query = "SELECT p FROM Prle p WHERE p.extId = :extId"),
    @NamedQuery(name = "Prle.findByTheme", query = "SELECT p FROM Prle p WHERE p.theme = :theme")})
public class Prle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tid")
    private Long tid;
    @Size(max = 50)
    @Column(name = "u_nme")
    private String uNme;
    @Size(max = 248)
    @Column(name = "p_wrd")
    private String pWrd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "f_nme")
    private String fNme;
    @Size(max = 50)
    @Column(name = "m_nme")
    private String mNme;
    @Size(max = 50)
    @Column(name = "l_nme")
    private String lNme;
    @Column(name = "l_pch")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lPch;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "b_cde")
    private String bCde;
    @Size(max = 25)
    @Column(name = "p_num")
    private String pNum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "c_try")
    private String cTry;
    @Size(max = 25)
    @Column(name = "S_TTE")
    private String sTte;
    @Size(max = 250)
    @Column(name = "a_ddy")
    private String aDdy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "e_mal")
    private String eMal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "c_dte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cDte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "c_byy")
    private long cByy;
    @Column(name = "u_dte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uDte;
    @Column(name = "u_byy")
    private BigInteger uByy;
    @Column(name = "a_dte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aDte;
    @Column(name = "a_byy")
    private BigInteger aByy;
    @Column(name = "l_lgn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lLgn;
    @Column(name = "p_rty")
    private Integer pRty;
    @Column(name = "p_typ")
    private BigInteger pTyp;
    @Size(max = 25)
    @Column(name = "defpass")
    private String defpass;
    @Size(max = 240)
    @Column(name = "profileFullname")
    private String profileFullname;
    @Size(max = 30)
    @Column(name = "ext_id")
    private String extId;
    @Size(max = 255)
    @Column(name = "theme")
    private String theme;
    @OneToMany(mappedBy = "aByy")
    private Collection<Rolap> rolapCollection;
    @OneToMany(mappedBy = "uByy")
    private Collection<Rolap> rolapCollection1;
    @OneToMany(mappedBy = "cByy")
    private Collection<Rolap> rolapCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cByy")
    private Collection<PassConf> passConfCollection;
    @OneToMany(mappedBy = "uByy")
    private Collection<PassConf> passConfCollection1;
    @OneToMany(mappedBy = "cByy")
    private Collection<BaLottoConfig> baLottoConfigCollection;
    @OneToMany(mappedBy = "uByy")
    private Collection<MGrps> mGrpsCollection;
    @OneToMany(mappedBy = "aByy")
    private Collection<MGrps> mGrpsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cByy")
    private Collection<MGrps> mGrpsCollection2;
    @OneToMany(mappedBy = "cByy")
    private Collection<BaLottoMember> baLottoMemberCollection;
    @OneToMany(mappedBy = "aByy")
    private Collection<MItms> mItmsCollection;
    @OneToMany(mappedBy = "uByy")
    private Collection<MItms> mItmsCollection1;
    @JoinColumn(name = "r_idd", referencedColumnName = "TID")
    @ManyToOne
    private Rolap rIdd;
    @JoinColumn(name = "I_STS", referencedColumnName = "TID")
    @ManyToOne
    private Sts iSts;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cByy")
    private Collection<Sts> stsCollection;
    @OneToMany(mappedBy = "uByy")
    private Collection<Sts> stsCollection1;
    @OneToMany(mappedBy = "cByy")
    private Collection<BaLottoChannel> baLottoChannelCollection;
    @OneToMany(mappedBy = "cByy")
    private Collection<BaLottoResponse> baLottoResponseCollection;
    @OneToMany(mappedBy = "cByy")
    private Collection<BaLottoAgent> baLottoAgentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cByy")
    private Collection<UserMgrps> userMgrpsCollection;
    @OneToMany(mappedBy = "uByy")
    private Collection<UserMgrps> userMgrpsCollection1;

    public Prle() {
    }

    public Prle(Long tid) {
        this.tid = tid;
    }

    public Prle(Long tid, String fNme, String bCde, String cTry, String eMal, Date cDte, long cByy) {
        this.tid = tid;
        this.fNme = fNme;
        this.bCde = bCde;
        this.cTry = cTry;
        this.eMal = eMal;
        this.cDte = cDte;
        this.cByy = cByy;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getUNme() {
        return uNme;
    }

    public void setUNme(String uNme) {
        this.uNme = uNme;
    }

    public String getPWrd() {
        return pWrd;
    }

    public void setPWrd(String pWrd) {
        this.pWrd = pWrd;
    }

    public String getFNme() {
        return fNme;
    }

    public void setFNme(String fNme) {
        this.fNme = fNme;
    }

    public String getMNme() {
        return mNme;
    }

    public void setMNme(String mNme) {
        this.mNme = mNme;
    }

    public String getLNme() {
        return lNme;
    }

    public void setLNme(String lNme) {
        this.lNme = lNme;
    }

    public Date getLPch() {
        return lPch;
    }

    public void setLPch(Date lPch) {
        this.lPch = lPch;
    }

    public String getBCde() {
        return bCde;
    }

    public void setBCde(String bCde) {
        this.bCde = bCde;
    }

    public String getPNum() {
        return pNum;
    }

    public void setPNum(String pNum) {
        this.pNum = pNum;
    }

    public String getCTry() {
        return cTry;
    }

    public void setCTry(String cTry) {
        this.cTry = cTry;
    }

    public String getSTte() {
        return sTte;
    }

    public void setSTte(String sTte) {
        this.sTte = sTte;
    }

    public String getADdy() {
        return aDdy;
    }

    public void setADdy(String aDdy) {
        this.aDdy = aDdy;
    }

    public String getEMal() {
        return eMal;
    }

    public void setEMal(String eMal) {
        this.eMal = eMal;
    }

    public Date getCDte() {
        return cDte;
    }

    public void setCDte(Date cDte) {
        this.cDte = cDte;
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

    public BigInteger getUByy() {
        return uByy;
    }

    public void setUByy(BigInteger uByy) {
        this.uByy = uByy;
    }

    public Date getADte() {
        return aDte;
    }

    public void setADte(Date aDte) {
        this.aDte = aDte;
    }

    public BigInteger getAByy() {
        return aByy;
    }

    public void setAByy(BigInteger aByy) {
        this.aByy = aByy;
    }

    public Date getLLgn() {
        return lLgn;
    }

    public void setLLgn(Date lLgn) {
        this.lLgn = lLgn;
    }

    public Integer getPRty() {
        return pRty;
    }

    public void setPRty(Integer pRty) {
        this.pRty = pRty;
    }

    public BigInteger getPTyp() {
        return pTyp;
    }

    public void setPTyp(BigInteger pTyp) {
        this.pTyp = pTyp;
    }

    public String getDefpass() {
        return defpass;
    }

    public void setDefpass(String defpass) {
        this.defpass = defpass;
    }

    public String getProfileFullname() {
        return profileFullname;
    }

    public void setProfileFullname(String profileFullname) {
        this.profileFullname = profileFullname;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @XmlTransient
    public Collection<Rolap> getRolapCollection() {
        return rolapCollection;
    }

    public void setRolapCollection(Collection<Rolap> rolapCollection) {
        this.rolapCollection = rolapCollection;
    }

    @XmlTransient
    public Collection<Rolap> getRolapCollection1() {
        return rolapCollection1;
    }

    public void setRolapCollection1(Collection<Rolap> rolapCollection1) {
        this.rolapCollection1 = rolapCollection1;
    }

    @XmlTransient
    public Collection<Rolap> getRolapCollection2() {
        return rolapCollection2;
    }

    public void setRolapCollection2(Collection<Rolap> rolapCollection2) {
        this.rolapCollection2 = rolapCollection2;
    }

    @XmlTransient
    public Collection<PassConf> getPassConfCollection() {
        return passConfCollection;
    }

    public void setPassConfCollection(Collection<PassConf> passConfCollection) {
        this.passConfCollection = passConfCollection;
    }

    @XmlTransient
    public Collection<PassConf> getPassConfCollection1() {
        return passConfCollection1;
    }

    public void setPassConfCollection1(Collection<PassConf> passConfCollection1) {
        this.passConfCollection1 = passConfCollection1;
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
    public Collection<MGrps> getMGrpsCollection1() {
        return mGrpsCollection1;
    }

    public void setMGrpsCollection1(Collection<MGrps> mGrpsCollection1) {
        this.mGrpsCollection1 = mGrpsCollection1;
    }

    @XmlTransient
    public Collection<MGrps> getMGrpsCollection2() {
        return mGrpsCollection2;
    }

    public void setMGrpsCollection2(Collection<MGrps> mGrpsCollection2) {
        this.mGrpsCollection2 = mGrpsCollection2;
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
    public Collection<MItms> getMItmsCollection1() {
        return mItmsCollection1;
    }

    public void setMItmsCollection1(Collection<MItms> mItmsCollection1) {
        this.mItmsCollection1 = mItmsCollection1;
    }

    public Rolap getRIdd() {
        return rIdd;
    }

    public void setRIdd(Rolap rIdd) {
        this.rIdd = rIdd;
    }

    public Sts getISts() {
        return iSts;
    }

    public void setISts(Sts iSts) {
        this.iSts = iSts;
    }

    @XmlTransient
    public Collection<Sts> getStsCollection() {
        return stsCollection;
    }

    public void setStsCollection(Collection<Sts> stsCollection) {
        this.stsCollection = stsCollection;
    }

    @XmlTransient
    public Collection<Sts> getStsCollection1() {
        return stsCollection1;
    }

    public void setStsCollection1(Collection<Sts> stsCollection1) {
        this.stsCollection1 = stsCollection1;
    }

    @XmlTransient
    public Collection<BaLottoChannel> getBaLottoChannelCollection() {
        return baLottoChannelCollection;
    }

    public void setBaLottoChannelCollection(Collection<BaLottoChannel> baLottoChannelCollection) {
        this.baLottoChannelCollection = baLottoChannelCollection;
    }

    @XmlTransient
    public Collection<BaLottoResponse> getBaLottoResponseCollection() {
        return baLottoResponseCollection;
    }

    public void setBaLottoResponseCollection(Collection<BaLottoResponse> baLottoResponseCollection) {
        this.baLottoResponseCollection = baLottoResponseCollection;
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

    @XmlTransient
    public Collection<UserMgrps> getUserMgrpsCollection1() {
        return userMgrpsCollection1;
    }

    public void setUserMgrpsCollection1(Collection<UserMgrps> userMgrpsCollection1) {
        this.userMgrpsCollection1 = userMgrpsCollection1;
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
        if (!(object instanceof Prle)) {
            return false;
        }
        Prle other = (Prle) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.Prle[ tid=" + tid + " ]";
    }
    
}
