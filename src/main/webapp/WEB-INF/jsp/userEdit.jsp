<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール編集 - バズミシュラン</title>
</head>
<body>
<h1>プロフィール編集</h1>
<c:if test="${not empty errorMsgs}">
    <div style="color:red;">
        <ul>
            <c:forEach var="msg" items="${errorMsgs}">
                <li><c:out value="${msg}" /></li>
            </c:forEach>
        </ul>
    </div>
</c:if>
<form action="UserEditConfirmServlet" method="post">
    ユーザーID<br>
    <input type="text" name="userId" value="${userId}" readonly><br>
    パスワード（変更する場合のみ入力）<br>
    <input type="password" name="pass"><br>
    ユーザー名<br>
    <input type="text" name="name" value="${name}"><br>
    プロフィール<br>
    <textarea name="profile" rows="4" cols="40">${profile}</textarea><br>
    <input type="submit" value="確認">
</form>
<p><a href="MypageServlet">戻る</a></p>
</body>
</html>
