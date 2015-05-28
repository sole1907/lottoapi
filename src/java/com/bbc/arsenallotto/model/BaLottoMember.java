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
@Table(name = "ba_lotto_member")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BaLottoMember.findAll", query = "SELECT b FROM BaLottoMember b"),
    @NamedQuery(name = "BaLottoMember.findById", query = "SELECT b FROM BaLottoMember b WHERE b.id = :id"),
    @NamedQuery(name = "BaLottoMember.findByMobileNumber", query = "SELECT b FROM BaLottoMember b WHERE b.mobileNumber = :mobileNumber"),
    @NamedQuery(name = "BaLottoMember.findByFirstname", query = "SELECT b FROM BaLottoMember b WHERE b.firstname = :firstname"),
    @NamedQuery(name = "BaLottoMember.findByLastname", query = "SELECT b FROM BaLottoMember b WHERE b.lastname = :lastname"),
    @NamedQuery(name = "BaLottoMember.findByDob", query = "SELECT b FROM BaLottoMember b WHERE b.dob = :dob"),
    @NamedQuery(name = "BaLottoMember.findBySex", query = "SELECT b FROM BaLottoMember b WHERE b.sex = :sex"),
    @NamedQuery(name = "BaLottoMember.findByEmail", query = "SELECT b FROM BaLottoMember b WHERE b.email = :email"),
    @NamedQuery(name = "BaLottoMember.findByCreated", query = "SELECT b FROM BaLottoMember b WHERE b.created = :created"),
    @NamedQuery(name = "BaLottoMember.findByModified", query = "SELECT b FROM BaLottoMember b WHERE b.modified = :modified"),
    @NamedQuery(name = "BaLottoMember.findByCode", query = "SELECT b FROM BaLottoMember b WHERE b.code = :code"),
    @NamedQuery(name = "BaLottoMember.findByAgentID", query = "SELECT b FROM BaLottoMember b WHERE b.agentID = :agentID")})
public class BaLottoMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Size(max = 50)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 50)
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "sex")
    private Character sex;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @Size(max = 25)
    @Column(name = "code")
    private String code;
    @Column(name = "agentID")
    private Integer agentID;
    @JoinColumn(name = "sts", referencedColumnName = "TID")
    @ManyToOne
    private Sts sts;
    @JoinColumn(name = "c_byy", referencedColumnName = "tid")
    @ManyToOne
    private Prle cByy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private Collection<BaLottoEntry> baLottoEntryCollection;

    public BaLottoMember() {
    }

    public BaLottoMember(Integer id) {
        this.id = id;
    }

    public BaLottoMember(Integer id, String mobileNumber, Date created) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getAgentID() {
        return agentID;
    }

    public void setAgentID(Integer agentID) {
        this.agentID = agentID;
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

    @XmlTransient
    public Collection<BaLottoEntry> getBaLottoEntryCollection() {
        return baLottoEntryCollection;
    }

    public void setBaLottoEntryCollection(Collection<BaLottoEntry> baLottoEntryCollection) {
        this.baLottoEntryCollection = baLottoEntryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BaLottoMember)) {
            return false;
        }
        BaLottoMember other = (BaLottoMember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.BaLottoMember[ id=" + id + " ]";
    }
    
}
