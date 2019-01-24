package com.smarterama.semina.university.dao.jdbc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smarterama.semina.university.dao.DAOException;
import com.smarterama.semina.university.dao.SemesterScheduleDao;
import com.smarterama.semina.university.domain.ScheduleUnit;
import com.smarterama.semina.university.domain.SemesterSchedule;
import com.smarterama.semina.university.hibernate.HibernateUtil;

public class SemesterScheduleDaoJdbc implements SemesterScheduleDao {
	private static final Logger log = LogManager.getLogger(SemesterScheduleDaoJdbc.class.getName());

	@Override
	public SemesterSchedule create(SemesterSchedule semesterSchedule) throws DAOException {
		Transaction transaction = null;
		log.debug("Creating new semesterSchedule");

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.save(semesterSchedule);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot create semesterSchedule", e);
			transaction.rollback();
			throw new DAOException("Cannot create semesterSchedule", e);
		}
		log.info("SemesterSchedule was created");
		return semesterSchedule;
	}

	@Override
	public void addScheduleUnit(int semesterScheduleId, int scheduleUnitId) throws DAOException {
		Transaction transaction = null;
		log.debug("Adding scheduleUnit");

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			SemesterSchedule semesterSchedule = session.get(SemesterSchedule.class, semesterScheduleId);
			ScheduleUnit scheduleUnit = session.get(ScheduleUnit.class, scheduleUnitId);
			semesterSchedule.addScheduleUnit(scheduleUnit);
			session.update(semesterSchedule);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot add the scheduleUnit with Id " + scheduleUnitId
					+ " to the semesterSchedule with Id" + semesterScheduleId, e);
			transaction.rollback();
			throw new DAOException("Cannot add the scheduleUnit with Id " + scheduleUnitId
					+ " to the semesterSchedule with Id" + semesterScheduleId, e);
		}
		log.info("ScheduleUnit was added with Id" + scheduleUnitId);
	}

	@Override
	public void deleteScheduleUnit(int semesterScheduleId, int scheduleUnitId) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting scheduleUnit");

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			SemesterSchedule semesterSchedule = session.get(SemesterSchedule.class, semesterScheduleId);
			ScheduleUnit scheduleUnit = session.get(ScheduleUnit.class, scheduleUnitId);
			semesterSchedule.removeScheduleUnit(scheduleUnit);
			session.update(semesterSchedule);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete the scheduleUnit with Id " + scheduleUnitId
					+ " from the semesterSchedule with Id" + semesterScheduleId, e);
			transaction.rollback();
			throw new DAOException("Cannot delete the scheduleUnit with Id " + scheduleUnitId
					+ " from the semesterSchedule with Id" + semesterScheduleId, e);
		}
		log.info("ScheduleUnit was deleted with Id" + scheduleUnitId);
	}

	@Override
	public SemesterSchedule update(SemesterSchedule semesterSchedule) throws DAOException {
		Transaction transaction = null;
		log.debug("Updating semesterSchedule");

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.update(semesterSchedule);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot update the semesterSchedule", e);
			transaction.rollback();
			throw new DAOException("Cannot update the semesterSchedule", e);
		}
		log.info("SemesterSchedule was updated:" + semesterSchedule);
		return semesterSchedule;
	}

	@Override
	public void delete(int id) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting semesterSchedule with id = " + id);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			SemesterSchedule semesterSchedule = session.get(SemesterSchedule.class, id);
			session.delete(semesterSchedule);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete the semesterSchedule with id" + id, e);
			transaction.rollback();
			throw new DAOException("Cannot delete the semesterSchedule with id" + id, e);
		}
		log.info("SemesterSchedule was deleted with id =" + id);
	}

	@Override
	public SemesterSchedule findOne(int id) throws DAOException {
		log.debug("Finding semesterSchedule with id = " + id);
		SemesterSchedule semesterSchedule = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from SemesterSchedule where id=:param");
			query.setParameter("param", id);
			semesterSchedule = (SemesterSchedule) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Cannot find one semesterSchedule with id" + id, e);
			throw new DAOException("Cannot find one semesterSchedule with id" + id, e);
		}
		log.info("SemesterSchedule was found with id =" + semesterSchedule);
		return semesterSchedule;
	}

	@Override
	public List<ScheduleUnit> findSemesterScheduleUnits(int semesterId) throws DAOException {
		log.debug("Finding scheduleUnits for this semester");
		SemesterSchedule semesterSchedule = null;
		List<ScheduleUnit> scheduleUnits = new ArrayList<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from SemesterSchedule where id=:param");
			query.setParameter("param", semesterId);
			semesterSchedule = (SemesterSchedule) query.uniqueResult();
			scheduleUnits = semesterSchedule.getSchedules();
		} catch (HibernateException e) {
			log.error("Cannot find scheduleUnits for this semester with id" + semesterId, e);
			throw new DAOException("Cannot find scheduleUnits for this semester with id"
					+ semesterId, e);
		}
		log.info("ScheduleUnits were found");
		return scheduleUnits;
	}

	@Override
	public List<SemesterSchedule> findAll() throws DAOException {
		log.debug("Finding all semesterSchedules");
		List<SemesterSchedule> semesterSchedules = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from SemesterSchedule");
			semesterSchedules = (List<SemesterSchedule>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find all semesterSchedules", e);
			throw new DAOException("Cannot find all semesterSchedules", e);
		}
		log.info("All semesterSchedules were found");
		return semesterSchedules;
	}

	@Override
	public Set<ScheduleUnit> findNoSemesterScheduleScheduleUnits() throws DAOException {
		log.debug("Finding no semester schedule's schedule units");
		List<ScheduleUnit> scheduleUnits;
		Set<ScheduleUnit> noSemesterScheduleScheduleUnits = new HashSet<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT su.scheduleunit_id, su.date, su_d.discipline_id,su_l.lecturer_id, su_r.room_id, su_g.group_id "
					+ "FROM scheduleunit as su, scheduleunit_discipline as su_d, scheduleunit_lecturer as su_l, scheduleunit_room as su_r, scheduleunit_group as su_g "
					+ " WHERE su.scheduleunit_id <> ALL (SELECT scheduleunit_id FROM semesterschedule_scheduleunit as ss_su) "
					+ "AND su.scheduleunit_id=su_d.scheduleunit_id "
					+ "AND su.scheduleunit_id=su_l.scheduleunit_id "
					+ "AND su.scheduleunit_id=su_r.scheduleunit_id "
					+ "AND su.scheduleunit_id=su_g.scheduleunit_id ").addEntity(ScheduleUnit.class);
			scheduleUnits = (List<ScheduleUnit>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find no semester schedule's scheduleUnits", e);
			throw new DAOException("Cannot find no semester schedule's scheduleUnits", e);
		}
		noSemesterScheduleScheduleUnits.addAll(scheduleUnits);
		log.info(noSemesterScheduleScheduleUnits.size()
				+ " no semester schedule's scheduleUnits found");
		return noSemesterScheduleScheduleUnits;
	}
}
