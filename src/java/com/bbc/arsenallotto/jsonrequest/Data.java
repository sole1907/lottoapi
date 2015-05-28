/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.jsonrequest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Noble
 */
@XmlRootElement
public class Data {

    private AItems aItems;

    /**
     * @return the aItems
     */
    public AItems getaItems() {
        return aItems;
    }

    /**
     * @param aItems the aItems to set
     */
    public void setaItems(AItems aItems) {
        this.aItems = aItems;
    }
}
