<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.NewsVO, vo.MemberVO, dao.NewsDAO, dao.MemberDAO, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스 수정</title>
</head>
<body>
	<h1>뉴스 수정</h1>
	<hr>
	<%
	NewsVO nvo = (NewsVO) request.getAttribute("selectedNewsVO");
	MemberVO mvo = (MemberVO) session.getAttribute("loginSession");
	%>
	<form method="post" action="/bbs/news">
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="id" value="<%=nvo.getId()%>">
		제목 : <input type ="text" value="<%=nvo.getTitle()%>" placeholder="제목" name="title"><br>
		작성자: <%=nvo.getWriter()%>
		<br>
		내용 : <textarea rows=10 cols=35 placeholder="내용" name = "content" required><%=nvo.getContent()%></textarea>
		<br>
		<input type="submit" value="수정">
		<input type="reset" value="재작성">
	<hr>
	<a href = "/bbs/news"><input type="submit" value="전체 글 목록"></a>
	</form>
</body>
</html>