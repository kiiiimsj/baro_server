package com.wantchu.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @프로그램 : DBCon.java
 * @저자 : 학번: 2016210162 이름: 이승재
 * @날짜 : 2021.09.30
 */
public class DBCon {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String user ="cmdwo2323", password="1234";
    String Drv="oracle.jdbc.OracleDriver";
    String Url="jdbc:oracle:thin:@dalma.dongguk.ac.kr:1521:stud1";
    
    /**
    * @param 없음 반환값 Connection 객체
    */
    public Connection getCon() {
        try{
            Class.forName(Drv);
            conn = DriverManager.getConnection(Url, user, password);
        }catch(ClassNotFoundException e) {
            System.out.println("CNF Err1:"+e.getException());
        }catch(SQLException e) {
            System.out.println("SQL Err1:"+e.getSQLState());
        }
        return conn;
    }
    
    public PreparedStatement getPstmt(String sql) {
        try{
            Class.forName(Drv);
            conn = DriverManager.getConnection(Url, user, password);
            pstmt = conn.prepareStatement(sql);
        }catch(ClassNotFoundException e) {
            System.out.println("CNF Err1:"+e.getException());
        }catch(SQLException e) {
            System.out.println("SQL Err1:"+e.getSQLState());
        }
        return pstmt;
    }
    
    public ResultSet getRS(String sql) {
        try {
            Class.forName(Drv);
            conn = DriverManager.getConnection(Url, user, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (ClassNotFoundException ex) {
            System.out.println("CNF Err1:"+ex.getException());
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        } 
        return rs;
    }
    
    
}
