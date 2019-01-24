<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Lecturer</title>
</head>
<body>
	<form action="lecturer" method="post">
		<fieldset>
			<div>
				<label for="id">Lecturer ID</label> <input type="text" name="id"
					value="<c:out value="${lecturer.id}" />" readonly="readonly"
					placeholder="Lecturer ID" />
			</div>
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
				<input type="submit" value="Update lecturer info" />
			</div>
		</fieldset>
	</form>
	<form action="deleteLecturer" method="post">
		<fieldset>
			<div>
				<input type="hidden" name="id"
					value="<c:out value="${lecturer.id}" />" /> <input type="submit"
					value="Delete lecturer" />
			</div>
		</fieldset>
	</form>

	<h3>Disciplines of lecturer</h3>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${lecturer.getDisciplines()}" var="discipline">

				<tr>
					<td><c:out value="${discipline.id}" /></td>
					<td><c:out value="${discipline.name}" /></td>

					<td><a
						href="lecturer/deleteDiscipline?disciplineId=<c:out value="${discipline.id}" />&lecturerId=<c:out value="${lecturer.id}"/>">Delete</a></td>
				</tr>

			</c:forEach>
		</tbody>
	</table>

	<h4>Add discipline to this lecturer from the list below</h4>

	<form action="${context}/lecturer/addDiscipline" method="post">
		<p>
			<select name="disciplineId">

				<c:forEach var="discipline" items="${noLecturerDisciplines}">
					<option value="${discipline.id}">${discipline.id} -
						${discipline.name}</option>
				</c:forEach>

				<input type="hidden" value="${lecturer.id}" name="lecturerId">
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