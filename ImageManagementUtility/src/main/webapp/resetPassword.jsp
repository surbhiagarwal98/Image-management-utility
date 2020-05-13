
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"href="CSS\index.css">
</head>
<body>
<h2>Reset your password</h2>
	<form method="post" action="PasswordResetServlet">
		<table style = "text-align:center">
			<tr>
				<td>Confirm Username</td>
				<td><input type="text" name="userName"><td>
			</tr>
			<tr>
				<td>Enter new password</td>
				<td><input type="text" name="newPassword"></td>
			</tr>
			<tr>
			<td>Confirm Password</td>
			<td><input type="text" name="confirmPassword"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" id="btn"></td>
			</tr>
		</table>
		
	</form>

</body>
</html>