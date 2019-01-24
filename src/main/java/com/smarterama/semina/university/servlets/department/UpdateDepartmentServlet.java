package com.smarterama.semina.university.servlets.department;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.domain.Department;
import com.smarterama.semina.university.service.DepartmentService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/department")
public class UpdateDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(UpdateDepartmentServlet.class.getName());
	private DepartmentService departmentService;

	public void init() throws ServletException {
		departmentService = new DepartmentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int departmentId = Integer.parseInt(request.getParameter("id"));

		try {
			request.setAttribute("noDepartmentDisciplines", departmentService.findNoDepartmentDisciplines(departmentId));
			request.setAttribute("noDepartmentLecturers", departmentService.findNoDepartmentLecturers(departmentId));
			request.setAttribute("department", departmentService.findOne(departmentId));

			request.getRequestDispatcher("/department.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find department", e);
			throw new ServletException("Cannot find department", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Department department = new Department();
		department.setName(request.getParameter("name"));
		String departmentId = request.getParameter("id");

		try {
			department.setId(Integer.parseInt(departmentId));
			departmentService.update(department);
			response.sendRedirect(request.getContextPath() + "/departments");
		} catch (ServiceException e) {
			log.error("Cannot update department", e);
			throw new IOException("Cannot update department", e);
		}
	}
}