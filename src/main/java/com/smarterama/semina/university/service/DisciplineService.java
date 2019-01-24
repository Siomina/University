package com.smarterama.semina.university.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.dao.DAOException;
import com.smarterama.semina.university.dao.DisciplineDao;
import com.smarterama.semina.university.dao.jdbc.DisciplineDaoJdbc;
import com.smarterama.semina.university.domain.Discipline;

public class DisciplineService {
	private static final Logger log = LogManager.getLogger(DisciplineService.class.getName());
	private DisciplineDao disciplineDao = new DisciplineDaoJdbc();

	public Discipline findOne(int id) throws ServiceException {
		Discipline discipline;
		try {
			discipline = disciplineDao.findOne(id);
		} catch (DAOException e) {
			log.error("Cannot find discipline", e);
			throw new ServiceException("Cannot find discipline", e);
		}
		return discipline;
	}

	public List<Discipline> findAll() throws ServiceException {
		List<Discipline> disciplines;
		try {
			disciplines = disciplineDao.findAll();
		} catch (DAOException e) {
			log.error("Cannot find all disciplines", e);
			throw new ServiceException("Cannot find all disciplines", e);
		}
		return disciplines;
	}

	public Discipline create(Discipline discipline) throws ServiceException {
		try {
			discipline = disciplineDao.create(discipline);
		} catch (DAOException e) {
			log.error("Cannot create discipline", e);
			throw new ServiceException("Cannot create discipline", e);
		}
		return discipline;
	}

	public Discipline update(Discipline discipline) throws ServiceException {
		try {
			discipline = disciplineDao.update(discipline);
		} catch (DAOException e) {
			log.error("Cannot update discipline", e);
			throw new ServiceException("Cannot update discipline", e);
		}
		return discipline;
	}

	public void delete(int id) throws ServiceException {
		try {
			disciplineDao.delete(id);
		} catch (DAOException e) {
			log.error("Cannot delete discipline", e);
			throw new ServiceException("Cannot delete discipline", e);
		}
	}
}