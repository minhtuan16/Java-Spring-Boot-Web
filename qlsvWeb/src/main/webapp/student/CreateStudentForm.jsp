<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Student</title>
</head>
<body>

	<form action="/qlsvWeb/create-student" method="post">
	  <label>id:</label>
	  <br> <input type="number" name="ID" value="">
	  <br> <label>name:</label>
	  <br> <input type="text" name="name" value="">
	  <br> <label>address:</label>
	  <br> <input type="text" name="address" value="">
	  <br> <label>maSV:</label>
	  <br> <input type="text" name="mSV" value="">
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
	
	<a href = '/qlsvWeb/menu-student'>Back</a>
</body>
</html>