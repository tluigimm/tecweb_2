<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	
		<%@ page import="java.util.*,br.edu.insper.model.*, br.edu.insper.controller.*"%>
		<meta charset="UTF-8">
		<title>Edite sua anotação</title>
		<% 
			String nome = request.getParameter("userName");
			Integer user_id = Integer.parseInt(request.getParameter("user_id"));
			String note = request.getParameter("text");
			Integer note_id = Integer.parseInt(request.getParameter("note_id"));
		%>
	</head>
	<body>
		<center><h1> Edite sua anotção </h1></center> <hr/>
		<form action="main" method="post">
			<input type="hidden" name="action" value="edit"/>
			<textarea name="newText"rows="10" cols="30"><%=note%></textarea>
			<select name="imp">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
			</select>
			
			<input type="hidden" name="note_id" value="<%=note_id%>"/>
			<input type="hidden" name="user_id" value="<%=user_id%>"/>
			<input type="hidden" name="userName" value="<%=nome%>"/>
			<input type="hidden" name="status" value="editado">
			<input type="submit" value="confirmar mudança">
		</form>
	</body>
</html>