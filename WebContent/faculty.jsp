<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Faculty</title>
</head>
<body>
	<form action="faculty" method="post">
		<fieldset>
			<div>
				<label for="id">Faculty ID</label> <input type="text" name="id"
					value="<c:out value="${faculty.id}" />" readonly="readonly"
					placeholder="Faculty ID" />
			</div>
			<div>
				<label for="name">Name</label> <input type="text" name="name"
					value="<c:out value="${faculty.name}" />" placeholder="name" />
			</div>
			<div>
				<input type="submit" value="Update faculty info" />
			</div>
		</fieldset>
	</form>

	<form action="faculty/delete" method="post">
		<fieldset>
			<div>
				<input type="hidden" name="id"
					value="<c:out value="${faculty.id}" />" /> <input type="submit"
					value="Delete faculty" />
			</div>
		</fieldset>
	</form>

	<h3>Groups of faculty</h3>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${faculty.getGroups()}" var="group">

				<tr>
					<td><c:out value="${group.id}" /></td>
					<td><c:out value="${group.name}" /></td>

					<td><a
						href="faculty/deleteGroup?groupId=<c:out value="${group.id}" />&facultyId=<c:out value="${faculty.id}"/>">Delete</a></td>
				</tr>

			</c:forEach>
		</tbody>
	</table>

	<h4>Add group to this faculty from the list below</h4>

	<form action="${context}/faculty/addGroup" method="post">
		<p>
			<select name="groupId">

				<c:forEach var="group" items="${noFacultyGroups}">
					<option value="${group.id}">${group.id} - ${group.name}</option>
				</c:forEach>

				<input type="hidden" value="${faculty.id}" name="facultyId">
			</select>
		</p>
		<p>
			<input type="submit" value="Add">
		</p>
	</form>

	<h3>Departments of faculty</h3>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${faculty.getDepartments()}" var="department">

				<tr>
					<td><c:out value="${department.id}" /></td>
					<td><c:out value="${department.name}" /></td>

					<td><a
						href="faculty/deleteDepartment?departmentId=<c:out value="${department.id}" />&facultyId=<c:out value="${faculty.id}"/>">Delete</a></td>
				</tr>

			</c:forEach>
		</tbody>
	</table>

	<h4>Add department to this faculty from the list below</h4>

	<form action="${context}/faculty/addDepartment" method="post">
		<p>
			<select name="departmentId">

				<c:forEach var="department" items="${noFacultyDepartments}">
					<option value="${department.id}">${department.id} -
						${department.name}</option>
				</c:forEach>

				<input type="hidden" value="${faculty.id}" name="facultyId">
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