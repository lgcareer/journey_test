package com.journey.demo.data.domain;

import java.io.Serializable;

/**
 * Created by qiaozhanwei on 18-8-13.
 */
public class Course implements Serializable {

    private int cno;
    private String cname;
    private int tno;

    public Course() {
    }
    public Course(int cno, String cname, int tno) {
        this.cno = cno;
        this.cname = cname;
        this.tno = tno;
    }

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getTno() {
        return tno;
    }

    public void setTno(int tno) {
        this.tno = tno;
    }
}