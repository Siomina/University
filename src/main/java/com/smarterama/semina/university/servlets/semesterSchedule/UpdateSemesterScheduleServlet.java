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

@WebServlet("/semesterSchedule")
public class UpdateSemesterScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(UpdateSemesterScheduleServlet.class.getName());
	private SemesterScheduleService semesterScheduleService;

	public void init() throws ServletException {
		semesterScheduleService = new SemesterScheduleService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int semesterScheduleId = Integer.parseInt(request.getParameter("id"));

		try {
			request.setAttribute("noSemesterScheduleScheduleUnits", semesterScheduleService.findNoSemesterScheduleScheduleUnits());

			request.setAttribute("semesterSchedule", semesterScheduleService.findOne(semesterScheduleId));

			request.getRequestDispatcher("/semesterSchedule.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find schedule unit", e);
			throw new ServletException("Cannot find schedule unit", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SemesterSchedule semesterSchedule = new SemesterSchedule();
		String semesterScheduleId = request.getParameter("id");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate;
		Date endDate;
		try {
			startDate = dateFormat.parse(request.getParameter("startDateTime"));
			endDate = dateFormat.parse(request.getParameter("endDateTime"));
		} catch (ParseException e1) {
			throw new ServletException("Cannot parse time and date of the semester schedule", e1);
		}

		GregorianCalendar startDateCalendar = new GregorianCalendar();
		GregorianCalendar endDateCalendar = new GregorianCalendar();
		startDateCalendar.setTime(startDate);
		semesterSchedule.setStartDate(startDateCalendar);
		endDateCalendar.setTime(endDate);
		semesterSchedule.setEndDate(endDateCalendar);

		try {
			semesterSchedule.setId(Integer.parseInt(semesterScheduleId));
			semesterScheduleService.update(semesterSchedule);
			response.sendRedirect(request.getContextPath() + "/semesterSchedules");
		} catch (ServiceException e) {
			log.error("Cannot update semesterSchedules", e);
			throw new IOException("Cannot update semesterSchedules", e);
		}
	}
}