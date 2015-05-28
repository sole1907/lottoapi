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
public class OrderItem {

    private int iGameID;
    private OGameData oGameData;

    /**
     * @return the iGameID
     */
    public int getiGameID() {
        return iGameID;
    }

    /**
     * @param iGameID the iGameID to set
     */
    public void setiGameID(int iGameID) {
        this.iGameID = iGameID;
    }

    /**
     * @return the oGameData
     */
    public OGameData getoGameData() {
        return oGameData;
    }

    /**
     * @param oGameData the oGameData to set
     */
    public void setoGameData(OGameData oGameData) {
        this.oGameData = oGameData;
    }
}
