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
@Table(name = "ba_lotto_channel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BaLottoChannel.findAll", query = "SELECT b FROM BaLottoChannel b"),
    @NamedQuery(name = "BaLottoChannel.findById", query = "SELECT b FROM BaLottoChannel b WHERE b.id = :id"),
    @NamedQuery(name = "BaLottoChannel.findByChannel", query = "SELECT b FROM BaLottoChannel b WHERE b.channel = :channel"),
    @NamedQuery(name = "BaLottoChannel.findByCreated", query = "SELECT b FROM BaLottoChannel b WHERE b.created = :created"),
    @NamedQuery(name = "BaLottoChannel.findByModified", query = "SELECT b FROM BaLottoChannel b WHERE b.modified = :modified"),
    @NamedQuery(name = "BaLottoChannel.findByCode", query = "SELECT b FROM BaLottoChannel b WHERE b.code = :code"),
    @NamedQuery(name = "BaLottoChannel.findByAuthDate", query = "SELECT b FROM BaLottoChannel b WHERE b.authDate = :authDate")})
public class BaLottoChannel implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "channelId")
    private Collection<BaLottoEntry> baLottoEntryCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "channel")
    private String channel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "code")
    private String code;
    @Column(name = "auth_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date authDate;
    @JoinColumn(name = "sts", referencedColumnName = "TID")
    @ManyToOne
    private Sts sts;
    @JoinColumn(name = "c_byy", referencedColumnName = "tid")
    @ManyToOne
    private Prle cByy;

    public BaLottoChannel() {
    }

    public BaLottoChannel(Integer id) {
        this.id = id;
    }

    public BaLottoChannel(Integer id, String channel, Date created, String code) {
        this.id = id;
        this.channel = channel;
        this.created = created;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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
        if (!(object instanceof BaLottoChannel)) {
            return false;
        }
        BaLottoChannel other = (BaLottoChannel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.BaLottoChannel[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<BaLottoEntry> getBaLottoEntryCollection() {
        return baLottoEntryCollection;
    }

    public void setBaLottoEntryCollection(Collection<BaLottoEntry> baLottoEntryCollection) {
        this.baLottoEntryCollection = baLottoEntryCollection;
    }
    
}
