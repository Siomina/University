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

@WebServlet("/group")
public class UpdateGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(UpdateGroupServlet.class.getName());
	private GroupService groupService;

	public void init() throws ServletException {
		groupService = new GroupService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int groupId = Integer.parseInt(request.getParameter("id"));

		try {
			request.setAttribute("noGroupStudents", groupService.findNoGroupStudents());
			request.setAttribute("group", groupService.findOne(groupId));
			request.getRequestDispatcher("/group.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find group", e);
			throw new ServletException("Cannot find group", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Group group = new Group();
		group.setName(request.getParameter("name"));
		String groupId = request.getParameter("id");

		try {
			group.setId(Integer.parseInt(groupId));
			groupService.update(group);
			response.sendRedirect(request.getContextPath() + "/groups");
		} catch (ServiceException e) {
			log.error("Cannot update group", e);
			throw new IOException("Cannot update group", e);
		}
	}
}
