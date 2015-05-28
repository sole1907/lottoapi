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
public class OOrder {
    
    private String dCreatedAt;
    private float fTotal;
    private String iCurrency;
    private float fBalance;
    private AOrderItems aOrderItems;

    /**
     * @return the dCreatedAt
     */
    public String getdCreatedAt() {
        return dCreatedAt;
    }

    /**
     * @param dCreatedAt the dCreatedAt to set
     */
    public void setdCreatedAt(String dCreatedAt) {
        this.dCreatedAt = dCreatedAt;
    }

    /**
     * @return the fTotal
     */
    public float getfTotal() {
        return fTotal;
    }

    /**
     * @param fTotal the fTotal to set
     */
    public void setfTotal(float fTotal) {
        this.fTotal = fTotal;
    }

    /**
     * @return the iCurrency
     */
    public String getiCurrency() {
        return iCurrency;
    }

    /**
     * @param iCurrency the iCurrency to set
     */
    public void setiCurrency(String iCurrency) {
        this.iCurrency = iCurrency;
    }

    /**
     * @return the fBalance
     */
    public float getfBalance() {
        return fBalance;
    }

    /**
     * @param fBalance the fBalance to set
     */
    public void setfBalance(float fBalance) {
        this.fBalance = fBalance;
    }

    /**
     * @return the aOrderItems
     */
    public AOrderItems getaOrderItems() {
        return aOrderItems;
    }

    /**
     * @param aOrderItems the aOrderItems to set
     */
    public void setaOrderItems(AOrderItems aOrderItems) {
        this.aOrderItems = aOrderItems;
    }
    
}
