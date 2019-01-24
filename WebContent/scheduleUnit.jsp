<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Schedule unit</title>
</head>

<body>
	<form action="scheduleUnit" method="post">
		<fieldset>
			<div>
				<label for="id">Schedule unit ID</label> <input type="text"
					name="id" value="<c:out value="${scheduleUnit.id}" />"
					readonly="readonly" placeholder="Schedule unit ID" />
			</div>
			<div>
				<fmt:formatDate var="schedUnitTime"
					value="${scheduleUnit.date.getTime()}"
					pattern="yyyy-MM-dd HH:mm:ss" />
				<input type="datetime" name="dateTime"
					value="<c:out value="${schedUnitTime}" />" placeholder="dateTime" />
				<input type="submit" value="Update schedule unit info" />
			</div>
		</fieldset>
	</form>
	<form action="scheduleUnit/delete" method="post">
		<fieldset>
			<div>
				<input type="hidden" name="id"
					value="<c:out value="${scheduleUnit.id}" />" /> <input
					type="submit" value="Delete schedule unit" />
			</div>
		</fieldset>
	</form>

	<h3>The room of the schedule unit</h3>
	<c:set var="room" scope="session" value="${scheduleUnit.getRoom()}" />
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Capacity</th>
			</tr>
		</thead>
		<tbody>

			<tr>
				<td><c:out value="${room.id}" /></td>
				<td><c:out value="${room.name}" /></td>
				<td><c:out value="${room.capacity}" /></td>
				<td><a
					href="scheduleUnit/deleteRoom?roomId=<c:out value="${room.id}" />&scheduleUnitId=<c:out value="${scheduleUnit.id}"/>">Delete</a></td>
			</tr>
		</tbody>
	</table>

	<h4>Assign the room to this schedule unit from the list below</h4>

	<form action="${context}/scheduleUnit/addRoom" method="post">
		<p>
			<select name="roomId">

				<c:forEach var="noUnitRoom" items="${noScheduleUnitRooms}">
					<option value="${noUnitRoom.id}">${noUnitRoom.id} -
						${noUnitRoom.name} - ${noUnitRoom.capacity}</option>
				</c:forEach>

				<input type="hidden" value="${scheduleUnit.id}"
				name="scheduleUnitId">
			</select>
		</p>
		<p>
			<input type="submit" value="Assign room">
		</p>
	</form>

	<h3>The group of the schedule unit</h3>
	<c:set var="group" scope="session" value="${scheduleUnit.getGroup()}" />
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>

			<tr>
				<td><c:out value="${group.id}" /></td>
				<td><c:out value="${group.name}" /></td>
				<td><a
					href="scheduleUnit/deleteGroup?groupId=<c:out value="${group.id}" />&scheduleUnitId=<c:out value="${scheduleUnit.id}"/>">Delete</a></td>
			</tr>
		</tbody>
	</table>

	<h4>Assign the group to this schedule unit from the list below</h4>

	<form action="${context}/scheduleUnit/addGroup" method="post">
		<p>
			<select name="groupId">

				<c:forEach var="noUnitGroup" items="${noScheduleUnitGroups}">
					<option value="${noUnitGroup.id}">${noUnitGroup.id} -
						${noUnitGroup.name}</option>
				</c:forEach>

				<input type="hidden" value="${scheduleUnit.id}"
				name="scheduleUnitId">
			</select>
		</p>
		<p>
			<input type="submit" value="Assign group">
		</p>
	</form>

	<h3>The discipline of the schedule unit</h3>
	<c:set var="discipline" scope="session"
		value="${scheduleUnit.getDiscipline()}" />
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>

			<tr>
				<td><c:out value="${discipline.id}" /></td>
				<td><c:out value="${discipline.name}" /></td>
				<td><a
					href="scheduleUnit/deleteDiscipline?disciplineId=<c:out value="${discipline.id}" />&scheduleUnitId=<c:out value="${scheduleUnit.id}"/>">Delete</a></td>
			</tr>
		</tbody>
	</table>

	<h4>Assign the discipline to this schedule unit from the list
		below</h4>

	<form action="${context}/scheduleUnit/addDiscipline" method="post">
		<p>
			<select name="disciplineId">

				<c:forEach var="noUnitDiscipline"
					items="${noScheduleUnitDisciplines}">
					<option value="${noUnitDiscipline.id}">
						${noUnitDiscipline.id} - ${noUnitDiscipline.name}</option>
				</c:forEach>

				<input type="hidden" value="${scheduleUnit.id}"
				name="scheduleUnitId">
			</select>
		</p>
		<p>
			<input type="submit" value="Assign discipline">
		</p>
	</form>

	<h3>The lecturer of the schedule unit</h3>
	<c:set var="lecturer" scope="session"
		value="${scheduleUnit.getLecturer()}" />
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Position</th>
			</tr>
		</thead>
		<tbody>

			<tr>
				<td><c:out value="${lecturer.id}" /></td>
				<td><c:out value="${lecturer.name}" /></td>
				<td><c:out value="${lecturer.position}" /></td>
				<td><a
					href="scheduleUnit/deleteLecturer?lecturerId=<c:out value="${lecturer.id}" />&scheduleUnitId=<c:out value="${scheduleUnit.id}"/>">Delete</a></td>
			</tr>
		</tbody>
	</table>

	<h4>Assign the lecturer to this schedule unit from the list below</h4>

	<form action="${context}/scheduleUnit/addLecturer" method="post">
		<p>
			<select name="lecturerId">

				<c:forEach var="noUnitLecturer" items="${noScheduleUnitLecturers}">
					<option value="${noUnitLecturer.id}">
						${noUnitLecturer.id} - ${noUnitLecturer.name} -
						${noUnitLecturer.position}</option>
				</c:forEach>

				<input type="hidden" value="${scheduleUnit.id}"
				name="scheduleUnitId">
			</select>
		</p>
		<p>
			<input type="submit" value="Assign lecturer">
		</p>
	</form>

	<a href="index.jsp"><c:out value="Back to Start page" /></a>
	<br>
	<br>
</body>
</html>
