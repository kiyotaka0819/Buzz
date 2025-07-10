<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String selectedShopFromSession = (String) session.getAttribute("selectedShopForPost");
String shopNameValue = (selectedShopFromSession != null && !selectedShopFromSession.isEmpty()) ? selectedShopFromSession
		: "";

/*
if (selectedShopFromSession != null) {
	session.removeAttribute("selectedShopForPost");
}
*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投稿ページ -バズミシュラン</title>
</head>
<body>

<!-- ユーザーID -->
  ${user_id}<br>
	<%
	if (shopNameValue != null && !shopNameValue.isEmpty()) {
	%>
	<p>
		選択店舗: **<%=shopNameValue%>**  <a
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
	<%-- 
	<p><select name="category">
	<option value="ramen">ラーメン</option>
	<option value="italy">イタリアン</option>
	<option value="yakiniku">焼肉</option>
</select></p>
--%>
<form action="<%=request.getContextPath()%>/PostServlet" method="post" enctype="multipart/form-data">
<input type="hidden"
			name="selectedShopForPost" value="<%=shopNameValue%>">
コメント<br>
<textarea name="comment" rows="4" cols="40"></textarea><br>
<label for="pictures">画像を添付する</label>
<input id="pictures" type="file" name="pictures"><br>
<button type="submit">つぶやく</button>
</form>
</body>
</html>