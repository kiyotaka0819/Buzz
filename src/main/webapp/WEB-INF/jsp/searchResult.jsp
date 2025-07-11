<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.PostInfo"%>
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

	<%
		// サーブレットから渡されたpostResultsを取得
		List<PostInfo> postList = (List<PostInfo>) request.getAttribute("postResults");
		
		// ログインユーザーIDも取得 編集・削除リンクの表示用
		String sessionUserId = (String) session.getAttribute("userId");
		
		if (postList != null && !postList.isEmpty()) {
			for (PostInfo post : postList) {
	%>

	<div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;">
		<p> <strong>投稿ユーザー：</strong>
		<a href="MypageServlet?userId=<%=post.userId()%>"><%=post.userId()%></a>
		</p>
		
		<p> <strong>店舗：</strong>
		<a href="ShopInfoPageServlet?shopName=<%=post.shopName()%>"><%=post.shopName()%></a>
		</p>
		
		<p> <strong>コメント：</strong><%=post.comment()%></p>
		
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

		<%-- 暫定でBuzzServlet --%>
		<form action="BuzzServlet" method="post" style="display: inline;">
			<input type="hidden" name="postId" value="<%=post.postId()%>">
			<button type="submit">バズ</button>
		</form>

		<%-- ログイン中のユーザー本人のみ編集・削除可能 --%>
		<%
		if (sessionUserId != null && sessionUserId.equals(post.userId())) {
		%>
		<a href="PostEditPageServlet?postId=<%=post.postId()%>">編集</a> <a
			href="PostDeleteServlet?postId=<%=post.postId()%>"
			onclick="return confirm('本当に削除しますか？')">削除</a>
		<%
		}
		%>
	</div>
	<%
	}
	} else {
	%>
	<p>該当する投稿は見つかりませんでした。</p>
	<%
	}
	%>
	<jsp:include page="footer.jsp" />
</body>
</html>