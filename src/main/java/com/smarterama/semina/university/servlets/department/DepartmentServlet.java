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

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DepartmentServlet.class.getName());
	private DepartmentService departmentService;

	public void init() throws ServletException {
		departmentService = new DepartmentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("departments", departmentService.findAll());
			request.getRequestDispatcher("/listDepartment.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find all departments", e);
			throw new ServletException("Cannot find all departments", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Department department = new Department();
		department.setName(request.getParameter("name"));

		try {
			departmentService.create(department);
			response.sendRedirect(request.getContextPath() + "/departments");

		} catch (ServiceException e) {
			log.error("Cannot create department", e);
			throw new ServletException("Cannot create department", e);
		}
	}
}
