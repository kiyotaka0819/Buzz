<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.PostInfo" %>
<%@ page import="model.Account" %>
<% 
  Account user = (Account) request.getAttribute("user");
  List<PostInfo> postList = (List<PostInfo>) request.getAttribute("postList");
  String sessionUserId = (String) session.getAttribute("userId");
  String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ -バズミシュラン</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />

<h2>ユーザー情報</h2>

<p>ユーザーID：<%= user.userId() %></p>
<p>パスワード：*****</p>
<p>ユーザー名：<%= user.name()%></p>
<p>プロフィール：<%= user.profile()%></p>
<a href="UserEditServlet">プロフィールを編集</a>
<hr>

<h2>投稿一覧</h2>
<%--投稿削除が失敗した場合のエラーメッセージ --%>
<% if (errorMessage != null) { %>
  <p style="color:red;"><%= errorMessage %></p>
<% } %>
<%
  if (postList != null && !postList.isEmpty()) {
    for (PostInfo post : postList) {
%>
    <div style="border:1px solid #ccc; padding:10px; margin-bottom:10px;">
      <p><strong>店舗：</strong><%= post.shopName() %></p>
      <p><strong>コメント：</strong><%= post.comment() %></p>
      <% if (post.pic() != null) { %>
        <p><img src="ImageServlet?postId=<%= post.postId() %>" width="200"></p>
      <% } %>

      <%-- ログイン中のユーザー本人の投稿のみ編集・削除可能（表示される） --%>
     <% if (sessionUserId != null && sessionUserId.equals(post.userId())) {%>
      
        <a href="PostEditServlet?postId=<%= post.postId() %>">編集</a>
        <a href="PostDeleteServlet?postId=<%= post.postId() %>&redirect=MypageServlet"
         	onclick="return confirm('本当に削除しますか？')">削除</a>
      <% } %>
    </div>
<%   
	}
  } else {
%>
    <p>まだ投稿がありません。</p>
<%}%>


<jsp:include page="footer.jsp" />
</body>
</html>