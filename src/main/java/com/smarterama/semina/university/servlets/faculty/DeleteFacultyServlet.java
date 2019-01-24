package com.smarterama.semina.university.servlets.faculty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.service.FacultyService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/faculty/delete")
public class DeleteFacultyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DeleteFacultyServlet.class.getName());
	private FacultyService facultyService;

	public void init() throws ServletException {
		facultyService = new FacultyService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int facultytId = Integer.parseInt(request.getParameter("id"));

		try {
			facultyService.delete(facultytId);
			response.sendRedirect(request.getContextPath() + "/faculties");
		} catch (ServiceException e) {
			log.error("Cannot delete faculty", e);
			throw new ServletException("Cannot delete faculty", e);
		}
	}
}