package com.smarterama.semina.university.servlets.semesterSchedule;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.service.SemesterScheduleService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/semesterSchedule/addScheduleUnit")
public class AddSemesterScheduleScheduleUnitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(AddSemesterScheduleScheduleUnitServlet.class.getName());
	private SemesterScheduleService semesterScheduleService;

	public void init() throws ServletException {
		semesterScheduleService = new SemesterScheduleService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int semesterScheduleId = Integer.parseInt(request.getParameter("semesterScheduleId"));
			int scheduleUnitId = Integer.parseInt(request.getParameter("scheduleUnitId"));
			semesterScheduleService.addScheduleUnit(semesterScheduleId, scheduleUnitId);
			response.sendRedirect(request.getContextPath() + "/semesterSchedule?id="
					+ semesterScheduleId);

		} catch (ServiceException e) {
			log.error("Cannot add schedule unit to the semester schedule", e);
			throw new ServletException("Cannot add schedule unit to the semester schedule", e);
		}
	}
}