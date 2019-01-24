package com.smarterama.semina.university.service;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.dao.DAOException;
import com.smarterama.semina.university.dao.GroupDao;
import com.smarterama.semina.university.dao.jdbc.GroupDaoJdbc;
import com.smarterama.semina.university.domain.Group;
import com.smarterama.semina.university.domain.Student;

public class GroupService {
	private static final Logger log = LogManager.getLogger(GroupService.class.getName());
	private GroupDao groupDao = new GroupDaoJdbc();

	public Group findOne(int id) throws ServiceException {
		Group group;
		try {
			group = groupDao.findOne(id);
		} catch (DAOException e) {
			log.error("Cannot find group", e);
			throw new ServiceException("Cannot find group", e);
		}
		return group;
	}

	public List<Group> findAll() throws ServiceException {
		List<Group> groups;
		try {
			groups = groupDao.findAll();
		} catch (DAOException e) {
			log.error("Cannot find all groups", e);
			throw new ServiceException("Cannot find all groups", e);
		}
		return groups;
	}

	public Group create(Group group) throws ServiceException {
		try {
			group = groupDao.create(group);
		} catch (DAOException e) {
			log.error("Cannot create group", e);
			throw new ServiceException("Cannot create group", e);
		}
		return group;
	}

	public void addStudent(int groupId, int studentId) throws ServiceException {
		try {
			groupDao.addStudent(groupId, studentId);
		} catch (DAOException e) {
			log.error("Cannot add the student to the group", e);
			throw new ServiceException("Cannot add the student to the group", e);
		}
	}

	public Group update(Group group) throws ServiceException {
		try {
			group = groupDao.update(group);
		} catch (DAOException e) {
			log.error("Cannot update group", e);
			throw new ServiceException("Cannot update group", e);
		}
		return group;
	}

	public void delete(int id) throws ServiceException {
		try {
			groupDao.delete(id);
		} catch (DAOException e) {
			log.error("Cannot delete group", e);
			throw new ServiceException("Cannot delete group", e);
		}
	}

	public void deleteStudent(int groupId, int studentId) throws ServiceException {
		try {
			groupDao.deleteStudent(groupId, studentId);
		} catch (DAOException e) {
			log.error("Cannot delete student", e);
			throw new ServiceException("Cannot delete student", e);
		}
	}

	public Set<Student> findNoGroupStudents() throws ServiceException {
		Set<Student> students;
		try {
			students = groupDao.findNoGroupStudents();
		} catch (DAOException e) {
			log.error("Cannot find students with no group", e);
			throw new ServiceException("Cannot find students with no group", e);
		}
		return students;
	}

}
