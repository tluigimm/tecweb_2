package br.edu.insper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String nome = request.getParameter("nome");
		String passwd = request.getParameter("passwd");
		
		DAO dao = new DAO();
		List<User> users = dao.getUsers();
		
		PrintWriter out = response.getWriter();
		
		boolean isUser = false;
		User newUser = null;
		
		for (User user : users) {
			out.println("");

			if (user.getName().contentEquals(nome) && user.getPasswd().contentEquals(passwd)) {
				isUser = true;
				newUser = user;
				
				break;
			}
		}
		
		if(isUser) {
			request.setAttribute("user", newUser);
			request.setAttribute("imp", 0);
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}else {
			out.println("informações invalidas");
		}
	}

}