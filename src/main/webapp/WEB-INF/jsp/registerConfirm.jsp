<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録 -バズミシュラン</title>
</head>
<body>
<h1>入力内容の確認</h1>
<table>
    <tr>
        <th>ユーザーID:</th>
        <td><c:out value="${user.userId}" /></td>
    </tr>
    <tr>
        <th>パスワード:</th>
        <td>********</td> <%-- セキュリティのため表示しない --%>
    </tr>
    <tr>
        <th>名前:</th>
        <td><c:out value="${user.name}" /></td>
    </tr>
    <tr>
        <th>プロフィール:</th>
        <td><c:out value="${user.profile}" /></td>
    </tr>
</table>

	<form action="RegisterOKServlet" method="post">
    <input type="hidden" name="action" value="confirm">
    <input type="submit" value="登録">
</form>
<form action="RegisterServlet" method="post">
    <input type="hidden" name="action" value="edit">
    <input type="submit" value="修正">
<p><a href="TopServlet">戻る</a></p>
</form>
</body>
</html>