<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Schedule Units</title>
</head>
<body>
	<h3>Schedule Units for the student for the given month</h3>
	<table>
		<thead>
			<tr>
				<th>Schedule Unit ID</th>
				<th>Date</th>
				<th>Room</th>
				<th>Group</th>
				<th>Discipline</th>
				<th>Lecturer</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${scheduleUnits}" var="scheduleUnit">
				<tr>
					<td><c:out value="${scheduleUnit.id}" /></td>
					<fmt:formatDate var="time" value="${scheduleUnit.date.getTime()}"
						pattern="yyyy-MM-dd HH:mm" />
					<td><c:out value="${time}" /></td>
					<td><c:out value="${scheduleUnit.room.name}" /></td>
					<td><c:out value="${scheduleUnit.group.name}" /></td>
					<td><c:out value="${scheduleUnit.discipline.name}" /></td>
					<td><c:out value="${scheduleUnit.lecturer.name}" /></td>
			</c:forEach>
		</tbody>
	</table>

	<a href="${context}/index.jsp"><c:out value="Back to Start page" /></a>
	<br>
	<br>
</body>
</html>