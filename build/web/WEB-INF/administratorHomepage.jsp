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
        <title>Admin homepage</title>
    </head>
    <body>
        <h1>Admin homepage</h1>
        <a href="index.jsp">Log out</a>
        <form method="POST" action="AdminsHomepage.do">
        <p />
            View a table <br />
            <input type="radio" name="tbl" value="ListCustomers">List Customers<br />
            <input type="radio" name="tbl" value="ListDrivers">List Drivers<br />
            <input type="radio" name="tbl" value="BookDemands">Book Demands<br />
            <input type="radio" name="tbl" value="RegisterDriver">Register A Driver<br />
            <input type="radio" name="tbl" value="NewUser">Register a User<br />
            <input type="radio" name="tbl" value="Update">Password Change<br />
            <input type="radio" name="tbl" value="Delete">Delete a User<br />
            <input type=submit value="Go!"> <br />
        </form> 
    </body>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <h1>Tables</h1>
    </head>    
    <body> 
        <%=(String)(request.getAttribute("query"))%>
    </body>
    <body>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
