/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbc.arsenallotto.utilities;

import com.bbc.arsenallotto.jsonrequest.OGameData;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Soul
 */
@XmlRootElement(name = "balotto")
public class BaLottoWsRequest {

    private String mobile;
    private String firstname;
    private String lastname;
    private String dob;
    private String sex;
    private String email;
    private String username;
    private String password;
    private String requestcode;
    private String amount;
    private String reference;
    private int channelid;
    private int gameId;
    private OGameData oGameData;

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

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the requestcode
     */
    public String getRequestcode() {
        return requestcode;
    }

    /**
     * @param requestcode the requestcode to set
     */
    public void setRequestcode(String requestcode) {
        this.requestcode = requestcode;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return the channelid
     */
    public int getChannelid() {
        return channelid;
    }

    /**
     * @param channelid the channelid to set
     */
    public void setChannelid(int channelid) {
        this.channelid = channelid;
    }

    /**
     * @return the oGameData
     */
    public OGameData getOGameData() {
        return oGameData;
    }

    /**
     * @param oGameData the oGameData to set
     */
    public void setOGameData(OGameData oGameData) {
        this.oGameData = oGameData;
    }

    /**
     * @return the gameId
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

}
