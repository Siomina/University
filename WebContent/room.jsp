<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Room</title>
</head>
<body>
	<form action="room" method="post">
		<fieldset>
			<div>
				<label for="id">Room ID</label> <input type="text" name="id"
					value="<c:out value="${room.id}" />" readonly="readonly"
					placeholder="Room ID" />
			</div>
			<div>
				<label for="name">Name</label> <input type="text" name="name"
					value="<c:out value="${room.name}" />" placeholder="name" />
			</div>
			<div>
				<label for="capacity">Capacity</label> <input type="text"
					name="capacity" value="<c:out value="${room.capacity}" />"
					placeholder="Capacity" />
			</div>
			<div>
				<input type="submit" value="Update room info" />
			</div>
		</fieldset>
	</form>

	<form action="room/delete" method="post">
		<fieldset>
			<div>
				<input type="hidden" name="id" value="<c:out value="${room.id}" />" />
				<input type="submit" value="Delete room" />
			</div>
		</fieldset>
	</form>
	<a href="index.jsp"><c:out value="Back to Start page" /></a>
	<br>
	<br>
</body>
</html>