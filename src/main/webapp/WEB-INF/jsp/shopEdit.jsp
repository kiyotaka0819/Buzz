<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ShopInfo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店舗情報編集 -バズミシュラン</title>
</head>
<body>
<% ShopInfo shopDetail = (ShopInfo)request.getAttribute("shopDetail"); %>
	<h1>店舗情報編集</h1>
	<form action="ShopEditServlet" method="post">
		<div class="form-group">
		<label for="shopName">店舗名</label> <input type="text" id="shopName"
				name="shopName" placeholder="店舗名入力"
				value="<% 
				if(shopDetail != null) {
					out.print(shopDetail.getShopName()); 
				} %>">
		</div>

		<div class="form-group">
			<label for="shopAddress">住所</label> 
			<input type="text" name="shopAddress" placeholder="店舗の住所を入力"
				value="<% 
				if(shopDetail != null) {
					out.print(shopDetail.getShopAddress()); 
				} %>">
		</div>
		
		<div class="form-group">
			<label for="shopURL">URL</label> <input type="text" id="url" name="shopURL"
			placeholder="店舗のURLを入力"
			value="<% 
				if(shopDetail != null) {
					out.print(shopDetail.getShopURL()); 
				} %>">
		</div>
		<div class="form-group">
			<label for="shopTEL">電話番号</label> <input type="text" id="tel" name="shopTEL"
				placeholder="店舗の電話番号を入力"
				value="<% 
				if(shopDetail != null) {
					out.print(shopDetail.getShopTEL()); 
				} %>">
		</div>

		<% if (shopDetail != null) { %>
		<input type="hidden" name="originalShopName"
			value="<%=shopDetail.getShopName()%>">
		<% } %>

		<button type="submit">登録</button>
	</form>
	<li><a href="MainMenuServlet">メインメニューに戻る</a></li>
	<%--CSSでform-groupをまとめて整えるように処理する予定 --%>
</body>
</html>