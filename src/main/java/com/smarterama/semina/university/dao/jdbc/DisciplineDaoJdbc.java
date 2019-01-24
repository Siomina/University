package com.smarterama.semina.university.dao.jdbc;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smarterama.semina.university.dao.DAOException;
import com.smarterama.semina.university.dao.DisciplineDao;
import com.smarterama.semina.university.domain.Discipline;
import com.smarterama.semina.university.hibernate.HibernateUtil;

public class DisciplineDaoJdbc implements DisciplineDao {
	private static final Logger log = LogManager.getLogger(DisciplineDaoJdbc.class.getName());

	@Override
	public Discipline create(Discipline discipline) throws DAOException {
		Transaction transaction = null;
		log.debug("Creating new discipline with name = " + discipline.getName());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.save(discipline);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot create discipline", e);
			transaction.rollback();
			throw new DAOException("Cannot create discipline", e);
		}
		log.info("Discipline was created:" + discipline);
		return discipline;
	}

	@Override
	public Discipline update(Discipline discipline) throws DAOException {
		Transaction transaction = null;
		log.debug("Updating discipline with name = " + discipline.getName());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.update(discipline);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot update discipline", e);
			transaction.rollback();
			throw new DAOException("Cannot update discipline", e);
		}
		log.info("Discipline was updated:" + discipline);
		return discipline;
	}

	@Override
	public void delete(int id) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting discipline with id = " + id);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Discipline discipline = session.get(Discipline.class, id);
			session.delete(discipline);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete discipline with id" + id, e);
			transaction.rollback();
			throw new DAOException("Cannot delete discipline with id" + id, e);
		}
		log.info("Discipline was deleted with id =" + id);
	}

	@Override
	public Discipline findOne(int id) throws DAOException {
		log.debug("Finding discipline with id = " + id);
		Discipline discipline = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Discipline where id=:param");
			query.setParameter("param", id);
			discipline = (Discipline) query.uniqueResult();

		} catch (HibernateException e) {
			log.error("Cannot find one discipline with id" + id, e);
			throw new DAOException("Cannot find one discipline with id" + id, e);
		}
		log.info("Discipline was found with id =" + id);
		return discipline;
	}

	@Override
	public List<Discipline> findAll() throws DAOException {
		log.debug("Finding all disciplines");
		List<Discipline> disciplineList = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Discipline");
			disciplineList = (List<Discipline>) query.list();

		} catch (HibernateException e) {
			log.error("Cannot find all disciplines", e);
			throw new DAOException("Cannot find all disciplines", e);
		}
		log.info("All disciplines were found");
		return disciplineList;
	}
}
