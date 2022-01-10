<%@  page pageEncoding="UTF-8"%>
<h2>회원가입</h2>
<hr>
<form method="post" action="/bbs/members">
	ID: <input type="text" placeholder="ID를 입력하세요" name="id" maxlength="12" required></input><br>
	PW: <input type="password" placeholder="암호를 입력하세요" name="password" maxlength="12" required><br>
	이름: <input type="text" placeholder="이름을 입력하세요" name="name"  maxlength="12" required></input><br>
	휴대폰 번호: <input type="text" placeholder="휴대폰번호를 입력하세요" name="phone" maxlength="12" required></input><br>
	<input type="submit" value="가입하기">
	<input type="reset" value="초기화">
</form>