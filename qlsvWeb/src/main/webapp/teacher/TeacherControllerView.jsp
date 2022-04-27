<%@page import="model.Teacher"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Teacher</title>
</head>
<body>
	<h1>Menu Teacher</h1>
	<a href = '/qlsvWeb/create-teacher'>Create Teacher</a>
	
	<%
	List<Teacher> tList = (List<Teacher>) request.getAttribute("tList");
	
	for(Teacher t : tList) {
		%>
		<p>id: <%=t.getId() %></p>
		<p>name: <%=t.getName() %></p>
		<p>address: <%=t.getAddress() %></p>
		<p>email: <%=t.getEmail() %></p>
		<a href = '/qlsvWeb/update-teacher?id=<%=t.getId()%>&name=<%=t.getName()%>&address=<%=t.getAddress()%>&email=<%=t.getEmail()%>'>Update Teacher</a>
		<a href = '/qlsvWeb/delete-teacher?id=<%=t.getId()%>'>Delete Teacher</a>
		<% 
	}
	%>
	
	<%
	String msg = (String) request.getAttribute("msg");
	if(msg != null) {
		%>
		<p><%=msg %></p>
		<% 
	}
	%>
</body>
</html>