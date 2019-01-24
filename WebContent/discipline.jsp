<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Discipline</title>
</head>
<body>
	<form action="discipline" method="post">
		<fieldset>
			<div>
				<label for="id">Discipline ID</label> <input type="text" name="id"
					value="<c:out value="${discipline.id}" />" readonly="readonly"
					placeholder="Discipline ID" />
			</div>
			<div>
				<label for="name">Name</label> <input type="text" name="name"
					value="<c:out value="${discipline.name}" />" placeholder="name" />
			</div>
			<div>
				<input type="submit" value="Update discipline info" />
			</div>
		</fieldset>
	</form>

	<form action="discipline/delete" method="post">
		<fieldset>
			<div>
				<input type="hidden" name="id"
					value="<c:out value="${discipline.id}" />" /> <input type="submit"
					value="Delete discipline" />
			</div>
		</fieldset>
	</form>
	<a href="index.jsp"><c:out value="Back to Start page" /></a>
	<br>
	<br>
</body>
</html>