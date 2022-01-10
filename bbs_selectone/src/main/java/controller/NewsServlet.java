package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NewsDAO;
import vo.NewsVO;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String action = request.getParameter("action");
		String pageNumber = request.getParameter("pageNumber");
		String looknum = request.getParameter("looknum");
		if(pageNumber==null) pageNumber="1";
		if(looknum == null) looknum="5";
		NewsDAO dao = new NewsDAO();
		String target = "/jspsrc/newsView.jsp";
		response.setContentType("text/html; charset=utf-8");
		System.out.println("doGet:" + action);
		if (action != null && action.equals("delete")) { // 삭제 버튼 클릭시 : 뉴스 삭제(id)
			dao.newsDelete(Integer.parseInt(id));
			System.out.println("글을 삭제합니다.");
		} else if (action != null && action.equals("select")) { // 제목 선택 시 : 해당 뉴스 id 로 해당 뉴스 내용 출력(id)
			NewsVO selectedNewsVO = dao.newsListOne(Integer.parseInt(id));
			dao.newsUpdateCnt(selectedNewsVO);
			target = "/jspsrc/newsViewOne.jsp";
			request.setAttribute("selectedNewsVO", selectedNewsVO);
			System.out.println("글을 선택합니다.");
		} else if (action != null && action.equals("search")) {
			String searchField = request.getParameter("searchField");
			String searchText = request.getParameter("searchText");
			int lnum = (Integer.parseInt(looknum));

			int page = (Integer.parseInt(pageNumber)-1)*lnum;
			target = "/jspsrc/searchNewsView.jsp";
			request.setAttribute("list", dao.getSearch(searchField, searchText, page, lnum));
			System.out.println("글을 검색합니다.");
		} else if (action != null && action.equals("submit")) {
			target = "/jspsrc/newsSubmit.jsp";
			System.out.println("글을 작성합니다.");
		} else if (action != null && action.equals("update")) {
			NewsVO selectedNewsVO = dao.newsListOne(Integer.parseInt(id));
			dao.newsUpdate(selectedNewsVO);
			target = "/jspsrc/newsUpdate.jsp";
			request.setAttribute("selectedNewsVO", selectedNewsVO);
			System.out.println("글을 수정합니다.");
		} else {
			int lnum = (Integer.parseInt(looknum));
			int page = (Integer.parseInt(pageNumber)-1)*lnum;
			List<NewsVO> list = dao.newsListAll(page, lnum);
			request.setAttribute("list", list);
			target = "/jspsrc/newsView.jsp";
			System.out.println("전체 글 리스트를 보여줍니다.");
		}
		request.getRequestDispatcher(target).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		String writeDate = request.getParameter("writedate");
		NewsDAO dao = new NewsDAO();
		NewsVO vo = new NewsVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		vo.setWriteDate(writeDate);
		System.out.println("doPost:" + action);
		if (action != null && action.equals("update")) {
			vo.setId(Integer.parseInt(request.getParameter("id")));
			dao.newsUpdate(vo);
		} else if (action != null) { // 작성
			dao.newsInsert(vo);
		}
		List<NewsVO> list = dao.newsListAll(0,5);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jspsrc/newsView.jsp").forward(request, response);
	}
}