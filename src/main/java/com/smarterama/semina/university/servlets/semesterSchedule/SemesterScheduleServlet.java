package com.smarterama.semina.university.servlets.semesterSchedule;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.domain.SemesterSchedule;
import com.smarterama.semina.university.service.SemesterScheduleService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/semesterSchedules")
public class SemesterScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(SemesterScheduleServlet.class.getName());
	private SemesterScheduleService semesterScheduleService;

	public void init() throws ServletException {
		semesterScheduleService = new SemesterScheduleService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("semesterSchedules", semesterScheduleService.findAll());
			request.getRequestDispatcher("/listSemesterSchedule.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find all semesterSchedules", e);
			throw new ServletException("Cannot find all semesterSchedules", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SemesterSchedule semesterSchedule = new SemesterSchedule();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate;
		Date endDate;
		try {
			startDate = dateFormat.parse(request.getParameter("startDateTime"));
			endDate = dateFormat.parse(request.getParameter("endDateTime"));

		} catch (ParseException e) {
			log.error("Cannot parse the date of semesterSchedules", e);
			throw new ServletException("Cannot parse the date of semesterSchedules", e);
		}
		GregorianCalendar startDateCalendar = new GregorianCalendar();
		GregorianCalendar endDateCalendar = new GregorianCalendar();
		startDateCalendar.setTime(startDate);
		semesterSchedule.setStartDate(startDateCalendar);
		endDateCalendar.setTime(endDate);
		semesterSchedule.setEndDate(endDateCalendar);

		try {
			semesterScheduleService.create(semesterSchedule);
			response.sendRedirect(request.getContextPath() + "/semesterSchedules");

		} catch (ServiceException e) {
			log.error("Cannot create semesterSchedules", e);
			throw new ServletException("Cannot create semesterSchedules", e);
		}
	}
}
