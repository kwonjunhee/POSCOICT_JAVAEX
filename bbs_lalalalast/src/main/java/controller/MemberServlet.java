package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import vo.MemberVO;

// 가입기능
@WebServlet("/members")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO(); 
		vo.setName(name);
		vo.setPhone(phone);
		vo.setId(id);
		vo.setPassword(password);
		dao.memberInsert(vo);
		request.getRequestDispatcher("/jspsrc/login.jsp").forward(request, response);
	}
}