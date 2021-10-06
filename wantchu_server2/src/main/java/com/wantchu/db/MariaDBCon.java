/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wantchu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dltmd
 */
public class MariaDBCon {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String user ="root", password="1234";
    String Drv="org.mariadb.jdbc.Driver";
    String Url="jdbc:mariadb://localhost:3306/s_da?user="+user+"&password="+password;
    //jdbc:mariadb://localhost:3306/s_da [root on Default schema]
    //org.mariadb.jdbc.Driver
    /**
    * @param 없음 반환값 Connection 객체
    */
    public Connection getCon() {
        try{
            Class.forName(Drv);
            
            conn = DriverManager.getConnection(Url);
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
