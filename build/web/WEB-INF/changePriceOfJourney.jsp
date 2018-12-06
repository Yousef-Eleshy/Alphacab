<%-- 
    Document   : changePriceOfJourney
    Created on : 05-Dec-2018, 20:18:10
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Price Of Journeys</title>
    </head>
    <body>
        <h1>Journeys</h1>
        <%=(String)(request.getAttribute("query"))%>
    </body>
    <body>
        <h1>Change The Price Of A Journey</h1>
        <a href="logout.jsp">Log out</a>
        <form method="POST" action="ChangeJourneyPrice.do">     
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
                    <td>Price:</td>
                    <td><input type="text" name="price"/></td>
                </tr>
                <tr>
                    <td> <input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form>
        <%=((String) (request.getAttribute("msg")) != null) ? (String) (request.getAttribute("msg")) : ""%> 
</html>
