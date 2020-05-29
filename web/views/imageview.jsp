<%@ include file="header.jsp"%>
<%@ page import="com.cranajit.models.UserFileUploadModel" %>
<html>
<head>
    <title>IMAGE VIEW</title>
</head>
<body>
<%
    String path = (String)request.getAttribute("path");
    UserFileUploadModel image = (UserFileUploadModel) request.getAttribute("image");
%>
<h1>IMAGE DETAILS PAGE</h1><br/>
<p> | ID: <%=image.getPhone()%> | NAME: <%=image.getFileName()%> |
<%
    if(image.getDescription()!=null) {
        out.print("DESCRIPTION: "+image.getDescription()+" | ");
    }
    if(image.getCaption()!=null) {
        out.print("CAPTION: "+image.getCaption()+" | ");
    }
%>
</p>
<img src="<%=path+image.getFileName()%>"/>

<%@ include file="footer.jsp"%>
