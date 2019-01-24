<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show All Faculties</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>Faculty ID</th>
				<th>Name</th>
				<th colspan="2">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${faculties}" var="faculty">
				<tr>
					<td><c:out value="${faculty.id}" /></td>
					<td><c:out value="${faculty.name}" /></td>
					<td><a href="faculty?id=<c:out value="${faculty.id}" />">Show
							info</a></td>
			</c:forEach>
		</tbody>
	</table>
	<form action="faculties" method="post">
		<fieldset>
			<h2>Add new faculty</h2>
			<div>
				<label for="name">Name</label> <input type="text" name="name"
					value="<c:out value="${faculty.name}" />" placeholder="name" />
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