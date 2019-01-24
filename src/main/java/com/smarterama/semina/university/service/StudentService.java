package com.smarterama.semina.university.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.dao.DAOException;
import com.smarterama.semina.university.dao.StudentDao;
import com.smarterama.semina.university.dao.jdbc.StudentDaoJdbc;
import com.smarterama.semina.university.domain.Student;

public class StudentService {
	private static final Logger log = LogManager.getLogger(StudentService.class.getName());
	private StudentDao studentDao = new StudentDaoJdbc();

	public Student findOne(int id) throws ServiceException {
		Student student;
		try {
			student = studentDao.findOne(id);
		} catch (DAOException e) {
			log.error("Cannot find student", e);
			throw new ServiceException("Cannot find student", e);
		}
		return student;
	}

	public List<Student> findAll() throws ServiceException {
		List<Student> students;
		try {
			students = studentDao.findAll();
		} catch (DAOException e) {
			log.error("Cannot find all students", e);
			throw new ServiceException("Cannot find all students", e);
		}
		return students;
	}

	public Student create(Student student) throws ServiceException {

		try {
			student = studentDao.create(student);
		} catch (DAOException e) {
			log.error("Cannot create student", e);
			throw new ServiceException("Cannot create student", e);
		}
		return student;
	}

	public Student update(Student student) throws ServiceException {
		try {
			student = studentDao.update(student);
		} catch (DAOException e) {
			log.error("Cannot update student", e);
			throw new ServiceException("Cannot update student", e);
		}
		return student;
	}

	public void delete(int id) throws ServiceException {
		try {
			studentDao.delete(id);
		} catch (DAOException e) {
			log.error("Cannot delete student", e);
			throw new ServiceException("Cannot delete student", e);
		}
	}
}
