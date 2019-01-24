package com.smarterama.semina.university.servlets.lecturer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.service.LecturerService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/lecturer/addDiscipline")
public class AddLecturerDisciplineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(AddLecturerDisciplineServlet.class.getName());
	private LecturerService lecturerService;

	public void init() throws ServletException {
		lecturerService = new LecturerService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int lecturerId = Integer.parseInt(request.getParameter("lecturerId"));
			int disciplineId = Integer.parseInt(request.getParameter("disciplineId"));
			lecturerService.addDiscipline(lecturerId, disciplineId);
			response.sendRedirect(request.getContextPath() + "/lecturer?id=" + lecturerId);

		} catch (ServiceException e) {
			log.error("Cannot add discipline to the lecturer", e);
			throw new ServletException("Cannot add discipline to the lecturer", e);
		}
	}
}