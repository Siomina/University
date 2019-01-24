package com.smarterama.semina.university.service;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.dao.DAOException;
import com.smarterama.semina.university.dao.DepartmentDao;
import com.smarterama.semina.university.dao.jdbc.DepartmentDaoJdbc;
import com.smarterama.semina.university.domain.Department;
import com.smarterama.semina.university.domain.Discipline;
import com.smarterama.semina.university.domain.Lecturer;

public class DepartmentService {
	private static final Logger log = LogManager.getLogger(DepartmentService.class.getName());
	private DepartmentDao departmentDao = new DepartmentDaoJdbc();

	public Department create(Department department) throws ServiceException {
		try {
			department = departmentDao.create(department);
		} catch (DAOException e) {
			log.error("Cannot create department", e);
			throw new ServiceException("Cannot create department", e);
		}
		return department;
	}

	public void addLecturer(int departmentId, int lecturerId) throws ServiceException {
		try {
			departmentDao.addLecturer(departmentId, lecturerId);
		} catch (DAOException e) {
			log.error("Cannot add the lecturer to the department", e);
			throw new ServiceException("Cannot add the lecturer to the department", e);
		}
	}

	public void deleteLecturer(int departmentId, int lecturerId) throws ServiceException {
		try {
			departmentDao.deleteLecturer(departmentId, lecturerId);
		} catch (DAOException e) {
			log.error("Cannot delete lecturer", e);
			throw new ServiceException("Cannot delete lecturer", e);
		}
	}

	public void addDiscipline(int departmentId, int disciplineId) throws ServiceException {
		try {
			departmentDao.addDiscipline(departmentId, disciplineId);
		} catch (DAOException e) {
			log.error("Cannot add the discipline to the department", e);
			throw new ServiceException("Cannot add the discipline to the department", e);
		}
	}

	public void deleteDiscipline(int departmentId, int disciplineId) throws ServiceException {
		try {
			departmentDao.deleteDiscipline(departmentId, disciplineId);
		} catch (DAOException e) {
			log.error("Cannot delete discipline", e);
			throw new ServiceException("Cannot delete discipline", e);
		}
	}

	public void delete(int departmentId) throws ServiceException {
		try {
			departmentDao.delete(departmentId);
		} catch (DAOException e) {
			log.error("Cannot create department", e);
			throw new ServiceException("Cannot create department", e);
		}
	}

	public Department update(Department department) throws ServiceException {
		try {
			department = departmentDao.update(department);
		} catch (DAOException e) {
			log.error("Cannot update department", e);
			throw new ServiceException("Cannot update department", e);
		}
		return department;
	}

	public Department findOne(int id) throws ServiceException {
		Department department;
		try {
			department = departmentDao.findOne(id);
		} catch (DAOException e) {
			log.error("Cannot find department", e);
			throw new ServiceException("Cannot find department", e);
		}
		return department;
	}

	public List<Department> findAll() throws ServiceException {
		List<Department> departments;
		try {
			departments = departmentDao.findAll();
		} catch (DAOException e) {
			log.error("Cannot find all departments", e);
			throw new ServiceException("Cannot find all department", e);
		}
		return departments;
	}

	public Set<Discipline> findNoDepartmentDisciplines(int departmentId) throws ServiceException {
		Set<Discipline> disciplines;
		try {
			disciplines = departmentDao.findNoDepartmentDisciplines(departmentId);
		} catch (DAOException e) {
			log.error("Cannot find no department's disciplines", e);
			throw new ServiceException("Cannot find no department's disciplines", e);
		}
		return disciplines;

	}

	public Set<Lecturer> findNoDepartmentLecturers(int departmentId) throws ServiceException {
		Set<Lecturer> lecturers;
		try {
			lecturers = departmentDao.findNoDepartmentLecturers(departmentId);
		} catch (DAOException e) {
			log.error("Cannot find no department's lecturers", e);
			throw new ServiceException("Cannot find no department's lecturers", e);
		}
		return lecturers;

	}
}
