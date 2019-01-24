package com.smarterama.semina.university.servlets.faculty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.domain.Faculty;
import com.smarterama.semina.university.service.FacultyService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/faculties")
public class FacultyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(FacultyServlet.class.getName());
	private FacultyService facultyService;

	public void init() throws ServletException {
		facultyService = new FacultyService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("faculties", facultyService.findAll());
			request.getRequestDispatcher("/listFaculty.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find all faculties", e);
			throw new ServletException("Cannot find all faculties", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Faculty faculty = new Faculty();
		faculty.setName(request.getParameter("name"));

		try {
			facultyService.create(faculty);
			response.sendRedirect(request.getContextPath() + "/faculties");

		} catch (ServiceException e) {
			log.error("Cannot create faculty", e);
			throw new ServletException("Cannot create faculty", e);
		}
	}
}
