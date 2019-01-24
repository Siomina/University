package com.smarterama.semina.university.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SemesterScheduleTest {
	static ScheduleUnit scheduleUnit1, scheduleUnit2, scheduleUnit3;
	static Room room1, room2;
	static Group group1, group2;
	static Discipline discipline1, discipline2;
	static Lecturer lecturer1, lecturer2;
	SemesterSchedule semesterSchedule;
	List<ScheduleUnit> schedules;

	@BeforeClass
	public static void setUpSemesterSchedule() {
		room1 = new Room("Classroom1", 20);
		room2 = new Room("Classroom2", 20);
		group1 = new Group("EG05", new ArrayList<Student>());
		group2 = new Group("EG06", new ArrayList<Student>());
		scheduleUnit1 = new ScheduleUnit(room1, group1, new GregorianCalendar(2012, 6, 20, 5, 30), discipline1, lecturer1);
		scheduleUnit2 = new ScheduleUnit(room2, group2, new GregorianCalendar(2012, 6, 23, 15, 30), discipline2, lecturer2);
		scheduleUnit3 = new ScheduleUnit(room2, group2, new GregorianCalendar(2012, 6, 24, 14, 00), discipline2, lecturer2);
		discipline1 = new Discipline();
		discipline2 = new Discipline();
		lecturer1 = new Lecturer();
		lecturer2 = new Lecturer();
	}

	@Before
	public void setUpDataSemesterSchedule() {
		semesterSchedule = new SemesterSchedule();
		schedules = new ArrayList<ScheduleUnit>();
	}

	@Test
	public void addScheduleUnitShouldAddOneScheduleUnit() {
		schedules.add(scheduleUnit1);
		semesterSchedule.addScheduleUnit(scheduleUnit1);
		assertEquals(schedules, semesterSchedule.getSchedules());
	}

	@Test
	public void addScheduleUnitShouldAddSeveralScheduleUnits() {
		schedules.add(scheduleUnit1);
		schedules.add(scheduleUnit2);
		schedules.add(scheduleUnit3);
		semesterSchedule.addScheduleUnit(scheduleUnit1);
		semesterSchedule.addScheduleUnit(scheduleUnit2);
		semesterSchedule.addScheduleUnit(scheduleUnit3);
		assertEquals(schedules, semesterSchedule.getSchedules());
	}

	@Test(expected = IllegalArgumentException.class)
	public void addScheduleUnitShouldThrowExceptionForNull() {
		semesterSchedule.addScheduleUnit(null);
	}

	@Test
	public void removeScheduleUnitShouldRemoveOneScheduleUnit() {
		schedules.add(scheduleUnit2);
		semesterSchedule.addScheduleUnit(scheduleUnit1);
		semesterSchedule.addScheduleUnit(scheduleUnit2);
		semesterSchedule.removeScheduleUnit(scheduleUnit1);
		assertEquals(schedules, semesterSchedule.getSchedules());
	}
}
