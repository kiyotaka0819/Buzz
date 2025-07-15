<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.PostInfo" %>
<% 
Map<String, List<PostInfo>> rankingMap = (Map<String, List<PostInfo>>) request.getAttribute("rankingMap");

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
<table border="1" style="width:600px"><tr bgcolor="gold">

<%
	for(Map.Entry<String, List<PostInfo>> entry : rankingMap.entrySet()){
		String shopName = entry.getKey();
		List<PostInfo> comments = entry.getValue();
	
%>
<tr bgcolor="<%= colors[index] %>">
	<th> <%= ranks[index]%> : 
		<a href="ShopInfoPageServlet?shopName=<%= shopName%>"><%= shopName %></a>
		</th><!--店名の部分をDBからソートして出力-->
		<td style="background-color: #c0f0e8";>
			<p>みんなのつぶやき例</p>
			<% for(PostInfo post : comments ){%>
				<p><%= post.comment() %></p> <!--ユーザーのつぶやきからいいね多いものを出力-->
			<%} %>
		</td>
</tr>
			<%
				index++;
				}
			%>

</table>
<jsp:include page="footer.jsp" />
<script src="<%= request.getContextPath() %>/js/script.js"></script>
</body>
</html>