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
import com.smarterama.semina.university.dao.FacultyDao;
import com.smarterama.semina.university.domain.Department;
import com.smarterama.semina.university.domain.Faculty;
import com.smarterama.semina.university.domain.Group;
import com.smarterama.semina.university.hibernate.HibernateUtil;

public class FacultyDaoJdbc implements FacultyDao {
	private static final Logger log = LogManager.getLogger(FacultyDaoJdbc.class.getName());

	@Override
	public Faculty create(Faculty faculty) throws DAOException {
		Transaction transaction = null;
		log.debug("Creating new faculty with name = " + faculty.getName());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.save(faculty);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot create faculty", e);
			transaction.rollback();
			throw new DAOException("Cannot create faculty", e);
		}
		log.info("Faculty was created:" + faculty);
		return faculty;
	}

	@Override
	public void addGroup(int facultyId, int groupId) throws DAOException {
		Transaction transaction = null;
		log.debug("Adding group with id = " + groupId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Faculty faculty = session.get(Faculty.class, facultyId);
			Group group = session.get(Group.class, groupId);
			faculty.addGroup(group);
			session.update(faculty);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot add the group with Id " + groupId + " to the faculty with Id"
					+ facultyId, e);
			transaction.rollback();
			throw new DAOException("Cannot add the group with Id " + groupId
					+ " to the faculty with Id" + facultyId, e);
		}
		log.info("Group was added with id =" + groupId);
	}

	@Override
	public void deleteGroup(int facultyId, int groupId) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting group with id = " + groupId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Faculty faculty = session.get(Faculty.class, facultyId);
			Group group = session.get(Group.class, groupId);
			faculty.removeGroup(group);
			session.update(faculty);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete the group with Id " + groupId + " from the faculty with Id"
					+ facultyId, e);
			transaction.rollback();
			throw new DAOException("Cannot delete the group with Id " + groupId
					+ " from the faculty with Id" + facultyId, e);
		}
		log.info("Group was deleted with id =" + groupId);
	}

	@Override
	public void addDepartment(int facultyId, int departmentId) throws DAOException {
		Transaction transaction = null;
		log.debug("Adding department with id = " + departmentId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Faculty faculty = session.get(Faculty.class, facultyId);
			Department department = session.get(Department.class, departmentId);
			faculty.addDepartment(department);
			session.update(faculty);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot add the department with Id " + departmentId
					+ " to the faculty with Id" + facultyId, e);
			transaction.rollback();
			throw new DAOException("Cannot add the department with Id " + departmentId
					+ " to the faculty with Id" + facultyId, e);
		}
		log.info("Department was added with id =" + departmentId);
	}

	@Override
	public void deleteDepartment(int facultyId, int departmentId) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting department with id = " + departmentId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Faculty faculty = session.get(Faculty.class, facultyId);
			Department department = session.get(Department.class, departmentId);
			faculty.removeDepartment(department);
			session.update(faculty);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete the department with Id " + departmentId
					+ " from the faculty with Id" + facultyId, e);
			transaction.rollback();
			throw new DAOException("Cannot delete the department with Id " + departmentId
					+ " from the faculty with Id" + facultyId, e);
		}
		log.info("Department was deleted with id =" + departmentId);
	}

	@Override
	public Faculty update(Faculty faculty) throws DAOException {
		Transaction transaction = null;
		log.debug("Updating faculty with name = " + faculty.getName());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.update(faculty);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot update the faculty", e);
			transaction.rollback();
			throw new DAOException("Cannot update the faculty", e);
		}
		log.info("Faculty was updated:" + faculty);
		return faculty;
	}

	@Override
	public void delete(int id) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting faculty with id = " + id);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Faculty faculty = session.get(Faculty.class, id);
			session.delete(faculty);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete the faculty with id" + id, e);
			transaction.rollback();
			throw new DAOException("Cannot delete the faculty with id" + id, e);
		}
		log.info("Faculty was deleted with id =" + id);
	}

	@Override
	public Faculty findOne(int id) throws DAOException {
		log.debug("Finding faculty with id = " + id);
		Faculty faculty = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Faculty where id=:param");
			query.setParameter("param", id);
			faculty = (Faculty) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Cannot find one faculty with id" + id, e);
			throw new DAOException("Cannot find one faculty with id" + id, e);
		}
		log.info("Faculty was found with id =" + id);
		return faculty;
	}

	@Override
	public List<Group> findFacultyGroups(int facultyId) throws DAOException {
		log.debug("Finding all groups of the faculty");
		Faculty faculty = null;
		List<Group> groups = new ArrayList<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Faculty where id=:param");
			query.setParameter("param", facultyId);
			faculty = (Faculty) query.uniqueResult();
			groups = faculty.getGroups();
		} catch (HibernateException e) {
			log.error("Cannot find groups of the faculty with id" + facultyId, e);
			throw new DAOException("Cannot find groups of the faculty with id" + facultyId, e);
		}
		log.info("Groups of the faculty were found");
		return groups;
	}

	@Override
	public List<Department> findFacultyDepartments(int facultyId) throws DAOException {
		log.debug("Finding departments of the faculty");
		Faculty faculty = null;
		List<Department> departments = new ArrayList<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Faculty where id=:param");
			query.setParameter("param", facultyId);
			faculty = (Faculty) query.uniqueResult();
			departments = faculty.getDepartments();
		} catch (HibernateException e) {
			log.error("Cannot find departments of the faculty with id" + facultyId, e);
			throw new DAOException("Cannot find departments of the faculty with id" + facultyId, e);
		}
		log.info("All departments were found");
		return departments;
	}

	@Override
	public List<Faculty> findAll() throws DAOException {
		log.debug("Finding all faculties");
		List<Faculty> facultyList = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Faculty");
			facultyList = (List<Faculty>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find all faculties", e);
			throw new DAOException("Cannot find all faculties", e);
		}
		log.info("All faculties were found");
		return facultyList;
	}

	@Override
	public Set<Group> findNoFacultyGroups(int facultyId) throws DAOException {
		log.debug("Finding no faculty's groups");
		List<Group> groups;
		Set<Group> noFacultyGroups = new HashSet<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT * FROM groups "
					+ " WHERE groups.group_id <> ALL (SELECT group_id FROM faculty_groups WHERE faculty_id =:param) ").addEntity(Group.class);
			query.setParameter("param", facultyId);
			groups = (List<Group>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find no faculty's groups with id" + facultyId, e);
			throw new DAOException("Cannot find no faculty's groups with id" + facultyId, e);
		}
		noFacultyGroups.addAll(groups);
		log.info(noFacultyGroups.size() + " no faculty's groups found");
		return noFacultyGroups;
	}

	@Override
	public Set<Department> findNoFacultyDepartments(int facultyId) throws DAOException {
		log.debug("Finding no faculty's departments");
		List<Department> departments;
		Set<Department> noFacultyDepartments = new HashSet<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT * FROM department "
					+ " WHERE department.department_id <> ALL (SELECT department_id FROM faculty_departments WHERE faculty_id =:param) ").addEntity(Department.class);
			query.setParameter("param", facultyId);
			departments = (List<Department>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find no faculty's departments with id" + facultyId, e);
			throw new DAOException("Cannot find no faculty's departments with id" + facultyId, e);
		}
		noFacultyDepartments.addAll(departments);
		log.info(noFacultyDepartments.size() + " no faculty's departments found");
		return noFacultyDepartments;
	}
}
