package com.smarterama.semina.university.servlets.scheduleUnit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.service.ScheduleUnitService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/scheduleUnit/addLecturer")
public class AddScheduleUnitLecturerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(AddScheduleUnitLecturerServlet.class.getName());
	private ScheduleUnitService scheduleUnitService;

	public void init() throws ServletException {
		scheduleUnitService = new ScheduleUnitService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int scheduleUnitId = Integer.parseInt(request.getParameter("scheduleUnitId"));
			int lecturerId = Integer.parseInt(request.getParameter("lecturerId"));
			scheduleUnitService.addLecturer(scheduleUnitId, lecturerId);
			response.sendRedirect(request.getContextPath() + "/scheduleUnit?id=" + scheduleUnitId);

		} catch (ServiceException e) {
			log.error("Cannot add lecturer to the schedule unit", e);
			throw new ServletException("Cannot add lecturer to the schedule unit", e);
		}
	}
}
