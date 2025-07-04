<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録 -バズミシュラン</title>
</head>
<body>
<h1>ユーザー登録</h1>
<form action="RegisterConfirmServlet" >
	ユーザーID<br>
	<input type="text" name="userId"><br>
	パスワード<br>
	<input type="password" name="pass"><br>
	ユーザー名<br>
	<input type="text" name="name"><br>
	プロフィール<br>
	<textarea name="memo" rows="4" cols="40">みなとです。ラーメンが大好きです。好きなラーメン屋は海鳴です。</textarea><br>
	<input type="submit" value="確認">
</form>
<p><a href="TopServlet">戻る</a></p>
</body>
</html>