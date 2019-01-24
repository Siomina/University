package com.smarterama.semina.university.servlets.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.domain.Student;
import com.smarterama.semina.university.service.ServiceException;
import com.smarterama.semina.university.service.StudentService;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(StudentServlet.class.getName());
	private StudentService studentService;

	public void init() throws ServletException {
		studentService = new StudentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("students", studentService.findAll());
			request.getRequestDispatcher("/listStudent.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find all students", e);
			throw new ServletException("Cannot find all students", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student student = new Student();
		student.setName(request.getParameter("name"));

		try {
			studentService.create(student);
			response.sendRedirect(request.getContextPath() + "/students");
		} catch (ServiceException e) {
			log.error("Cannot create student", e);
			throw new ServletException("Cannot create student", e);
		}
	}
}