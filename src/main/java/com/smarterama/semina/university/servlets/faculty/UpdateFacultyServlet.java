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

@WebServlet("/faculty")
public class UpdateFacultyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(UpdateFacultyServlet.class.getName());
	private FacultyService facultyService;

	public void init() throws ServletException {
		facultyService = new FacultyService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int facultyId = Integer.parseInt(request.getParameter("id"));

		try {
			request.setAttribute("noFacultyGroups", facultyService.findNoFacultyGroups(facultyId));
			request.setAttribute("noFacultyDepartments", facultyService.findNoFacultyDepartments(facultyId));
			request.setAttribute("faculty", facultyService.findOne(facultyId));

			request.getRequestDispatcher("/faculty.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find faculty", e);
			throw new ServletException("Cannot find faculty", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Faculty faculty = new Faculty();
		faculty.setName(request.getParameter("name"));
		String facultyId = request.getParameter("id");

		try {
			faculty.setId(Integer.parseInt(facultyId));
			facultyService.update(faculty);
			response.sendRedirect(request.getContextPath() + "/faculties");
		} catch (ServiceException e) {
			log.error("Cannot update faculty", e);
			throw new IOException("Cannot update faculty", e);
		}
	}
}