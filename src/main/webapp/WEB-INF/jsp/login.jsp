<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン - バズミシュラン</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/top.css">
</head>
<body>
    <%-- ログイン前ヘッダー読み込み用 --%>
    <jsp:include page="headerTop.jsp" />

    <div class="login-wrapper">
        <div class="login-container">
            <h1>ログイン</h1>

            <% if(request.getAttribute("errorMsg") != null) { %>
                <p class="error-msg"><%= request.getAttribute("errorMsg") %></p>
            <% } %>

            <form action="LoginServlet" method="post">
                <label for="userId">ユーザーID</label><br>
                <input type="text" name="userId" id="userId"><br>

                <label for="pass">パスワード</label><br>
                <input type="password" name="pass" id="pass"><br>

                <input type="submit" class="button-link full-width" value="ログイン">
            </form>

            <a href="TopServlet" class="back-link">戻る</a>
        </div>
    </div>

    <%-- ログイン前フッター読み込み用 --%>
    <jsp:include page="footerTop.jsp" />
</body>
</html>
