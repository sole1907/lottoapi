package com.bbc.arsenallotto.jsonrequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noble
 */
public class AItems {
    
    private OOrder oOrder;
    private OCustomer oCustomer;
    private OTransaction oTransaction;

    /**
     * @return the oOrder
     */
    public OOrder getoOrder() {
        return oOrder;
    }

    /**
     * @param oOrder the oOrder to set
     */
    public void setoOrder(OOrder oOrder) {
        this.oOrder = oOrder;
    }

    /**
     * @return the oCustomer
     */
    public OCustomer getoCustomer() {
        return oCustomer;
    }

    /**
     * @param oCustomer the oCustomer to set
     */
    public void setoCustomer(OCustomer oCustomer) {
        this.oCustomer = oCustomer;
    }

    /**
     * @return the oTransaction
     */
    public OTransaction getoTransaction() {
        return oTransaction;
    }

    /**
     * @param oTransaction the oTransaction to set
     */
    public void setoTransaction(OTransaction oTransaction) {
        this.oTransaction = oTransaction;
    }
    
}
