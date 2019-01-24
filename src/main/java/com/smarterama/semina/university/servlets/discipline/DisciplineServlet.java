package com.smarterama.semina.university.servlets.discipline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.domain.Discipline;
import com.smarterama.semina.university.service.DisciplineService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/disciplines")
public class DisciplineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DisciplineServlet.class.getName());
	private DisciplineService disciplineService;

	public void init() throws ServletException {
		disciplineService = new DisciplineService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("disciplines", disciplineService.findAll());
			request.getRequestDispatcher("/listDiscipline.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find all disciplines", e);
			throw new ServletException("Cannot find all disciplines", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Discipline discipline = new Discipline();
		discipline.setName(request.getParameter("name"));

		try {
			disciplineService.create(discipline);
			response.sendRedirect(request.getContextPath() + "/disciplines");
		} catch (ServiceException e) {
			log.error("Cannot create discipline", e);
			throw new ServletException("Cannot create discipline", e);
		}
	}
}