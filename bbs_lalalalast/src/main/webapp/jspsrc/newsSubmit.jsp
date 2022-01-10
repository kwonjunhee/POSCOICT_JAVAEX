<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.NewsVO, vo.MemberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>뉴스 작성</h1>
	<hr>
	<%
	MemberVO mvo = (MemberVO)session.getAttribute("loginSession");
	%>
	<form method="post" action="/bbs/news">
		<input type="hidden" name="action" value="submit">
		<input type ="text" placeholder="제목" name="title" maxlength='100' required><br>
		<input type="hidden" name="writer" value="<%=mvo.getId() %>">
		<textarea rows=10 cols=35 placeholder="내용" name = "content" maxlength='1000' required></textarea><br>
		<input type="submit" value="등록">
		<input type="reset" value="재작성">
	</form>
	<hr>
	<a href = "/bbs/news"><input type="submit" value="목록"></a>			
	</body>
</html>