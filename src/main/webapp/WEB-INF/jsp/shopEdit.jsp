<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>店舗情報編集 -バズミシュラン</title>
</head>
<body>
	<h1>店舗情報編集</h1>
	<form action="ShopInfoPageServlet" method="post">
		<div class="form-group">]
		<label for="shopName">店舗名</label> <input type="text" id="shopName"
				name="shopName" placeholder="店舗名入力">
		</div>

		<div class="form-group">
			<label for="shopAddress">住所</label> 
			<input type="text" name="shopAddress" placeholder="店舗の住所を入力">
		</div>
		
		<div class="form-group">
			<label for="shopURL">URL</label> <input type="text" id="url" name="url"
			placeholder="店舗のURLを入力">
		</div>
		<div class="form-group">
			<label for="shopTEL">電話番号</label> <input type="text" id="tel" name="tel"
				placeholder="店舗の電話番号を入力">
		</div>

		<button type="submit">登録</button>
	</form>
	<li><a href="MainMenuServlet">メインメニューに戻る</a></li>
	<%--CSSでform-groupをまとめて整えるように処理する予定 --%>
</body>
</html>