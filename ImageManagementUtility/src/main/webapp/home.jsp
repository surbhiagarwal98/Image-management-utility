<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.nagarro.model.User"%>
<%@page import="com.nagarro.model.Image"%>
<%@page import="com.nagarro.service.api.LoginService"%>
<%@page import="com.nagarro.service.LoginServiceImp"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" type="text/css"href="CSS\homeStyle.css">
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>

<body>
	<div class = "container">
	<h1>Image Management Utility</h1>
	<%
		User user = (User) session.getAttribute("user");
		String username = user.getUserName();
		LoginService loginService = new LoginServiceImp();
		User validUser = loginService.getUser(username);
		if(validUser != null)
		{
	%>
		<h2>Welcome, <%=validUser.getUserName()%>!</h2>
	<% 
		}
	%>
	</div>
	
	<form action="ImageServlet" method="post" id="imageBrowse" enctype="multipart/form-data">
		<table id="browsetable">
		<tr>
		<td>Image(max size:1MB)</td>
		<td><input type = "file" name = "imagename"></td>
		<td><input type = "text" placeholder="Enter image name here" name = "name"></td>
		</tr>
		</table>
		<input type = "submit" value="Submit" id="btn">
	</form>
	<h3>Uploaded Images</h3>
	<div id="images">
	<table id="imagetable">
	<tr>
		<th class="header">S.no</th>
		<th class="header">Image name</th>
		<th class="header">Image size</th>
		<th class="header">Preview</th>
		<th class="header">Action</th>
	</tr>
	
	<%
		Set<Image> images = validUser.getImages();
		int i = 0;
		if(!images.isEmpty()){
			for(Image image:images){
		i++;
	%>
		<tr>
			<td class="data"><%=i%></td>
			<td class="data"><%=image.getName()%></td>
			<td class="data"><%=image.getSize()%></td>
			<td class="data"><a href="ImageFetcherServlet?sno=<%=image.getSno()%>"><img
                                src="ImageFetcherServlet?sno=<%=image.getSno()%>"
                                style="height:150px;"/></a></td>
			
			<td class="data"><a href="editImage.jsp?sno=<%=image.getSno()%>">Edit</a>
			<a href="ImageDeleteServlet?sno=<%=image.getSno()%>">Delete</a>
			</td>	
		</tr>
		<%
			}
		}else{ %>
					<tr><td>No images found!</td></tr>
		<%} %>
		</table>
	</div>
	
</body>
</html>