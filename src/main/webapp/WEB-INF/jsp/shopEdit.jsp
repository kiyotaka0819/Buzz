<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店舗情報編集 -バズミシュラン</title>
</head>
<body>
<ul>
<h1>店舗情報編集</h1>
	<form action="ShopInfoPageServlet" method="get">
		<input type="text" id="query" name="q" placeholder="店舗名入力"><br><!--検索キーワードの入力を保持する-->
			<input type="text" id="query" name="q" placeholder="店舗の住所を入力"><br>
			<input type="text" id="query" name="q" placeholder="店舗のURLを入力"><br>
			<input type="text" id="query" name="q" placeholder="店舗の電話番号を入力"><br>
			<form>
			<button type="submit">登録</button>
	</form>
	</ul>
<li><a href="MainManuServlet">メインメニューに戻る</a></li>

</body>
</html>