<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="top-ribbon">
    全国のグルメ情報をみんなと共有　～バズミシュラン～
</div>

<header>
	<div class="header-content">
		<div class="logo">
			<a href="<%=request.getContextPath()%>/MainMenuServlet"> 
			<img src="<%=request.getContextPath()%>/image/logo.png"
				alt="バズミシュラン ロゴ">
			</a>
		</div>
		<div class="search-bar">
			<form action="<%=request.getContextPath()%>/SearchResultServlet" method="get">
				<label for="query">検索キーワード:</label> <input type="text" id="query"
					name="searchWord" placeholder="店舗名、料理名などを入力">
				<button type="submit">
					<img src="<%=request.getContextPath()%>/image/searchButton.png" alt="検索">検索
				</button>
			</form>
		</div>
		<nav class="hamburger-menu-container">
			<div class="hamburger-icon">
				<span class="bar"></span> <span class="bar"></span> <span
					class="bar"></span>
			</div>
			<ul class="nav-links">
				<li><a href="PostServlet">つぶやく</a></li>
				<li><a href="MypageServlet">マイページ</a></li>
				<li><a href="RankingServlet">ランキング</a></li>
			</ul>
		</nav>
	</div>
</header>