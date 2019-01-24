package com.smarterama.semina.university.service;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.dao.DAOException;
import com.smarterama.semina.university.dao.SemesterScheduleDao;
import com.smarterama.semina.university.dao.jdbc.SemesterScheduleDaoJdbc;
import com.smarterama.semina.university.domain.ScheduleUnit;
import com.smarterama.semina.university.domain.SemesterSchedule;

public class SemesterScheduleService {
	private static final Logger log = LogManager.getLogger(SemesterScheduleService.class.getName());
	private SemesterScheduleDao semesterScheduleDao = new SemesterScheduleDaoJdbc();

	public SemesterSchedule create(SemesterSchedule semesterSchedule) throws ServiceException {
		try {
			semesterSchedule = semesterScheduleDao.create(semesterSchedule);
		} catch (DAOException e) {
			log.error("Cannot create semesterSchedule", e);
			throw new ServiceException("Cannot create semesterSchedule", e);
		}
		return semesterSchedule;
	}

	public void addScheduleUnit(int semesterScheduleId, int scheduleUnitId) throws ServiceException {
		try {
			semesterScheduleDao.addScheduleUnit(semesterScheduleId, scheduleUnitId);
		} catch (DAOException e) {
			log.error("Cannot add the schedule unit to the semester schedule", e);
			throw new ServiceException("Cannot add the schedule unit to the semester schedule", e);
		}

	}

	public void deleteScheduleUnit(int semesterScheduleId, int scheduleUnitId)
			throws ServiceException {
		try {
			semesterScheduleDao.deleteScheduleUnit(semesterScheduleId, scheduleUnitId);
		} catch (DAOException e) {
			log.error("Cannot delete the schedule unit from the semester schedule", e);
			throw new ServiceException("Cannot delete the schedule unit from the semester schedule", e);
		}

	}

	public SemesterSchedule update(SemesterSchedule semesterSchedule) throws ServiceException {
		try {
			semesterSchedule = semesterScheduleDao.update(semesterSchedule);
		} catch (DAOException e) {
			log.error("Cannot update semesterSchedule", e);
			throw new ServiceException("Cannot update semesterSchedule", e);
		}
		return semesterSchedule;
	}

	public void delete(int semesterScheduleId) throws ServiceException {
		try {
			semesterScheduleDao.delete(semesterScheduleId);
		} catch (DAOException e) {
			log.error("Cannot delete semester schedule", e);
			throw new ServiceException("Cannot delete semester schedule", e);
		}
	}

	public SemesterSchedule findOne(int id) throws ServiceException {
		SemesterSchedule semesterSchedule;
		try {
			semesterSchedule = semesterScheduleDao.findOne(id);
		} catch (DAOException e) {
			log.error("Cannot find semester schedule", e);
			throw new ServiceException("Cannot find semester schedule", e);
		}
		return semesterSchedule;
	}

	public List<SemesterSchedule> findAll() throws ServiceException {
		List<SemesterSchedule> semesterSchedules;
		try {
			semesterSchedules = semesterScheduleDao.findAll();
		} catch (DAOException e) {
			log.error("Cannot find all semester schedules", e);
			throw new ServiceException("Cannot find all semester schedules", e);
		}
		return semesterSchedules;
	}

	public Set<ScheduleUnit> findNoSemesterScheduleScheduleUnits() throws ServiceException {
		Set<ScheduleUnit> scheduleUnits;
		try {
			scheduleUnits = semesterScheduleDao.findNoSemesterScheduleScheduleUnits();
		} catch (DAOException e) {
			log.error("Cannot find no semesterSchedule's schedule units", e);
			throw new ServiceException("Cannot find no semesterSchedule's schedule units", e);
		}
		return scheduleUnits;
	}
}
