package com.journey.demo.data.domain;

import java.io.Serializable;

/**
 * Created by qiaozhanwei on 18-8-13.
 */
public class Sc implements Serializable {

    private int sno;
    private int cno;
    private int score;

    public Sc() {
    }

    public Sc(int sno, int cno, int score) {
        this.sno = sno;
        this.cno = cno;
        this.score = score;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}