/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wantchu.db;

/**
 *
 * @author dltmd
 */
public class TakesTitleBean {
    String idTitle;
    String courseIdTitle;
    String secIdTitle;
    String semesterTitle;
    String yearTitle;
    String gradeTitle;
    StudentTitleBean stb;

    public StudentTitleBean getStb() {
        return stb;
    }

    public void setStb(StudentTitleBean stb) {
        this.stb = stb;
    }
    
    

    public String getIdTitle() {
        return idTitle;
    }

    public void setIdTitle(String idTitle) {
        this.idTitle = idTitle;
    }

    public String getCourseIdTitle() {
        return courseIdTitle;
    }

    public void setCourseIdTitle(String courseIdTitle) {
        this.courseIdTitle = courseIdTitle;
    }

    public String getSecIdTitle() {
        return secIdTitle;
    }

    public void setSecIdTitle(String secIdTitle) {
        this.secIdTitle = secIdTitle;
    }

    public String getSemesterTitle() {
        return semesterTitle;
    }

    public void setSemesterTitle(String semesterTitle) {
        this.semesterTitle = semesterTitle;
    }

    public String getYearTitle() {
        return yearTitle;
    }

    public void setYearTitle(String yearTitle) {
        this.yearTitle = yearTitle;
    }

    public String getGradeTitle() {
        return gradeTitle;
    }

    public void setGradeTitle(String gradeTitle) {
        this.gradeTitle = gradeTitle;
    }
    
    
}
