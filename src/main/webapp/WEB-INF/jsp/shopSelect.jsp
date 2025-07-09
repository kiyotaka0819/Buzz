<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.ShopInfo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店舗検索結果 -バズミシュラン</title>
</head>
<body>
	<ul>
	<form action="/shopSelectServlet" method="get">
		<label for="query">店舗名：</label>
		<input type="text" id="query" name="searchName" placeholder="検索キーワードを入力" value="<%= request.getAttribute("searchKeyword") != null ? request.getAttribute("searchKeyword") : "" %>">
		<button type="submit">検索</button>
	</form>
	</ul>
	<h2>
		<%
		// searchKeyword があれば表示、なければ「全店舗」と表示
		String displayKeyword = (String) request.getAttribute("searchKeyword");
		if (displayKeyword != null && !displayKeyword.isEmpty()) {
			out.print("「" + displayKeyword + "」の検索結果");
		} else {
			out.print("全店舗一覧");
		}
		%>
	</h2>

	<ul>
	<form action="/shopSelectServlet" method="post">
		<input type="text" id="query" name="newShopName" placeholder="新規店舗名入力"><br><!--検索キーワードの入力を保持する-->
	<form action="/search" method="get">
			<input type="text" id="query" name="newShopAddress" placeholder="店舗の住所を入力"><br>
			<input type="text" id="query" name="newShopURL" placeholder="店舗のURLを入力"><br>
			<input type="text" id="query" name="newShopTEL" placeholder="店舗の電話番号を入力"><br>
			<form>
			<button type="submit">登録</button>
	</form>
</ul>
<a href="https://ichiran.com/shop/kyushu/sohonten/">一蘭本社総本店</a>
	<form action="PostServlet" method="post" name="frmMain">
		<input type="hidden" name="hidMode" value="1">
		<button type="submit">選択</button>
	</form>
<jsp:include page="footer.jsp" />
</body>
</html>