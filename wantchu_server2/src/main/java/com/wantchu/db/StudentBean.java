/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wantchu.db.*;

/**
 *
 * @author dltmd
 */
public class StudentBean {
    String id;
    String name;
    String deptName;
    int totCred;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getTotCred() {
        return totCred;
    }

    public void setTotCred(int totCred) {
        this.totCred = totCred;
    }
    
    
}
