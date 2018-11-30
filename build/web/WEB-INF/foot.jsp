<%-- 
    Document   : Foot
    Created on : 16-Nov-2015, 13:44:47
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String str;
    if ((String) request.getAttribute("back") == "login") {
        str = "index.jsp";
    } 
    else if ((String) request.getAttribute("back") == "admin") {
        str = "adminHomepage.jsp";
    } 
    
    else if ((String) request.getAttribute("back") == "driver") {
        str = "driverHomepage.jsp";
    } 

    else{
        str = "index.jsp";
    }
%>

<a href=<%=str%>> Back </a>