/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.jsonresponse;

/**
 *
 * @author Soul
 */
public class Item {
    
    private String sCustomerAction;
    private String iCustomerId;
    private String sTransactionRef;
    private String iOrderId;
    private ABookingRefs aBookingRefs;

    /**
     * @return the iCustomerId
     */
    public String getiCustomerId() {
        return iCustomerId;
    }

    /**
     * @param iCustomerId the iCustomerId to set
     */
    public void setiCustomerId(String iCustomerId) {
        this.iCustomerId = iCustomerId;
    }

    /**
     * @return the sCustomerAction
     */
    public String getsCustomerAction() {
        return sCustomerAction;
    }

    /**
     * @param sCustomerAction the sCustomerAction to set
     */
    public void setsCustomerAction(String sCustomerAction) {
        this.sCustomerAction = sCustomerAction;
    }

    /**
     * @return the sTransactionRef
     */
    public String getsTransactionRef() {
        return sTransactionRef;
    }

    /**
     * @param sTransactionRef the sTransactionRef to set
     */
    public void setsTransactionRef(String sTransactionRef) {
        this.sTransactionRef = sTransactionRef;
    }

    /**
     * @return the iOrderId
     */
    public String getiOrderId() {
        return iOrderId;
    }

    /**
     * @param iOrderId the iOrderId to set
     */
    public void setiOrderId(String iOrderId) {
        this.iOrderId = iOrderId;
    }

    /**
     * @return the aBookingRefs
     */
    public ABookingRefs getaBookingRefs() {
        return aBookingRefs;
    }

    /**
     * @param aBookingRefs the aBookingRefs to set
     */
    public void setaBookingRefs(ABookingRefs aBookingRefs) {
        this.aBookingRefs = aBookingRefs;
    }
    
}
