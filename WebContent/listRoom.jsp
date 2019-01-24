<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show All Rooms</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>Room ID</th>
				<th>Name</th>
				<th>Capacity</th>
				<th colspan="2">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${rooms}" var="room">
				<tr>
					<td><c:out value="${room.id}" /></td>
					<td><c:out value="${room.name}" /></td>
					<td><c:out value="${room.capacity}" /></td>
					<td><a href="room?id=<c:out value="${room.id}" />">Show
							info</a></td>
			</c:forEach>
		</tbody>
	</table>
	<form action="rooms" method="post">
		<fieldset>
			<h2>Add new room</h2>
			<div>
				<label for="name">Name</label> <input type="text" name="name"
					value="<c:out value="${room.name}" />" placeholder="name" /> <label
					for="capacity">Capacity</label> <input type="text" name="capacity"
					value="<c:out value="${room.capacity}" />" placeholder="capacity" />
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