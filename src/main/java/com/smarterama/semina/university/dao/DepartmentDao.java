package com.smarterama.semina.university.dao;

import java.util.List;
import java.util.Set;

import com.smarterama.semina.university.domain.Department;
import com.smarterama.semina.university.domain.Discipline;
import com.smarterama.semina.university.domain.Lecturer;

public interface DepartmentDao extends CrudDao<Department> {

	void addLecturer(int departmentId, int lecturerId) throws DAOException;

	void deleteLecturer(int departmentId, int lecturerId) throws DAOException;

	void addDiscipline(int departmentId, int disciplineId) throws DAOException;

	void deleteDiscipline(int departmentId, int disciplineId) throws DAOException;

	List<Lecturer> findDepartmentLecturers(int departmentId) throws DAOException;

	List<Discipline> findDepartmentDisciplines(int departmentId) throws DAOException;

	Set<Discipline> findNoDepartmentDisciplines(int departmentId) throws DAOException;

	Set<Lecturer> findNoDepartmentLecturers(int departmentId) throws DAOException;
}
