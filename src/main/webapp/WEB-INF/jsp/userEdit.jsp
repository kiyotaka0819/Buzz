<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール編集 -バズミシュラン</title>
</head>
<body>
<h1>プロフィール編集</h1>
<form action="UserEditConfirmServlet"method="post">
	ユーザーID<br>
	<input type="text" name="userId" value="${userId}"><br>
	パスワード<br>
	<input type="password" name="pass" value="${pass}"><br>
	ユーザー名<br>
	<input type="text" name="name" value="${userName}"><br>
	プロフィール<br>
	<textarea name="memo" rows="4" cols="40">${profile}</textarea><br>
	<input type="submit" value="確認">
</form>
<p><a href="MypageServlet">戻る</a></p>
</body>
</html>