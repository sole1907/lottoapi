/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.utilities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Soul
 */
@XmlRootElement(name = "balotto")
public class BaLottoWsResponse {
    private int responseCode;
    private String responseMessage;
    private String lottoReference;
    private String paymentReference;
    private String mobile;

    /**
     * @return the responseCode
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the responseMessage
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * @param responseMessage the responseMessage to set
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    /**
     * @return the lottoReference
     */
    public String getLottoReference() {
        return lottoReference;
    }

    /**
     * @param lottoReference the lottoReference to set
     */
    public void setLottoReference(String lottoReference) {
        this.lottoReference = lottoReference;
    }

    /**
     * @return the paymentReference
     */
    public String getPaymentReference() {
        return paymentReference;
    }

    /**
     * @param paymentReference the paymentReference to set
     */
    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
}
