<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン -バズミシュラン</title>
</head>
<body>
    <%-- ログイン前ヘッダー読み込み用 --%>
    <jsp:include page="headerTop.jsp" />
<form action="LoginServlet" method="post">
	<% if(request.getAttribute("errorMsg") != null) { %>
		<p style="color: red;"><%= request.getAttribute("errorMsg") %></p>
	<% } %>
	ユーザーID<br>
	<input type="text" name="userId"><br>
	パスワード<br>
	<input type="password" name="pass"><br>
	<input type="submit" value="ログイン">
</form>
</body>
<p><a href="TopServlet">トップページ</a></p>
</html>