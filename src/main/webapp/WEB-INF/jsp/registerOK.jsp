<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録完了 -バズミシュラン</title>
<!-- CSSの読み込みを追加 -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/top.css">
</head>
<body>
    <%-- ログイン前ヘッダー読み込み用 --%>
    <jsp:include page="headerTop.jsp" />
    <p>ユーザーID：<c:out value="${sessionScope.userId}" />を登録しました</p>
<a href="MainMenuServlet">メインメニューへ</a>
</body>
</html>