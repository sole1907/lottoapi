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
 * @author Soul
 */
@Entity
@Table(name = "ba_lotto_notifications")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BaLottoNotifications.findAll", query = "SELECT b FROM BaLottoNotifications b"),
    @NamedQuery(name = "BaLottoNotifications.findById", query = "SELECT b FROM BaLottoNotifications b WHERE b.id = :id"),
    @NamedQuery(name = "BaLottoNotifications.findByGameId", query = "SELECT b FROM BaLottoNotifications b WHERE b.gameId = :gameId"),
    @NamedQuery(name = "BaLottoNotifications.findByDrawDate", query = "SELECT b FROM BaLottoNotifications b WHERE b.drawDate = :drawDate"),
    @NamedQuery(name = "BaLottoNotifications.findByProcessed", query = "SELECT b FROM BaLottoNotifications b WHERE b.processed = :processed"),
    @NamedQuery(name = "BaLottoNotifications.findDuplicate", query = "SELECT b FROM BaLottoNotifications b WHERE b.gameId = :gameId and b.drawDate = :drawDate"),
    @NamedQuery(name = "BaLottoNotifications.findByCreated", query = "SELECT b FROM BaLottoNotifications b WHERE b.created = :created")})
public class BaLottoNotifications implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "game_id")
    private short gameId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "draw_date")
    private String drawDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "processed")
    private boolean processed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public BaLottoNotifications() {
    }

    public BaLottoNotifications(Integer id) {
        this.id = id;
    }

    public BaLottoNotifications(Integer id, short gameId, String drawDate, boolean processed, Date created) {
        this.id = id;
        this.gameId = gameId;
        this.drawDate = drawDate;
        this.processed = processed;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getGameId() {
        return gameId;
    }

    public void setGameId(short gameId) {
        this.gameId = gameId;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }

    public boolean getProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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
        if (!(object instanceof BaLottoNotifications)) {
            return false;
        }
        BaLottoNotifications other = (BaLottoNotifications) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.BaLottoNotifications[ id=" + id + " ]";
    }
    
}
