package com.smarterama.semina.university.dao.jdbc;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smarterama.semina.university.dao.DAOException;
import com.smarterama.semina.university.dao.StudentDao;
import com.smarterama.semina.university.domain.Student;
import com.smarterama.semina.university.hibernate.HibernateUtil;

public class StudentDaoJdbc implements StudentDao {
	private static final Logger log = LogManager.getLogger(StudentDaoJdbc.class.getName());

	@Override
	public Student create(Student student) throws DAOException {
		Transaction transaction = null;
		log.debug("Creating new student with name = " + student.getName());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.save(student);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot create student", e);
			transaction.rollback();
			throw new DAOException("Cannot create student", e);
		}
		log.info("Student was created:" + student);
		return student;
	}

	@Override
	public Student update(Student student) throws DAOException {
		Transaction transaction = null;
		log.debug("Updating new student with name = " + student.getName());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.update(student);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot update student", e);
			transaction.rollback();
			throw new DAOException("Cannot update student", e);
		}
		log.info("Student was updated:" + student);
		return student;
	}

	@Override
	public void delete(int id) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting student with id = " + id);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Student student = session.get(Student.class, id);
			session.delete(student);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete student with id" + id, e);
			transaction.rollback();
			throw new DAOException("Cannot delete student with id" + id, e);
		}
		log.info("Student was deleted with id =" + id);
	}

	@Override
	public Student findOne(int id) throws DAOException {
		log.debug("Finding student with id = " + id);
		Student student = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("FROM Student WHERE id=:param");
			query.setParameter("param", id);
			student = (Student) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Cannot find one student with id" + id, e);
			throw new DAOException("Cannot find one student with id" + id, e);
		}
		log.info("Student was found with id =" + student);
		return student;
	}

	@Override
	public List<Student> findAll() throws DAOException {
		log.debug("Finding all students");
		List<Student> studentList;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Student");
			studentList = query.list();
		} catch (HibernateException e) {
			log.error("Cannot find all students", e);
			throw new DAOException("Cannot find all students", e);
		}
		log.info("All students were found");
		return studentList;
	}
}
