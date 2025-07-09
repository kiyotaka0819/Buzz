<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String selectedShopFromSession = (String) session.getAttribute("selectedShopForPost");
String shopNameValue = (selectedShopFromSession != null && !selectedShopFromSession.isEmpty()) ? selectedShopFromSession
		: "";
if (selectedShopFromSession != null) {
	session.removeAttribute("selectedShopForPost");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投稿ページ -バズミシュラン</title>
</head>
<body>

｛ユーザー名｝<br>
	<%
	if (shopNameValue != null && !shopNameValue.isEmpty()) {
	%>
	<p>
		選択店舗: **<%=shopNameValue%>** <input type="hidden"
			name="selectedShopForPost" value="<%=shopNameValue%>"> <a
			href="<%=request.getContextPath()%>/PostServlet?clearShop=true">店舗名を変更する</a>
	</p>
	<p></p>
	<%
	} else {
	%>
	<form action="<%=request.getContextPath()%>/ShopSelectServlet"
		method="get">
		店名<br> <input type="text" name="searchName" value="">
		<button type="submit">検索</button>
	</form>
	<%
	}
	%>
	<p><select name="category">
	<option value="ramen">ラーメン</option>
	<option value="italy">イタリアン</option>
	<option value="yakiniku">焼肉</option>
</select></p>
コメント<br>
<textarea name="memo" rows="4" cols="40">投稿内容</textarea><br>
<label for="attachment">添付ファイル</label>
<input id="attachment" type="file" name="attachment"><br>
<a href="MainMenuServlet">つぶやく</a>
</body>
</html>