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

public class SectionDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql;
    DBCon dbc = new DBCon();

   public List makeListAllSection(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                SectionBean sb = new SectionBean();
                sb.setCourseId(rs.getString(1));
                sb.setSecId(rs.getString(2));
                sb.setSemester(rs.getString(3));
                sb.setYear(rs.getInt(4));
                sb.setBuilding(rs.getString(5));
                sb.setRoomNumber(rs.getString(6));
                sb.setTimeSlotId(rs.getString(7));
                list.add(sb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
    }
    public List makeListAllSectionCourseId(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                SectionBean sb = new SectionBean();
                sb.setCourseId(rs.getString(1));
                list.add(sb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
    }
   
    public SectionTitleBean makeSectionTitle(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        SectionTitleBean stb = new SectionTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            stb.setCourseIdTitle(rsmd.getColumnName(1));
            stb.setSecIdTitle(rsmd.getColumnName(2));
            stb.setSemesterTitle(rsmd.getColumnName(3));
            stb.setYearTitle(rsmd.getColumnName(4));
            stb.setBuildingTitle(rsmd.getColumnName(5));
            stb.setRoomNumberTitle(rsmd.getColumnName(6));
            stb.setTimeSlotIdTitle(rsmd.getColumnName(7));
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return stb;
    }
     public SectionTitleBean makeSectionTitleCourseId(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        SectionTitleBean stb = new SectionTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            stb.setCourseIdTitle(rsmd.getColumnName(1));
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return stb;
    }
     
    public int insertASection(SectionBean sb){
        int n=0;
        String sql="insert into section values(?,?,?,?,?,?,?)";
        try {
            pstmt = dbc.getPstmt(sql);
            pstmt.setString(1, sb.getCourseId());
            pstmt.setString(2, sb.getSecId());
            pstmt.setString(3, sb.getSemester());
            pstmt.setInt(4, sb.getYear());
            pstmt.setString(5, sb.getBuilding());
            pstmt.setString(6, sb.getRoomNumber());
            pstmt.setString(7, sb.getTimeSlotId());

            n = pstmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println("SQL insert SQLState: "+ex.getSQLState());
        }
        return n;
    }
}
