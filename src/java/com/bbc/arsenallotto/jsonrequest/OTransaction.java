/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.jsonrequest;

/**
 *
 * @author Noble
 */
public class OTransaction {

    private String sRef;
    private String bApproved;
    private float fAmount;
    private String sDetails;

    /**
     * @return the sRef
     */
    public String getsRef() {
        return sRef;
    }

    /**
     * @param sRef the sRef to set
     */
    public void setsRef(String sRef) {
        this.sRef = sRef;
    }

    /**
     * @return the bApproved
     */
    public String getbApproved() {
        return bApproved;
    }

    /**
     * @param bApproved the bApproved to set
     */
    public void setbApproved(String bApproved) {
        this.bApproved = bApproved;
    }

    /**
     * @return the fAmount
     */
    public float getfAmount() {
        return fAmount;
    }

    /**
     * @param fAmount the fAmount to set
     */
    public void setfAmount(float fAmount) {
        this.fAmount = fAmount;
    }

    /**
     * @return the sDetails
     */
    public String getsDetails() {
        return sDetails;
    }

    /**
     * @param sDetails the sDetails to set
     */
    public void setsDetails(String sDetails) {
        this.sDetails = sDetails;
    }
}
