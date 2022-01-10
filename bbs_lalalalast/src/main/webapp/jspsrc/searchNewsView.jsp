<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.NewsVO, dao.NewsDAO , vo.MemberVO, java.util.*, java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
td {border-bottom: 1px dotted green;}
tr:hover {
	background-color: pink;
	font-weight: bold;
}
td:nth-child(2) {width: 400px;}
</style>
</head>
<body>
<%-- 검색한 후에 검색결과가 나오는 창 --%>
	<%	
	ArrayList<NewsVO> list = (ArrayList<NewsVO>)(request.getAttribute("list"));
	System.out.println(list.size());
		NewsDAO dao = new NewsDAO();
		String searchField = (String) request.getParameter("searchField");
		String searchText = (String) request.getParameter("searchText");
		
		String looknum = request.getParameter("looknum");
		if(looknum == null) looknum="5";
		int look = Integer.parseInt(looknum);
		
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber == null) pageNumber="1";
		int page1 = Integer.parseInt(pageNumber);
		
		if (list.size() == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('검색결과가 없습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
	%>

	<h2>뉴스 게시판</h2>
	<hr>
	
	<div class="container">
		<div class="row">
			<form method="get" action="/bbs/news">
				<input type="hidden" name="action" value="search">
				<input type="hidden" name="searchField" value="<%=searchField%>">
				<input type="hidden" name="searchText" value="<%=searchText%>">
				
				<table class="pull-right">
					<tr>
						<td><select class="form-control" name="looknum">
								<option value="5" selected = "selected">5개</option>
								<option value="10">10개</option>
								<option value="15">15개</option>
								<option value="20">20개</option>
						</select></td>
						<td><button type="submit" class="btn btn-success">보기</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<% if(session.getAttribute("loginSession")!=null) {
		MemberVO mvo = (MemberVO) session.getAttribute("loginSession");// 로그인 되어있는 상태 %>
	<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
		<%for (NewsVO vo : list) {%>
		<tr>
			<td><%=vo.getId()%></td>
			<td><a href = "/bbs/news?id=<%=vo.getId()%>&action=select"><%=vo.getTitle()%></a></td>
			<td><a href = "/bbs/news?action=search&searchField=writer&searchText=<%=vo.getWriter()%>"><%=vo.getWriter()%></a></td>
			<td><%=vo.getWriteDate()%></td>
			<td><%=vo.getCnt()%></td>
		</tr>
		<%}%>
	</table>
		<a href="/bbs/jspsrc/logout.jsp"><input type="button" value="로그아웃"/></a>
		<a href="/bbs/news?&action=submit"><input type="button" value="글 작성" /></a>
		<a href="/bbs/news?pageNumber=1&action=search&searchField=writer&searchText=<%=mvo.getId()%>"><input type="button" value="내가 쓴 글 보기" /></a>
		<a href="/bbs/news?pageNumber=<%=pageNumber%>&looknum=<%=look%>"><input type="button" value="전체 글 목록"/></a>
	<% } else { // 로그인이 안되어있는 상태%>
	<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
		<%for (NewsVO vo : list) {%>
		<tr>
			<td><%=vo.getId()%></td>
			<td><%=vo.getTitle()%></td>
			<td><%=vo.getWriter()%></td>
			<td><%=vo.getWriteDate()%></td>
			<td><%=vo.getCnt()%></td>
		</tr>
		<%}%>
	<%}%>
		
	</table>
	<a href="/bbs/jspsrc/login.jsp"><input type="button" value="로그인" /></a>
	<%	int n = (int) (dao.getSearchCount(searchField, searchText) / look + 1);
		for (int i = 1; i <= n; i++) { %>
			<a href="/bbs/news?action=search&searchField=<%=searchField%>&searchText=<%=searchText%>&pageNumber=<%=i%>&looknum=<%=look%>">[<%=i%>]</a>
	<% } %>
	<a href="/bbs/news?pageNumber=1&looknum=<%=look%>"><input type="button" value="전체 글 목록"/></a>
	
	<div class="container">
		<div class="row">
			<form method="get" action="/bbs/jspsrc/searchNewsView.jsp">
			<input type="hidden" name="action" value="search">
				<table class="pull-right">
					<tr>
						<td><select class="form-control" name="searchField">
								<option value="0">선택</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
						</select></td>
						<td><input type="text" class="form-control"
							placeholder="검색어 입력" name="searchText" maxlength="100"></td>
						<td><button type="submit" class="btn btn-success">검색</button></td>
					</tr>

				</table>
			</form>
		</div>
	</div>
</body>
</html>