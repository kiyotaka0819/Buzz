<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録 -バズミシュラン</title>
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
	パスワード<br>
	<input type="password" name="pass"><br>
	ユーザー名<br>
	<input type="text" name="name" value="${requestScope.name}"><br>
	プロフィール<br>
	<textarea name="profile" rows="4" cols="40">${requestScope.profile}</textarea><br>
	<input type="submit" value="確認">
</form>
<p><a href="TopServlet">戻る</a></p>
</body>
</html>