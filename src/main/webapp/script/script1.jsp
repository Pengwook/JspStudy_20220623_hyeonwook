<%@page import= "java.util.ArrayList" %>
<%@page import= "java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%!
	String[] name = new String[5];
	String[] email = new String[5];
	
	
	String madeName(String name) {
		return name + "님";
	}
	
	String madeEmail(String email) {
		return email + "@google.com";
	}
%>
<%
	name[0] = "길현욱";
	email[0] = "khw7219";
	name[1] = "길현우";
	email[1] = "khw7218";
	name[2] = "길현웅";
	email[2] = "khw7217";
	name[3] = "길현움";
	email[3] = "khw7216";
	name[4] = "길현오";
	email[4] = "khw7215";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>이메일</th>
			</tr>
		</thead>
		<tbody>	
		<c:forEach var="name1" items="${name}">
			<tr>
				<td>${name1 }</td>
				<td>${name1 }</td>
				<td>${name1 }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>