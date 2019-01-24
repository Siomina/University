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

@WebServlet("/student")
public class UpdateStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String UPDATE = "/student.jsp";
	private static final Logger log = LogManager.getLogger(UpdateStudentServlet.class.getName());
	private StudentService studentService;

	public void init() throws ServletException {
		studentService = new StudentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int studentId = Integer.parseInt(request.getParameter("id"));

		try {
			request.setAttribute("student", studentService.findOne(studentId));
			request.getRequestDispatcher(UPDATE).forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find student", e);
			throw new ServletException("Cannot find student", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student student = new Student();
		student.setName(request.getParameter("name"));
		String studentId = request.getParameter("id");

		try {
			student.setId(Integer.parseInt(studentId));
			studentService.update(student);
			response.sendRedirect(request.getContextPath() + "/students");
		} catch (ServiceException e) {
			log.error("Cannot update student", e);
			throw new IOException("Cannot update student", e);
		}
	}
}
