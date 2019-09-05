package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.MemberDAO;

@WebServlet("/ReadCSV")
public class ReadCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application=this.getServletContext();
		String path=application.getRealPath("/WEB-INF/data/dummy.csv");
		MemberDAO dao=new MemberDAO();
		dao.insertFromCSV(path);
		response.sendRedirect("/meibo/Read");
	}
}
