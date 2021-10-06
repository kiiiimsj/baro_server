<%-- 
    Document   : sql403.jsp
    Created on : 2021. 9. 30., 오후 10:30:43
    Author     : 이승재
--%>
<%@page import="com.wantchu.db.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student List</title>
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
        <h1>Having</h1>
        <UL>
            <li>학번 : 2016210162 이름 : 이승재</li>
        </UL>
        <table id="customers" >
        <% 
        List<InstructorBean> list = new ArrayList();
        String sql="select dept_name , avg(salary ) as avg_salary from instructor group by dept_name having avg (salary) > 42000";
        
        InstructorDao sd = new InstructorDao();
        InstructorBean sb = null;
        
        InstructorTitleBean stb = sd.makeInstructorDeptNameSalaryTitle(sql, 0);
        out.print("<tr><td>"+stb.getDeptNameTitle()+"</td>");
        out.print("<td>"+stb.getSalaryTitle()+"</td></tr>");
        
        list = sd.makeListAllInstructorDeptNameSalary(sql, 0);
        Iterator it = list.iterator();
        
        while(it.hasNext()){
            sb = (InstructorBean)it.next();
            out.print("<tr><td> "+ sb.getDeptName() +"</td>");
            out.print("<td> "+ sb.getSalary() +"</td></tr>");
            out.print("\n");
        }
        out.println();
        %>
        </table>
    </body>
</html>
