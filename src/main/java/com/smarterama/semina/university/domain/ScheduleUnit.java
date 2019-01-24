package com.smarterama.semina.university.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ScheduleUnit {
	private int id;
	private Room room;
	private Group group;
	private GregorianCalendar date;
	private Discipline discipline;
	private Lecturer lecturer;

	public ScheduleUnit() {

	}

	public ScheduleUnit(GregorianCalendar date) {
		this.date = date;
	}

	public ScheduleUnit(Room room, Group group, GregorianCalendar date, Discipline discipline, Lecturer lecturer) {
		this.room = room;
		this.group = group;
		this.date = date;
		this.discipline = discipline;
		this.lecturer = lecturer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleUnit other = (ScheduleUnit) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (discipline == null) {
			if (other.discipline != null)
				return false;
		} else if (!discipline.equals(other.discipline))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (id != other.id)
			return false;
		if (lecturer == null) {
			if (other.lecturer != null)
				return false;
		} else if (!lecturer.equals(other.lecturer))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((discipline == null) ? 0 : discipline.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + id;
		result = prime * result + ((lecturer == null) ? 0 : lecturer.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "[ScheduleUnit " + id + ": " + room + ", " + group + ", Date: " + "MONTH "
				+ date.get(Calendar.MONTH) + "/" + "DAY " + date.get(Calendar.DAY_OF_MONTH) + "/"
				+ "TIME " + date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE) + ", "

				+ discipline + ", " + lecturer + "]" + "\n";
	}
}
