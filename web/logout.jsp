<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
        <h1>Logout successful!</h1>
        <%
            session.invalidate();
            response.sendRedirect("index.jsp");
        %>
    </body>
</html>