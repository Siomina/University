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

@WebServlet("/semesterSchedule/deleteScheduleUnit")
public class DeleteSemesterScheduleScheduleUnitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DeleteSemesterScheduleScheduleUnitServlet.class.getName());
	private SemesterScheduleService semesterScheduleService;

	public void init() throws ServletException {
		semesterScheduleService = new SemesterScheduleService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int semesterScheduleId = Integer.parseInt(request.getParameter("semesterScheduleId"));
			int scheduleUnitId = Integer.parseInt(request.getParameter("scheduleUnitId"));
			semesterScheduleService.deleteScheduleUnit(semesterScheduleId, scheduleUnitId);
			response.sendRedirect(request.getContextPath() + "/semesterSchedule?id="
					+ semesterScheduleId);

		} catch (ServiceException e) {
			log.error("Cannot delete schedule unit from the semester schedule", e);
			throw new ServletException("Cannot delete schedule unit from the semester schedule", e);
		}
	}
}