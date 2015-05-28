/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbc.arsenallotto.jsonrequest;

import java.util.List;

/**
 *
 * @author Noble
 */
public class OGameData {
    private int playDuration;
    private List<Integer> day;
    private List<Boards> boards;

    /**
     * @return the playDuration
     */
    public int getPlayDuration() {
        return playDuration;
    }

    /**
     * @param playDuration the playDuration to set
     */
    public void setPlayDuration(int playDuration) {
        this.playDuration = playDuration;
    }

    /**
     * @return the day
     */
    public List<Integer> getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(List<Integer> day) {
        this.day = day;
    }

    /**
     * @return the boards
     */
    public List<Boards> getBoards() {
        return boards;
    }

    /**
     * @param boards the boards to set
     */
    public void setBoards(List<Boards> boards) {
        this.boards = boards;
    }
}
