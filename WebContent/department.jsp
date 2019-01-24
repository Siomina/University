<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Department</title>
</head>
<body>
	<form action="department" method="post">
		<fieldset>
			<div>
				<label for="id">Department ID</label> <input type="text" name="id"
					value="<c:out value="${department.id}" />" readonly="readonly"
					placeholder="Department ID" />
			</div>
			<div>
				<label for="name">Name</label> <input type="text" name="name"
					value="<c:out value="${department.name}" />" placeholder="name" />
			</div>
			<div>
				<input type="submit" value="Update department info" />
			</div>
		</fieldset>
	</form>

	<form action="department/delete" method="post">
		<fieldset>
			<div>
				<input type="hidden" name="id"
					value="<c:out value="${department.id}" />" /> <input type="submit"
					value="Delete department" />
			</div>
		</fieldset>
	</form>

	<h3>Disciplines of department</h3>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${department.getDisciplines()}" var="discipline">

				<tr>
					<td><c:out value="${discipline.id}" /></td>
					<td><c:out value="${discipline.name}" /></td>

					<td><a
						href="department/deleteDiscipline?disciplineId=<c:out value="${discipline.id}" />&departmentId=<c:out value="${department.id}"/>">Delete</a></td>
				</tr>

			</c:forEach>
		</tbody>
	</table>

	<h4>Add discipline to this department from the list below</h4>

	<form action="${context}/department/addDiscipline" method="post">
		<p>
			<select name="disciplineId">

				<c:forEach var="discipline" items="${noDepartmentDisciplines}">
					<option value="${discipline.id}">${discipline.id} -
						${discipline.name}</option>
				</c:forEach>

				<input type="hidden" value="${department.id}" name="departmentId">
			</select>
		</p>
		<p>
			<input type="submit" value="Add">
		</p>
	</form>

	<h3>Lecturers of department</h3>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Position</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${department.getLecturers()}" var="lecturer">

				<tr>
					<td><c:out value="${lecturer.id}" /></td>
					<td><c:out value="${lecturer.name}" /></td>
					<td><c:out value="${lecturer.position}" /></td>

					<td><a
						href="department/deleteLecturer?lecturerId=<c:out value="${lecturer.id}" />&departmentId=<c:out value="${department.id}"/>">Delete</a></td>
				</tr>

			</c:forEach>
		</tbody>
	</table>

	<h4>Add lecturer to this department from the list below</h4>

	<form action="${context}/department/addLecturer" method="post">
		<p>
			<select name="lecturerId">

				<c:forEach var="lecturer" items="${noDepartmentLecturers}">
					<option value="${lecturer.id}">${lecturer.id} -
						${lecturer.name} - ${lecturer.position}</option>
				</c:forEach>

				<input type="hidden" value="${department.id}" name="departmentId">
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