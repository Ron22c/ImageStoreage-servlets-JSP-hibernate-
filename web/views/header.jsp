<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String title = "WELCOME TO THE APPLICATION";
    if (request.getAttribute("title") != null) {
        title = (String) request.getAttribute("title");
    }
%>
<html>
<head>
    <title><%=title%></title>
</head>
<body>
<h1>WELCOME TO THE IMAGE UPLOADER APPLICATION</h1><br/>
<p>************************************************</p>
<br/>