<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="semesterSchedule" method="post">
		<fieldset>
			<div>
				<label for="id">Semester schedule ID</label> <input type="text"
					name="id" value="<c:out value="${semesterSchedule.id}" />"
					readonly="readonly" placeholder="Semester schedule ID" />
			</div>
			<div>
				<fmt:formatDate var="semesterSchedStartTime"
					value="${semesterSchedule.startDate.getTime()}"
					pattern="yyyy-MM-dd HH:mm:ss" />
				<input type="datetime" name="startDateTime"
					value="<c:out value="${semesterSchedStartTime}" />"
					placeholder="dateTime" />
			</div>
			<div>
				<fmt:formatDate var="semesterSchedEndTime"
					value="${semesterSchedule.endDate.getTime()}"
					pattern="yyyy-MM-dd HH:mm:ss" />
				<input type="datetime" name="endDateTime"
					value="<c:out value="${semesterSchedEndTime}" />"
					placeholder="dateTime" /> <input type="submit"
					value="Update semester schedule info" />
			</div>
		</fieldset>
	</form>
	<form action="semesterSchedule/delete" method="post">
		<fieldset>
			<div>
				<input type="hidden" name="id"
					value="<c:out value="${semesterSchedule.id}" />" /> <input
					type="submit" value="Delete semester schedule" />
			</div>
		</fieldset>
	</form>

	<h3>The schedule unit of the semester schedule</h3>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Date&Time</th>
				<th>Room</th>
				<th>Group</th>
				<th>Discipline</th>
				<th>Lecturer</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<c:forEach var="semesterScheduleUnit"
					items="${semesterSchedule.getSchedules()}">
					<tr>
						<fmt:formatDate var="semesterScheduleUnitTime"
							value="${semesterScheduleUnit.date.getTime()}"
							pattern="yyyy-MM-dd HH:mm:ss" />
						<td><c:out value="${semesterScheduleUnit.id}" /></td>
						<td><c:out value="${semesterScheduleUnitTime}" /></td>

						<td><c:out value="${semesterScheduleUnit.room.name}" /></td>
						<td><c:out value="${semesterScheduleUnit.group.name}" /></td>
						<td><c:out value="${semesterScheduleUnit.discipline.name}" /></td>
						<td><c:out value="${semesterScheduleUnit.lecturer.name}" /></td>

						<td><a
							href="semesterSchedule/deleteScheduleUnit?scheduleUnitId=<c:out value="${semesterScheduleUnit.id}" />&semesterScheduleId=<c:out value="${semesterSchedule.id}"/>">Delete</a></td>
				</c:forEach>

			</tr>
		</tbody>
	</table>

	<h4>Assign the schedule unit to this semester schedule from the
		list below</h4>

	<form action="${context}/semesterSchedule/addScheduleUnit"
		method="post">
		<p>
			<select name="scheduleUnitId">

				<c:forEach var="noSemesterScheduleUnit"
					items="${noSemesterScheduleScheduleUnits}">
					<fmt:formatDate var="scheduleUnitTime"
						value="${noSemesterScheduleUnit.date.getTime()}"
						pattern="yyyy-MM-dd HH:mm:ss" />
					<option value="${noSemesterScheduleUnit.id}">
						${noSemesterScheduleUnit.id} - ${scheduleUnitTime} -
						${noSemesterScheduleUnit.room.name} -
						${noSemesterScheduleUnit.group.name} -
						${noSemesterScheduleUnit.discipline.name}-${noSemesterScheduleUnit.lecturer.name}
					</option>
				</c:forEach>
				<input type="hidden" value="${semesterSchedule.id}"
				name="semesterScheduleId">
			</select>
		</p>
		<p>
			<input type="submit" value="Assign schedule unit">
		</p>
	</form>

	<a href="index.jsp"><c:out value="Back to Start page" /></a>
	<br>
	<br>
</body>
</html>