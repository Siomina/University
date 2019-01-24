package com.smarterama.semina.university.servlets.room;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.domain.Room;
import com.smarterama.semina.university.service.RoomService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/rooms")
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(RoomServlet.class.getName());
	private RoomService roomService;

	public void init() throws ServletException {
		roomService = new RoomService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("rooms", roomService.findAll());
			request.getRequestDispatcher("/listRoom.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find all rooms", e);
			throw new ServletException("Cannot find all rooms", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Room room = new Room();
		room.setName(request.getParameter("name"));
		room.setCapacity(Integer.parseInt(request.getParameter("capacity")));

		try {
			roomService.create(room);
			response.sendRedirect(request.getContextPath() + "/rooms");
		} catch (ServiceException e) {
			log.error("Cannot create room", e);
			throw new ServletException("Cannot create room", e);
		}
	}
}