<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインメニュー -バズミシュラン</title>
<%-- CSS読み込み用 --%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<li><a href="PostServlet">つぶやく</a></li>
<li><a href="MypageServlet">マイページ</a></li>
<li><a href="RankingServlet">ランキング</a></li>
<h2>新着つぶやき</h2><!--5~15件程度表示予定-->
<p><a href="MypageServlet">みなと(minato001)</a></p><!--ユーザー情報画面に遷移-->
<p><a href="ShopInfoPageServlet">吉野家</a></p><!--店舗情報画面に遷移-->

<p>美味しかった。カレーを選んだが、次は牛丼にしようと思う。</p><!--つぶやき内容に遷移-->
<a href="UserPageServlet">
  <img src="image/CurryRice.jpg" width="320" height="240"alt="カレーライス">
</a>
<form action="MainMenuServlet" method="get"><!--1回押すごとにインクリメント-->
	<button type="submit">バズ</button>
<input type="button" onclick="location.href='PostEditServlet'" value="編集">
<form action="MainMenuServlet" method="get">
	<button type="submit">削除</button>
</form>
</ul>
<jsp:include page="footer.jsp" />
<script src="<%= request.getContextPath() %>/js/script.js"></script>
</body>
</html>