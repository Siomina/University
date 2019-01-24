package com.smarterama.semina.university.servlets.scheduleUnit;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.domain.ScheduleUnit;
import com.smarterama.semina.university.service.ScheduleUnitService;
import com.smarterama.semina.university.service.ServiceException;

@WebServlet("/scheduleUnit")
public class UpdateScheduleUnitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(UpdateScheduleUnitServlet.class.getName());
	private ScheduleUnitService scheduleUnitService;

	public void init() throws ServletException {
		scheduleUnitService = new ScheduleUnitService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int scheduleUnitId = Integer.parseInt(request.getParameter("id"));

		try {
			request.setAttribute("noScheduleUnitRooms", scheduleUnitService.findNoScheduleUnitRooms(scheduleUnitId));
			request.setAttribute("noScheduleUnitGroups", scheduleUnitService.findNoScheduleUnitGroups(scheduleUnitId));
			request.setAttribute("noScheduleUnitDisciplines", scheduleUnitService.findNoScheduleUnitDisciplines(scheduleUnitId));
			request.setAttribute("noScheduleUnitLecturers", scheduleUnitService.findNoScheduleUnitLecturers(scheduleUnitId));
			request.setAttribute("scheduleUnit", scheduleUnitService.findOne(scheduleUnitId));

			request.getRequestDispatcher("/scheduleUnit.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find scheduleUnit", e);
			throw new ServletException("Cannot find scheduleUnit", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ScheduleUnit scheduleUnit = new ScheduleUnit();
		String scheduleUnitId = request.getParameter("id");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = dateFormat.parse(request.getParameter("dateTime"));
		} catch (ParseException e1) {
			throw new ServletException("Cannot parse time and date of the scheduleUnit", e1);
		}
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		scheduleUnit.setDate(calendar);

		try {
			scheduleUnit.setId(Integer.parseInt(scheduleUnitId));
			scheduleUnitService.update(scheduleUnit);
			response.sendRedirect(request.getContextPath() + "/scheduleUnits");
		} catch (ServiceException e) {
			log.error("Cannot update scheduleUnit", e);
			throw new IOException("Cannot update scheduleUnit", e);
		}
	}
}
