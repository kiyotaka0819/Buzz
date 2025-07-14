<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト -バズミシュラン</title>
<!-- CSSの読み込みを追加 -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/top.css">
</head>
<body>
    <%-- ログイン前ヘッダー読み込み用 --%>
    <jsp:include page="headerTop.jsp" />
<p>ログアウトしました</p>
<a href="TopServlet">トップページ</a>
</body>
</html>