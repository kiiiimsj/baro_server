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
 * @프로그램 : InstructorDao.java
 * @저자 : 학번: 2016210162 이름: 이승재
 * @날짜 : 2021.09.30
 */

public class InstructorDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql;
    MariaDBCon dbc = new MariaDBCon();
    DBCon oracleBc = new DBCon();

    
   public List makeListAllInstructor(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                InstructorBean sb = new InstructorBean();
                sb.setId(rs.getString(1));
                sb.setName(rs.getString(2));
                sb.setDeptName(rs.getString(3));
                sb.setSalary(rs.getDouble(4));
                list.add(sb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
   }
   
   public List makeListAllInstructorWithTeaches(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                InstructorBean sb = new InstructorBean();
                TeachesBean tb = new TeachesBean();
                sb.setId(rs.getString(1));
                sb.setName(rs.getString(2));
                sb.setDeptName(rs.getString(3));
                sb.setSalary(rs.getDouble(4));
                tb.setId(rs.getString(5));
                tb.setCourseId(rs.getString(6));
                tb.setSecId(rs.getString(7));
                tb.setSemester(rs.getString(8));
                tb.setYear(rs.getInt(9));
                sb.setTb(tb);
                
                list.add(sb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
   }
   public List makeListAllInstructorWithTeachesCourseId(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                InstructorBean sb = new InstructorBean();
                TeachesBean tb = new TeachesBean();
                sb.setName(rs.getString(1));
                tb.setCourseId(rs.getString(2));
                sb.setTb(tb);
                
                list.add(sb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
   }
   
   public List makeListAllInstructorWithBuilding(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                InstructorBean sb = new InstructorBean();
                sb.setName(rs.getString(1));
                sb.setDeptName(rs.getString(2));
                sb.setBuildingName(rs.getString(3));
                list.add(sb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
    }
    public List makeListAllInstructorDeptName(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                InstructorBean sb = new InstructorBean();
                sb.setDeptName(rs.getString(1));
                list.add(sb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
    }
    public List makeSalaryAvg(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                InstructorBean sb = new InstructorBean();
                sb.setSalary(rs.getDouble(1));
                list.add(sb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
    }
    public List makeListAllInstructorName(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                InstructorBean sb = new InstructorBean();
                sb.setName(rs.getString(1));
                list.add(sb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
    }
    public List makeListAllInstructorIdNameSalary(String sql) {
        List list = new ArrayList();
        try {
            rs = dbc.getRS(sql);
            while(rs.next()) {
                InstructorBean sb = new InstructorBean();
                sb.setId(rs.getString(1));
                sb.setName(rs.getString(2));
                sb.setSalary(rs.getDouble(3));
                list.add(sb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
    }
     public List makeListAllInstructorDeptNameSalary(String sql, int sw) {
        List list = new ArrayList();
        try {
            if(sw == 1) rs = oracleBc.getRS(sql);
            else rs = dbc.getRS(sql);
            while(rs.next()) {
                InstructorBean sb = new InstructorBean();
                sb.setDeptName(rs.getString(1));
                sb.setSalary(rs.getDouble(2));
                list.add(sb);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Err1:"+ex.getSQLState());
        }
        return list;
    }
    
    /*
    Title Methods
    */
    
    public InstructorTitleBean makeInstructorTitle(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        InstructorTitleBean stb = new InstructorTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            stb.setIdTitle(rsmd.getColumnName(1));
            stb.setNameTitle(rsmd.getColumnName(2));
            stb.setDeptNameTitle(rsmd.getColumnName(3));
            stb.setSalaryTitle(rsmd.getColumnName(4));
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return stb;
    }
    public InstructorTitleBean makeInstructorTitleWithTeaches(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        InstructorTitleBean stb = new InstructorTitleBean();
        TeachesTitleBean ttb = new TeachesTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            stb.setIdTitle(rsmd.getColumnName(1));
            stb.setNameTitle(rsmd.getColumnName(2));
            stb.setDeptNameTitle(rsmd.getColumnName(3));
            stb.setSalaryTitle(rsmd.getColumnName(4));
            ttb.setIdTitle(rsmd.getColumnName(5));
            ttb.setCourseIdTitle(rsmd.getColumnName(6));
            ttb.setSecIdTitle(rsmd.getColumnName(7));
            ttb.setSemesterTitle(rsmd.getColumnName(8));
            ttb.setYearTitle(rsmd.getColumnName(9));
            
            stb.setTtb(ttb);
            
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return stb;
    }
     public InstructorTitleBean makeInstructorTitleWithTeachesCourseId(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        InstructorTitleBean stb = new InstructorTitleBean();
        TeachesTitleBean ttb = new TeachesTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            stb.setNameTitle(rsmd.getColumnName(1));
            ttb.setCourseIdTitle(rsmd.getColumnName(2));
            
            stb.setTtb(ttb);
            
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return stb;
    }
    public InstructorTitleBean makeInstructorTitleWithTeachesTitle(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        InstructorTitleBean stb = new InstructorTitleBean();
        TeachesTitleBean ttb = new TeachesTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            stb.setIdTitle(rsmd.getColumnName(1));
            stb.setNameTitle(rsmd.getColumnName(2));
            stb.setDeptNameTitle(rsmd.getColumnName(3));
            stb.setSalaryTitle(rsmd.getColumnName(4));
            ttb.setIdTitle(rsmd.getColumnName(5));
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return stb;
    }
    
    public InstructorTitleBean makeInstructorTitleWithBuilding(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        InstructorTitleBean stb = new InstructorTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            stb.setNameTitle(rsmd.getColumnName(1));
            stb.setDeptNameTitle(rsmd.getColumnName(2));
            stb.setBuildingNameTitle(rsmd.getColumnName(3));
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return stb;
    }
    
    public InstructorTitleBean makeInstructorNameTitle(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        InstructorTitleBean stb = new InstructorTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            stb.setNameTitle(rsmd.getColumnName(1));
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return stb;
    }
    public InstructorTitleBean makeInstructorSalaryTitle(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        InstructorTitleBean stb = new InstructorTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            stb.setSalaryTitle(rsmd.getColumnName(1));
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return stb;
    }
    
    public InstructorTitleBean makeInstructorDeptNameTitle(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        InstructorTitleBean stb = new InstructorTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            stb.setDeptNameTitle(rsmd.getColumnName(1));
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return stb;
    }
    
    public InstructorTitleBean makeInstructorIdNameSalaryTitle(String sql){
        ResultSetMetaData rsmd;
        pstmt = dbc.getPstmt(sql);
        InstructorTitleBean stb = new InstructorTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            stb.setIdTitle(rsmd.getColumnName(1));
            stb.setNameTitle(rsmd.getColumnName(2));
            stb.setSalaryTitle(rsmd.getColumnName(3));
            
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return stb;
    }
    public InstructorTitleBean makeInstructorDeptNameSalaryTitle(String sql, int sw){
        ResultSetMetaData rsmd;
        if(sw == 1) pstmt = oracleBc.getPstmt(sql);
        else pstmt = dbc.getPstmt(sql);
        
        InstructorTitleBean stb = new InstructorTitleBean();
        try {
            rs = pstmt.executeQuery();
            rsmd = rs.getMetaData();
            stb.setDeptNameTitle(rsmd.getColumnName(1));
            stb.setSalaryTitle(rsmd.getColumnName(2));
            
        }catch(SQLException ex){
            System.out.println("SQL State: "+ex.getSQLState());
        }
        return stb;
    }
    public int insertAInstructor(InstructorBean sb){
        int n=0;
        String sql="insert into instructor values(?,?,?,?)";
        try {
            pstmt = dbc.getPstmt(sql);
            pstmt.setString(1, sb.getId());
            pstmt.setString(2, sb.getName());
            pstmt.setString(3, sb.getDeptName());
            pstmt.setDouble(4, sb.getSalary());

            n = pstmt.executeUpdate();
        } catch (SQLException ex){
            System.out.println("SQL insert SQLState: "+ex.getSQLState());
        }
        return n;
    }
    
    public int deleteAInstructor(String sql) {
        int n =0;
        try {
         pstmt = dbc.getPstmt(sql);   
         n = pstmt.executeUpdate();
        } catch(SQLException ex) {
            System.out.println("SQL delete SQLState: "+ex.getSQLState());
        }
        return n;
    }
}
