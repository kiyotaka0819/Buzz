<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<!--searchWordでDB検索-->
	<p>
		<a href="UserServlet">みなと(minato001)</a>
	</p>
	<!--ユーザー情報画面に遷移-->
	<p>
		<a href="ShopInfoPageServlet">吉野家</a>
	</p>
	<!--店舗情報画面に遷移-->
	<a href="UserPageServlet">
		<p>美味しかった。カレーを選んだが、次は牛丼にしようと思う。</p>
		<!--つぶやき内容に遷移--> <img src="image/CurryRice.jpg" width="320"
		height="240" alt="カレーライス">
		<form action="SearchResultServlet" method="get">
			<!--1回押すごとにインクリメント-->
			<button type="submit">バズ</button>
		</form>
	</a>
	<li><a href="MainMenuServlet">メインメニューに戻る</a></li>
	</ul>
	<jsp:include page="footer.jsp" />
</body>
</html>