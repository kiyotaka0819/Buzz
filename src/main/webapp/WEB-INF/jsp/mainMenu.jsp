<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.PostInfo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインメニュー -バズミシュラン</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />

<h2>ようこそ！<%= session.getAttribute("userId") %>さん</h2>

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
    <div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;">
        <p><strong>投稿ユーザー：</strong>
            <a href="MypageServlet?userId=<%=post.userId()%>"><%=post.userId()%></a>
        </p>
        
        <p><strong>店舗：</strong>
            <a href="ShopInfoPageServlet?shopName=<%=post.shopName()%>"><%=post.shopName()%></a>
        </p>
        
        <p><strong>コメント：</strong><%=post.comment()%></p>
        
        <% if (post.pic() != null && post.pic().length > 0) { %>
            <p>
                <img src="ImageServlet?postId=<%=post.postId()%>" width="320" height="240">
            </p>
        <% } %>

        <form action="BuzzServlet" method="post" style="display: inline;">
            <input type="hidden" name="postId" value="<%=post.postId()%>">
            <button type="submit">バズ</button>
        </form>

        <% if (sessionUserId != null && sessionUserId.equals(post.userId())) { %>
            <a href="PostEditPageServlet?postId=<%=post.postId()%>">編集</a>
            <a href="PostDeleteServlet?postId=<%=post.postId()%>" onclick="return confirm('本当に削除しますか？')">削除</a>
        <% } %>
    </div>
<%
        } // end for
    } else {
%>
    <p>新着つぶやきはまだありません。</p>
<%
    } // end if
%>

<jsp:include page="footer.jsp" />
<script src="<%= request.getContextPath() %>/js/script.js"></script>
</body>
</html>