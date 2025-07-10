<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール編集 -バズミシュラン</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<h2>プロフィールを変更しました</h2>
<p><a href="MainMenuServlet">メインメニュー</a></p>
<p><a href="MypageServlet">マイページ</a></p>
<p><a href="LogoutServlet">ログアウト</a></p>
</body>
<jsp:include page="footer.jsp" />
</html>