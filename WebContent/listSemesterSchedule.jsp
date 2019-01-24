<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show All Semester Schedules</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>Semester Schedule ID</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th colspan="2">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${semesterSchedules}" var="semesterSchedule">
				<tr>
					<td><c:out value="${semesterSchedule.id}" /></td>
					<fmt:formatDate var="startDate"
						value="${semesterSchedule.startDate.getTime()}"
						pattern="yyyy-MM-dd HH:mm" />
					<td><c:out value="${startDate}" /></td>
					<fmt:formatDate var="endDate"
						value="${semesterSchedule.endDate.getTime()}"
						pattern="yyyy-MM-dd HH:mm" />
					<td><c:out value="${endDate}" /></td>
					<td><a
						href="semesterSchedule?id=<c:out value="${semesterSchedule.id}" />">Show
							info</a></td>
			</c:forEach>
		</tbody>
	</table>
	<form action="semesterSchedules" method="post">
		<fieldset>
			<h3>Add new Semester Schedule</h3>

			<p>
				choose start date: <input type=datetime name="startDateTime"
					value="2017-01-13 20:00:00"> choose end date: <input
					type=datetime name="endDateTime" value="2017-01-13 20:00:00">
				<input type="submit" value="submit">
			</p>
		</fieldset>
	</form>
	<a href="index.jsp"><c:out value="Back to Start page" /></a>
	<br>
	<br>
</body>
</html>