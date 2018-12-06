<%-- 
    Document   : DriverHomepage
    Created on : 11-Nov-2018, 21:00:40
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Driver Homepage</title>
        <link href="${pageContext.request.contextPath}/css/driverHomePageStyle.css" rel="stylesheet" >
    </head>
    <body>
        <h1>My Journeys</h1>
        <a href="logout.jsp">Log out</a>
        <form method="POST" action="DriverHomepage.do">
        <p />
        <h2>
            View a table <br />
            <input type="radio" name="tbl" value="todaysJourneys">List Today's Journeys<br />
            <input type="radio" name="tbl" value="previousJourneys">List Previous Journeys<br />
            <input type=submit value="Go!"> <br />
        </h2>
    </body>
    <body>
        <%=(String)(request.getAttribute("query"))%>
    </body>
</html>
