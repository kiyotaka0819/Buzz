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
<title>つぶやき編集 -バズミシュラン</title>
</head>
<body>
<h2>つぶやき編集</h2>

<form action="${pageContext.request.contextPath}/PostEditServlet" method="post" enctype="multipart/form-data">

<!-- つぶやきID -->
  <input type="hidden" name="post_id" value="${post_id}"><br>

<!-- 店舗名 -->
<% if (post.shopName() != null && !post.shopName().isEmpty()) { %>
      <p>
        選択店舗: <strong>${post.shopName}</strong>
        <a href="${pageContext.request.contextPath}/PostServlet?clearShop=true">店舗名を変更する</a>
      </p>
      <input type="hidden" name="shop" value="${post.shopName}">
    <% } else { %>
      <p>
        店名<br>
        <input type="text" name="shop" value="">
        <button type="submit" formaction="${pageContext.request.contextPath}/ShopSelectServlet">検索</button>
      </p>
    <% } %>
<%-- 
<p><select name="category">
	<option value="ramen">ラーメン</option>
	<option value="italy">イタリアン</option>
	<option value="yakiniku">焼肉</option>
</select></p>
--%>
コメント<br>
    <textarea name="comment" rows="4" cols="40">${post.comment}</textarea><br>

    <label for="pictures">画像を添付する</label>
    <input id="pictures" type="file" name="pictures"><br>

    <% if (hasPicture != null && hasPicture) { %>
      <input type="checkbox" name="deletePicture" value="true"> 画像を削除する<br>
    <% } %>

    <button type="submit">更新</button>
  </form>
<a href="MypageServlet">キャンセル</a>
</body>
</html>