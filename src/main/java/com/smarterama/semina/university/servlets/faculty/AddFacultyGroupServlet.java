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

@WebServlet("/faculty/addGroup")
public class AddFacultyGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(AddFacultyGroupServlet.class.getName());
	private FacultyService facultyService;

	public void init() throws ServletException {
		facultyService = new FacultyService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int facultyId = Integer.parseInt(request.getParameter("facultyId"));
			int groupId = Integer.parseInt(request.getParameter("groupId"));
			facultyService.addGroup(facultyId, groupId);
			response.sendRedirect(request.getContextPath() + "/faculty?id=" + facultyId);

		} catch (ServiceException e) {
			log.error("Cannot add group to the faculty", e);
			throw new ServletException("Cannot add group to the faculty", e);
		}
	}
}