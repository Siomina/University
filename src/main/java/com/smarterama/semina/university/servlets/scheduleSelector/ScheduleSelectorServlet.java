package com.smarterama.semina.university.servlets.scheduleSelector;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.service.LecturerService;
import com.smarterama.semina.university.service.ServiceException;
import com.smarterama.semina.university.service.StudentService;

@WebServlet("/schedule")
public class ScheduleSelectorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(ScheduleSelectorServlet.class.getName());
	private StudentService studentService;
	private LecturerService lecturerService;

	public void init() throws ServletException {
		studentService = new StudentService();
		lecturerService = new LecturerService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("students", studentService.findAll());
			request.setAttribute("lecturers", lecturerService.findAll());
			request.getRequestDispatcher("/selectSchedules.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find all students", e);
			throw new ServletException("Cannot find all students", e);
		}
	}
}
