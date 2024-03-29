package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Member;
import util.MemberDAO;

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		if(id==null) {
			response.sendRedirect("/meibo/Read");
		}else {
			Member member=new MemberDAO().findOne(Integer.parseInt(id));
			request.setAttribute("member", member);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/update.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String kana=request.getParameter("kana");
		String email=request.getParameter("email");
		String age=request.getParameter("age");
		Member member=new Member(Integer.parseInt(id),name,kana,email,Integer.parseInt(age));
		new MemberDAO().updateOne(member);
		response.sendRedirect("/meibo/Read");
	}
}
