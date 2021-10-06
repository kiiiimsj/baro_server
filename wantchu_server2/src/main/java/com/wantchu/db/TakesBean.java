
package com.wantchu.db.*;

/**
 *
 * @author dltmd
 */
public class TakesBean {
    String Id;
    String courseId;
    String secId;
    String semester;
    int year;
    String grade;
    StudentBean sb;

    public StudentBean getSb() {
        return sb;
    }

    public void setSb(StudentBean sb) {
        this.sb = sb;
    }
    
    

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    
}
