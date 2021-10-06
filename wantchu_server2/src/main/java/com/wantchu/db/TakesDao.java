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
public class TakesDao {
     Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql;
    MariaDBCon dbc = new MariaDBCon();

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
   public List makeListAllTakes(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                TakesBean tb = new TakesBean();
                tb.setId(rs.getString(1));
                tb.setCourseId(rs.getString(2));
                tb.setSecId(rs.getString(3));
                tb.setSemester(rs.getString(4));
                tb.setYear(rs.getInt(5));
                list.add(tb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
    }
   
   public List makeListAllTakesWithStudent(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                TakesBean tb = new TakesBean();
                StudentBean sb = new StudentBean();
                tb.setCourseId(rs.getString(1));
                tb.setSemester(rs.getString(2));
                tb.setYear(rs.getInt(3));
                tb.setSecId(rs.getString(4));
                sb.setTotCred(rs.getInt(5));
                tb.setSb(sb);
                
                list.add(tb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
    }
    public TakesTitleBean makeTakesTitle(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        TakesTitleBean ttb = new TakesTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            ttb.setIdTitle(rsmd.getColumnName(1));
            ttb.setCourseIdTitle(rsmd.getColumnName(2));
            ttb.setSecIdTitle(rsmd.getColumnName(3));
            ttb.setSemesterTitle(rsmd.getColumnName(4));
            ttb.setYearTitle(rsmd.getColumnName(5));
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return ttb;
    }
    public TakesTitleBean makeTakesTitleWithStudent(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        TakesTitleBean ttb = new TakesTitleBean();
        StudentTitleBean stb = new StudentTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            ttb.setCourseIdTitle(rsmd.getColumnName(1));
            ttb.setSecIdTitle(rsmd.getColumnName(2));
            ttb.setSemesterTitle(rsmd.getColumnName(3));
            ttb.setYearTitle(rsmd.getColumnName(4));
            stb.setTotCredTitle(rsmd.getColumnName(5));
            ttb.setStb(stb);
            
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return ttb;
    }
    public int insertATakes(TakesBean tb){
        int n=0;
        String sql="insert into Takes values(?,?,?,?,?)";
        try {
            pstmt = dbc.getPstmt(sql);
            pstmt.setString(1, tb.getId());
            pstmt.setString(2, tb.getCourseId());
            pstmt.setString(3, tb.getSecId());
            pstmt.setString(4, tb.getSemester());
            pstmt.setInt(5, tb.getYear());

            n = pstmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println("SQL insert SQLState: "+ex.getSQLState());
        }
        return n;
    }
}
