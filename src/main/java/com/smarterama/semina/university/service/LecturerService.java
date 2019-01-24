package com.smarterama.semina.university.service;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.dao.DAOException;
import com.smarterama.semina.university.dao.LecturerDao;
import com.smarterama.semina.university.dao.jdbc.LecturerDaoJdbc;
import com.smarterama.semina.university.domain.Discipline;
import com.smarterama.semina.university.domain.Lecturer;

public class LecturerService {
	private static final Logger log = LogManager.getLogger(LecturerService.class.getName());
	private LecturerDao lecturerDao = new LecturerDaoJdbc();

	public Lecturer create(Lecturer lecturer) throws ServiceException {
		try {
			lecturer = lecturerDao.create(lecturer);
		} catch (DAOException e) {
			log.error("Cannot create lecturer", e);
			throw new ServiceException("Cannot create lecturer", e);
		}
		return lecturer;
	}

	public void addDiscipline(int lecturerId, int disciplineId) throws ServiceException {
		try {
			lecturerDao.addDiscipline(lecturerId, disciplineId);
		} catch (DAOException e) {
			log.error("Cannot add the discipline to the lecturer", e);
			throw new ServiceException("Cannot add the discipline to the lecturer", e);
		}
	}

	public void deleteDiscipline(int lecturerId, int disciplineId) throws ServiceException {
		try {
			lecturerDao.deleteDiscipline(lecturerId, disciplineId);
		} catch (DAOException e) {
			log.error("Cannot delete the discipline from the lecturer", e);
			throw new ServiceException("Cannot delete the discipline from the lecturer", e);
		}
	}

	public Lecturer update(Lecturer lecturer) throws ServiceException {
		try {
			lecturer = lecturerDao.update(lecturer);
		} catch (DAOException e) {
			log.error("Cannot update lecturer", e);
			throw new ServiceException("Cannot update lecturer", e);
		}
		return lecturer;
	}

	public void delete(int id) throws ServiceException {
		try {
			lecturerDao.delete(id);
		} catch (DAOException e) {
			log.error("Cannot delete lecturer", e);
			throw new ServiceException("Cannot delete lecturer", e);
		}
	}

	public Lecturer findOne(int id) throws ServiceException {
		Lecturer lecturer;
		try {
			lecturer = lecturerDao.findOne(id);
		} catch (DAOException e) {
			log.error("Cannot find lecturer", e);
			throw new ServiceException("Cannot find lecturer", e);
		}
		return lecturer;
	}

	public List<Lecturer> findAll() throws ServiceException {
		List<Lecturer> lecturers;
		try {
			lecturers = lecturerDao.findAll();
		} catch (DAOException e) {
			log.error("Cannot find all lecturers", e);
			throw new ServiceException("Cannot find all lecturers", e);
		}
		return lecturers;
	}

	public Set<Discipline> findNoLecturerDisciplines(int lecturerId) throws ServiceException {
		Set<Discipline> disciplines;
		try {
			disciplines = lecturerDao.findNoLecturerDisciplines(lecturerId);
		} catch (DAOException e) {
			log.error("Cannot find students with no group", e);
			throw new ServiceException("Cannot find students with no group", e);
		}
		return disciplines;

	}

}
