package com.smarterama.semina.university.servlets.lecturer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.domain.Lecturer;
import com.smarterama.semina.university.service.LecturerService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/lecturer")
public class UpdateLecturerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(UpdateLecturerServlet.class.getName());
	private LecturerService lecturerService;

	public void init() throws ServletException {
		lecturerService = new LecturerService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int lecturerId = Integer.parseInt(request.getParameter("id"));

		try {
			request.setAttribute("noLecturerDisciplines", lecturerService.findNoLecturerDisciplines(lecturerId));
			request.setAttribute("lecturer", lecturerService.findOne(lecturerId));
			request.getRequestDispatcher("/lecturer.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find lecturer", e);
			throw new ServletException("Cannot find lecturer", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Lecturer lecturer = new Lecturer();
		lecturer.setName(request.getParameter("name"));
		lecturer.setPosition(request.getParameter("position"));
		String lecturerId = request.getParameter("id");

		try {
			lecturer.setId(Integer.parseInt(lecturerId));
			lecturerService.update(lecturer);
			response.sendRedirect(request.getContextPath() + "/lecturers");
		} catch (ServiceException e) {
			log.error("Cannot update lecturer", e);
			throw new IOException("Cannot update lecturer", e);
		}
	}
}
