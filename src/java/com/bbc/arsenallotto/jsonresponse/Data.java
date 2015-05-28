/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.jsonresponse;

import com.bbc.arsenallotto.jsonrequest.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Noble
 */
@XmlRootElement
public class Data {

    private String aParams;
    private AMessages aMessages;
    private AOrderResponse aOrderResponse;
    private String messages;

    /**
     * @return the aParams
     */
    public String getaParams() {
        return aParams;
    }

    /**
     * @param aParams the aParams to set
     */
    public void setaParams(String aParams) {
        this.aParams = aParams;
    }

    /**
     * @return the aMessages
     */
    public AMessages getaMessages() {
        return aMessages;
    }

    /**
     * @param aMessages the aMessages to set
     */
    public void setaMessages(AMessages aMessages) {
        this.aMessages = aMessages;
    }

    /**
     * @return the aOrderResponse
     */
    public AOrderResponse getaOrderResponse() {
        return aOrderResponse;
    }

    /**
     * @param aOrderResponse the aOrderResponse to set
     */
    public void setaOrderResponse(AOrderResponse aOrderResponse) {
        this.aOrderResponse = aOrderResponse;
    }

    /**
     * @return the messages
     */
    public String getMessages() {
        return messages;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(String messages) {
        this.messages = messages;
    }

}
