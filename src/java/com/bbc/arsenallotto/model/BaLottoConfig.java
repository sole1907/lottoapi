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
@Table(name = "ba_lotto_config")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BaLottoConfig.findAll", query = "SELECT b FROM BaLottoConfig b"),
    @NamedQuery(name = "BaLottoConfig.findById", query = "SELECT b FROM BaLottoConfig b WHERE b.id = :id"),
    @NamedQuery(name = "BaLottoConfig.findByParameterName", query = "SELECT b FROM BaLottoConfig b WHERE b.parameterName = :parameterName"),
    @NamedQuery(name = "BaLottoConfig.findByParameterValue", query = "SELECT b FROM BaLottoConfig b WHERE b.parameterValue = :parameterValue"),
    @NamedQuery(name = "BaLottoConfig.findByCreated", query = "SELECT b FROM BaLottoConfig b WHERE b.created = :created"),
    @NamedQuery(name = "BaLottoConfig.findByModified", query = "SELECT b FROM BaLottoConfig b WHERE b.modified = :modified"),
    @NamedQuery(name = "BaLottoConfig.findByAuthDate", query = "SELECT b FROM BaLottoConfig b WHERE b.authDate = :authDate")})
public class BaLottoConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "parameter_name")
    private String parameterName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "parameter_value")
    private String parameterValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @Column(name = "auth_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date authDate;
    @JoinColumn(name = "sts", referencedColumnName = "TID")
    @ManyToOne
    private Sts sts;
    @JoinColumn(name = "c_byy", referencedColumnName = "tid")
    @ManyToOne
    private Prle cByy;

    public BaLottoConfig() {
    }

    public BaLottoConfig(Integer id) {
        this.id = id;
    }

    public BaLottoConfig(Integer id, String parameterName, String parameterValue, Date created) {
        this.id = id;
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
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

    public Date getAuthDate() {
        return authDate;
    }

    public void setAuthDate(Date authDate) {
        this.authDate = authDate;
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
        if (!(object instanceof BaLottoConfig)) {
            return false;
        }
        BaLottoConfig other = (BaLottoConfig) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.BaLottoConfig[ id=" + id + " ]";
    }
    
}
