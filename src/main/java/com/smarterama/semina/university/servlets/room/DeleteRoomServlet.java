package com.smarterama.semina.university.servlets.room;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.service.RoomService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/room/delete")
public class DeleteRoomServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DeleteRoomServlet.class.getName());
	private RoomService roomService;

	public void init() throws ServletException {
		roomService = new RoomService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int roomId = Integer.parseInt(request.getParameter("id"));

		try {
			roomService.delete(roomId);
			response.sendRedirect(request.getContextPath() + "/rooms");
		} catch (ServiceException e) {
			log.error("Cannot delete room", e);
			throw new ServletException("Cannot delete room", e);
		}
	}
}