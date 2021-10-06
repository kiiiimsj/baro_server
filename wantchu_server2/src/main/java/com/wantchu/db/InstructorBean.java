package com.wantchu.db.*;

/**
 * @프로그램 : StudentBean.java
 * @저자 : 학번: 2016210162 이름: 이승재
 * @날짜 : 2021.09.30
 */
public class InstructorBean {
    String id;
    String name;
    String deptName;
    String buildingName;
    double salary;
    TeachesBean tb;

    public TeachesBean getTb() {
        return tb;
    }

    public void setTb(TeachesBean tb) {
        this.tb = tb;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    
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

    public void setDeptName(String dept_name) {
        this.deptName = dept_name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    
    
    
}
