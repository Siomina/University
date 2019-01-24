
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="ffffff">

	<img src=image.jpg height="200" width="300" />
	<br>

	<a href="students"><c:out value="Students" /></a>
	<br>
	<br>

	<a href="groups"><c:out value="Groups" /></a>
	<br>
	<br>

	<a href="rooms"><c:out value="Rooms" /></a>
	<br>
	<br>

	<a href="disciplines"><c:out value="Disciplines" /></a>
	<br>
	<br>

	<a href="lecturers"><c:out value="Lecturers" /></a>
	<br>
	<br>

	<a href="departments"><c:out value="Departments" /></a>
	<br>
	<br>

	<a href="faculties"><c:out value="Faculties" /></a>
	<br>
	<br>

	<a href="scheduleUnits"><c:out value="Schedule Units" /></a>
	<br>
	<br>

	<a href="semesterSchedules"><c:out value="Semester Schedules" /></a>
	<br>
	<br>

	<a href="schedule"><c:out
			value="Select Schedule for Student or Lecturer" /></a>
	<br>
	<br>

</body>
</html>