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
        <title>SQL203_web</title>
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
        <h1>Instructor List</h1>
        <UL>
            <li>학번 : 2016210162 이름 : 이승재</li>
        </UL>
        <% 
        List<InstructorBean> list = new ArrayList();
        String sql = "delete from instructor "
            + "where dept_name in (select dept_name "
            + "from department where building ='Watson')";
        String sql2="select name, instructor.dept_name, building "
            + "from instructor, department "
            + "where instructor.dept_name = department.dept_name";
        
        InstructorDao sd = new InstructorDao();
        InstructorBean sb = null;
        InstructorTitleBean stb = sd.makeInstructorTitleWithBuilding(sql2);
        out.print("<table id="+"customers"+">");
        out.print("<tr><td>"+stb.getNameTitle()+"</td>");
        out.print("<td>"+stb.getDeptNameTitle()+"</td>");
        out.print("<td>"+stb.getBuildingNameTitle()+"</td></tr>");
        
        list = sd.makeListAllInstructorWithBuilding(sql2);
        Iterator it = list.iterator();
        
        while(it.hasNext()){
            sb = (InstructorBean)it.next();
            out.print("<tr><td> "+ sb.getName() +"</td>");
            out.print("<td> "+ sb.getDeptName() +"</td>");
            out.print("<td> "+ sb.getBuildingName() +"</td></tr>");
            out.print("\n");
        }
        out.print("</table>");
        sd.deleteAInstructor(sql);
        
        stb = sd.makeInstructorTitleWithBuilding(sql2);
        out.print("<h3>AFTER</h3>");
        out.print("<table id="+"customers"+">");
       out.print("<tr><td>"+stb.getNameTitle()+"</td>");
        out.print("<td>"+stb.getDeptNameTitle()+"</td>");
        out.print("<td>"+stb.getBuildingNameTitle()+"</td></tr>");
        
        list = sd.makeListAllInstructorWithBuilding(sql2);
        it = list.iterator();
        
        while(it.hasNext()){
            sb = (InstructorBean)it.next();
            out.print("<tr><td> "+ sb.getName() +"</td>");
            out.print("<td> "+ sb.getDeptName() +"</td>");
            out.print("<td> "+ sb.getBuildingName() +"</td></tr>");
            out.print("\n");
        }
        out.print("</table>");
        %>
    </body>
</html>
