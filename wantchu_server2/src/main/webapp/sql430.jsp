<%-- 
    Document   : sql408.jsp
    Created on : 2021. 9. 30., 오후 10:30:43
    Author     : 이승재
--%>
<%@page import="apachetest.maven.sql_webpage.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Takes, Student List</title>
        <style>
            #customers {
                font-family: Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width:100%;
                
            }
            #customers td, #customers th {
                border: 1px solid #ddd;
                padding: 8px;
            }
            
            #customers tr:nth-child(even){background-color: #f2f2f2;}
            #customers tr:hover{background-color: #ddd;}
            
            #customers th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
                background-color: #04AA6D;
                color: white;
            }
        </style>
    </head>
    <body>
        <h1>Group by, having</h1>
        <UL>
            <li>학번 : 2016210162 이름 : 이승재</li>
        </UL>
        <table id="customers" >
        <% 
        List<TakesBean> list = new ArrayList();
        String sql="select course_id , semester, year, sec_id , avg (tot_cred) "
                    + "from takes natural join student "
                    + "where year = 2017 "
                    + "group by course_id , semester , year, sec_id "
                    + "having count(ID) >= 2 ";
        
        TakesDao td = new TakesDao();
        TakesBean tb = null;
        
        TakesTitleBean ttb = td.makeTakesTitleWithStudent(sql);
        StudentTitleBean stb = ttb.getStb();
        out.print("<tr><td>"+ttb.getCourseIdTitle()+"</td>");
        out.print("<td>"+ttb.getSemesterTitle()+"</td>");
        out.print("<td>"+ttb.getYearTitle()+"</td>");
        out.print("<td>"+ttb.getSecIdTitle()+"</td>");
        out.print("<td>"+stb.getTotCredTitle()+"</td></tr>");
        
        
        list = td.makeListAllTakesWithStudent(sql);
        Iterator it = list.iterator();
        
        while(it.hasNext()){
            tb = (TakesBean)it.next();
            StudentBean sb = tb.getSb();
            out.print("<tr><td> "+ tb.getCourseId() +"</td>");
            out.print("<td> "+ tb.getSemester() +"</td>");
            out.print("<td> "+ tb.getYear() +"</td>");
            out.print("<td> "+ tb.getSecId() +"</td>");
            out.print("<td> "+ sb.getTotCred() +"</td></tr>");
            out.print("\n");
        }
        out.println();
        %>
        </table>
    </body>
</html>
