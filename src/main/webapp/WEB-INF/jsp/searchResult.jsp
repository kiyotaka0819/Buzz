<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.PostInfo"%>
<%@ page import="model.ShopInfo"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果 -バズミシュラン</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<h2>
	  『<%=request.getAttribute("searchWord")%>』の検索結果
	</h2>

	<%-- 投稿の検索結果を表示 --%>
	<h3>投稿</h3>
	<%
		List<PostInfo> postList = (List<PostInfo>) request.getAttribute("postResults");
		String sessionUserId = (String) session.getAttribute("userId");
		
		if (postList != null && !postList.isEmpty()) {
			for (PostInfo post : postList) {
	%>

	<div>
		<p> <strong>投稿ユーザー：</strong>
		<a href="MypageServlet?userId=<%=post.userId()%>"><%=post.userId()%></a>
		</p>
		
		<p> <strong>店舗：</strong>
		<a href="ShopInfoPageServlet?shopName=<%=post.shopName()%>"><%=post.shopName()%></a>
		</p>
		
		<p> <strong>つぶやき：</strong><%=post.comment()%></p>
		
		<%
		if (post.pic() != null && post.pic().length > 0) {
		%>
		<p>
			<img src="ImageServlet?postId=<%=post.postId()%>" width="320"
				height="240">
		</p>
		<%
		}
		%>

		<form action="BuzzServlet" method="post">
			<input type="hidden" name="postId" value="<%=post.postId()%>">
			<button type="submit">バズ</button>
		</form>
		

		<%
		if (sessionUserId != null && sessionUserId.equals(post.userId())) {
		%>
		<a href="PostEditPageServlet?postId=<%=post.postId()%>">編集</a> <a
			href="PostDeleteServlet?postId=<%=post.postId()%>"
			onclick="return confirm('本当に削除しますか？')">削除</a>
		<%
		}
		%>
		
		<hr>
	</div>
	<%
	}
	} else {
	%>
	<p>該当する投稿は見つかりませんでした。</p>
	<%
	}
	%>
	
	<br>
		
	<%-- 店舗の検索結果を表示 --%>
	<%
		List<ShopInfo> shopList = (List<ShopInfo>) request.getAttribute("shopResults");
	%>
	<h3>店舗</h3>
	<% if (shopList != null && !shopList.isEmpty()) { %>
		<% for (ShopInfo shop : shopList) { %>
		<div>
			<p>
				<strong>店舗名：</strong><a href="ShopInfoPageServlet?shopName=<%=shop.shopName()%>"><%=shop.shopName()%></a>
			</p>
			<p><strong>住所：</strong><%= (shop.shopAddress() != null) ? shop.shopAddress() : "情報なし" %></p>
			<p><strong>URL：</strong><%= (shop.shopURL() != null) ? shop.shopURL() : "情報なし" %></p>
			<p><strong>電話番号：</strong><%= (shop.shopTEL() != null) ? shop.shopTEL() : "情報なし" %></p>
			<hr>
		</div>
		<% } %>
	<% } else { %>
		<p>該当する店舗は見つかりませんでした。</p>
	<% } %>
	<jsp:include page="footer.jsp" />
</body>
</html>