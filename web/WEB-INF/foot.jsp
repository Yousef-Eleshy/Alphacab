<%-- 
    Document   : Foot
    Created on : 16-Nov-2015, 13:44:47
    Author     : me-aydin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String str;
    if ((String) request.getAttribute("query") == "No customers") {
        str = "administratorHomepage.jsp";
    } 

    else{
        str = "index.jsp";
    }
%>

<a href=<%=str%>> Back </a>