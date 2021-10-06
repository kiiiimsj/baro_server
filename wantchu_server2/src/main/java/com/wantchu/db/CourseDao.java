/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wantchu.db;

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
public class CourseDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql;
    DBCon dbc = new DBCon();

    
   public List makeListAllCourse(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                CourseBean cb = new CourseBean();
                cb.setCourseId(rs.getString(1));
                cb.setTitle(rs.getString(2));
                cb.setDeptName(rs.getString(3));
                cb.setCredits(rs.getInt(4));
                list.add(cb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
    }
   
    public CourseTitleBean makeCourseTitle(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        CourseTitleBean ctb = new CourseTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            ctb.setCourseIdTitle(rsmd.getColumnName(1));
            ctb.setTitleTitle(rsmd.getColumnName(2));
            ctb.setDeptNameTitle(rsmd.getColumnName(3));
            ctb.setCreditsTitle(rsmd.getColumnName(4));
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return ctb;
    }
    public int insertACourse(CourseBean cb){
        int n=0;
        String sql="insert into course values(?,?,?,?)";
        try {
            pstmt = dbc.getPstmt(sql);
            pstmt.setString(1, cb.getCourseId());
            pstmt.setString(2, cb.getTitle());
            pstmt.setString(3, cb.getDeptName());
            pstmt.setInt(4, cb.getCredits());

            n = pstmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println("SQL insert SQLState: "+ex.getSQLState());
        }
        return n;
    }
    public int insertACourse(String sql){
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
