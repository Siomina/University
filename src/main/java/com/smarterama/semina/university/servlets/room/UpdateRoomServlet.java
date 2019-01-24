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

@WebServlet("/room")
public class UpdateRoomServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(UpdateRoomServlet.class.getName());
	private RoomService roomService;

	public void init() throws ServletException {
		roomService = new RoomService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int roomId = Integer.parseInt(request.getParameter("id"));

		try {
			request.setAttribute("room", roomService.findOne(roomId));
			request.getRequestDispatcher("/room.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find room", e);
			throw new ServletException("Cannot find room", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Room room = new Room();
		room.setName(request.getParameter("name"));
		room.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		String roomId = request.getParameter("id");

		try {
			room.setId(Integer.parseInt(roomId));
			roomService.update(room);
			response.sendRedirect(request.getContextPath() + "/rooms");
		} catch (ServiceException e) {
			log.error("Cannot update room", e);
			throw new IOException("Cannot update room", e);
		}
	}
}
