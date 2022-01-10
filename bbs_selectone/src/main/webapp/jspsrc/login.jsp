<%@page import="dao.MemberDAO"%>
<%@  page pageEncoding="UTF-8"%>
<%@ page import="vo.MemberVO"%>
<%@ page import="java.util.*"%>

<h2>로그인</h2>
<hr>
<form method="post" action="/bbs/login">
	ID:	<input type="text" placeholder="ID를 입력하세요" name="id" maxlength="12" required><br>
	PW:	<input type="password" placeholder="PW를 입력하세요" name="password" maxlength="12" required><br>
	<input type="submit" value="로그인">
	<a href="/bbs/jspsrc/sign.jsp"><input type="button" value="회원가입" /></a>
</form>