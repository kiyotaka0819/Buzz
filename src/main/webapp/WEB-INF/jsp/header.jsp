<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
	<div style="display: flex; justify-content: space-between; align-items: center; padding: 5px; border-bottom: 2px solid #ccc;">
		<div class="logo">
			<a href="<%= request.getContextPath() %>/MainMenuServlet"> <img
				src="<%= request.getContextPath() %>/image/logo.png"
				alt="バズミシュラン ロゴ" style="height: 100px;">
			</a>
		</div>
		<div class="search-bar">
			<form action="<%=request.getContextPath()%>/SearchResultServlet" method="get">
				<label for="query">検索キーワード:</label>
				<input type="text" id="query" name="searchName" placeholder="店舗名、料理名などを入力">
				<button type="submit">検索</button>
			</form>
		</div>
		<nav></nav>
	</div>
</header>
<hr>