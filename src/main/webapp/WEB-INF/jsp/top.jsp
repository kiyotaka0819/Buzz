<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ - バズミシュラン</title>
<%-- CSS読み込み用 --%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/top.css">
</head>
<body>
    <%-- ログイン前ヘッダー読み込み用 --%>
    <jsp:include page="headerTop.jsp" />
    <%-- javascript --%>
    <div class="main-content">
      <div class="slideshow">
      <img src="<%= request.getContextPath() %>/image/slide1.png" class="slide active">
      <img src="<%= request.getContextPath() %>/image/slide2.png" class="slide">
      <img src="<%= request.getContextPath() %>/image/slide3.png" class="slide">
      <img src="<%= request.getContextPath() %>/image/slide4.png" class="slide">
      </div>
      <!-- メインコンテンツ -->
      <div class="container">
        <h1>ようこそ バズミシュラン へ！</h1>
        <h2>みんなのおいしいがここに</h2>
        <p>今すぐあなたのおいしいを投稿しましょう。</p>
        
        <a href="LoginServlet" class="button-link">ログイン</a>
        <br>
        新規の方はこちら
        <a href="RegisterServlet" class="button-link">アカウントを作成</a>
      </div>
    </div>
    <%-- javascript --%>
    <script>
    document.addEventListener('DOMContentLoaded', function() {
    let currentSlide = 0;
    const slides = document.querySelectorAll('.slide');
    const totalSlides = slides.length;

    if (totalSlides === 0) {
        console.warn("No slides found!");
        return;
    }
    function showNextSlide() {
        slides[currentSlide].classList.remove('active');
        currentSlide = (currentSlide + 1) % totalSlides;
        slides[currentSlide].classList.add('active');
    }
    setInterval(showNextSlide, 5000);
});
</script>
    <%-- ログイン前フッター読み込み用 --%>
    <jsp:include page="footerTop.jsp" />
</body>
</html>
