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
@Table(name = "ba_lotto_agent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BaLottoAgent.findAll", query = "SELECT b FROM BaLottoAgent b"),
    @NamedQuery(name = "BaLottoAgent.findById", query = "SELECT b FROM BaLottoAgent b WHERE b.id = :id"),
    @NamedQuery(name = "BaLottoAgent.findByUsername", query = "SELECT b FROM BaLottoAgent b WHERE b.username = :username"),
    @NamedQuery(name = "BaLottoAgent.findByPassword", query = "SELECT b FROM BaLottoAgent b WHERE b.password = :password"),
    @NamedQuery(name = "BaLottoAgent.findByIpAddress", query = "SELECT b FROM BaLottoAgent b WHERE b.ipAddress = :ipAddress"),
    @NamedQuery(name = "BaLottoAgent.findByValidateIp", query = "SELECT b FROM BaLottoAgent b WHERE b.validateIp = :validateIp"),
    @NamedQuery(name = "BaLottoAgent.findByEmailAddress", query = "SELECT b FROM BaLottoAgent b WHERE b.emailAddress = :emailAddress"),
    @NamedQuery(name = "BaLottoAgent.findByAgentName", query = "SELECT b FROM BaLottoAgent b WHERE b.agentName = :agentName"),
    @NamedQuery(name = "BaLottoAgent.findByCreated", query = "SELECT b FROM BaLottoAgent b WHERE b.created = :created"),
    @NamedQuery(name = "BaLottoAgent.findByModified", query = "SELECT b FROM BaLottoAgent b WHERE b.modified = :modified"),
    @NamedQuery(name = "BaLottoAgent.findByCode", query = "SELECT b FROM BaLottoAgent b WHERE b.code = :code")})
public class BaLottoAgent implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agentId")
    private Collection<BaLottoEntry> baLottoEntryCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ip_address")
    private String ipAddress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "validate_ip")
    private boolean validateIp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email_address")
    private String emailAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "agent_name")
    private String agentName;
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
    @JoinColumn(name = "sts", referencedColumnName = "TID")
    @ManyToOne
    private Sts sts;
    @JoinColumn(name = "c_byy", referencedColumnName = "tid")
    @ManyToOne
    private Prle cByy;

    public BaLottoAgent() {
    }

    public BaLottoAgent(Integer id) {
        this.id = id;
    }

    public BaLottoAgent(Integer id, String username, String password, String ipAddress, boolean validateIp, String emailAddress, String agentName, Date created) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.ipAddress = ipAddress;
        this.validateIp = validateIp;
        this.emailAddress = emailAddress;
        this.agentName = agentName;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean getValidateIp() {
        return validateIp;
    }

    public void setValidateIp(boolean validateIp) {
        this.validateIp = validateIp;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BaLottoAgent)) {
            return false;
        }
        BaLottoAgent other = (BaLottoAgent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.BaLottoAgent[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<BaLottoEntry> getBaLottoEntryCollection() {
        return baLottoEntryCollection;
    }

    public void setBaLottoEntryCollection(Collection<BaLottoEntry> baLottoEntryCollection) {
        this.baLottoEntryCollection = baLottoEntryCollection;
    }
    
}
