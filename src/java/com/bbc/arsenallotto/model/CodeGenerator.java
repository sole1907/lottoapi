/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbc.arsenallotto.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Noble
 */
@Entity
@Table(name = "code_generator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CodeGenerator.findAll", query = "SELECT c FROM CodeGenerator c"),
    @NamedQuery(name = "CodeGenerator.findByTid", query = "SELECT c FROM CodeGenerator c WHERE c.tid = :tid"),
    @NamedQuery(name = "CodeGenerator.findByCodeKey", query = "SELECT c FROM CodeGenerator c WHERE c.codeKey = :codeKey"),
    @NamedQuery(name = "CodeGenerator.findByValueNow", query = "SELECT c FROM CodeGenerator c WHERE c.valueNow = :valueNow")})
public class CodeGenerator implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tid")
    private Long tid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "code_key")
    private String codeKey;
    @Column(name = "value_now")
    private BigInteger valueNow;

    public CodeGenerator() {
    }

    public CodeGenerator(Long tid) {
        this.tid = tid;
    }

    public CodeGenerator(Long tid, String codeKey) {
        this.tid = tid;
        this.codeKey = codeKey;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey;
    }

    public BigInteger getValueNow() {
        return valueNow;
    }

    public void setValueNow(BigInteger valueNow) {
        this.valueNow = valueNow;
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
        if (!(object instanceof CodeGenerator)) {
            return false;
        }
        CodeGenerator other = (CodeGenerator) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.CodeGenerator[ tid=" + tid + " ]";
    }
    
}
