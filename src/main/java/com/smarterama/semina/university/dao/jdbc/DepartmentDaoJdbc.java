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
import com.smarterama.semina.university.dao.DepartmentDao;
import com.smarterama.semina.university.domain.Department;
import com.smarterama.semina.university.domain.Discipline;
import com.smarterama.semina.university.domain.Lecturer;
import com.smarterama.semina.university.hibernate.HibernateUtil;

public class DepartmentDaoJdbc implements DepartmentDao {
	private static final Logger log = LogManager.getLogger(DepartmentDaoJdbc.class.getName());

	@Override
	public Department create(Department department) throws DAOException {
		Transaction transaction = null;
		log.debug("Creating new department with name = " + department.getName());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.save(department);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot create department", e);
			transaction.rollback();
			throw new DAOException("Cannot create department", e);
		}
		log.info("Department was created:" + department);
		return department;
	}

	@Override
	public void addLecturer(int departmentId, int lecturerId) throws DAOException {
		Transaction transaction = null;
		log.debug("Adding lecturer with Id = " + lecturerId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Department department = session.get(Department.class, departmentId);
			Lecturer lecturer = session.get(Lecturer.class, lecturerId);
			department.addLecturer(lecturer);
			session.update(department);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot add the lecturer with Id " + lecturerId
					+ " to the department with Id" + departmentId, e);
			transaction.rollback();
			throw new DAOException("Cannot add the lecturer with Id " + lecturerId
					+ " to the department with Id" + departmentId, e);
		}
		log.info("Lecturer was added with id =" + lecturerId);
	}

	@Override
	public void deleteLecturer(int departmentId, int lecturerId) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting lecturer with Id = " + lecturerId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Department department = session.get(Department.class, departmentId);
			Lecturer lecturer = session.get(Lecturer.class, lecturerId);
			department.removeLecturer(lecturer);
			session.update(department);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete the lecturer with Id " + lecturerId
					+ " from the department with Id" + departmentId, e);
			transaction.rollback();
			throw new DAOException("Cannot delete the lecturer with Id " + lecturerId
					+ " from the department with Id" + departmentId, e);
		}
		log.info("Lecturer was deleted with id =" + lecturerId);
	}

	@Override
	public void addDiscipline(int departmentId, int disciplineId) throws DAOException {
		Transaction transaction = null;
		log.debug("Adding discipline with Id = " + disciplineId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Department department = session.get(Department.class, departmentId);
			Discipline discipline = session.get(Discipline.class, disciplineId);
			department.addDiscipline(discipline);
			session.update(department);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot add the discipline with Id " + disciplineId
					+ "to the department with Id" + departmentId, e);
			transaction.rollback();
			throw new DAOException("Cannot add the discipline with Id " + disciplineId
					+ "to the department with Id" + departmentId, e);
		}
		log.info("Discipline was added with id =" + disciplineId);
	}

	@Override
	public void deleteDiscipline(int departmentId, int disciplineId) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting discipline with Id = " + disciplineId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Department department = session.get(Department.class, departmentId);
			Discipline discipline = session.get(Discipline.class, disciplineId);
			department.removeDiscipline(discipline);
			session.update(department);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete the discipline with Id " + disciplineId
					+ "from the department with Id" + departmentId, e);
			transaction.rollback();
			throw new DAOException("Cannot delete the discipline with Id " + disciplineId
					+ "from the department with Id" + departmentId, e);
		}
		log.info("Discipline was deleted with id =" + disciplineId);
	}

	@Override
	public Department update(Department department) throws DAOException {
		Transaction transaction = null;
		log.debug("Updating department with name = " + department.getName());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.update(department);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot update the department", e);
			transaction.rollback();
			throw new DAOException("Cannot update the department", e);
		}
		log.info("Department was updated:" + department);
		return department;
	}

	@Override
	public void delete(int id) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting department with id = " + id);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Department department = session.get(Department.class, id);
			session.delete(department);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete department", e);
			transaction.rollback();
			throw new DAOException("Cannot delete group with id" + id, e);
		}
		log.info("Department was deleted with id =" + id);
	}

	@Override
	public Department findOne(int id) throws DAOException {
		log.debug("Finding department with id = " + id);
		Department department = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Department where id=:param");
			query.setParameter("param", id);
			department = (Department) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Cannot find one department with id" + id, e);
			throw new DAOException("Cannot find one department with id" + id, e);
		}
		log.info("Department was found with id =" + id);
		return department;
	}

	@Override
	public List<Lecturer> findDepartmentLecturers(int departmentId) throws DAOException {
		log.debug("Finding all lecturers of the department with id = " + departmentId);
		Department department = null;
		List<Lecturer> lecturers = new ArrayList<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Department where id=:param");
			query.setParameter("param", departmentId);
			department = (Department) query.uniqueResult();
			lecturers = department.getLecturers();
		} catch (HibernateException e) {
			log.error("Cannot find lecturers for the department with id" + departmentId, e);
			throw new DAOException("Cannot find lecturers for the department with id"
					+ departmentId, e);
		}
		log.info("Lecturers of the department were found");
		return lecturers;
	}

	@Override
	public List<Discipline> findDepartmentDisciplines(int departmentId) throws DAOException {
		log.debug("Finding disciplines of the department with id = " + departmentId);
		Department department = null;
		List<Discipline> disciplines = new ArrayList<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Department where id=:param");
			query.setParameter("param", departmentId);
			department = (Department) query.uniqueResult();
			disciplines = department.getDisciplines();
		} catch (HibernateException e) {
			log.error("Cannot find disciplines for the department with id" + departmentId, e);
			throw new DAOException("Cannot find disciplines for the department with id"
					+ departmentId, e);
		}
		log.info("Disciplines of the department were found");
		return disciplines;
	}

	@Override
	public List<Department> findAll() throws DAOException {
		log.debug("Finding all departments");
		List<Department> departmentList = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Department");
			departmentList = (List<Department>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find all departments", e);
			throw new DAOException("Cannot find all departments", e);
		}
		log.info("All departments were found");
		return departmentList;
	}

	@Override
	public Set<Discipline> findNoDepartmentDisciplines(int departmentId) throws DAOException {
		log.debug("Finding no department's disciplines");
		List<Discipline> disciplines;
		Set<Discipline> noDepartmentDisciplines = new HashSet<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT * FROM discipline "
					+ " WHERE discipline.discipline_id <> ALL (SELECT discipline_id FROM department_disciplines WHERE department_id =:param) ").addEntity(Discipline.class);
			query.setParameter("param", departmentId);
			disciplines = (List<Discipline>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find no department's disciplines with id" + departmentId, e);
			throw new DAOException("Cannot find no department's disciplines with id" + departmentId, e);
		}
		noDepartmentDisciplines.addAll(disciplines);
		log.info(noDepartmentDisciplines.size() + " no department's disciplines found");
		return noDepartmentDisciplines;
	}

	@Override
	public Set<Lecturer> findNoDepartmentLecturers(int departmentId) throws DAOException {
		log.debug("Finding no department's lecturers");
		List<Lecturer> lecturers;
		Set<Lecturer> noDepartmentLecturers = new HashSet<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT * FROM lecturer "
					+ " WHERE lecturer.lecturer_id <> ALL (SELECT lecturer_id FROM department_lecturers WHERE department_id =:param) ").addEntity(Lecturer.class);
			query.setParameter("param", departmentId);
			lecturers = (List<Lecturer>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find no department's lecturers with id" + departmentId, e);
			throw new DAOException("Cannot find no department's lecturers with id" + departmentId, e);
		}
		noDepartmentLecturers.addAll(lecturers);
		log.info(noDepartmentLecturers.size() + " no department's lecturers found");
		return noDepartmentLecturers;
	}
}
