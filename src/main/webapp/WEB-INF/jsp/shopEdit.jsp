<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ShopInfo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店舗情報編集 -バズミシュラン</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<%
ShopInfo shopDetail = (ShopInfo)request.getAttribute("shopDetail");
%>
	<h1>店舗情報編集</h1>
	<form action="ShopEditServlet" method="post">
		<div class="form-group">
		<label for="shopName">店舗名</label> <input type="text" id="shopName"
				name="shopName" placeholder="店舗名入力"
				value="<% 
				if(shopDetail != null) {
					out.print(shopDetail.shopName()); 
				} %>">
		</div>

		<div class="form-group">
			<label for="shopAddress">住所</label> <input type="text"
				name="shopAddress" placeholder="店舗の住所を入力"
				value="<%if (shopDetail != null) {
					out.print(shopDetail.shopAddress());
				} else {
					out.print(" ");
				}%>">
		</div>

		<div class="form-group">
			<label for="shopURL">URL</label> <input type="text" id="url" name="shopURL"
			placeholder="店舗のURLを入力"
			value="<% 
				if(shopDetail != null) {
					out.print(shopDetail.shopURL()); 
				} %>">
		</div>
		<div class="form-group">
			<label for="shopTEL">電話番号</label> <input type="text" id="tel" name="shopTEL"
				placeholder="店舗の電話番号を入力"
				value="<% 
				if(shopDetail != null) {
					out.print(shopDetail.shopTEL()); 
				} %>">
		</div>

		<% if (shopDetail != null) { %>
		<input type="hidden" name="originalShopName"
			value="<%=shopDetail.shopName()%>">
		<% } %>

		<button type="submit">登録</button>
	</form>
	<li><a href="MainMenuServlet">メインメニューに戻る</a></li>
<jsp:include page="footer.jsp" />
<script src="<%= request.getContextPath() %>/js/script.js"></script>
</body>
</html>