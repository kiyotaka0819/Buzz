]<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果 -バズミシュラン</title>
</head>
<body>
	<ul>
	<form action="/SearchResult" method="get">
		<label for="query">検索キーワードを入力:</label>
		<input type="text" id="query" name="q" placeholder="気になる料理名などを入力">
		<button type="submit">検索</button>
	</form>
<h2>「カレー」の検索結果</h2> <!--ユーザーが検索したキーワードでDB検索-->
<p><a href="Mypage.html">みなと(minato001)</a></p><!--ユーザー情報画面に遷移-->
<p><a href="ShopInfoPageServlet">吉野家</a></p><!--店舗情報画面に遷移-->
<a href="UserPageServlet">
<p>美味しかった。カレーを選んだが、次は牛丼にしようと思う。</p><!--つぶやき内容に遷移-->
  <img src="CurryRice.jpg" width="320" height="240"alt="カレーライス">
  <form action="MainMenu.html" method="get"><!--1回押すごとにインクリメント-->
  	<button type="submit">バズ</button>
  </form>
</a>
<li><a href="MainManuServlet">メインメニューに戻る</a></li>
</ul>
</body>
</html>