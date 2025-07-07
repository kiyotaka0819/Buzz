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
<form action="RegisterOKServlet" method="post">
	<p>ユーザーID:{ユーザーID}</p>
	<p>パスワード：{********}</p>
	<p>ユーザー名:{ユーザー名}</p>
	<p>プロフィール：{みなとです。ラーメンが大好きです。好きなラーメン屋は海鳴です。}</p>
	<input type="submit" value="登録">
<p><a href="TopServlet">戻る</a></p>
</form>
</body>
</html>