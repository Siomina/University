<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show All Schedule Units</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>Schedule Unit ID</th>
				<th>Date</th>
				<th colspan="2">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${scheduleUnits}" var="scheduleUnit">
				<tr>
					<td><c:out value="${scheduleUnit.id}" /></td>
					<fmt:formatDate var="time2" value="${scheduleUnit.date.getTime()}"
						pattern="yyyy-MM-dd HH:mm" />
					<td><c:out value="${time2}" /></td>
					<td><a
						href="scheduleUnit?id=<c:out value="${scheduleUnit.id}" />">Show
							info</a></td>
			</c:forEach>
		</tbody>
	</table>
	<form action="scheduleUnits" method="post">
		<fieldset>
			<h3>Add new Schedule Unit</h3>

			<p>
				choose date: <input type=datetime name="dateTime"
					value="2017-01-13 20:00:00"> <input type="submit"
					value="submit">
			</p>
		</fieldset>
	</form>
	<a href="index.jsp"><c:out value="Back to Start page" /></a>
	<br>
	<br>
</body>
</html>