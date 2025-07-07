<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店舗検索結果 -バズミシュラン</title>
</head>
<body>
	<ul>
	<form action="/shopSelectServlet" method="get">
		<label for="query">店舗名：</label>
		<input type="text" id="query" name="q" placeholder="検索キーワードが表示"><!--検索キーワードの入力を保持する-->
		<button type="submit">検索</button>
	</form>
	</ul>
<h2>「検索キーワード」の検索結果</h2> <!--ユーザーが検索したキーワードでDB検索-->
<!--DBに存在したら表示される-->

<ul>
	<form action="/shopSelectServlet" method="get">
		<input type="text" id="query" name="q" placeholder="新規店舗名入力"><br><!--検索キーワードの入力を保持する-->
	<form action="/search" method="get">
			<input type="text" id="query" name="q" placeholder="店舗の住所を入力"><br>
			<input type="text" id="query" name="q" placeholder="店舗のURLを入力"><br>
			<input type="text" id="query" name="q" placeholder="店舗の電話番号を入力"><br>
			<form>
			<button type="submit">登録</button>
	</form>
</ul>
<a href="https://ichiran.com/shop/kyushu/sohonten/">一蘭本社総本店</a>
	<form action="PostServlet" method="post" name="frmMain">
		<input type="hidden" name="hidMode" value="1">
		<button type="submit">選択</button>
	</form>
<jsp:include page="footer.jsp" />
</body>
</html>