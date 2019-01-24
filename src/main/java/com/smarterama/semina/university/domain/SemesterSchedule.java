package com.smarterama.semina.university.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SemesterSchedule {
	private int id;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private List<ScheduleUnit> schedules = new ArrayList<ScheduleUnit>();

	public SemesterSchedule() {

	}

	public SemesterSchedule(GregorianCalendar startDate, GregorianCalendar endDate) {
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}

	public SemesterSchedule(GregorianCalendar startDate, GregorianCalendar endDate, List<ScheduleUnit> schedules) {
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.schedules = schedules;
	}

	public void addScheduleUnit(ScheduleUnit scheduleAdded) {
		if (scheduleAdded == null) {
			throw new IllegalArgumentException("The argument cannot be null");
		}
		schedules.add(scheduleAdded);
	}

	public void removeScheduleUnit(ScheduleUnit scheduleRemoved) {
		schedules.remove(scheduleRemoved);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GregorianCalendar getStartDate() {
		return startDate;
	}

	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}

	public GregorianCalendar getEndDate() {
		return endDate;
	}

	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}

	public List<ScheduleUnit> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<ScheduleUnit> schedules) {
		this.schedules = schedules;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SemesterSchedule other = (SemesterSchedule) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (schedules == null) {
			if (other.schedules != null)
				return false;
		} else if (!schedules.equals(other.schedules))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((schedules == null) ? 0 : schedules.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "SemesterSchedule " + id + " [startDate=" + "MONTH " + startDate.get(Calendar.MONTH)
				+ "/" + "DAY " + startDate.get(Calendar.DAY_OF_MONTH) + "/" + "TIME "
				+ startDate.get(Calendar.HOUR_OF_DAY) + ":" + startDate.get(Calendar.MINUTE) + ", "
				+ ", endDate=" + "MONTH " + endDate.get(Calendar.MONTH) + "/" + "DAY "
				+ endDate.get(Calendar.DAY_OF_MONTH) + "/" + "TIME "
				+ endDate.get(Calendar.HOUR_OF_DAY) + ":" + endDate.get(Calendar.MINUTE) + ", "
				+ ", schedules =" + "\n" + schedules + "]";
	}
}
