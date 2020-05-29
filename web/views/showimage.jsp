<%@ include file="header.jsp"%>
<%@page import="java.util.List" %>
<%@page import="com.cranajit.models.UserFileUploadModel" %>

<h1>PHOTOS AVAILABLE ON THE WEBSITE ARE: </h1><br/>
<table border="2">
    <thead>
        <th>ID</th>
        <th>IMAGE</th>
        <th>METADATA</th>
        <th>UPDATE METADATA</th>
        <th>ACTION</th>
    </thead>
    <tbody>

    <%
        List<UserFileUploadModel> photos = (List<UserFileUploadModel>)request.getAttribute("photos");
        String path = (String)request.getAttribute("path");

        for(UserFileUploadModel photo: photos) {
            out.println("<tr>");

            out.println("<td>"+photo.getPhone()+"</td>");

            out.println("<td><ul>" +
                    "<li>"+photo.getPhone()+"</li>" +
                    "<li>"+photo.getFileName()+"</li>" +
                    "<li>"+photo.getDescription()+"</li>" +
                    "<li>"+photo.getCaption()+"</li>" +
                    "</ul></td>");

            out.println("<td><img src="+path+photo.getFileName()+" height='100'/></td>");

            out.println("<td><form action="+request.getContextPath()+"/image method=\"post\">" +
                    "    DESCRIPTION: <input type=\"text\" name=\"description\"/><br/>" +
                    "    CAPTION: <input type=\"text\" name=\"caption\"/><br/>" +
                    "    <input type=\"hidden\" name=\"id\" value="+photo.getPhone()+">" +
                    "    <input type=\"hidden\" name=\"action\" value=\"updatemetadata\">" +
                    "    <input type=\"submit\" value=\"SUBMIT\">" +
                    "</form></td>");

            out.println("" +
                    "<td>\n" +
                    "    <form action="+request.getContextPath()+"/image method=\"post\">\n" +
                    "        <input type=\"hidden\" name=\"fileid\" value="+photo.getPhone()+">\n" +
                    "        <input type=\"hidden\" name=\"action\" value=\"show\">\n" +
                    "        <input type=\"submit\" value=\"VIEW IMAGE\">\n" +
                    "    </form>\n" +
                    "    <br/>\n" +
                    "    <form action="+request.getContextPath()+"/image method=\"post\">\n" +
                    "        <input type=\"hidden\" name=\"fileid\" value="+photo.getPhone()+">\n" +
                    "        <input type=\"hidden\" name=\"action\" value=\"delete\">\n" +
                    "        <input type=\"submit\" value=\"DELETE IMAGE\">\n" +
                    "    </form>\n" +
                    "</td>");

            out.println("</tr>");
        }
    %>
    </tbody>
</table>

<%@ include file="footer.jsp"%>
