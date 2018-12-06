<%-- 
    Document   : index
    Created on : 09-Mar-2016, 16:52:19
    Author     : me-aydin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/indexStyle.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Alphacab</h1>
        <form method="POST" action="UserService.do">
        <p />
        <h2>
            
            <input type="radio" name="tbl" value="Login">Login<br />
            <input type="radio" name="tbl" value="NewUser">Register<br />
            <input type="radio" name="tbl" value="Update">Password Change<br />
            <input type=submit value="Go!"> <br />
            
        </h2>
        </form> 
    </body>
</html>
