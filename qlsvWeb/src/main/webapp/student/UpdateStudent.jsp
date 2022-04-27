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
	String address = request.getParameter("address");
	String maSV = request.getParameter("maSV");
	%>
	<form action="/qlsvWeb/update-student" method="post">
	  <label>id:</label>
	  <br> <input type="number" name="ID" value='<%=id%>'>
	  <br> <label>name:</label>
	  <br> <input type="text" name="name" value='<%=name%>'>
	  <br> <label>address:</label>
	  <br> <input type="text" name="address" value='<%=address%>'>
	  <br> <label>maSV:</label>
	  <br> <input type="text" name="mSV" value='<%=maSV%>'>
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
	
	<a href = '/qlsvWeb/menu-student'>Back</a>
</body>
</html>