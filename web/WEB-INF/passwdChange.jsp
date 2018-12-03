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
        <title>Change Password</title>
    </head>
    <body>
        <h2>Password change</h2>
        <form method="POST" action="Update.do">     
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
                    <td>Current Password:</td>
                    <td><input type="password" name="currentPassword"/></td>
                </tr>
                <tr>
                    <td>New Password:</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td>Confirm Password:</td>
                    <td><input type="password" name="newpasswd"/></td>
                </tr>
                <tr>
                    <td>
                        <select name ="usertype">
                            <option>Customer</option>
                            <option>Driver</option>
                            <option>Administrator</option>
                        </select>
                    </td>
                </tr>
                <tr> 
                    <td> <input type="submit" value="Change"/></td>
                </tr>
            </table>
        </form>
        <%=((String) (request.getAttribute("msg")) != null) ? (String) (request.getAttribute("msg")) : ""%>
    </body>
    <body>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
