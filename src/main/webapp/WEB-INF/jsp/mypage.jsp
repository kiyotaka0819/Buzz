<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ -バズミシュラン</title>
</head>
<body>
<ul>
<h2>ユーザー情報を表示します</h2>
<p>ユーザーID：minato001</p>
<p>パスワード：*****</p>
<p>ユーザー名:minato</p>
<p>プロフィール：みなとです。ラーメンが大好きです。</p>
<p>        好きなラーメン屋は海鳴です。</p>
<% if (session.getAttribute("userId").equals(post.getUserId())) { %>
		  <a href="postEdit.jsp?posts_id=<%= post.getPostsId() %>">編集</a>
		<% } %>
<li><a href="PostEditServlet">つぶやきを編集</a></li>
<li><a href="PostDeleteServlet">つぶやきを削除</a></li>
<li><a href="UserEditServlet">プロフィールを編集</a></li>
<li><a href="MainMenuServlet">メインメニュー</a></li>
<li><a href="TopServlet">ログアウト</a></li>
</ul>
</body>
</html>