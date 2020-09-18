<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ page import="java.util.*, br.edu.insper.model.*, br.edu.insper.controller.*"%>
		<meta charset="UTF-8">
		<title> Notas </title>
	</head>
	<body>
		<% 
			User user = (User) request.getAttribute("user");
			Integer imp = (Integer) request.getAttribute("imp");
			DAO dao = new DAO();
			String nome = user.getName();
			Integer user_id = user.getId();
			
			String action = (String) request.getAttribute("action");
			List<Note> notes = new ArrayList<Note>();
			
			System.out.println(action);
	 		String busca = (String) request.getAttribute("busca");

	 		notes = dao.getNotes(user.getId(), imp, busca);
	
		%>
		<a href="view/index.jsp"><button>exit</button></a>
		<center><h1> Olá <%= nome %> </h1></center> <hr/>
		
		<form action="main" method="post">
			<input type="hidden" name="action" value="add"/>
			<textarea name="nota" placeholder="adicione a sua nota aqui..." rows="10" cols="30"></textarea>
			<select name="imp">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
			</select>
			<input type="hidden" name="user_id" value="<%=user_id%>"/>
			<input type="hidden" name="userName" value="<%=nome%>"/>
			<input type="submit" value="Adicionar nova nota">
		</form>
		
		<form action="main" method="get">
			buscar nota:
			<% if (busca!=null){ %>
				<textarea type="text" name="textBusca"><%=busca%></textarea>
			<%} else{%>
				<textarea type="text" name="textBusca"></textarea>
			<%}%>
			<select name="imp">
				<option value="1">mais importante</option>
				<option value="2">menos importante</option>
				<option value="0">irrelevante</option>
			</select>
			<input type="hidden" name="user_id" value="<%=user_id%>"/>
			<input type="hidden" name="userName" value="<%=nome%>"/>
			<input type="submit" value="filtrar notas">
		</form>

		<table border="1">
			<% for (Note note : notes) { 
					String text = note.getNote();
					int note_id = note.getId();%>
				<tr>
					<td><%= note.getNote() %></td>
					<td><%= note.getImportance() %></td>
					
					<td><form action="main" method="post" >
						<input type="hidden" name="action" value="edit"/>
						<input type="hidden" name="note_id" value="<%=note_id%>"/>
						<input type="hidden" name="text" value="<%=text%>"/>
						<input type="hidden" name="user_id" value="<%=user_id%>"/>
						<input type="hidden" name="userName" value="<%=nome%>"/>
						<input type="hidden" name="status" value="editing">
						<input type="submit" value="editar">
					</form></td>
					
					<td><form action="main" method="post">
						<input type="hidden" name="action" value="del"/>
						<input type="hidden" name="note_id" value="<%=note_id%>"/>
						<input type="hidden" name="user_id" value="<%=user_id%>"/>
						<input type="hidden" name="userName" value="<%=nome%>"/>
						<input type="submit" value="Remover">
					</form></td>
				</tr>
			

			<% } %>
		</table>
	</body>
</html>