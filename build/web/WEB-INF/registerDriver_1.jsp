<%-- 
    Document   : passwdChange
    Created on : 05-Nov-2018, 15:18:08
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Driver</title>
    </head>
    <body>
        <h2>Register New Driver</h2>
        <a href="logout.jsp">Log out</a>
        <form method="POST" action="RegisterDriver.do">     
            <table>
                <tr>
                    <th></th>
                    <th>Please provide your following details</th>
                </tr>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td>Registration:</td>
                    <td><input type="text" name="registration"/></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="password" name="name"/></td>
                </tr>
                <tr> 
                    <td> <input type="submit" value="Register"/></td>
                </tr>
            </table>
        </form>
        <%=((String) (request.getAttribute("msg")) != null) ? (String) (request.getAttribute("msg")) : ""%>
    </body>
    <body>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
