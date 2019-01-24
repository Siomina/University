package com.smarterama.semina.university.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.smarterama.semina.university.domain.ScheduleUnit;
import com.smarterama.semina.university.service.ScheduleUnitService;
import com.smarterama.semina.university.service.ServiceException;

@Path("/schedules")
@Produces(MediaType.APPLICATION_JSON)
public class ScheduleSelectorRest {

private ScheduleUnitService scheduleUnitService = new ScheduleUnitService();

    @GET
    public List<ScheduleUnit> findAll() throws ServiceException{
    return scheduleUnitService.findAll();
    }

    @GET
    @Path("/studentDay")
    public List<ScheduleUnit> findDayStudentSchedule(@QueryParam("id") int id,
													 @QueryParam("day") String day) throws ServiceException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(day);
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        calendar.setTime(date);
        return scheduleUnitService.findDayStudentSchedule(id, calendar);
    }
	
    @GET
    @Path("/studentMonth")
    public List<ScheduleUnit> findMonthStudentSchedule(@QueryParam("id") int id,
													   @QueryParam("month") String month) throws ServiceException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
        Date date = dateFormat.parse(month);
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        calendar.setTime(date);
        return scheduleUnitService.findMonthStudentSchedule(id, calendar);
    }
	
    @GET
    @Path("/lecturerDay")
    public List<ScheduleUnit> findDayLecturerSchedule(@QueryParam("id") int id,
													  @QueryParam("day") String day) throws ServiceException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(day);
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        calendar.setTime(date);
        return scheduleUnitService.findDayLecturerSchedule(id, calendar);
    }
	
    @GET
    @Path("/lecturerMonth")
    public List<ScheduleUnit> findMonthLecturerSchedule(@QueryParam("id") int id,
													    @QueryParam("month") String month) throws ServiceException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
        Date date = dateFormat.parse(month);
        GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        calendar.setTime(date);
        return scheduleUnitService.findMonthLecturerSchedule(id, calendar);
    }
}
