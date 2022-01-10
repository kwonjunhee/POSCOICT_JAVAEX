package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import vo.MemberVO;

// 가입기능
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		List<MemberVO> mlist = new MemberDAO().memberAll();
		for (MemberVO mvo : mlist) {
			if (id.equals(mvo.getId()) && password.equals(mvo.getPassword())) {
				if (session.getAttribute("loginSession") == null) {
					session.setAttribute("loginSession", new MemberVO());
				}
				session.setAttribute("loginSession", mvo);
				request.getRequestDispatcher("/jspsrc/loginSuccess.jsp").forward(request, response);
			}
		}
		request.getRequestDispatcher("/jspsrc/loginFail.jsp").forward(request, response);
	}
}