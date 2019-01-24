package com.smarterama.semina.university.dao;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import com.smarterama.semina.university.domain.Discipline;
import com.smarterama.semina.university.domain.Group;
import com.smarterama.semina.university.domain.Lecturer;
import com.smarterama.semina.university.domain.Room;
import com.smarterama.semina.university.domain.ScheduleUnit;

public interface ScheduleUnitDao extends CrudDao<ScheduleUnit> {

	void addRoom(int scheduleUnitId, int roomId) throws DAOException;

	void deleteRoom(int scheduleUnitId, int roomId) throws DAOException;

	void addGroup(int scheduleUnitId, int groupId) throws DAOException;

	void deleteGroup(int scheduleUnitId, int groupId) throws DAOException;

	void addDiscipline(int scheduleUnitId, int disciplineId) throws DAOException;

	void deleteDiscipline(int scheduleUnitId, int disciplineId) throws DAOException;

	void addLecturer(int scheduleUnitId, int lecturerId) throws DAOException;

	void deleteLecturer(int scheduleUnitId, int lecturerId) throws DAOException;

	Room findScheduleUnitRoom(int scheduleUnitId) throws DAOException;

	Group findScheduleUnitGroup(int scheduleUnitId) throws DAOException;

	Discipline findScheduleUnitDiscipline(int scheduleUnitId) throws DAOException;

	Lecturer findScheduleUnitLecturer(int scheduleUnitId) throws DAOException;

	Set<Room> findNoScheduleUnitRooms(int scheduleUnitId) throws DAOException;

	Set<Group> findNoScheduleUnitGroups(int scheduleUnitId) throws DAOException;

	Set<Discipline> findNoScheduleUnitDisciplines(int scheduleUnitId) throws DAOException;

	Set<Lecturer> findNoScheduleUnitLecturers(int scheduleUnitId) throws DAOException;

	List<ScheduleUnit> findDayStudentSchedule(int studentId, GregorianCalendar calendar)
			throws DAOException;

	List<ScheduleUnit> findMonthStudentSchedule(int studentId, GregorianCalendar calendar)
			throws DAOException;

	List<ScheduleUnit> findDayLecturerSchedule(int lecturerId, GregorianCalendar calendar)
			throws DAOException;

	List<ScheduleUnit> findMonthLecturerSchedule(int lecturerId, GregorianCalendar calendar)
			throws DAOException;

}
