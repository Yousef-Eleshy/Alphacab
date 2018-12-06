<%-- 
    Document   : login
    Created on : 05-Nov-2018, 15:18:08
    Author     : Sean
--%>

<%@page import="model.Jdbc"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Main Page</title>
    </head>
    <body>
        <h2>User's details:</h2>
        <form method="POST" action="Login.do">     
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
                    <td>
                        <select name ="usertype" >
                        <option>Driver</option>
                        <option>Administrator</option>
                        <option>Customer</option>
                        </select>
                    </td>
                </tr> 
                <tr>
                    <td> <input type="submit" value="Login"/></td>
                </tr>
            </table>
        </form>   
         <%=((String)(request.getAttribute("msg"))!=null)?(String)(request.getAttribute("msg")):""%>
        </br>
    </body>
    <body>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
