<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show All Lecturers</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>Lecturer ID</th>
				<th>Name</th>
				<th>Position</th>
				<th colspan="2">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${lecturers}" var="lecturer">
				<tr>
					<td><c:out value="${lecturer.id}" /></td>
					<td><c:out value="${lecturer.name}" /></td>
					<td><c:out value="${lecturer.position}" /></td>
					<td><a href="lecturer?id=<c:out value="${lecturer.id}" />">Show
							info</a></td>
			</c:forEach>
		</tbody>
	</table>
	<form action="lecturers" method="post">
		<fieldset>
			<h2>Add new lecturer</h2>
			<div>
				<label for="name">Name</label> <input type="text" name="name"
					value="<c:out value="${lecturer.name}" />" placeholder="name" />
			</div>
			<div>
				<label for="position">Position</label> <input type="text"
					name="position" value="<c:out value="${lecturer.position}" />"
					placeholder="position" />
			</div>
			<div>
				<input type="submit" value="Submit" />
			</div>
		</fieldset>
	</form>
	<a href="index.jsp"><c:out value="Back to Start page" /></a>
	<br>
	<br>
</body>
</html>