package com.journey.demo.data.domain;

import java.io.Serializable;

/**
 * Created by qiaozhanwei on 18-8-13.
 */
public class Student implements Serializable{

    private int sno;
    private String sname;
    private String ssex;

    public Student() {
    }

    public Student(int sno, String sname, String ssex) {
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }
}