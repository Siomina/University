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
import com.smarterama.semina.university.dao.LecturerDao;
import com.smarterama.semina.university.domain.Discipline;
import com.smarterama.semina.university.domain.Lecturer;
import com.smarterama.semina.university.hibernate.HibernateUtil;

public class LecturerDaoJdbc implements LecturerDao {
	private static final Logger log = LogManager.getLogger(LecturerDaoJdbc.class.getName());

	@Override
	public Lecturer create(Lecturer lecturer) throws DAOException {
		Transaction transaction = null;
		log.debug("Creating new lecturer with name = " + lecturer.getName() + "and position = "
				+ lecturer.getPosition());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.save(lecturer);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot create lecturer", e);
			transaction.rollback();
			throw new DAOException("Cannot create lecturer", e);
		}
		log.info("Group was created:" + lecturer);
		return lecturer;
	}

	@Override
	public void addDiscipline(int lecturerId, int disciplineId) throws DAOException {
		Transaction transaction = null;
		log.debug("Adding discipline with id = " + disciplineId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Lecturer lecturer = session.get(Lecturer.class, lecturerId);
			Discipline discipline = session.get(Discipline.class, disciplineId);
			lecturer.addDiscipline(discipline);
			session.update(lecturer);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot add the discipline with Id " + disciplineId
					+ " to the lecturer with Id" + lecturerId, e);
			transaction.rollback();
			throw new DAOException("Cannot add the discipline with Id " + disciplineId
					+ " to the lecturer with Id" + lecturerId, e);
		}
		log.info("Discipline was added with id =" + disciplineId);

	}

	@Override
	public void deleteDiscipline(int lecturerId, int disciplineId) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting discipline with id = " + disciplineId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Lecturer lecturer = session.get(Lecturer.class, lecturerId);
			Discipline discipline = session.get(Discipline.class, disciplineId);
			lecturer.removeDiscipline(discipline);
			session.update(lecturer);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete the discipline with Id " + disciplineId
					+ " from the lecturer with Id" + lecturerId, e);
			transaction.rollback();
			throw new DAOException("Cannot delete the discipline with Id " + disciplineId
					+ " from the lecturer with Id" + lecturerId, e);
		}
		log.info("Discipline was deleted with id =" + disciplineId);

	}

	@Override
	public Lecturer update(Lecturer lecturer) throws DAOException {
		Transaction transaction = null;
		log.debug("Updating lecturer with name = " + lecturer.getName() + "and position = "
				+ lecturer.getPosition());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.update(lecturer);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot update lecturer", e);
			transaction.rollback();
			throw new DAOException("Cannot update lecturer", e);
		}
		log.info("Lecturer was updated:" + lecturer);
		return lecturer;
	}

	@Override
	public void delete(int id) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting lecturer with id = " + id);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Lecturer lecturer = session.get(Lecturer.class, id);
			session.delete(lecturer);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete lecturer with id" + id, e);
			transaction.rollback();
			throw new DAOException("Cannot delete lecturer with id" + id, e);
		}
		log.info("Lecturer was deleted with id =" + id);
	}

	@Override
	public Lecturer findOne(int id) throws DAOException {
		log.debug("Finding lecturer with id = " + id);
		Lecturer lecturer = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Lecturer where id=:param");
			query.setParameter("param", id);
			lecturer = (Lecturer) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Cannot find one lecturer with id" + id, e);
			throw new DAOException("Cannot find one lecturer with id" + id, e);
		}
		log.info("Lecturer was found with id =" + lecturer.getId());
		return lecturer;
	}

	@Override
	public List<Discipline> findLecturerDisciplines(int lecturerId) throws DAOException {
		log.debug("Finding all disciplines of the lecturer with id = " + lecturerId);
		Lecturer lecturer = null;
		List<Discipline> disciplines = new ArrayList<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Lecturer where id=:param");
			query.setParameter("param", lecturerId);
			lecturer = (Lecturer) query.uniqueResult();
			disciplines = lecturer.getDisciplines();
		} catch (HibernateException e) {
			log.error("Cannot find disciplines of the lecturer with id" + lecturerId, e);
			throw new DAOException("Cannot find disciplines of the lecturer with id" + lecturerId, e);
		}
		log.info("Disciplines of the lecturer were found with id = " + lecturerId);
		return disciplines;
	}

	@Override
	public List<Lecturer> findAll() throws DAOException {
		log.debug("Finding all lecturers");
		List<Lecturer> lecturerList = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Lecturer");

			lecturerList = (List<Lecturer>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find all lecturers", e);
			throw new DAOException("Cannot find all lecturers", e);
		}
		log.info("All lecturers were found");
		return lecturerList;
	}

	@Override
	public Set<Discipline> findNoLecturerDisciplines(int lecturerId) throws DAOException {
		log.debug("Finding no lecturer's disciplines");
		List<Discipline> disciplines;
		Set<Discipline> noLecturerDisciplines = new HashSet<Discipline>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT * FROM discipline "
					+ " WHERE discipline.discipline_id <> ALL (SELECT discipline_id FROM lecturer_disciplines WHERE lecturer_id =:param) ").addEntity(Discipline.class);
			query.setParameter("param", lecturerId);
			disciplines = (List<Discipline>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find no lecturer's disciplines with id" + lecturerId, e);
			throw new DAOException("Cannot find no lecturer's disciplines with id" + lecturerId, e);
		}
		noLecturerDisciplines.addAll(disciplines);
		log.info(noLecturerDisciplines.size() + " no lecturer's disciplines found");
		return noLecturerDisciplines;
	}
}
