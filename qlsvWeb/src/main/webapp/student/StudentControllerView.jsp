<%@page import="model.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Menu Student</title>
</head>
<body>
	<h1>Menu Student</h1>
	<a href = '/qlsvWeb/create-student'>Create Student</a>
	
	<%
	List<Student> sList = (List<Student>) request.getAttribute("sList");
	
	for(Student s : sList) {
		%> 
		<p>------------------</p>
		<p>ID: <%=s.getId()%> </p>
		<p>Name: <%=s.getName()%> </p>
		<p>Address: <%=s.getAddress()%> </p>
		<p>Ma SV: <%=s.getMaSV()%> </p>
				
		<a href = '/qlsvWeb/update-student?id=<%=s.getId()%>&name=<%=s.getName()%>&address=<%=s.getAddress()%>&maSV=<%=s.getMaSV()%>'>Update Student</a>
		<a href = '/qlsvWeb/delete-student?id=<%=s.getId()%>'>Delete Student</a>
		<%
	}
	%>
	
	<%
	String msg = (String) request.getAttribute("msg");
	if(msg != null) {
		%> 
		<p><%=msg%></p>
		<% 
	}
	%>
</body>
</html>