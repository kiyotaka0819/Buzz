<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp" />
<meta charset="UTF-8">
<title>バズ飯ランキング -バズミシュラン</title>
</head>
<body>
<ul>
<h2>バズミシュランのバズ飯ランキング</h2>
<table border="1" style="width:600px"><tr bgcolor="gold">

<th>第1位：一双</th><!--店名の部分をDBからソートして出力-->
	<td style="background-color: #c0f0e8";>
		<p>みんなのつぶやき例</p>
		<p>『こってりとした泡系豚骨が最高！』</p> <!--ユーザーのつぶやきからいいね多いものを出力-->
		<p>『生にんにくを入れて食べるのが本当においしかった』</p>
		<p>『飲みの後の最高の一杯』</p>
	</td>
</tr>

<tr bgcolor="silver">
<th>第2位：一蘭</th>
	<td style="background-color: #c0f0e8";>
	<p>みんなのつぶやき例</p>
	<p>『顔を合わせずにラーメンが食べられる』</p>
	<p>『癖のない一杯』</p>
	<p>『好みを語るのが楽しい』</p>
	</td>
</tr>

<tr bgcolor="bronze">
<th>第3位：はかたや</th>
	<td style="background-color: #c0f0e8";>
		<p>みんなのつぶやき例</p>
		<p>『290円は安すぎる！』</p>
		<p>『なんだかんだでここに来た』</p>
		<p>『いつ来ても安心する味』</p>
	</td>
</tr>
</table>

<p><a href="MainMenuServlet">メインメニュー</a></p>
<p><a href="MypageServlet">マイページ</a></p>
<p><a href="LoginServlet">ログアウト</a></p>
<jsp:include page="footer.jsp" />
</body>
</html>