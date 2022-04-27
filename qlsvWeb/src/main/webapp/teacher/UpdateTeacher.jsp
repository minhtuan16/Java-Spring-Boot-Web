<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Teacher</title>
</head>
<body>
	<%
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String address = request.getParameter("address");
	String email = request.getParameter("email");
	%>

	<form action="/qlsvWeb/update-teacher" method="post">
	  <label>ID:</label>
	  <br> <input type="number" name="ID" value='<%=id%>'>
	  <br> <label>Name:</label>
	  <br> <input type="text" name="name" value='<%=name%>'>
	  <br> <label>Address:</label>
	  <br> <input type="text" name="address" value='<%=address%>'>
	  <br> <label>email:</label>
	  <br> <input type="text" name="email" value='<%=email%>'>
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
	
	<a href = '/qlsvWeb/menu-teacher'>Back</a>
</body>
</html>