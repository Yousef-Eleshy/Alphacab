<%-- 
    Document   : bookDemands
    Created on : 22-Nov-2018, 11:44:04
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Drivers</title>
    </head>
    <body>
        <h1>Book Demands</h1>
        <a href="logout.jsp">Log out</a>
    </body>
    <body>
        <h2>Demands</h2>
        <%=(String)(request.getAttribute("query"))%>
    </body>
    <body>
        <h2>Journeys</h2>
        <%=(String)(request.getAttribute("query1"))%>
    </body>
    <body>
        <h2>Drivers</h2>
        <%=(String)(request.getAttribute("query2"))%>
    </body>
    <body>
        <h2>Book taxi</h2>
        <form method="POST" action="BookDemands.do">     
            <table>
                <tr>
                    <th></th>
                    <th>Please provide your following details</th>
                </tr>
                <tr>
                    <td>Demand ID:</td>
                    <td><input type="text" name="id"/></td>
                </tr>
                <tr>
                    <td>Driver Registration:</td>
                    <td><input type="text" name="registration"/></td>
                </tr>
                <tr> 
                    <td> <input type="submit" value="Book"/></td>
                </tr>
            </table>
        </form>
        <%=((String) (request.getAttribute("msg")) != null) ? (String) (request.getAttribute("msg")) : ""%>
    </body>
    <body>
        <jsp:include page="foot.jsp"/>
    </body>