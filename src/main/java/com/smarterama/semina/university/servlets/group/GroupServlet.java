package com.smarterama.semina.university.servlets.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.domain.Group;
import com.smarterama.semina.university.service.GroupService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/groups")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(GroupServlet.class.getName());
	private GroupService groupService;

	public void init() throws ServletException {
		groupService = new GroupService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("groups", groupService.findAll());
			request.getRequestDispatcher("/listGroup.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find all groups", e);
			throw new ServletException("Cannot find all groups", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Group group = new Group();
		group.setName(request.getParameter("name"));

		try {
			groupService.create(group);
			response.sendRedirect(request.getContextPath() + "/groups");

		} catch (ServiceException e) {
			log.error("Cannot create group", e);
			throw new ServletException("Cannot create group", e);
		}
	}
}
