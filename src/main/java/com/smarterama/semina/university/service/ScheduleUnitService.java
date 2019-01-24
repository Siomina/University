package com.smarterama.semina.university.service;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.dao.DAOException;
import com.smarterama.semina.university.dao.ScheduleUnitDao;
import com.smarterama.semina.university.dao.jdbc.ScheduleUnitDaoJdbc;
import com.smarterama.semina.university.domain.Discipline;
import com.smarterama.semina.university.domain.Group;
import com.smarterama.semina.university.domain.Lecturer;
import com.smarterama.semina.university.domain.Room;
import com.smarterama.semina.university.domain.ScheduleUnit;

public class ScheduleUnitService {
	private static final Logger log = LogManager.getLogger(ScheduleUnitService.class.getName());
	private ScheduleUnitDao scheduleUnitDao = new ScheduleUnitDaoJdbc();

	public ScheduleUnit create(ScheduleUnit scheduleUnit) throws ServiceException {
		try {
			scheduleUnit = scheduleUnitDao.create(scheduleUnit);
		} catch (DAOException e) {
			log.error("Cannot create scheduleUnit", e);
			throw new ServiceException("Cannot create scheduleUnit", e);
		}
		return scheduleUnit;
	}

	public void addRoom(int scheduleUnitId, int roomId) throws ServiceException {
		try {
			scheduleUnitDao.addRoom(scheduleUnitId, roomId);
		} catch (DAOException e) {
			log.error("Cannot add the room to the scheduleUnit", e);
			throw new ServiceException("Cannot add the room to the scheduleUnit", e);
		}
	}

	public void deleteRoom(int scheduleUnitId, int roomId) throws ServiceException {
		try {
			scheduleUnitDao.deleteRoom(scheduleUnitId, roomId);
		} catch (DAOException e) {
			log.error("Cannot delete room", e);
			throw new ServiceException("Cannot delete room", e);
		}
	}

	public void addGroup(int scheduleUnitId, int groupId) throws ServiceException {
		try {
			scheduleUnitDao.addGroup(scheduleUnitId, groupId);
		} catch (DAOException e) {
			log.error("Cannot add the group to the scheduleUnit", e);
			throw new ServiceException("Cannot add the group to the scheduleUnit", e);
		}
	}

	public void deleteGroup(int scheduleUnitId, int groupId) throws ServiceException {
		try {
			scheduleUnitDao.deleteGroup(scheduleUnitId, groupId);
		} catch (DAOException e) {
			log.error("Cannot delete group", e);
			throw new ServiceException("Cannot delete group", e);
		}
	}

	public void addDiscipline(int scheduleUnitId, int disciplineId) throws ServiceException {
		try {
			scheduleUnitDao.addDiscipline(scheduleUnitId, disciplineId);
		} catch (DAOException e) {
			log.error("Cannot add the discipline to the scheduleUnit", e);
			throw new ServiceException("Cannot add the discipline to the scheduleUnit", e);
		}
	}

	public void deleteDiscipline(int scheduleUnitId, int disciplineId) throws ServiceException {
		try {
			scheduleUnitDao.deleteDiscipline(scheduleUnitId, disciplineId);
		} catch (DAOException e) {
			log.error("Cannot delete discipline", e);
			throw new ServiceException("Cannot delete discipline", e);
		}
	}

	public void addLecturer(int scheduleUnitId, int lecturerId) throws ServiceException {
		try {
			scheduleUnitDao.addLecturer(scheduleUnitId, lecturerId);
		} catch (DAOException e) {
			log.error("Cannot add the lecturer to the scheduleUnit", e);
			throw new ServiceException("Cannot add the lecturer to the scheduleUnit", e);
		}
	}

	public void deleteLecturer(int scheduleUnitId, int lecturerId) throws ServiceException {
		try {
			scheduleUnitDao.deleteLecturer(scheduleUnitId, lecturerId);
		} catch (DAOException e) {
			log.error("Cannot delete lecturer", e);
			throw new ServiceException("Cannot delete lecturer", e);
		}
	}

	public ScheduleUnit update(ScheduleUnit scheduleUnit) throws ServiceException {
		try {
			scheduleUnit = scheduleUnitDao.update(scheduleUnit);
		} catch (DAOException e) {
			log.error("Cannot update scheduleUnit", e);
			throw new ServiceException("Cannot update scheduleUnit", e);
		}
		return scheduleUnit;
	}

	public void delete(int scheduleUnitId) throws ServiceException {
		try {
			scheduleUnitDao.delete(scheduleUnitId);
		} catch (DAOException e) {
			log.error("Cannot delete scheduleUnit", e);
			throw new ServiceException("Cannot delete scheduleUnit", e);
		}
	}

