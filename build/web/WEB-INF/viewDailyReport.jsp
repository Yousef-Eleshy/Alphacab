<%-- 
    Document   : viewDailyReport
    Created on : 06-Dec-2018, 09:21:52
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/css/viewDailyReportStyle.css" rel="stylesheet" >
    </head>
     <body>
        <h2>Today's Report</h2>
        <%=(String)(request.getAttribute("query"))%>
    </body>
</html>
