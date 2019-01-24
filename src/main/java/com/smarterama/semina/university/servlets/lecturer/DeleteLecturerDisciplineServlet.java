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

@WebServlet("/lecturer/deleteDiscipline")
public class DeleteLecturerDisciplineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DeleteLecturerDisciplineServlet.class.getName());
	private LecturerService lecturerService;

	public void init() throws ServletException {
		lecturerService = new LecturerService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int disciplineId = Integer.parseInt(request.getParameter("disciplineId"));
			int lecturerId = Integer.parseInt(request.getParameter("lecturerId"));
			lecturerService.deleteDiscipline(lecturerId, disciplineId);
			response.sendRedirect(request.getContextPath() + "/lecturer?id=" + lecturerId);
		} catch (ServiceException e) {
			log.error("Cannot delete discipline", e);
			throw new ServletException("Cannot delete discipline", e);
		}
	}
}
