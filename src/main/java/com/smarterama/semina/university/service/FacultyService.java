package com.smarterama.semina.university.service;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.dao.DAOException;
import com.smarterama.semina.university.dao.FacultyDao;
import com.smarterama.semina.university.dao.jdbc.FacultyDaoJdbc;
import com.smarterama.semina.university.domain.Department;
import com.smarterama.semina.university.domain.Faculty;
import com.smarterama.semina.university.domain.Group;

public class FacultyService {
	private static final Logger log = LogManager.getLogger(FacultyService.class.getName());
	private FacultyDao facultyDao = new FacultyDaoJdbc();

	public Faculty create(Faculty faculty) throws ServiceException {
		try {
			faculty = facultyDao.create(faculty);
		} catch (DAOException e) {
			log.error("Cannot create faculty", e);
			throw new ServiceException("Cannot create faculty", e);
		}
		return faculty;
	}

	public void addGroup(int facultyId, int groupId) throws ServiceException {
		try {
			facultyDao.addGroup(facultyId, groupId);
		} catch (DAOException e) {
			log.error("Cannot add the group to the faculty", e);
			throw new ServiceException("Cannot add the group to the faculty", e);
		}
	}

	public void deleteGroup(int facultyId, int groupId) throws ServiceException {
		try {
			facultyDao.deleteGroup(facultyId, groupId);
		} catch (DAOException e) {
			log.error("Cannot delete group", e);
			throw new ServiceException("Cannot delete group", e);
		}
	}

	public void addDepartment(int facultyId, int departmentId) throws ServiceException {
		try {
			facultyDao.addDepartment(facultyId, departmentId);
		} catch (DAOException e) {
			log.error("Cannot add the department to the faculty", e);
			throw new ServiceException("Cannot add the department to the faculty", e);
		}
	}

	public void deleteDepartment(int facultyId, int departmentId) throws ServiceException {
		try {
			facultyDao.deleteDepartment(facultyId, departmentId);
		} catch (DAOException e) {
			log.error("Cannot delete department", e);
			throw new ServiceException("Cannot delete department", e);
		}
	}

	public Faculty update(Faculty faculty) throws ServiceException {
		try {
			faculty = facultyDao.update(faculty);
		} catch (DAOException e) {
			log.error("Cannot update faculty", e);
			throw new ServiceException("Cannot update faculty", e);
		}
		return faculty;
	}

	public void delete(int facultyId) throws ServiceException {
		try {
			facultyDao.delete(facultyId);
		} catch (DAOException e) {
			log.error("Cannot create faculty", e);
			throw new ServiceException("Cannot create faculty", e);
		}
	}

	public Faculty findOne(int id) throws ServiceException {
		Faculty faculty;
		try {
			faculty = facultyDao.findOne(id);
		} catch (DAOException e) {
			log.error("Cannot find faculty", e);
			throw new ServiceException("Cannot find faculty", e);
		}
		return faculty;
	}

	public List<Faculty> findAll() throws ServiceException {
		List<Faculty> faculties;
		try {
			faculties = facultyDao.findAll();
		} catch (DAOException e) {
			log.error("Cannot find all faculties", e);
			throw new ServiceException("Cannot find all faculties", e);
		}
		return faculties;
	}

	public Set<Group> findNoFacultyGroups(int facultyId) throws ServiceException {
		Set<Group> groups;
		try {
			groups = facultyDao.findNoFacultyGroups(facultyId);
		} catch (DAOException e) {
			log.error("Cannot find no faculty's groups", e);
			throw new ServiceException("Cannot find no faculty's groups", e);
		}
		return groups;
	}

	public Set<Department> findNoFacultyDepartments(int facultyId) throws ServiceException {
		Set<Department> departments;
		try {
			departments = facultyDao.findNoFacultyDepartments(facultyId);
		} catch (DAOException e) {
			log.error("Cannot find no faculty's departments", e);
			throw new ServiceException("Cannot find no faculty's departments", e);
		}
		return departments;
	}
}
