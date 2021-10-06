<%-- 
    Document   : sql408.jsp
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
        <title>SQL440_web</title>
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
        <h1>Max Budget</h1>
        <UL>
            <li>학번 : 2016210162 이름 : 이승재</li>
        </UL>
        <table id="customers" >
        <% 
        String sql="with max_budget(value) as( "
                    +"select max(budget) "
                    +"from department) "
            +"select department.budget "
            +"from department, max_budget "
            +"where department.budget = max_budget.value";
        
        DepartmentDao td = new DepartmentDao();
       
        out.print("<tr><td>"+td.getTitleOne(sql)+"</td></tr>");
        
        out.print("<tr><td> "+ td.getCount(sql) +"</td></tr>");
        out.print("\n");
        out.println();
        %>
        </table>
    </body>
</html>
