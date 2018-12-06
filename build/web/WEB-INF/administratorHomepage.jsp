<%-- 
    Document   : administratorHomepage
    Created on : 22-Nov-2018, 11:44:04
    Author     : Sean
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
        <a href="logout.jsp">Log out</a>
        <form method="POST" action="AdminsHomepage.do">
        <p />
            View a table <br />
            <input type="radio" name="tbl" value="ListCustomers">List Customers<br />
            <input type="radio" name="tbl" value="ListDrivers">List Drivers<br />
            <input type="radio" name="tbl" value="ListJobs">List All Drivers Jobs<br />
            <input type="radio" name="tbl" value="BookDemands">Book Demands<br />
            <input type="radio" name="tbl" value="CreateDailyReport">Create Daily Report<br />
            <input type="radio" name="tbl" value="ViewDailyReport">View Today's Report<br />
            <input type="radio" name="tbl" value="CreateCustomerInvoice">Create Customer Invoice<br />
            <input type="radio" name="tbl" value="ChangeJourneyPrice">Change Price of a Journey<br />
            <input type="radio" name="tbl" value="RegisterDriver">Register A Driver<br />
            <input type="radio" name="tbl" value="NewUser">Register a User<br />
            <input type="radio" name="tbl" value="Update">Password Change<br />
            <input type="radio" name="tbl" value="Delete">Delete a User<br />
            <input type=submit value="Go!"> <br />
        </form> 
        <%=((String) (request.getAttribute("msg")) != null) ? (String) (request.getAttribute("msg")) : ""%>
    </body>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <h1>Views</h1>
    </head>    
    <body> 
        <%=(String)(request.getAttribute("query"))%>
    </body>
</html>
