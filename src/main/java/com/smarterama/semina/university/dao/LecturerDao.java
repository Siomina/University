package com.smarterama.semina.university.dao;

import java.util.List;
import java.util.Set;

import com.smarterama.semina.university.domain.Discipline;
import com.smarterama.semina.university.domain.Lecturer;

public interface LecturerDao extends CrudDao<Lecturer> {

	void addDiscipline(int lecturerId, int disciplineId) throws DAOException;

	void deleteDiscipline(int lecturerId, int disciplineId) throws DAOException;

	List<Discipline> findLecturerDisciplines(int lecturerId) throws DAOException;

	Set<Discipline> findNoLecturerDisciplines(int lecturerId) throws DAOException;
}
