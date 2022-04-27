<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Teacher</title>
</head>
<body>
	<form action="/qlsvWeb/create-teacher" method="post">
	  <label>id:</label>
	  <br> <input type="number" name="cID" value="">
	  <br> <label>name:</label>
	  <br> <input type="text" name="cname" value="">
	  <br> <label>address:</label>
	  <br> <input type="text" name="caddress" value="">
	  <br> <label>email:</label>
	  <br> <input type="text" name="cemail" value="">
	  <br> <button type="submit">Create</button>
	</form>
	
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