<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Group</title>
</head>
<body>
	<form action="group" method="post">
		<fieldset>
			<div>
				<label for="id">Group ID</label> <input type="text" name="id"
					value="<c:out value="${group.id}" />" readonly="readonly"
					placeholder="Group ID" />
			</div>
			<div>
				<label for="name">Name</label> <input type="text" name="name"
					value="<c:out value="${group.name}" />" placeholder="name" />
			</div>
			<div>
				<input type="submit" value="Update group info" />
			</div>
		</fieldset>
	</form>

	<form action="deleteGroup" method="post">
		<fieldset>
			<div>
				<input type="hidden" name="id" value="<c:out value="${group.id}" />" />
				<input type="submit" value="Delete group" />
			</div>
		</fieldset>
	</form>

	<h3>Students of group</h3>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${group.getStudents()}" var="student">

				<tr>
					<td><c:out value="${student.id}" /></td>
					<td><c:out value="${student.name}" /></td>

					<td><a
						href="group/deleteStudent?studentId=<c:out value="${student.id}" />&groupId=<c:out value="${group.id}"/>">Delete</a></td>
				</tr>

			</c:forEach>
		</tbody>
	</table>

	<h4>Add student to this group from the list below</h4>

	<form action="${context}/group/addStudent" method="post">
		<p>
			<select name="studentId">

				<c:forEach var="student" items="${noGroupStudents}">
					<option value="${student.id}">${student.id} -
						${student.name}</option>
				</c:forEach>

				<input type="hidden" value="${group.id}" name="groupId">
			</select>
		</p>
		<p>
			<input type="submit" value="Add">
		</p>
	</form>
	<a href="index.jsp"><c:out value="Back to Start page" /></a>
	<br>
	<br>
</body>
</html>