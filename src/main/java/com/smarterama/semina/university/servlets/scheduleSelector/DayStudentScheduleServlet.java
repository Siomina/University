package com.smarterama.semina.university.servlets.scheduleSelector;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.domain.ScheduleUnit;
import com.smarterama.semina.university.service.ScheduleUnitService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/schedule/dayStudentSchedule")
public class DayStudentScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DayStudentScheduleServlet.class.getName());
	private ScheduleUnitService scheduleUnitService;

	public void init() throws ServletException {
		scheduleUnitService = new ScheduleUnitService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ScheduleUnit> scheduleUnits = new ArrayList<>();

		int studentId = Integer.parseInt(request.getParameter("studentId"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = dateFormat.parse(request.getParameter("dateTime"));
		} catch (ParseException e) {
			log.error("Cannot parse date", e);
			throw new ServletException("Cannot parse date", e);
		}
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		try {
			scheduleUnits = scheduleUnitService.findDayStudentSchedule(studentId, calendar);
			request.setAttribute("scheduleUnits", scheduleUnits);
			request.getRequestDispatcher("/listDayStudentSchedule.jsp").forward(request, response);

		} catch (ServiceException e) {
			log.error("Cannot find scheduleUnits", e);
			throw new ServletException("Cannot find scheduleUnits", e);
		}
	}

}
