<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール編集 -バズミシュラン</title>
</head>
<body>
<h1>プロフィール編集</h1>
<form action="UserEditOKServlet" >
	<p>ユーザーID:{ユーザーID}</p>
	<p>パスワード：{********}</p>
	<p>ユーザー名:{ユーザー名}</p>
	<p>プロフィール：{みなとです。ラーメンが大好きです。好きなラーメン屋は海鳴です。}</p>
	<input type="submit" value="変更">
</form>
<p><a href="userEditServlet">戻る</a></p>
</body>
</html>