<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.PostInfo" %>
 <%
String errorMessage = (String) request.getAttribute("errorMessage");
PostInfo post = (PostInfo) request.getAttribute("post");
Boolean hasPicture = (Boolean) request.getAttribute("hasPicture");
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
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<h2>つぶやき編集</h2>

<%--投稿編集が失敗した場合のエラーメッセージ --%>
<% if (errorMessage != null) { %>
  <p style="color:red;"><%= errorMessage %></p>
<% } %>

<form action="<%= request.getContextPath() %>/PostEditServlet" method="post" enctype="multipart/form-data">

<!-- つぶやきID -->
  <input type="hidden" name="postId" value="<%= post.postId() %>"><br>

<!-- 店舗名 -->
<% if (post.shopName() != null && !post.shopName().isEmpty()) { %>
      <p>
        選択店舗: <strong><%= post.shopName() %></strong>
        <a href="<%= request.getContextPath() %>/PostServlet?clearShop=true">店舗名を変更する</a>
      </p>
      <input type="hidden" name="shop" value="<%= post.shopName() %>">
    <% } else { %>
      <p>
        店名<br>
        <input type="text" name="shop" value="">
        <button type="submit" formaction="<%= request.getContextPath() %>/ShopSelectServlet">検索</button>
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
    <textarea name="comment" rows="4" cols="40"><%= post.comment() %></textarea><br>

    <label for="pictures">画像を添付する</label>
    <input id="pictures" type="file" name="pictures"><br>

    <% if (hasPicture != null && hasPicture) { %>
      <input type="checkbox" name="deletePicture" value="true"> 画像を削除する<br>
    <% } %>

    <button type="submit">更新</button>
  </form>
<a href="MypageServlet">キャンセル</a>
<jsp:include page="footer.jsp" />
</body>
</html>