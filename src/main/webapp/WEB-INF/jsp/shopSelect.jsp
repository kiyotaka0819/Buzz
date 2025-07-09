<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ShopInfo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店舗検索結果 -バズミシュラン</title>
</head>
<body>
	<ul>
		<form action="<%=request.getContextPath()%>/ShopSelectServlet"
			method="get">
			店名<br>
			<input type="text" name="searchName"
				value="<%=request.getAttribute("searchKeyword") != null ? request.getAttribute("searchKeyword") : ""%>">
			<button type="submit">検索</button>
		</form>
	</ul>
	<h2>
		<%
		String displayKeyword = (String) request.getAttribute("searchKeyword");
		if (displayKeyword != null && !displayKeyword.isEmpty()) {
			out.print("「" + displayKeyword + "」の検索結果");
		} else {
			out.print("全店舗一覧");
		}
		%>
	</h2>

	<table border="1">
		<tr>
			<th>店舗名</th>
			<th>URL</th>
			<th>住所</th>
			<th>電話番号</th>
			<th>操作</th>
		</tr>
		<%
		List<ShopInfo> shopList = (List<ShopInfo>) request.getAttribute("shopList");
		if (shopList != null && !shopList.isEmpty()) {
			for (ShopInfo shop : shopList) {
		%>
		<tr>
			<td><a href="/Buzz/ShopInfoPageServlet?shopName=<%=shop.getShopName()%>">
					<%=shop.getShopName()%>
			</a></td>
			<td><a href="<%=shop.getShopURL()%>" target="_blank"><%=shop.getShopURL()%></a></td>
			<td><%=shop.getShopAddress()%></td>
			<td><%=shop.getShopTEL()%></td>
			<td>
				<form action="<%=request.getContextPath()%>/PostServlet"
					method="post">
					<input type="hidden" name="shopName"
						value="<%= shop.getShopName() %>">
					<button type="submit">選択</button>
				</form>
			</td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="5">該当する店舗情報はありません。</td>
		</tr>
		<%
		}
		%>
	</table>
	<br>

	<h2>新規店舗登録</h2>
	<form action="<%=request.getContextPath()%>/ShopSelectServlet"
		method="post">
		<label for="newShopNameInput">新規店舗名:</label> <input type="text"
			id="newShopNameInput" name="newShopName" placeholder="新規店舗名入力"
			required><br> <label for="newShopAddressInput">店舗の住所:</label>
		<input type="text" id="newShopAddressInput" name="newShopAddress"
			placeholder="店舗の住所を入力" required><br> <label
			for="newShopURLInput">店舗のURL:</label> <input type="text"
			id="newShopURLInput" name="newShopURL" placeholder="店舗のURLを入力"
			required><br> <label for="newShopTelInput">店舗の電話番号:</label>
		<input type="text" id="newShopTelInput" name="newShopTEL"
			placeholder="店舗の電話番号を入力" required><br>

		<button type="submit">登録</button>
	</form>
	<br>

	<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
	<% if (errorMessage != null && !errorMessage.isEmpty()) { %>
	<p style="color: red;"><%=errorMessage%></p>
	<% } %>
	<% String message = (String) request.getAttribute("message"); %>
	<% if (message != null && !message.isEmpty()) { %>
	<p style="color: green;"><%=message%></p>
	<% } %>

	<jsp:include page="footer.jsp" />
</body>
</html>