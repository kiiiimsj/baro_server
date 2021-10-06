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
 * @프로그램 : StudentBean.java
 * @저자 : 학번: 2016210162 이름: 이승재
 * @날짜 : 2021.10.01
 */
public class TeachesDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql;
    MariaDBCon dbc = new MariaDBCon();

    
   public List makeListAllTeaches(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                TeachesBean tb = new TeachesBean();
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
   
    public TeachesTitleBean makeTeachesTitle(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        TeachesTitleBean ttb = new TeachesTitleBean();
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
    public int insertATeaches(TeachesBean tb){
        int n=0;
        String sql="insert into teaches values(?,?,?,?,?)";
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
