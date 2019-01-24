package com.smarterama.semina.university.servlets.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.service.GroupService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/deleteGroup")
public class DeleteGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DeleteGroupServlet.class.getName());
	private GroupService groupService;

	public void init() throws ServletException {
		groupService = new GroupService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int groupId = Integer.parseInt(request.getParameter("id"));

		try {
			groupService.delete(groupId);
			response.sendRedirect(request.getContextPath() + "/groups");
		} catch (ServiceException e) {
			log.error("Cannot delete group", e);
			throw new ServletException("Cannot delete group", e);
		}
	}
}
