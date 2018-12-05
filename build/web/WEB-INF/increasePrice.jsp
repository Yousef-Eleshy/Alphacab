<%-- 
    Document   : increasePrice
    Created on : 05-Dec-2018, 21:07:08
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
        <h1>Change The Price Of Short Journeys</h1>
        <a href="logout.jsp">Log out</a>
        <form method="POST" action="IncreasePrice.do">     
            <table>
                <tr>
                    <th></th>
                    <th>Please provide your following details</th>
                </tr>
                <tr>
                    <td>Minimum amount of miles to increase price:</td>
                    <td><input type="text" name="miles"/></td>
                </tr>
                <tr>
                    <td>Price to increase:</td>
                    <td><input type="text" name="newPrice"/></td>
                </tr>
                <tr>
                    <td> <input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form>
        <%=((String) (request.getAttribute("msg")) != null) ? (String) (request.getAttribute("msg")) : ""%> 
</html>
