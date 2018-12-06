<%-- 
    Document   : Foot
    Created on : 16-Nov-2015, 13:44:47
    Author     : Sean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<link href="${pageContext.request.contextPath}/css/footStyle.css" rel="stylesheet" >

<button onclick="goBack()">Go Back</button>

<script>
function goBack() {
    window.history.back();
}
</script>
