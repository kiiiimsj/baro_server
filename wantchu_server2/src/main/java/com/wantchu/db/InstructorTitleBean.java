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
public class InstructorTitleBean {
    String idTitle;
    String nameTitle;
    String deptNameTitle;
    String buildingNameTitle;
    String salaryTitle;
    TeachesTitleBean ttb;

    public TeachesTitleBean getTtb() {
        return ttb;
    }

    public void setTtb(TeachesTitleBean ttb) {
        this.ttb = ttb;
    }
    
    

    public String getBuildingNameTitle() {
        return buildingNameTitle;
    }

    public void setBuildingNameTitle(String buildingNameTitle) {
        this.buildingNameTitle = buildingNameTitle;
    }

    public String getIdTitle() {
        return idTitle;
    }

    public void setIdTitle(String idTitle) {
        this.idTitle = idTitle;
    }

    
    public String getNameTitle() {
        return nameTitle;
    }

    public void setNameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }

    public String getDeptNameTitle() {
        return deptNameTitle;
    }

    public void setDeptNameTitle(String deptNameTitle) {
        this.deptNameTitle = deptNameTitle;
    }

    public String getSalaryTitle() {
        return salaryTitle;
    }

    public void setSalaryTitle(String salaryTitle) {
        this.salaryTitle = salaryTitle;
    }
    
}
