<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録 -バズミシュラン</title>
<!-- CSSの読み込みを追加 -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/top.css">
</head>
<body>
    <%-- ログイン前ヘッダー読み込み用 --%>
    <jsp:include page="headerTop.jsp" />
<h1>ユーザー登録</h1>
<c:if test="${not empty requestScope.errorMsgs}">
    <div style="color: red;">
        <ul>
            <c:forEach var="errorMsg" items="${requestScope.errorMsgs}">
                <li><c:out value="${errorMsg}"/></li>
            </c:forEach>
        </ul>
    </div>
</c:if>
<form action="RegisterConfirmServlet" method="post">
ユーザーID<br>
<input type="text" name="userId" value="${requestScope.userId}"><br>
<small style="color: gray;">※40文字以内、半角英数字と一部記号（例: ! - / : @ など）</small><br><br>

パスワード<br>
<input type="password" name="pass"><br>
<small style="color: gray;">※8〜40文字、半角英数字と一部記号、英字・数字をそれぞれ1文字以上含む</small><br><br>

ユーザー名<br>
<input type="text" name="name" value="${requestScope.name}"><br>
<small style="color: gray;">※40文字以内</small><br><br>

プロフィール<br>
<textarea name="profile" rows="4" cols="40">${requestScope.profile}</textarea><br>
	<input type="submit" value="確認">
</form>
<p><a href="TopServlet">戻る</a></p>
</body>
</html>