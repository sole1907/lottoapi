/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Soul
 */
@Entity
@Table(name = "ba_lotto_entry_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BaLottoEntryDetails.findAll", query = "SELECT b FROM BaLottoEntryDetails b"),
    @NamedQuery(name = "BaLottoEntryDetails.findById", query = "SELECT b FROM BaLottoEntryDetails b WHERE b.id = :id"),
    @NamedQuery(name = "BaLottoEntryDetails.findByEntry1", query = "SELECT b FROM BaLottoEntryDetails b WHERE b.entry1 = :entry1"),
    @NamedQuery(name = "BaLottoEntryDetails.findByEntry2", query = "SELECT b FROM BaLottoEntryDetails b WHERE b.entry2 = :entry2"),
    @NamedQuery(name = "BaLottoEntryDetails.findByEntry3", query = "SELECT b FROM BaLottoEntryDetails b WHERE b.entry3 = :entry3"),
    @NamedQuery(name = "BaLottoEntryDetails.findByEntry4", query = "SELECT b FROM BaLottoEntryDetails b WHERE b.entry4 = :entry4"),
    @NamedQuery(name = "BaLottoEntryDetails.findByEntry5", query = "SELECT b FROM BaLottoEntryDetails b WHERE b.entry5 = :entry5"),
    @NamedQuery(name = "BaLottoEntryDetails.findByEntry6", query = "SELECT b FROM BaLottoEntryDetails b WHERE b.entry6 = :entry6"),
    @NamedQuery(name = "BaLottoEntryDetails.findByLegend", query = "SELECT b FROM BaLottoEntryDetails b WHERE b.legend = :legend")})
public class BaLottoEntryDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entry1")
    private int entry1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entry2")
    private int entry2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entry3")
    private int entry3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entry4")
    private int entry4;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entry5")
    private int entry5;
    @Column(name = "entry6")
    private Integer entry6;
    @Column(name = "legend")
    private Integer legend;
    @JoinColumn(name = "entry_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BaLottoEntry entryId;

    public BaLottoEntryDetails() {
    }

    public BaLottoEntryDetails(Integer id) {
        this.id = id;
    }

    public BaLottoEntryDetails(Integer id, int entry1, int entry2, int entry3, int entry4, int entry5) {
        this.id = id;
        this.entry1 = entry1;
        this.entry2 = entry2;
        this.entry3 = entry3;
        this.entry4 = entry4;
        this.entry5 = entry5;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEntry1() {
        return entry1;
    }

    public void setEntry1(int entry1) {
        this.entry1 = entry1;
    }

    public int getEntry2() {
        return entry2;
    }

    public void setEntry2(int entry2) {
        this.entry2 = entry2;
    }

    public int getEntry3() {
        return entry3;
    }

    public void setEntry3(int entry3) {
        this.entry3 = entry3;
    }

    public int getEntry4() {
        return entry4;
    }

    public void setEntry4(int entry4) {
        this.entry4 = entry4;
    }

    public int getEntry5() {
        return entry5;
    }

    public void setEntry5(int entry5) {
        this.entry5 = entry5;
    }

    public Integer getEntry6() {
        return entry6;
    }

    public void setEntry6(Integer entry6) {
        this.entry6 = entry6;
    }

    public Integer getLegend() {
        return legend;
    }

    public void setLegend(Integer legend) {
        this.legend = legend;
    }

    public BaLottoEntry getEntryId() {
        return entryId;
    }

    public void setEntryId(BaLottoEntry entryId) {
        this.entryId = entryId;
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
        if (!(object instanceof BaLottoEntryDetails)) {
            return false;
        }
        BaLottoEntryDetails other = (BaLottoEntryDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.BaLottoEntryDetails[ id=" + id + " ]";
    }
    
}
