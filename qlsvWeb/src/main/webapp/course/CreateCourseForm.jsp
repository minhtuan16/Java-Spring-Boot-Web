<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/qlsvWeb/create-course" method="post">
	  <label>ID:</label>
	  <br> <input type="number" name="cID">
	  <br> <label>Name:</label>
	  <br> <input type="text" name="cname">
	  <br> <button type="submit">Create</button>
	</form>
	
	<% 
		String msg = (String) request.getAttribute("msg"); // getAttribute : lay gia tri cua thang request de if else
		if(msg != null) {
	%> 
		<p><%=msg %></p>
	<% 
		}
	%>
	<a href = '/qlsvWeb/menu-course'>Quay lai trang chu</a>
</body>
</html>