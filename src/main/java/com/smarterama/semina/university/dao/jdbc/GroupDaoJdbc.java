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
import com.smarterama.semina.university.dao.GroupDao;
import com.smarterama.semina.university.domain.Group;
import com.smarterama.semina.university.domain.Student;
import com.smarterama.semina.university.hibernate.HibernateUtil;

public class GroupDaoJdbc implements GroupDao {
	private static final Logger log = LogManager.getLogger(GroupDaoJdbc.class.getName());

	@Override
	public Group create(Group group) throws DAOException {
		Transaction transaction = null;
		log.debug("Creating new group with name = " + group.getName());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.save(group);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot create group", e);
			transaction.rollback();
			throw new DAOException("Cannot create group", e);
		}
		log.info("Group was created:" + group);
		return group;
	}

	@Override
	public void addStudent(int groupId, int studentId) throws DAOException {
		Transaction transaction = null;
		log.debug("Adding student with Id = " + studentId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Group group = session.get(Group.class, groupId);
			Student student = session.get(Student.class, studentId);
			group.addStudent(student);
			session.update(group);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot add the student with Id " + studentId + " to the group with Id"
					+ groupId, e);
			transaction.rollback();
			throw new DAOException("Cannot add the student with Id " + studentId
					+ " to the group with Id" + groupId, e);
		}
		log.info("Student was added with id =" + studentId);
	}

	@Override
	public void deleteStudent(int groupId, int studentId) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting student with Id = " + studentId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Group group = session.get(Group.class, groupId);
			Student student = session.get(Student.class, studentId);
			group.removeStudent(student);
			session.update(group);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete the student with Id " + studentId + " from the group with Id"
					+ groupId, e);
			transaction.rollback();
			throw new DAOException("Cannot delete the student with Id " + studentId
					+ " from the group with Id" + groupId, e);
		}
		log.info("Student was deleted with id =" + studentId);
	}

	@Override
	public Group update(Group group) throws DAOException {
		Transaction transaction = null;
		log.debug("Updating group with name = " + group.getName());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.update(group);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot update group", e);
			transaction.rollback();
			throw new DAOException("Cannot update group", e);
		}
		log.info("Group was updated:" + group);
		return group;
	}

	@Override
	public void delete(int id) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting group with id = " + id);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Group group = session.get(Group.class, id);
			session.delete(group);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete group with id" + id, e);
			transaction.rollback();
			throw new DAOException("Cannot delete group with id" + id, e);
		}
		log.info("Group was deleted with id =" + id);
	}

	@Override
	public Group findOne(int id) throws DAOException {
		log.debug("Finding group with id = " + id);
		Group group = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Group where id=:param");
			query.setParameter("param", id);
			group = (Group) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Cannot find one group with id" + id, e);
			throw new DAOException("Cannot find one group with id" + id, e);
		}
		log.info("Group was found with id =" + id);
		return group;
	}

	@Override
	public List<Student> findGroupStudents(int groupId) throws DAOException {
		log.debug("Finding all students of the group with id = " + groupId);
		Group group = null;
		List<Student> students = new ArrayList<Student>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Group where id=:param");
			query.setParameter("param", groupId);
			group = (Group) query.uniqueResult();
			students = group.getStudents();
		} catch (HibernateException e) {
			log.error("Cannot find students of the group with id = " + groupId, e);
			throw new DAOException("Cannot find students of the group with id = " + groupId, e);
		}
		log.info("Students of the group were found with id = " + groupId);
		return students;
	}

	@Override
	public List<Group> findAll() throws DAOException {
		log.debug("Finding all groups");
		List<Group> groupList = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Group");
			groupList = (List<Group>) query.list();

		} catch (HibernateException e) {
			log.error("Cannot find all groups", e);
			throw new DAOException("Cannot find all groups", e);
		}
		log.info("All groups were found");
		return groupList;
	}

	@Override
	public Set<Student> findNoGroupStudents() throws DAOException {
		log.debug("Finding students with no group");
		List<Student> students;
		Set<Student> noGroupStudent = new HashSet<Student>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT * FROM student "
					+ " WHERE student.student_id <> ALL (SELECT student_id FROM groups_students)").addEntity(Student.class);
			students = (List<Student>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find students with no group", e);
			throw new DAOException("Cannot find students with no group ", e);
		}
		noGroupStudent.addAll(students);
		log.info(noGroupStudent.size() + " students with no group were found");
		return noGroupStudent;
	}
}
