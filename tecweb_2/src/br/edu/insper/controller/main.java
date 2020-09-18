package br.edu.insper.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.model.DAO;
import br.edu.insper.model.Note;
import br.edu.insper.model.User;

/**
 * Servlet implementation class main
 */
@WebServlet("/main")
public class main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		DAO dao = new DAO();
		
		Integer user_id = Integer.parseInt(request.getParameter("user_id"));
		String userName = request.getParameter("userName");
		
		String busca = request.getParameter("textBusca");
		Integer imp = Integer.parseInt(request.getParameter("imp"));

		System.out.println("entramos no order");
		request.setAttribute("imp", imp);

		
		User user = new User();
		user.setId(user_id);
		user.setName(userName);
		
		request.setAttribute("user", user);
		request.setAttribute("busca", busca);
		request.getRequestDispatcher("view/main.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DAO dao = new DAO();
		
		String action = request.getParameter("action");
		
		if (action.contentEquals("add")) {
			String nota = request.getParameter("nota");
			Integer imp = Integer.parseInt(request.getParameter("imp"));
			Integer user_id = Integer.parseInt(request.getParameter("user_id"));
			String userName = request.getParameter("userName");
			dao.addNote(nota, user_id, imp);
			
			User user = new User();
			user.setId(user_id);
			user.setName(userName);
			
			request.setAttribute("user", user);
			request.setAttribute("imp", 0);
			RequestDispatcher  dispathcer = request.getRequestDispatcher("view/main.jsp");
			dispathcer.forward(request, response);
		} 
		
		else if (action.contentEquals("del")) {
			Integer noteId= Integer.parseInt(request.getParameter("note_id"));
			Integer user_id = Integer.parseInt(request.getParameter("user_id"));
			String userName = request.getParameter("userName");
			dao.delNote(noteId);
			
			User user = new User();
			user.setId(user_id);
			user.setName(userName);
			
			request.setAttribute("user", user);
			request.setAttribute("imp", 0);
			RequestDispatcher  dispathcer = request.getRequestDispatcher("view/main.jsp");
			dispathcer.forward(request, response);
		} 
		
		else if (action.contentEquals("edit")) {
			String status = request.getParameter("status");
			Integer note_id = null;
			
			System.out.println(status);
			System.out.println(status.contentEquals("editing"));
			System.out.println(status.contentEquals("editado"));
			if (status.contentEquals("editing")) {
			
				String userName = request.getParameter("userName");
				int user_id = Integer.parseInt(request.getParameter("user_id"));
				note_id = Integer.parseInt(request.getParameter("note_id"));
				String text = request.getParameter("text");
				
				RequestDispatcher  dispathcer = request.getRequestDispatcher("view/edit.jsp");
				dispathcer.forward(request, response);
			}
			
			else if (status.contentEquals("editado")) {
				String newText = request.getParameter("newText");
				Integer imp = Integer.parseInt(request.getParameter("imp"));
				note_id = Integer.parseInt(request.getParameter("note_id"));

				Note note = new Note();
				note.setId(note_id);
				note.setNote(newText);
				note.setImportance(imp);
				
				dao.editNote(note);
				
				doGet(request, response);
				
			}
		}
	}

}
