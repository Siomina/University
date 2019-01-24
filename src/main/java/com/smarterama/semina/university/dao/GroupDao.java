package com.smarterama.semina.university.dao;

import java.util.List;
import java.util.Set;

import com.smarterama.semina.university.domain.Group;
import com.smarterama.semina.university.domain.Student;

public interface GroupDao extends CrudDao<Group> {

	void addStudent(int groupId, int studentId) throws DAOException;

	void deleteStudent(int groupId, int studentId) throws DAOException;

	List<Student> findGroupStudents(int groupId) throws DAOException;

	Set<Student> findNoGroupStudents() throws DAOException;

}
