<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.PostInfo" %>
<%@ page import="model.Account" %>
<%
  Account user = (Account) request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインメニュー -バズミシュラン</title>
<!-- CSSの読み込み -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/buzz.css">
</head>
<body>
<jsp:include page="header.jsp" />

<h2>
  ようこそ！<%= user != null ? user.name() : "ゲスト" %>  さん
</h2>

<li><a href="PostServlet">つぶやく</a></li>
<li><a href="MypageServlet">マイページ</a></li>
<li><a href="RankingServlet">ランキング</a></li>

<h2>新着つぶやき</h2>
<%
    List<PostInfo> postList = (List<PostInfo>) request.getAttribute("newPosts");
    String sessionUserId = (String) session.getAttribute("userId");

    if (postList != null && !postList.isEmpty()) {
        for (PostInfo post : postList) {
%>
    <div style="border:1px solid #ccc; padding:10px; margin-bottom:10px;">
      <p><strong>店舗：</strong>
      <% 
      	String shopName = post.shopName();
      	if (shopName != null && !shopName.isEmpty()) { 
      %>
      <a href="ShopInfoPageServlet?shopName=<%=post.shopName()%>"><%=post.shopName()%></a>
      <% } else { %>
        未記入
      <%} %>
      </p>

		<p style="white-space: pre-line;">
			<strong>コメント：</strong><%=post.comment()%>
		</p>
		<%
		if (post.pic() != null) {
		%>
        <p><img src="ImageServlet?postId=<%= post.postId() %>" width="200"></p>
      <% } %>

      <%-- ログイン中のユーザー本人の投稿のみ編集・削除可能（表示される） --%>
     <% if (sessionUserId != null && sessionUserId.equals(post.userId())) {%>
      
        <a href="PostEditServlet?postId=<%= post.postId() %>&redirect=MypageServlet">編集</a>
        <!-- 削除リンク -->
      <a href="#" class="delete-link" data-url="PostDeleteServlet?postId=<%= post.postId() %>&redirect=MypageServlet">削除</a> |
      <% } %>
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
  <input type="hidden" name="postId" value="<%= post.postId() %>">
  <button type="submit" class="buzz-button <%= hasBuzzed ? "buzzed" : "" %>">
    <%= hasBuzzed ? "バズ済み✔️" : "バズる🔥" %>
  </button>
  <span class="buzz-count"><%= buzzCount %></span>
</form>
    </div>
<%
        } // end for
    } else {
%>
    <p>新着つぶやきはまだありません。</p>
<%
    } // end if
%>
<!-- モーダル読み込み -->
<jsp:include page="/WEB-INF/jsp/deleteModal.jsp" />

<jsp:include page="footer.jsp" />
<script src="<%= request.getContextPath() %>/js/delete.js"></script>
<script src="<%= request.getContextPath() %>/js/script.js"></script>
<script src="<%= request.getContextPath() %>/js/buzz.js"></script>
</body>
</html>