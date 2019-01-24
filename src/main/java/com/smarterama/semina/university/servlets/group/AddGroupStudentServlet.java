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

@WebServlet("/group/addStudent")
public class AddGroupStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(AddGroupStudentServlet.class.getName());
	private GroupService groupService;

	public void init() throws ServletException {
		groupService = new GroupService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int groupId = Integer.parseInt(request.getParameter("groupId"));
			int studentId = Integer.parseInt(request.getParameter("studentId"));
			groupService.addStudent(groupId, studentId);
			response.sendRedirect(request.getContextPath() + "/group?id=" + groupId);

		} catch (ServiceException e) {
			log.error("Cannot add student to the group", e);
			throw new ServletException("Cannot add student to the group", e);
		}
	}
}
