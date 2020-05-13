<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS\editImageStyle.css" >
<meta charset="ISO-8859-1">
<title>Edit Image</title>
</head>
<body>

	<h2>Image Edit</h2>
	<form method="post" action="ImageEditServlet" enctype="multipart/form-data">
	<%System.out.println(request.getParameter("sno")); %>
		S.no. <input type="text" id="snotext" name="sno"
			value=<%=request.getParameter("sno")%> readonly> <br>
		<br> Put the new Image here.<br>
		<br> Image name <input type="text" name="newImagename"><br>
		<br> <input type="file"> <input type="submit"
			value="Submit" id="btn">
	</form>
</body>
</html>