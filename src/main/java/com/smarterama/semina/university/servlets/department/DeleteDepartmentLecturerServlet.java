package com.smarterama.semina.university.servlets.department;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.service.DepartmentService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/department/deleteLecturer")
public class DeleteDepartmentLecturerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DeleteDepartmentDisciplineServlet.class.getName());
	private DepartmentService departmentService;

	public void init() throws ServletException {
		departmentService = new DepartmentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int lecturerId = Integer.parseInt(request.getParameter("lecturerId"));
			int departmentId = Integer.parseInt(request.getParameter("departmentId"));
			departmentService.deleteLecturer(departmentId, lecturerId);
			response.sendRedirect(request.getContextPath() + "/department?id=" + departmentId);
		} catch (ServiceException e) {
			log.error("Cannot delete lecturer from department", e);
			throw new ServletException("Cannot delete lecturer from department", e);
		}
	}
}