	public ScheduleUnit findOne(int id) throws ServiceException {
		ScheduleUnit scheduleUnit;
		try {
			scheduleUnit = scheduleUnitDao.findOne(id);
		} catch (DAOException e) {
			log.error("Cannot find scheduleUnit", e);
			throw new ServiceException("Cannot find scheduleUnit", e);
		}
		return scheduleUnit;
	}

	public List<ScheduleUnit> findAll() throws ServiceException {
		List<ScheduleUnit> scheduleUnits;
		try {
			scheduleUnits = scheduleUnitDao.findAll();
		} catch (DAOException e) {
			log.error("Cannot find all scheduleUnits", e);
			throw new ServiceException("Cannot find all scheduleUnits", e);
		}
		return scheduleUnits;
	}

	public Set<Room> findNoScheduleUnitRooms(int scheduleUnitId) throws ServiceException {
		Set<Room> rooms;
		try {
			rooms = scheduleUnitDao.findNoScheduleUnitRooms(scheduleUnitId);
		} catch (DAOException e) {
			log.error("Cannot find no scheduleUnit's rooms", e);
			throw new ServiceException("Cannot find no scheduleUnit's rooms", e);
		}
		return rooms;
	}

	public Set<Group> findNoScheduleUnitGroups(int scheduleUnitId) throws ServiceException {
		Set<Group> groups;
		try {
			groups = scheduleUnitDao.findNoScheduleUnitGroups(scheduleUnitId);
		} catch (DAOException e) {
			log.error("Cannot find no scheduleUnit's groups", e);
			throw new ServiceException("Cannot find no scheduleUnit's groups", e);
		}
		return groups;
	}

	public Set<Discipline> findNoScheduleUnitDisciplines(int scheduleUnitId)
			throws ServiceException {
		Set<Discipline> disciplines;
		try {
			disciplines = scheduleUnitDao.findNoScheduleUnitDisciplines(scheduleUnitId);
		} catch (DAOException e) {
			log.error("Cannot find no scheduleUnit's disciplines", e);
			throw new ServiceException("Cannot find no scheduleUnit's disciplines", e);
		}
		return disciplines;
	}

	public Set<Lecturer> findNoScheduleUnitLecturers(int scheduleUnitId) throws ServiceException {
		Set<Lecturer> lecturers;
		try {
			lecturers = scheduleUnitDao.findNoScheduleUnitLecturers(scheduleUnitId);
		} catch (DAOException e) {
			log.error("Cannot find no scheduleUnit's lecturers", e);
			throw new ServiceException("Cannot find no scheduleUnit's lecturers", e);
		}
		return lecturers;
	}

	public List<ScheduleUnit> findDayStudentSchedule(int studentId, GregorianCalendar calendar)
			throws ServiceException {
		List<ScheduleUnit> scheduleUnits;
		try {
			scheduleUnits = scheduleUnitDao.findDayStudentSchedule(studentId, calendar);
		} catch (DAOException e) {
			log.error("Cannot find day's student scheduleUnits", e);
			throw new ServiceException("Cannot find day's student scheduleUnits", e);
		}
		return scheduleUnits;
	}

	public List<ScheduleUnit> findMonthStudentSchedule(int studentId, GregorianCalendar calendar)
			throws ServiceException {
		List<ScheduleUnit> scheduleUnits;
		try {
			scheduleUnits = scheduleUnitDao.findMonthStudentSchedule(studentId, calendar);
		} catch (DAOException e) {
			log.error("Cannot find month's student scheduleUnits", e);
			throw new ServiceException("Cannot find month's student scheduleUnits", e);
		}
		return scheduleUnits;
	}

	public List<ScheduleUnit> findDayLecturerSchedule(int lecturerId, GregorianCalendar calendar)
			throws ServiceException {
		List<ScheduleUnit> scheduleUnits;
		try {
			scheduleUnits = scheduleUnitDao.findDayLecturerSchedule(lecturerId, calendar);
		} catch (DAOException e) {
			log.error("Cannot find day's lecturer scheduleUnits", e);
			throw new ServiceException("Cannot find day's lecturer scheduleUnits", e);
		}
		return scheduleUnits;
	}

	public List<ScheduleUnit> findMonthLecturerSchedule(int lecturerId, GregorianCalendar calendar)
			throws ServiceException {
		List<ScheduleUnit> scheduleUnits;
		try {
			scheduleUnits = scheduleUnitDao.findMonthLecturerSchedule(lecturerId, calendar);
		} catch (DAOException e) {
			log.error("Cannot find month's lecturer scheduleUnits", e);
			throw new ServiceException("Cannot find month's lecturer scheduleUnits", e);
		}
		return scheduleUnits;
	}

}
