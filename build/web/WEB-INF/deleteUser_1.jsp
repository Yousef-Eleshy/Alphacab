<%-- 
    Document   : deleteUser
    Created on : 25-Nov-2018, 12:47:34
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete User</title>
    </head>
    <body>
        <h1>Delete User</h1>
        <a href="logout.jsp">Log out</a>
        <form method="POST" action="Delete.do">   
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
                    <td>Confirm Password:</td>
                    <td><input type="password" name="confPassword"/></td>
                </tr>
                <tr>
                    <td>
                        <select name ="usertype" >
                        <option>Driver</option>
                        <option>Administrator</option>
                        <option>Customer</option>
                        </select>
                    </td>
                </tr> 
                <tr> 
                    <td> <input type="submit" value="Delete"/></td>
                </tr>
            </table>
        </form>   
    </body>
    <body>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
