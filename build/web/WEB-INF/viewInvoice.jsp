<%-- 
    Document   : viewInvoice
    Created on : 06-Dec-2018, 08:12:31
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Invoice</title>
    </head>
    <body>
        <h2>Alphacab Customer Invoice</h2>
        <%=(String)(request.getAttribute("query"))%>
    </body>
     <body>
        <h1>Send Invoice To Customer</h1> 
            <table>
                <tr>
                    <td> <input type="submit" value="Send"/></td>
                </tr>
            </table>
        </form>
</html>
