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

@WebServlet("/scheduleUnit/delete")
public class DeleteScheduleUnitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DeleteScheduleUnitServlet.class.getName());
	private ScheduleUnitService scheduleUnitService;

	public void init() throws ServletException {
		scheduleUnitService = new ScheduleUnitService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int scheduleUnitId = Integer.parseInt(request.getParameter("id"));

		try {
			scheduleUnitService.delete(scheduleUnitId);
			response.sendRedirect(request.getContextPath() + "/scheduleUnits");
		} catch (ServiceException e) {
			log.error("Cannot delete schedule unit", e);
			throw new ServletException("Cannot delete schedule unit", e);
		}
	}
}