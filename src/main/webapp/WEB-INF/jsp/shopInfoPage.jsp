<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ShopInfo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店舗情報 -バズミシュラン</title>
</head>
<body>
<ul>
	<form action="ShopEditServlet" method="get">
	<h1>店舗情報</h1>
			<label>店舗名：<%= request.getParameter("shopName") %></label><br>
			<label>住所：<%= request.getParameter("shopAddress") %></label><br>
			<label>URL：</label><br>
			<label>電話番号：</label><br>
			<form>
			<button type="submit">編集</button>
	</form>
	</ul>
<li><a href="MainMenuServlet">メインメニューに戻る</a></li>

</body>
</html>