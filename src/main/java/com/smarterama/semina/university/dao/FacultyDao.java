package com.smarterama.semina.university.dao;

import java.util.List;
import java.util.Set;

import com.smarterama.semina.university.domain.Department;
import com.smarterama.semina.university.domain.Faculty;
import com.smarterama.semina.university.domain.Group;

public interface FacultyDao extends CrudDao<Faculty> {

	void addGroup(int facultyId, int groupId) throws DAOException;

	void deleteGroup(int facultyId, int groupId) throws DAOException;

	void addDepartment(int facultyId, int departmentId) throws DAOException;

	void deleteDepartment(int facultyId, int departmentId) throws DAOException;

	List<Group> findFacultyGroups(int facultyId) throws DAOException;

	List<Department> findFacultyDepartments(int facultyId) throws DAOException;

	Set<Group> findNoFacultyGroups(int facultyId) throws DAOException;

	Set<Department> findNoFacultyDepartments(int facultyId) throws DAOException;
}
