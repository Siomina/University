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

@WebServlet("/faculty/deleteDepartment")
public class DeleteFacultyDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DeleteFacultyDepartmentServlet.class.getName());
	private FacultyService facultyService;

	public void init() throws ServletException {
		facultyService = new FacultyService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int departmentId = Integer.parseInt(request.getParameter("departmentId"));
			int facultyId = Integer.parseInt(request.getParameter("facultyId"));
			facultyService.deleteDepartment(facultyId, departmentId);
			response.sendRedirect(request.getContextPath() + "/faculty?id=" + facultyId);
		} catch (ServiceException e) {
			log.error("Cannot delete department from faculty", e);
			throw new ServletException("Cannot delete department from faculty", e);
		}
	}
}
