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
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	%>
 	<form action="/qlsvWeb/update-course" method="post">
	  <label>ID:</label>
	  <br> <input type="number" name="ID" value='<%=id%>'>
	  <br> <label>Name:</label>
	  <br> <input type="text" name="name" value='<%=name%>'>
	  <br> <button type="submit">Save</button>
	</form>
	
	<%
	String msg = (String) request.getAttribute("msg");
	if(msg != null) {
		%>
		<p><%=msg %></p>
		<% 
	}
	%>
	
	<a href = '/qlsvWeb/menu-course'>Back</a>
</body>
</html>