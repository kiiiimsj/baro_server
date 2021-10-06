package com.wantchu.db.*;

/**
 * @프로그램 : StudentBean.java
 * @저자 : 학번: 2016210162 이름: 이승재
 * @날짜 : 2021.10.01
 */
public class TeachesBean {
    String id;
    String courseId;
    String secId;
    String semester;
    int year;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
}
