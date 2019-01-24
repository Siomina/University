package com.smarterama.semina.university.servlets.lecturer;

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

@WebServlet("/deleteLecturer")
public class DeleteLecturerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DeleteLecturerServlet.class.getName());
	private LecturerService lecturerService;

	public void init() throws ServletException {
		lecturerService = new LecturerService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int lecturerId = Integer.parseInt(request.getParameter("id"));

		try {
			lecturerService.delete(lecturerId);
			response.sendRedirect(request.getContextPath() + "/lecturers");
		} catch (ServiceException e) {
			log.error("Cannot delete lecturer", e);
			throw new ServletException("Cannot delete lecturer", e);
		}
	}
}
