/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ba_lotto_entry")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BaLottoEntry.findAll", query = "SELECT b FROM BaLottoEntry b"),
    @NamedQuery(name = "BaLottoEntry.findById", query = "SELECT b FROM BaLottoEntry b WHERE b.id = :id"),
    @NamedQuery(name = "BaLottoEntry.findByChannelId", query = "SELECT b FROM BaLottoEntry b WHERE b.channelId = :channelId"),
    @NamedQuery(name = "BaLottoEntry.findByAgentId", query = "SELECT b FROM BaLottoEntry b WHERE b.agentId = :agentId"),
    @NamedQuery(name = "BaLottoEntry.findByPaymentReference", query = "SELECT b FROM BaLottoEntry b WHERE b.paymentReference = :paymentReference"),
    @NamedQuery(name = "BaLottoEntry.findByLottoReference", query = "SELECT b FROM BaLottoEntry b WHERE b.lottoReference = :lottoReference"),
    @NamedQuery(name = "BaLottoEntry.findByAmount", query = "SELECT b FROM BaLottoEntry b WHERE b.amount = :amount"),
    @NamedQuery(name = "BaLottoEntry.findByEntryDate", query = "SELECT b FROM BaLottoEntry b WHERE b.entryDate = :entryDate"),
    @NamedQuery(name = "BaLottoEntry.findByResponseCode", query = "SELECT b FROM BaLottoEntry b WHERE b.responseCode = :responseCode"),
    @NamedQuery(name = "BaLottoEntry.findByResponseMessage", query = "SELECT b FROM BaLottoEntry b WHERE b.responseMessage = :responseMessage"),
    @NamedQuery(name = "BaLottoEntry.findByResponseDate", query = "SELECT b FROM BaLottoEntry b WHERE b.responseDate = :responseDate"),
    @NamedQuery(name = "BaLottoEntry.findAgentReference", query = "SELECT b FROM BaLottoEntry b WHERE b.agentId.id = :agentId and b.paymentReference = :reference"),
    @NamedQuery(name = "BaLottoEntry.findByCheckDigit", query = "SELECT b FROM BaLottoEntry b WHERE b.checkDigit = :checkDigit")})
public class BaLottoEntry implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "game_id")
    private int gameId;
    @Size(max = 50)
    @Column(name = "action")
    private String action;
    @Size(max = 50)
    @Column(name = "order_id")
    private String orderId;
    @Size(max = 50)
    @Column(name = "booking_ref")
    private String bookingRef;
    @Column(name = "attempts")
    private Short attempts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duration")
    private short duration;

    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BaLottoAgent agentId;
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BaLottoChannel channelId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "days")
    private short days;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entryId")
    private Collection<BaLottoEntryDetails> baLottoEntryDetailsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "payment_reference")
    private String paymentReference;
    @Size(max = 20)
    @Column(name = "lotto_reference")
    private String lottoReference;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;
    @Column(name = "response_code")
    private Integer responseCode;
    @Size(max = 200)
    @Column(name = "response_message")
    private String responseMessage;
    @Column(name = "response_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date responseDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "check_digit")
    private String checkDigit;
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BaLottoMember memberId;
    @JoinColumn(name = "status", referencedColumnName = "TID")
    @ManyToOne
    private Sts status;

    public BaLottoEntry() {
    }

    public BaLottoEntry(Integer id) {
        this.id = id;
    }

    public BaLottoEntry(Integer id, String paymentReference, BigDecimal amount, Date entryDate, String checkDigit) {
        this.id = id;
        this.paymentReference = paymentReference;
        this.amount = amount;
        this.entryDate = entryDate;
        this.checkDigit = checkDigit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getLottoReference() {
        return lottoReference;
    }

    public void setLottoReference(String lottoReference) {
        this.lottoReference = lottoReference;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public String getCheckDigit() {
        return checkDigit;
    }

    public void setCheckDigit(String checkDigit) {
        this.checkDigit = checkDigit;
    }

    public BaLottoMember getMemberId() {
        return memberId;
    }

    public void setMemberId(BaLottoMember memberId) {
        this.memberId = memberId;
    }

    public Sts getStatus() {
        return status;
    }

    public void setStatus(Sts status) {
        this.status = status;
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
        if (!(object instanceof BaLottoEntry)) {
            return false;
        }
        BaLottoEntry other = (BaLottoEntry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bbc.arsenallotto.model.BaLottoEntry[ id=" + id + " ]";
    }

    public short getDays() {
        return days;
    }

    public void setDays(short days) {
        this.days = days;
    }

    @XmlTransient
    public Collection<BaLottoEntryDetails> getBaLottoEntryDetailsCollection() {
        return baLottoEntryDetailsCollection;
    }

    public void setBaLottoEntryDetailsCollection(Collection<BaLottoEntryDetails> baLottoEntryDetailsCollection) {
        this.baLottoEntryDetailsCollection = baLottoEntryDetailsCollection;
    }

    public BaLottoAgent getAgentId() {
        return agentId;
    }

    public void setAgentId(BaLottoAgent agentId) {
        this.agentId = agentId;
    }

    public BaLottoChannel getChannelId() {
        return channelId;
    }

    public void setChannelId(BaLottoChannel channelId) {
        this.channelId = channelId;
    }

    public short getDuration() {
        return duration;
    }

    public void setDuration(short duration) {
        this.duration = duration;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBookingRef() {
        return bookingRef;
    }

    public void setBookingRef(String bookingRef) {
        this.bookingRef = bookingRef;
    }

    public Short getAttempts() {
        return attempts;
    }

    public void setAttempts(Short attempts) {
        this.attempts = attempts;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

}
