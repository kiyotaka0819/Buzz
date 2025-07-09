<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録完了 -バズミシュラン</title>
</head>
<body>
    <p>ユーザーID：<c:out value="${sessionScope.userId}" />を登録しました</p>
<a href="MainMenuServlet">メインメニューへ</a>
</body>
</html>