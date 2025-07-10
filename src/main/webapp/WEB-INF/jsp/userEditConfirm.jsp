<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール確認 - バズミシュラン</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<jsp:include page="header.jsp" />
<body>
<h1>プロフィール編集確認</h1>
<table>
    <tr>
        <th>ユーザーID:</th>
        <td><c:out value="${sessionScope.editAccount.userId}" /></td>
    </tr>
    <tr>
        <th>パスワード:</th>
        <td>********</td>
    </tr>
    <tr>
        <th>ユーザー名:</th>
        <td><c:out value="${sessionScope.editAccount.name}" /></td>
    </tr>
    <tr>
        <th>プロフィール:</th>
        <td><c:out value="${sessionScope.editAccount.profile}" /></td>
    </tr>
</table>

<form action="UserEditOKServlet" method="post">
    <input type="hidden" name="action" value="confirm">
    <input type="submit" value="変更">
</form>

<form action="UserEditServlet" method="get">
    <input type="submit" value="修正">
</form>
<p><a href="MypageServlet">戻る</a></p>
</body>
<jsp:include page="footer.jsp" />
</html>
