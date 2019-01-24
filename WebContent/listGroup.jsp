<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show All Groups</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>Group ID</th>
				<th>Name</th>
				<th colspan="2">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${groups}" var="group">
				<tr>
					<td><c:out value="${group.id}" /></td>
					<td><c:out value="${group.name}" /></td>
					<td><a href="group?id=<c:out value="${group.id}" />">Show
							info</a></td>
			</c:forEach>
		</tbody>
	</table>
	<form action="groups" method="post">
		<fieldset>
			<h2>Add new group</h2>
			<div>
				<label for="name">Name</label> <input type="text" name="name"
					value="<c:out value="${group.name}" />" placeholder="name" />
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