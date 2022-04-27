<%@page import="model.Clazz"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Clazz</title>
</head>
<body>
	<h1>Menu Clazz</h1>
	<a href = '/qlsvWeb/create-clazz'>Create Clazz</a>
	<%
	List<Clazz> cList = (List<Clazz>) request.getAttribute("cList");
	for(Clazz c : cList) {
		%>
		<p>-------------------</p>
		<p>id: <%=c.getId()%> </p>
		<p>name: <%=c.getName() %></p>
		<p>course_id: <%=c.getCourse().getId() %></p>
		<p>namecourse: <%=c.getCourse().getName() %></p>	
		
		<a href = '/qlsvWeb/update-clazz?id=<%=c.getId()%>&name=<%=c.getName()%>&namecourse=<%=c.getCourse().getName()%>'>Update clazz</a>
		
		<a href = '/qlsvWeb/delete-clazz?id=<%=c.getId()%>'>DeleteClazz</a>
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