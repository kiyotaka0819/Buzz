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
<title>ユーザー情報 -バズミシュラン</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />

<h2>ユーザー情報</h2>
<%-- ユーザー情報の場合はパスワード表示と、編集機能不要 --%>
<p>ユーザーID：<%= user.userId() %></p>
<p>ユーザー名：<%= user.name()%></p>
<p>プロフィール：<%= user.profile()%></p>
<hr>

	<h2>投稿一覧</h2>
	<%
	if (postList != null && !postList.isEmpty()) {
		for (PostInfo post : postList) {
	%>
	<div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;">
		<p><strong>店舗：</strong><a href="ShopInfoPageServlet?shopName=<%=post.shopName()%>"><%=post.shopName()%></a></p>
		<p style="white-space: pre-line;"><strong>コメント：</strong><%=post.comment()%></p>
		<%
		if (post.pic() != null) {
		%>
		<p><img src="ImageServlet?postId=<%=post.postId()%>" width="200"></p>
		<%
		}
		%>
		<%
		boolean hasBuzzed = false;
		try {
			hasBuzzed = new dao.BuzzDAO().exists(post.postId(), sessionUserId);
		} catch (Exception e) {
			e.printStackTrace(); // 必要ならログ
		}
		int buzzCount = new dao.BuzzDAO().countBuzz(post.postId());
		%>

		<form class="buzz-form" method="post" action="BuzzServlet">
			<input type="hidden" name="postId" value="<%=post.postId()%>">
			<button type="submit"
				class="buzz-button <%=hasBuzzed ? "buzzed" : ""%>">
				<%=hasBuzzed ? "バズ済み✔️" : "バズる🔥"%>
			</button>
			<span class="buzz-count"><%=buzzCount%></span>
		</form>
	</div>
	<%
	}
	} else {
	%>
	<p>まだ投稿がありません。</p>
	<%}%>


	<jsp:include page="footer.jsp" />
<script src="<%= request.getContextPath() %>/js/script.js"></script>
<script src="<%= request.getContextPath() %>/js/buzz.js"></script>
</body>
</html>