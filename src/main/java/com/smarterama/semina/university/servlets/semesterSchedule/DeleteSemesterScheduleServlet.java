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

@WebServlet("/semesterSchedule/delete")
public class DeleteSemesterScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DeleteSemesterScheduleServlet.class.getName());
	private SemesterScheduleService semesterScheduleService;

	public void init() throws ServletException {
		semesterScheduleService = new SemesterScheduleService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int semesterScheduleId = Integer.parseInt(request.getParameter("id"));

		try {
			semesterScheduleService.delete(semesterScheduleId);
			response.sendRedirect(request.getContextPath() + "/semesterSchedules");
		} catch (ServiceException e) {
			log.error("Cannot delete semester schedule", e);
			throw new ServletException("Cannot delete semester schedule", e);
		}
	}
}
