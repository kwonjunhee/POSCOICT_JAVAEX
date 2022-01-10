<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="vo.MemberVO"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<% session.removeAttribute("loginSession"); %>
<script type="text/javascript">
	alert("로그아웃에 성공했습니다.");
	document.location.href="/bbs/news";
</script>
</body>
</html>