<html>
<link rel="stylesheet" type="text/css"href="CSS\index.css">
<body>
<h2>Login to Image Management Utility</h2>

<form method="post" action="LoginServlet">
	<table>
		<tr>
			<td style="align: left"><b>Login</b></td>
		</tr>
		<tr>
			<td>Username</td>
			<td>* <input type="text" name = "userName"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td>* <input type="password" id="pwd" name = "password"></td>
		</tr>
		<tr>
			<td><a href="resetPassword.jsp">Forgotten your password?</a></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Login >>" id="btn"></td>
		</tr>
	</table>
</form>

</body>
</html>
