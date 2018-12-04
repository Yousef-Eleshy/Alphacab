<%-- 
    Document   : driver
    Created on : 01-Nov-2015, 15:18:08
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
    </head>
    <body>
        <h2>User's details:</h2>
        <%! int i = 0;
            String str = "Register";
            String url = "NewUser.do";
        %>
        <form method="POST" action="<%=url%>">     
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
                    <td>Name:</td>
                    <td><input type="text" name="name"/></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" name="address"/></td>
                </tr>
                <tr> 
                    <td> <input type="submit" value="<%=str%>"/></td>
                </tr>
            </table>
        </form>   
        <%
            if (i++ > 0 && request.getAttribute("message") != null) {
                out.println(request.getAttribute("message"));
                i--;
            }
        %>
        </br>
    </body>
    <body>
    <a href="index.jsp">Back</a>
    </body>
</html>
