<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール編集 - バズミシュラン</title>
<!-- CSSの読み込みを追加 -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<jsp:include page="header.jsp" />
<body>
<h1>プロフィール編集</h1>
<c:if test="${not empty requestScope.errorMsgs}">
    <div style="color:red;">
        <ul>
            <c:forEach var="msg" items="${requestScope.errorMsgs}">
                <li><c:out value="${msg}" /></li>
            </c:forEach>
        </ul>
    </div>
</c:if>
<form action="UserEditConfirmServlet" method="post">
    ユーザーID<br>
    <input type="text" name="userId" value="${userId}" readonly><br>
    
    パスワード（変更する場合のみ入力）<br>
    <input type="password" id="pass" name="pass"><br>
    
    確認用パスワード<br>
    <input type="password" id="confirmPass" name="confirmPass"><br>
    <input type="checkbox" id="togglePassword"> パスワードを表示
    <small style="color: gray;">※8〜40文字、半角英数字と一部記号、英字・数字をそれぞれ1文字以上含む</small><br>
    
    ユーザー名<br>
    <input type="text" name="name" value="${name}"><br>
    <small style="color: gray;">※40文字以内</small><br>
    
    プロフィール<br>
    <textarea name="profile" rows="4" cols="40">${profile}</textarea><br>
    <small style="color: gray;">※200文字以内</small><br>
    <input type="submit" value="確認">
    
</form>
<p><a href="MypageServlet">戻る</a></p>
<script src="<%= request.getContextPath() %>/js/script.js"></script>
<script src="<%= request.getContextPath() %>/js/password.js"></script>
</body>
<jsp:include page="footer.jsp" />
</html>
