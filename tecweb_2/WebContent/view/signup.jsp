<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ page import="java.util.*, br.edu.insper.model.*, br.edu.insper.controller.*"%>
		<meta charset="UTF-8">
		<title>Sign Up</title>
	</head>

	<body>
		<center> <h1> Sign Up</h1> </center> <hr/>
		
		<center><form action="signup" method="post">
			usuário: <input type="text" name="nome"/><br/>
			senha: <input type="password" name="passwd"/><br/>
			<input type="submit" value="Enter">
		</form></center>
	</body>
</html>