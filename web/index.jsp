<%@ include file="views/header.jsp"%>

<h1>PLEASE UPLOAD YOUR IMAGES HERE</h1><br/>

<form action="<%=request.getContextPath()%>/image?action=images" method="post" enctype="multipart/form-data">
UPLOAD IMAGES: <input type="file" name="files" multiple>
    <input type="submit" value="UPLOAD IMAGE">
</form>

<h1><a href="<%=request.getContextPath()%>/image?action=showimages">SHOW ALL OF THE IMAGES</a></h1>

<%@ include file="views/footer.jsp"%>