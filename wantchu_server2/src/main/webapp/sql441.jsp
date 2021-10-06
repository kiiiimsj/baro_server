<%-- 
    Document   : sql401.jsp
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
        <title>SQL441</title>
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
        <h1>With</h1>
        <UL>
            <li>학번 : 2016210162 이름 : 이승재</li>
        </UL>
        <table id="customers" >
        <% 
        List<InstructorBean> list = new ArrayList();
        String sql="with dept_total (dept_name , value ) as " +
                "(select dept_name , sum(salary) " +
                "from instructor " +
                "group by dept_name), " +
                "dept_total_avg (value) as " +
                "(select avg(value) " +
                "from dept_total) " +
                "select dept_name " +
                "from dept_total , dept_total_avg " +
                "where dept_total.value >= dept_total_avg.value";
        
        InstructorDao sd = new InstructorDao();
        InstructorBean sb = null;
        
        InstructorTitleBean stb = sd.makeInstructorDeptNameTitle(sql);
        out.print("<tr><td>"+stb.getDeptNameTitle()+"</td></tr>");
        
        list = sd.makeListAllInstructorDeptName(sql);
        Iterator it = list.iterator();
        
        while(it.hasNext()){
            sb = (InstructorBean)it.next();
            out.print("<tr><td> "+ sb.getDeptName() +"</td></tr>");
            out.print("\n");
        }
        out.println();
        %>
        </table>
    </body>
</html>
