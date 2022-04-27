<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String msg = (String) request.getAttribute("msg");
	if(msg != null) {
		%>
		<p><%=msg %></p>
		<% 
	}
	%>
	
	<a href = '/qlsvWeb/menu-teacher'>Back</a>
</body>
</html>