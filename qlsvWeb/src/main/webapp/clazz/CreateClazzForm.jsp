<%@page import="model.Course"%>
<%@page import="java.util.List"%>
<%@page import="model.Clazz"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	List<Course> courseList = (List<Course>) request.getAttribute("courseList");
	%>
	<form action="/qlsvWeb/create-clazz" method="post">
	  <label>id:</label>
	  <br> <input type="number" name="id">
	  <br> <label>name:</label>
	  <br> <input type="text" name="name">
	  
	  <br> <label>Course name:</label>
	  <br> <select name="course_id">
	  		<%
	  		for(Course c : courseList) {
	  			%>
	  			<option value="<%=c.getId() %>"> <%=c.getName() %> </option> 
	  			<% 
	  		}
	  		%>
	  	</select>
	  
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
	<a href = '/qlsvWeb/menu-clazz'>Back</a>
</body>
</html>