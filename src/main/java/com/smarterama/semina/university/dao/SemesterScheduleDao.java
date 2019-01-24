package com.smarterama.semina.university.dao;

import java.util.List;
import java.util.Set;

import com.smarterama.semina.university.domain.ScheduleUnit;
import com.smarterama.semina.university.domain.SemesterSchedule;

public interface SemesterScheduleDao extends CrudDao<SemesterSchedule> {

	void addScheduleUnit(int semesterScheduleId, int scheduleUnitId) throws DAOException;

	void deleteScheduleUnit(int semesterScheduleId, int scheduleUnitId) throws DAOException;

	List<ScheduleUnit> findSemesterScheduleUnits(int semesterScheduleId) throws DAOException;

	Set<ScheduleUnit> findNoSemesterScheduleScheduleUnits() throws DAOException;
}
