package com.smarterama.semina.university.servlets.discipline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.service.DisciplineService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/discipline/delete")
public class DeleteDisciplineServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DeleteDisciplineServlet.class.getName());
	private DisciplineService disciplineService;

	public void init() throws ServletException {
		disciplineService = new DisciplineService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int disciplineId = Integer.parseInt(request.getParameter("id"));

		try {
			disciplineService.delete(disciplineId);
			response.sendRedirect(request.getContextPath() + "/disciplines");
		} catch (ServiceException e) {
			log.error("Cannot delete discipline", e);
			throw new ServletException("Cannot delete discipline", e);
		}
	}
}