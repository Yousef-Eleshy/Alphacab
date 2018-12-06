<%-- 
    Document   : passwdChange
    Created on : 25-Nov-2018, 12:47:34
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book taxi</title>
    </head>
    <body>
        <h1>Book taxi</h1>
        <a href="logout.jsp">Log out</a>
        <form method="POST" action="BookTaxi.do">     
            <table>
                <tr>
                    <th></th>
                    <th>Please provide your following details</th>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name"/></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="address"/></td>
                </tr>
                <tr>
                    <td>Destination:</td>
                    <td><input type="text" name="destination"/></td>
                </tr>
                <tr>
                    <td>Date:</td>
                    <td><input type="text" name="date"/></td>
                </tr>
                <tr>
                    <td>Time:</td>
                    <td><input type="text" name="time"/></td>
                </tr>
                <tr> 
                    <td> <input type="submit" value="Book"/></td>
                </tr>
            </table>
        </form>
        <%=((String) (request.getAttribute("msg")) != null) ? (String) (request.getAttribute("msg")) : ""%>
    </body>
</html>
