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

/**
 *
 * @author dltmd
 */
public class StudentDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql;
    MariaDBCon dbc = new MariaDBCon();

    
   public List makeListAllStudent(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                StudentBean tb = new StudentBean();
                tb.setId(rs.getString(1));
                tb.setName(rs.getString(2));
                tb.setDeptName(rs.getString(3));
                tb.setTotCred(rs.getInt(4));
                list.add(tb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
    }
    public List makeListAllStudentIdName(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                StudentBean tb = new StudentBean();
                tb.setId(rs.getString(1));
                tb.setName(rs.getString(2));
                list.add(tb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
    }
   
    public StudentTitleBean makeStudentTitle(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        StudentTitleBean ttb = new StudentTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            ttb.setIdTitle(rsmd.getColumnName(1));
            ttb.setNameTitle(rsmd.getColumnName(2));
            ttb.setDeptNameTitle(rsmd.getColumnName(3));
            ttb.setTotCredTitle(rsmd.getColumnName(4));
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return ttb;
    }
    public StudentTitleBean makeStudentTitleIdName(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        StudentTitleBean ttb = new StudentTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            ttb.setIdTitle(rsmd.getColumnName(1));
            ttb.setNameTitle(rsmd.getColumnName(2));
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return ttb;
    }
    public int insertAStudent(StudentBean tb){
        int n=0;
        String sql="insert into Student values(?,?,?,?)";
        try {
            pstmt = dbc.getPstmt(sql);
            pstmt.setString(1, tb.getId());
            pstmt.setString(2, tb.getName());
            pstmt.setString(3, tb.getDeptName());
            pstmt.setInt(4, tb.getTotCred());

            n = pstmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println("SQL insert SQLState: "+ex.getSQLState());
        }
        return n;
    }
    public int insertAStudent(String sql){
        int n=0;
        try {
            pstmt = dbc.getPstmt(sql);

            n = pstmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println("SQL insert SQLState: "+ex.getSQLState());
        }
        return n;
    }
}
