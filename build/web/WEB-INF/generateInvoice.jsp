<%-- 
    Document   : generateInvoice
    Created on : 06-Dec-2018, 07:08:12
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/css/generateInvoiceStyle.css" rel="stylesheet" >
    </head>
    <body>
        <h2>Completed Journeys</h2>
        <%=(String)(request.getAttribute("query"))%>
    </body>
    <body>
        <h1>Generate an invoice</h1>
        <a href="logout.jsp">Log out</a>
        <form method="POST" action="GenerateInvoice.do">     
            <table>
                <tr>
                    <th></th>
                    <th>Please provide your following details</th>
                </tr>
                <tr>
                    <td>Journey ID:</td>
                    <td><input type="text" name="id"/></td>
                </tr>
                <tr>
                    <td> <input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form>
    </body>
        <%=((String) (request.getAttribute("msg")) != null) ? (String) (request.getAttribute("msg")) : ""%>
</html>
