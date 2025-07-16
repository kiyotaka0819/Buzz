<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ - バズミシュラン</title>
<%-- CSS読み込み用 --%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/top.css">
</head>
<body>
    <%-- ログイン前ヘッダー読み込み用 --%>
    <jsp:include page="headerTop.jsp" />
    <!-- メインコンテンツ -->
    <div class="container">
        <h1>ようこそバズミシュランへ！</h1>
        <h2>みんなのおいしいがここに</h2>
        <p>今すぐあなたのおいしいを投稿しましょう。</p>

        <a href="RegisterServlet" class="button-link">アカウントを作成</a>
        <br>
        アカウントをお持ちの場合は
        <a href="LoginServlet" class="button-link">ログイン</a>
    </div>
    <%-- ログイン前ヘッダー読み込み用 --%>
    <jsp:include page="footerTop.jsp" />
</body>
</html>
