<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.NewsVO, vo.MemberVO, dao.NewsDAO, dao.MemberDAO, java.util.*"%>
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
td:nth-child(3) {width: 400px;}
</style>
</head>
<body>
	<form method="post" action="/bbs/news">
	<%
	NewsVO nvo = (NewsVO) request.getAttribute("selectedNewsVO");
	MemberVO mvo = (MemberVO) session.getAttribute("loginSession");
	%>
	<h2>뉴스 내용</h2>
	<hr>
	<h2><%=nvo.getTitle()%></h2><hr>
		작성자: <%=nvo.getWriter()%>
		작성일: <%=nvo.getWriteDate()%>
		조회수: <%=nvo.getCnt()+1%>
		추천/비추천: <%=nvo.getGood()%>&sol;<%=nvo.getBad()%><hr>
		<%=nvo.getContent()%><hr>
		<a href="/bbs/news"><input type="button" value="전체 글 목록"/></a>
	<% if(mvo.getId().equals(nvo.getWriter())) {// 작성자가 본인인 경우 %>
		<a href='/bbs/news?id=<%=nvo.getId()%>&action=update'><input type="button" value="수정"/></a>
		<a href='/bbs/news?id=<%=nvo.getId()%>&action=delete'><input type="button" value="삭제"/></a>
		<a href='/bbs/news?id=<%=nvo.getId()%>&action=good'><input type="button" value="추천"/></a>
		<a href='/bbs/news?id=<%=nvo.getId()%>&action=bad'><input type="button" value="비추천"/></a>
	<% } else { // 작성자가 본인이 아닌 경우%>
	<a href="/bbs/jspsrc/logout.jsp"><input type="button" value="로그아웃" /></a>
	<a href='/bbs/news?id=<%=nvo.getId()%>&action=good'><input type="button" value="추천"/></a>
	<a href='/bbs/news?id=<%=nvo.getId()%>&action=bad'><input type="button" value="비추천"/></a>
	<%} %>
	</form>
</body>
</html>