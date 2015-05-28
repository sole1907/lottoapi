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
@Table(name = "ba_lotto_response")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BaLottoResponse.findAll", query = "SELECT b FROM BaLottoResponse b"),
    @NamedQuery(name = "BaLottoResponse.findById", query = "SELECT b FROM BaLottoResponse b WHERE b.id = :id"),
    @NamedQuery(name = "BaLottoResponse.findByResponseCode", query = "SELECT b FROM BaLottoResponse b WHERE b.responseCode = :responseCode"),
    @NamedQuery(name = "BaLottoResponse.findByResponseMessage", query = "SELECT b FROM BaLottoResponse b WHERE b.responseMessage = :responseMessage"),
    @NamedQuery(name = "BaLottoResponse.findByCreated", query = "SELECT b FROM BaLottoResponse b WHERE b.created = :created"),
    @NamedQuery(name = "BaLottoResponse.findByModified", query = "SELECT b FROM BaLottoResponse b WHERE b.modified = :modified"),
    @NamedQuery(name = "BaLottoResponse.findByResponseDesc", query = "SELECT b FROM BaLottoResponse b WHERE b.responseDesc = :responseDesc")})
public class BaLottoResponse implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "token")
    private String token;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "response_code")
    private int responseCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "response_message")
    private String responseMessage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @Size(max = 25)
    @Column(name = "response_desc")
    private String responseDesc;
    @JoinColumn(name = "c_byy", referencedColumnName = "tid")
    @ManyToOne
    private Prle cByy;

    public BaLottoResponse() {
    }

    public BaLottoResponse(Integer id) {
        this.id = id;
    }

    public BaLottoResponse(Integer id, int responseCode, String responseMessage, Date created) {
        this.id = id;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
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

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
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
        if (!(object instanceof BaLottoResponse)) {
            return false;
        }
        BaLottoResponse other = (BaLottoResponse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.BaLottoResponse[ id=" + id + " ]";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
