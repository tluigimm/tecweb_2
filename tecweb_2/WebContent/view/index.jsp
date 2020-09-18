<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Log in</title>
	</head>
	<body>
	
		<center> <h1> Log in</h1> </center> <hr/>
		
		<center><form action="${pageContext.request.contextPath}/login" method="post">
			usuário: <input type="text" name="nome"/></br/>
			senha: <input type="password" name="passwd"/></br/>
			<input type="submit" value="Enter">
		</form></center>
		
		<center style="border: 1px solid black;"> 
			<a href="view/signup.jsp">Sign up</a> 
		</center>
	</body>
</html>