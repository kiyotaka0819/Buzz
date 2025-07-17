<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.PostInfo, model.ShopInfo" %>
<% 
Map<String, List<PostInfo>> rankingMap = (Map<String, List<PostInfo>>) request.getAttribute("rankingMap");
Map<String, ShopInfo> shopInfoMap = (Map<String, ShopInfo>) request.getAttribute("shopInfoMap");

String[] ranks = { "第1位", "第2位", "第3位" };
String[] colors = { "gold", "silver", "bronze" };
int index = 0;
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp" />
<meta charset="UTF-8">
<title>バズ飯ランキング -バズミシュラン</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<ul>
<h2>👑バズミシュランのバズ飯ランキング👑</h2>
<table border="1" style="width: 600px">
    <tr bgcolor= gold>
        <th>順位</th>
        <th>みんなのつぶやき例</th>
    </tr>

<%
	for (Map.Entry<String, List<PostInfo>> entry : rankingMap.entrySet()) {
		String shopName = entry.getKey();
		List<PostInfo> comments = entry.getValue();
		String rankDisplay = (index < ranks.length) ? ranks[index] : (index + 1) + "位";
		String colorClass = (index < colors.length) ? colors[index] : "";
%>
	<tr bgcolor="<%=colorClass%>">
		<th> 
            <%= rankDisplay %> : 
            <a href="ShopInfoPageServlet?shopName=<%=shopName%>"><%=shopName%></a>
			<%
			ShopInfo shopDetails = shopInfoMap != null ? shopInfoMap.get(shopName) : null;
			if (shopDetails != null) {
			%>
			<p><%=(shopDetails.shopAddress() != null) ? shopDetails.shopAddress() : "情報なし"%></p>
			<p><%=(shopDetails.shopTEL() != null) ? shopDetails.shopTEL() : "情報なし"%></p>
			<%
			} else { // shopDetailsがnullの場合
			%>
			<p>店舗情報なし</p> 
			<% } %>
		</th>
		<td style="background-color: #c0f0e8;"> 
			<% for (PostInfo post : comments) { %>
				<p><%=post.comment()%></p>
			<% } %>
		</td>
	</tr>
<%
		index++; // Rankingをすべて取得するためにインクリメント
	}
%>

</table>
<jsp:include page="footer.jsp" />
<script src="<%= request.getContextPath() %>/js/script.js"></script>
</body>
</html>