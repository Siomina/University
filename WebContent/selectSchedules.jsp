<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedule</title>
</head>

<body>
	<h3>DAY schedule for a STUDENT</h3>
	<h4>Select the student</h4>
	<form action="${context}/schedule/dayStudentSchedule" method="get">
		<p>
			<select name="studentId">

				<c:forEach var="student" items="${students}">
					<option value="${student.id}">${student.id} -
						${student.name}</option>
				</c:forEach>

			</select>
		</p>
		<h4>Select date</h4>
		<input type=datetime name="dateTime" value="2017-01-13">

		<p>
			<input type="submit" value="Show DAY schedule for Student">
		</p>
	</form>

	<hr width="500" align="left">

	<h3>MONTH schedule for a STUDENT</h3>
	<h4>Select the student</h4>
	<form action="${context}/schedule/monthStudentSchedule" method="get">
		<p>
			<select name="studentId">

				<c:forEach var="student" items="${students}">
					<option value="${student.id}">${student.id} -
						${student.name}</option>
				</c:forEach>

			</select>
		</p>
		<h4>Select date</h4>
		<input type=datetime name="dateTime" value="2017-01">

		<p>
			<input type="submit" value="Show MONTH schedule for Student">
		</p>
	</form>

	<hr width="500" align="left">

	<h3>DAY schedule for a LECTURER</h3>
	<h4>Select the lecturer</h4>
	<form action="${context}/schedule/dayLecturerSchedule" method="get">
		<p>
			<select name="lecturerId">

				<c:forEach var="lecturer" items="${lecturers}">
					<option value="${lecturer.id}">${lecturer.id} -
						${lecturer.name}</option>
				</c:forEach>

			</select>
		</p>
		<h4>Select date</h4>
		<input type=datetime name="dateTime" value="2017-01-13">

		<p>
			<input type="submit" value="Show DAY schedule for Lecturer">
		</p>
	</form>

	<hr width="500" align="left">

	<h3>MONTH schedule for a LECTURER</h3>
	<h4>Select the lecturer</h4>
	<form action="${context}/schedule/monthLecturerSchedule" method="get">
		<p>
			<select name="lecturerId">

				<c:forEach var="lecturer" items="${lecturers}">
					<option value="${lecturer.id}">${lecturer.id} -
						${lecturer.name}</option>
				</c:forEach>

			</select>
		</p>
		<h4>Select date</h4>
		<input type=datetime name="dateTime" value="2017-01">

		<p>
			<input type="submit" value="Show MONTH schedule for Lecturer">
		</p>
	</form>
</body>
</html>