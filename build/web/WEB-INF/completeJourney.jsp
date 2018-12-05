<%-- 
    Document   : completeJourney
    Created on : 05-Dec-2018, 16:54:50
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Your Journeys For Today</h1>
        <%=(String)(request.getAttribute("query"))%>
    </body>
    <body>
        <h1>Complete A Journey</h1>
        <a href="logout.jsp">Log out</a>
        <form method="POST" action="CompleteJourney.do">     
            <table>
                <tr>
                    <th></th>
                    <th>Please provide your following details</th>
                </tr>
                <tr>
                    <td>Journey ID:</td>
                    <td><input type="text" name="journeyID"/></td>
                    <td>
                        <select name ="status" >
                        <option>Completed</option>
                        <option>Cancelled by customer</option>
                        <option>Cancelled by home office</option>
                        </select>
                    </td>
                </tr>
                <tr>
                <tr>
                    <td> <input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form>
        <%=((String) (request.getAttribute("msg")) != null) ? (String) (request.getAttribute("msg")) : ""%>        
    </body>
</html>
