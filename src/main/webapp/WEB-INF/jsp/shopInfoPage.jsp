<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ShopInfo"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店舗情報 -バズミシュラン</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
	<%
	ShopInfo shopDetail = (ShopInfo)request.getAttribute("shopDetail");
	%>
	<ul>
		<form action="ShopEditServlet" method="get">
			<h2>店舗情報</h2>
			<label>店舗名：<%
			if (shopDetail != null) {
				out.print(shopDetail.shopName());
			} else {
				out.print("店舗情報なし");
			}
			%></label><br> 
			
			<label>住所：<%
			if(shopDetail != null) {
				out.print(shopDetail.shopAddress());
			} else {
			 	out.print("住所情報なし");
			 }%></label><br> 
			 
			<label>URL：<%
			if (shopDetail != null) {
				String url = shopDetail.shopURL();
				if (url != null && !url.isEmpty()) {
					// 最初省略してる場合はこちらで付け足しておく
					if (!url.startsWith("http://") && !url.startsWith("https://")) {
						url = "http://" + url;
					}
					out.print("<a href=\"" + url + "\" target=\"_blank\">");
					out.print(shopDetail.shopURL());
					out.print("</a>");
				} else {
					out.print("URL情報なし");
				}
			} else {
				out.print("URL情報なし");
			}
			%></label><br>
			
			<label>電話番号：<%
			if (shopDetail != null && shopDetail.shopTEL() 
			!= null && !shopDetail.shopTEL().isEmpty()) {
			 } else {
		 	out.print("電話番号情報なし");
		 }%></label><br>
		 
		 <% if(shopDetail != null) { %>
            <input type="hidden" name="shopNameForEdit" value="<%= shopDetail.shopName() %>">
            <% } %>
			<button type="submit">編集</button>
		</form>
	</ul>
<jsp:include page="footer.jsp" />
</body>
</html>