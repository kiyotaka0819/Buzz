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
	<% ShopInfo shopDetail = (ShopInfo)request.getAttribute("shopDetail"); %>
	<ul>
		<form action="ShopEditServlet" method="get">
			<h1>店舗情報</h1>
			<label>店舗名：<%
			if (shopDetail != null) {
				out.print(shopDetail.getShopName());
			} else {
				out.print("店舗情報なし");
			}
			%></label><br> 
			
			<label>住所：<%
			if(shopDetail != null) {
				out.print(shopDetail.getShopAddress());
			} else {
			 	out.print("住所情報なし");
			 }%></label><br> 
			 
			<label>URL：<%
			if (shopDetail != null) {
				out.print(shopDetail.getShopURL());
			} else {
				out.print("URL情報なし");
			}
			%></label><br>
			
			<label>電話番号：<%
			if (shopDetail != null) {
				out.print(shopDetail.getShopTEL());
			 } else {
		 	out.print("電話番号情報なし");
		 }%></label><br>
		 
		 <% if(shopDetail != null) { %>
            <input type="hidden" name="shopNameForEdit" value="<%= shopDetail.getShopName() %>">
            <% } %>
			<button type="submit">編集</button>
		</form>
	</ul>
	<li><a href="MainMenuServlet">メインメニューに戻る</a></li>

</body>
</html>