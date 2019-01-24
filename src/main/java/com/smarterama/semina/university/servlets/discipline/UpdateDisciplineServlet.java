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

@WebServlet("/discipline")
public class UpdateDisciplineServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String UPDATE = "/discipline.jsp";
	private static final Logger log = LogManager.getLogger(UpdateDisciplineServlet.class.getName());
	private DisciplineService disciplineService;

	public void init() throws ServletException {
		disciplineService = new DisciplineService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int disciplineId = Integer.parseInt(request.getParameter("id"));

		try {
			request.setAttribute("discipline", disciplineService.findOne(disciplineId));
			request.getRequestDispatcher(UPDATE).forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find discipline", e);
			throw new ServletException("Cannot find discipline", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Discipline discipline = new Discipline();
		discipline.setName(request.getParameter("name"));
		String disciplineId = request.getParameter("id");

		try {
			discipline.setId(Integer.parseInt(disciplineId));
			disciplineService.update(discipline);
			response.sendRedirect(request.getContextPath() + "/disciplines");
		} catch (ServiceException e) {
			log.error("Cannot update discipline", e);
			throw new IOException("Cannot update discipline", e);
		}
	}
}
