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
public class OCustomer {

    private String sFirstName;
    private String sLastName;
    private String sEmail;
    private String sPhone;

    /**
     * @return the sFirstName
     */
    public String getsFirstName() {
        return sFirstName;
    }

    /**
     * @param sFirstName the sFirstName to set
     */
    public void setsFirstName(String sFirstName) {
        this.sFirstName = sFirstName;
    }

    /**
     * @return the sLastName
     */
    public String getsLastName() {
        return sLastName;
    }

    /**
     * @param sLastName the sLastName to set
     */
    public void setsLastName(String sLastName) {
        this.sLastName = sLastName;
    }

    /**
     * @return the sEmail
     */
    public String getsEmail() {
        return sEmail;
    }

    /**
     * @param sEmail the sEmail to set
     */
    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    /**
     * @return the sPhone
     */
    public String getsPhone() {
        return sPhone;
    }

    /**
     * @param sPhone the sPhone to set
     */
    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }
}
