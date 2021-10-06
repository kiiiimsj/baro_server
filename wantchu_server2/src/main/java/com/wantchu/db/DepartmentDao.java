/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wantchu.db.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql;
    MariaDBCon dbc = new MariaDBCon();

   public List makeListAllDepartment(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                DepartmentBean tb = new DepartmentBean();
                tb.setDeptName(rs.getString(1));
                tb.setBuilding(rs.getString(2));
                tb.setBudget(rs.getInt(3));
                list.add(tb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
    }
   
    public DepartmentTitleBean makeDepartmentTitle(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        DepartmentTitleBean ttb = new DepartmentTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            ttb.setDeptNameTitle(rsmd.getColumnName(1));
            ttb.setBuildingTitle(rsmd.getColumnName(2));
            ttb.setBudgetTitle(rsmd.getColumnName(3));
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return ttb;
    }
    public int insertADepartment(DepartmentBean tb){
        int n=0;
        String sql="insert into department values(?,?,?)";
        try {
            pstmt = dbc.getPstmt(sql);
            pstmt.setString(1, tb.getDeptName());
            pstmt.setString(2, tb.getBuilding());
            pstmt.setInt(3, tb.getBudget());

            n = pstmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println("SQL insert SQLState: "+ex.getSQLState());
        }
        return n;
    }

    public String getTitleOne(String sql) {
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            return rsmd.getColumnName(1);
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return "";
    }

    public int getCount(String sql) {
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return 0;
    }
}
