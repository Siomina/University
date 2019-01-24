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

@WebServlet("/scheduleUnits")
public class ScheduleUnitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(ScheduleUnitServlet.class.getName());
	private ScheduleUnitService scheduleUnitService;

	public void init() throws ServletException {
		scheduleUnitService = new ScheduleUnitService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("scheduleUnits", scheduleUnitService.findAll());
			request.getRequestDispatcher("/listScheduleUnit.jsp").forward(request, response);
		} catch (ServiceException e) {
			log.error("Cannot find all scheduleUnits", e);
			throw new ServletException("Cannot find all scheduleUnits", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ScheduleUnit scheduleUnit = new ScheduleUnit();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		try {
			date = df.parse(request.getParameter("dateTime"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		scheduleUnit.setDate(cal);

		try {
			scheduleUnitService.create(scheduleUnit);
			response.sendRedirect(request.getContextPath() + "/scheduleUnits");

		} catch (ServiceException e) {
			log.error("Cannot create scheduleUnit", e);
			throw new ServletException("Cannot create scheduleUnit", e);
		}
	}
}