package com.smarterama.semina.university.servlets.lecturer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.domain.Lecturer;
import com.smarterama.semina.university.service.LecturerService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/lecturers")
public class LecturerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(LecturerServlet.class.getName());
	private LecturerService lecturerService;

	public void init() throws ServletException {
		lecturerService = new LecturerService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("lecturers", lecturerService.findAll());
			request.getRequestDispatcher("/listLecturer.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find all lecturers", e);
			throw new ServletException("Cannot find all lecturers", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Lecturer lecturer = new Lecturer();
		lecturer.setName(request.getParameter("name"));
		lecturer.setPosition(request.getParameter("position"));

		try {
			lecturerService.create(lecturer);
			response.sendRedirect(request.getContextPath() + "/lecturers");

		} catch (ServiceException e) {
			log.error("Cannot create lecturer", e);
			throw new ServletException("Cannot create lecturer", e);
		}
	}
}