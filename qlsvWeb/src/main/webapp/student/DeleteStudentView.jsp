<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Student</title>
</head>
<body>
	<% 
		String msg = (String) request.getAttribute("msg"); // getAttribute : lay gia tri cua thang request de if else
		if(msg != null) {
	%> 
		<p><%=msg %></p>
	<% 
		}
	%>
	<a href = '/qlsvWeb/menu-student'>Back</a>

</body>
</html>