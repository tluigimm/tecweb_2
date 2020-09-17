package br.edu.insper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		Integer imp = Integer.parseInt(request.getParameter("imp"));
		Integer user_id = Integer.parseInt(request.getParameter("user_id"));
		String userName = request.getParameter("userName");
		dao.getNotes(user_id, imp);
		
		User user = new User();
		user.setId(user_id);
		user.setName(userName);
		
		request.setAttribute("user", user);
		request.setAttribute("imp", imp);
		request.getRequestDispatcher("main.jsp").forward(request, response);
	
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
		} 
		
		else if (action.contentEquals("edit")) {
			String text = request.getParameter("newText");
			String userName = request.getParameter("userName");
			Integer imp = Integer.parseInt(request.getParameter("imp"));
			Integer user_id = Integer.parseInt(request.getParameter("user_id"));
			Integer note_id = Integer.parseInt(request.getParameter("note_id"));
			
			Note note = new Note();
			note.setId(note_id);
			note.setNote(text);
			note.setImportance(imp);
			
			dao.editNote(note);
			
			User user = new User();
			user.setId(user_id);
			user.setName(userName);
			
			request.setAttribute("user", user);
			request.setAttribute("imp", 0);
		}
		

		RequestDispatcher  dispathcer = request.getRequestDispatcher("/main.jsp");
		dispathcer.forward(request, response);
	}

}
