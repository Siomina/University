package com.smarterama.semina.university.servlets.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.service.ServiceException;
import com.smarterama.semina.university.service.StudentService;

@WebServlet("/student/delete")
public class DeleteStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DeleteStudentServlet.class.getName());
	private StudentService studentService;

	public void init() throws ServletException {
		studentService = new StudentService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int studentId = Integer.parseInt(request.getParameter("id"));

		try {
			studentService.delete(studentId);
			response.sendRedirect(request.getContextPath() + "/students");
		} catch (ServiceException e) {
			log.error("Cannot delete student", e);
			throw new ServletException("Cannot delete student", e);
		}
	}
}