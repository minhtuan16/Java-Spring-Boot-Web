<%@page import="model.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Course</title>
</head>
<body>

	<h1>Menu Course</h1>
	<a href = '/qlsvWeb/create-course'>Create Course</a>
	
	<%
	List<Course> courseList = (List<Course>) request.getAttribute("courselist");
	
	for(Course c : courseList) {
		%>
			<p>--------------</p>
			<p>ID: <%=c.getId()%></p>
			<p>Name: <%=c.getName()%></p>
			<a href = '/qlsvWeb/delete-course?id=<%=c.getId()%>'>Delete Course</a>
			<a href = '/qlsvWeb/update-course?id=<%=c.getId()%>&name=<%=c.getName()%>'>Update Course</a>
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