package com.smarterama.semina.university.dao.jdbc;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
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
import com.smarterama.semina.university.dao.ScheduleUnitDao;
import com.smarterama.semina.university.domain.Discipline;
import com.smarterama.semina.university.domain.Group;
import com.smarterama.semina.university.domain.Lecturer;
import com.smarterama.semina.university.domain.Room;
import com.smarterama.semina.university.domain.ScheduleUnit;
import com.smarterama.semina.university.hibernate.HibernateUtil;

public class ScheduleUnitDaoJdbc implements ScheduleUnitDao {
	private static final Logger log = LogManager.getLogger(ScheduleUnitDaoJdbc.class.getName());

	@Override
	public ScheduleUnit create(ScheduleUnit scheduleUnit) throws DAOException {
		Transaction transaction = null;
		log.debug("Creating new scheduleUnit");

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.save(scheduleUnit);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot create scheduleUnitDate", e);
			transaction.rollback();
			throw new DAOException("Cannot create scheduleUnitDate", e);
		}
		log.info("ScheduleUnit was created:" + scheduleUnit);
		return scheduleUnit;
	}

	@Override
	public void addRoom(int schedUnitId, int roomId) throws DAOException {
		Transaction transaction = null;
		log.debug("Adding room with Id = " + roomId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			ScheduleUnit scheduleUnit = session.get(ScheduleUnit.class, schedUnitId);
			Room room = session.get(Room.class, roomId);
			scheduleUnit.setRoom(room);
			session.update(scheduleUnit);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot add the room with Id " + roomId + " to the scheduleUnit with Id"
					+ schedUnitId, e);
			transaction.rollback();
			throw new DAOException("Cannot add the room with Id " + roomId
					+ " to the scheduleUnit with Id" + schedUnitId, e);
		}
		log.info("Room was added with id =" + roomId);
	}

	@Override
	public void deleteRoom(int schedUnitId, int roomId) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting room with id = " + roomId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			ScheduleUnit scheduleUnit = session.get(ScheduleUnit.class, schedUnitId);
			scheduleUnit.setRoom(null);
			session.update(scheduleUnit);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete the room with Id " + roomId + " from the scheduleUnit with Id"
					+ schedUnitId, e);
			transaction.rollback();
			throw new DAOException("Cannot delete the room with Id " + roomId
					+ " from the scheduleUnit with Id" + schedUnitId, e);
		}
		log.info("Room was deleted with id =" + roomId);
	}

	@Override
	public void addGroup(int schedUnitId, int groupId) throws DAOException {
		Transaction transaction = null;
		log.debug("Adding group with id = " + groupId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			ScheduleUnit scheduleUnit = session.get(ScheduleUnit.class, schedUnitId);
			Group group = session.get(Group.class, groupId);
			scheduleUnit.setGroup(group);
			session.update(scheduleUnit);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot add the group with Id " + groupId + " to the scheduleUnit with Id"
					+ schedUnitId, e);
			transaction.rollback();
			throw new DAOException("Cannot add the group with Id " + groupId
					+ " to the scheduleUnit with Id" + schedUnitId, e);
		}
		log.info("Group was added with id =" + groupId);
	}

	@Override
	public void deleteGroup(int schedUnitId, int groupId) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting group with id = " + groupId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			ScheduleUnit scheduleUnit = session.get(ScheduleUnit.class, schedUnitId);
			scheduleUnit.setGroup(null);
			session.update(scheduleUnit);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete the group with Id " + groupId
					+ " from the scheduleUnit with Id" + schedUnitId, e);
			transaction.rollback();
			throw new DAOException("Cannot delete the group with Id " + groupId
					+ " from the scheduleUnit with Id" + schedUnitId, e);
		}
		log.info("Group was deleted with id =" + groupId);
	}

	@Override
	public void addDiscipline(int schedUnitId, int disciplineId) throws DAOException {
		Transaction transaction = null;
		log.debug("Adding discipline with id = " + disciplineId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			ScheduleUnit scheduleUnit = session.get(ScheduleUnit.class, schedUnitId);
			Discipline discipline = session.get(Discipline.class, disciplineId);
			scheduleUnit.setDiscipline(discipline);
			session.update(scheduleUnit);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot add the discipline with Id " + disciplineId
					+ " to the scheduleUnit with Id" + schedUnitId, e);
			transaction.rollback();
			throw new DAOException("Cannot add the discipline with Id " + disciplineId
					+ " to the scheduleUnit with Id" + schedUnitId, e);
		}
		log.info("Discipline was added with id =" + disciplineId);
	}

	@Override
	public void deleteDiscipline(int schedUnitId, int disciplineId) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting discipline with id = " + disciplineId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			ScheduleUnit scheduleUnit = session.get(ScheduleUnit.class, schedUnitId);
			scheduleUnit.setDiscipline(null);
			session.update(scheduleUnit);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete the discipline with Id " + disciplineId
					+ " from the scheduleUnit with Id" + schedUnitId, e);
			transaction.rollback();
			throw new DAOException("Cannot delete the discipline with Id " + disciplineId
					+ " from the scheduleUnit with Id" + schedUnitId, e);
		}
		log.info("Discipline was deleted with id =" + disciplineId);
	}

	@Override
	public void addLecturer(int schedUnitId, int lecturerId) throws DAOException {
		Transaction transaction = null;
		log.debug("Adding lecturer with id = " + lecturerId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			ScheduleUnit scheduleUnit = session.get(ScheduleUnit.class, schedUnitId);
			Lecturer lecturer = session.get(Lecturer.class, lecturerId);
			scheduleUnit.setLecturer(lecturer);
			session.update(scheduleUnit);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot add the lecturer with Id " + lecturerId
					+ " to the scheduleUnit with Id" + schedUnitId, e);
			transaction.rollback();
			throw new DAOException("Cannot add the lecturer with Id " + lecturerId
					+ " to the scheduleUnit with Id" + schedUnitId, e);
		}
		log.info("Lecturer was added with id =" + lecturerId);
	}

	@Override
	public void deleteLecturer(int schedUnitId, int lecturerId) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting lecturer with id = " + lecturerId);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			ScheduleUnit scheduleUnit = session.get(ScheduleUnit.class, schedUnitId);
			scheduleUnit.setLecturer(null);
			session.update(scheduleUnit);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete the lecturer with Id " + lecturerId
					+ " from the scheduleUnit with Id" + schedUnitId, e);
			transaction.rollback();
			throw new DAOException("Cannot delete the lecturer with Id " + lecturerId
					+ " from the scheduleUnit with Id" + schedUnitId, e);
		}
		log.info("Lecturer was deleted with id =" + lecturerId);
	}

	@Override
	public ScheduleUnit update(ScheduleUnit scheduleUnit) throws DAOException {
		Transaction transaction = null;
		log.debug("Updating scheduleUnit");

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.update(scheduleUnit);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot update the scheduleUnit", e);
			transaction.rollback();
			throw new DAOException("Cannot update the scheduleUnit", e);
		}
		log.info("ScheduleUnit was updated:" + scheduleUnit);
		return scheduleUnit;
	}

	@Override
	public void delete(int id) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting scheduleUnit with id = " + id);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			ScheduleUnit scheduleUnit = session.get(ScheduleUnit.class, id);
			session.delete(scheduleUnit);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete the scheduleUnit with id" + id, e);
			transaction.rollback();
			throw new DAOException("Cannot delete the scheduleUnit with id" + id, e);
		}
		log.info("ScheduleUnit was deleted with id =" + id);
	}

	@Override
	public ScheduleUnit findOne(int id) throws DAOException {
		log.debug("Finding scheduleUnit with id = " + id);
		ScheduleUnit scheduleUnit = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from ScheduleUnit where id=:param");
			query.setParameter("param", id);
			scheduleUnit = (ScheduleUnit) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Cannot find one scheduleUnit with id" + id, e);
			throw new DAOException("Cannot find one scheduleUnit with id" + id, e);
		}
		log.info("ScheduleUnit was found with id =" + scheduleUnit);
		return scheduleUnit;
	}

	@Override
	public Room findScheduleUnitRoom(int scheduleUnitId) throws DAOException {
		log.debug("Finding the room of the scheduleUnit");
		ScheduleUnit scheduleUnit = null;
		Room room = new Room();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from ScheduleUnit where id=:param");
			query.setParameter("param", scheduleUnitId);
			scheduleUnit = (ScheduleUnit) query.uniqueResult();
			room = scheduleUnit.getRoom();
		} catch (HibernateException e) {
			log.error("Cannot find room of the scheduleUnit with id" + scheduleUnitId, e);
			throw new DAOException("Cannot find room of the scheduleUnit with id" + scheduleUnitId, e);
		}
		log.info("Room of the scheduleUnit was found");
		return room;
	}

	@Override
	public Group findScheduleUnitGroup(int scheduleUnitId) throws DAOException {
		log.debug("Finding the group of the scheduleUnit");
		ScheduleUnit scheduleUnit = null;
		Group group = new Group();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from ScheduleUnit where id=:param");
			query.setParameter("param", scheduleUnitId);
			scheduleUnit = (ScheduleUnit) query.uniqueResult();
			group = scheduleUnit.getGroup();
		} catch (HibernateException e) {
			log.error("Cannot find group of the scheduleUnit with id" + scheduleUnitId, e);
			throw new DAOException("Cannot find group of the scheduleUnit with id" + scheduleUnitId, e);
		}
		log.info("Group of the scheduleUnit was found");
		return group;
	}

	@Override
	public Discipline findScheduleUnitDiscipline(int scheduleUnitId) throws DAOException {
		log.debug("Finding the group of the scheduleUnit");
		ScheduleUnit scheduleUnit = null;
		Discipline discipline = new Discipline();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from ScheduleUnit where id=:param");
			query.setParameter("param", scheduleUnitId);
			scheduleUnit = (ScheduleUnit) query.uniqueResult();
			discipline = scheduleUnit.getDiscipline();
		} catch (HibernateException e) {
			log.error("Cannot find discipline of the scheduleUnit with id" + scheduleUnitId, e);
			throw new DAOException("Cannot find discipline of the scheduleUnit with id"
					+ scheduleUnitId, e);
		}
		log.info("Discipline of the scheduleUnit was found");
		return discipline;
	}

	@Override
	public Lecturer findScheduleUnitLecturer(int scheduleUnitId) throws DAOException {
		log.debug("Finding the lecturer of the scheduleUnit");
		ScheduleUnit scheduleUnit = null;
		Lecturer lecturer = new Lecturer();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from ScheduleUnit where id=:param");
			query.setParameter("param", scheduleUnitId);
			scheduleUnit = (ScheduleUnit) query.uniqueResult();
			lecturer = scheduleUnit.getLecturer();
		} catch (HibernateException e) {
			log.error("Cannot find lecturer of the scheduleUnit with id" + scheduleUnitId, e);
			throw new DAOException("Cannot find lecturer of the scheduleUnit with id"
					+ scheduleUnitId, e);
		}
		log.info("Lecturer of the scheduleUnit was found");
		return lecturer;
	}

	@Override
	public List<ScheduleUnit> findAll() throws DAOException {
		log.debug("Finding all scheduleUnits");
		List<ScheduleUnit> scheduleUnitList = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from ScheduleUnit");
			scheduleUnitList = (List<ScheduleUnit>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find all scheduleUnits", e);
			throw new DAOException("Cannot find all scheduleUnits", e);
		}
		log.info("All scheduleUnits were found");
		return scheduleUnitList;
	}

	@Override
	public Set<Room> findNoScheduleUnitRooms(int scheduleUnitId) throws DAOException {
		log.debug("Finding no scheduleUnit's rooms");
		List<Room> rooms;
		Set<Room> noScheduleUnitRooms = new HashSet<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT * FROM room "
					+ " WHERE room.room_id <> ALL (SELECT room_id FROM scheduleunit_room WHERE scheduleunit_id =:param) ").addEntity(Room.class);
			query.setParameter("param", scheduleUnitId);
			rooms = (List<Room>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find no scheduleUnit's rooms with id" + scheduleUnitId, e);
			throw new DAOException("Cannot find no scheduleUnit's rooms with id" + scheduleUnitId, e);
		}
		noScheduleUnitRooms.addAll(rooms);
		log.info(noScheduleUnitRooms.size() + " no scheduleUnit's rooms found");
		return noScheduleUnitRooms;
	}

	@Override
	public Set<Group> findNoScheduleUnitGroups(int scheduleUnitId) throws DAOException {
		log.debug("Finding no scheduleUnit's groups");
		List<Group> groups;
		Set<Group> noScheduleUnitGroups = new HashSet<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT * FROM groups "
					+ " WHERE groups.group_id <> ALL (SELECT group_id FROM scheduleunit_group WHERE scheduleunit_id =:param) ").addEntity(Group.class);
			query.setParameter("param", scheduleUnitId);
			groups = (List<Group>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find no scheduleUnit's groups with id" + scheduleUnitId, e);
			throw new DAOException("Cannot find no scheduleUnit's groups with id" + scheduleUnitId, e);
		}
		noScheduleUnitGroups.addAll(groups);
		log.info(noScheduleUnitGroups.size() + " no scheduleUnit's groups found");
		return noScheduleUnitGroups;
	}

	@Override
	public Set<Discipline> findNoScheduleUnitDisciplines(int scheduleUnitId) throws DAOException {
		log.debug("Finding no scheduleUnit's disciplines");
		List<Discipline> disciplines;
		Set<Discipline> noScheduleUnitDisciplines = new HashSet<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT * FROM discipline "
					+ " WHERE discipline.discipline_id <> ALL (SELECT discipline_id FROM scheduleunit_discipline WHERE scheduleunit_id =:param) ").addEntity(Discipline.class);
			query.setParameter("param", scheduleUnitId);
			disciplines = (List<Discipline>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find no scheduleUnit's disciplines with id" + scheduleUnitId, e);
			throw new DAOException("Cannot find no scheduleUnit's disciplines with id"
					+ scheduleUnitId, e);
		}
		noScheduleUnitDisciplines.addAll(disciplines);
		log.info(noScheduleUnitDisciplines.size() + " no scheduleUnit's disciplines found");
		return noScheduleUnitDisciplines;
	}

	@Override
	public Set<Lecturer> findNoScheduleUnitLecturers(int scheduleUnitId) throws DAOException {
		log.debug("Finding no scheduleUnit's lecturers");
		List<Lecturer> lecturers;
		Set<Lecturer> noScheduleUnitLecturers = new HashSet<>();

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT * FROM lecturer "
					+ " WHERE lecturer.lecturer_id <> ALL (SELECT lecturer_id FROM scheduleunit_lecturer WHERE scheduleunit_id =:param) ").addEntity(Lecturer.class);
			query.setParameter("param", scheduleUnitId);
			lecturers = (List<Lecturer>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find no scheduleUnit's lecturers with id" + scheduleUnitId, e);
			throw new DAOException("Cannot find no scheduleUnit's lecturers with id"
					+ scheduleUnitId, e);
		}
		noScheduleUnitLecturers.addAll(lecturers);
		log.info(noScheduleUnitLecturers.size() + " no scheduleUnit's lecturers found");
		return noScheduleUnitLecturers;
	}

	@Override
	public List<ScheduleUnit> findDayStudentSchedule(int studentId, GregorianCalendar calendar)
			throws DAOException {
		log.debug("Finding day's student scheduleUnits");
		List<ScheduleUnit> scheduleUnitList;
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT su.scheduleunit_id, su.date, s_d.discipline_id,s_l.lecturer_id, s_r.room_id, s_g.group_id "
					+ "FROM scheduleunit as su, groups_students as g_s, student as s, scheduleunit_discipline as s_d, scheduleunit_lecturer as s_l, scheduleunit_room as s_r, scheduleunit_group as s_g "
					+ "WHERE su.scheduleunit_id = s_g.scheduleunit_id AND s_g.group_id = g_s.group_id AND g_s.student_id = s.student_id AND s.student_id =:param1 "
					+ "AND (SELECT date_trunc('day', su.date))= (SELECT date_trunc('day', cast(:param2 as timestamp)))"
					+ "AND su.scheduleunit_id=s_d.scheduleunit_id "
					+ "AND su.scheduleunit_id=s_l.scheduleunit_id "
					+ "AND su.scheduleunit_id=s_r.scheduleunit_id "
					+ "AND su.scheduleunit_id=s_g.scheduleunit_id ").addEntity(ScheduleUnit.class);
			query.setParameter("param1", studentId);
			query.setParameter("param2", timestamp);
			scheduleUnitList = (List<ScheduleUnit>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find day's student scheduleUnits", e);
			throw new DAOException("Cannot find day's student scheduleUnits", e);
		}
		log.info("All scheduleUnits were found");
		return scheduleUnitList;
	}

	@Override
	public List<ScheduleUnit> findMonthStudentSchedule(int studentId, GregorianCalendar calendar)
			throws DAOException {
		log.debug("Finding month's student scheduleUnits");
		List<ScheduleUnit> scheduleUnitList;
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT su.scheduleunit_id, su.date, s_d.discipline_id,s_l.lecturer_id, s_r.room_id, s_g.group_id "
					+ "FROM scheduleunit as su, groups_students as g_s, student as s, scheduleunit_discipline as s_d, scheduleunit_lecturer as s_l, scheduleunit_room as s_r, scheduleunit_group as s_g "
					+ "WHERE su.scheduleunit_id = s_g.scheduleunit_id AND s_g.group_id = g_s.group_id AND g_s.student_id = s.student_id AND s.student_id =:param1 "
					+ "AND (SELECT date_trunc('month', su.date))= (SELECT date_trunc('month', cast(:param2 as timestamp)))"
					+ "AND su.scheduleunit_id=s_d.scheduleunit_id "
					+ "AND su.scheduleunit_id=s_l.scheduleunit_id "
					+ "AND su.scheduleunit_id=s_r.scheduleunit_id "
					+ "AND su.scheduleunit_id=s_g.scheduleunit_id ").addEntity(ScheduleUnit.class);
			query.setParameter("param1", studentId);
			query.setParameter("param2", timestamp);
			scheduleUnitList = (List<ScheduleUnit>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find month's student scheduleUnits", e);
			throw new DAOException("Cannot find month's student scheduleUnits", e);
		}
		log.info("All scheduleUnits were found");
		return scheduleUnitList;
	}

	@Override
	public List<ScheduleUnit> findDayLecturerSchedule(int lecturerId, GregorianCalendar calendar)
			throws DAOException {
		log.debug("Finding day's lecturer scheduleUnits");
		List<ScheduleUnit> scheduleUnitList;
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT su.scheduleunit_id, su.date, s_d.discipline_id,s_l.lecturer_id, s_r.room_id, s_g.group_id "
					+ "FROM scheduleunit as su, lecturer as l, scheduleunit_discipline as s_d, scheduleunit_lecturer as s_l, scheduleunit_room as s_r, scheduleunit_group as s_g "
					+ "WHERE su.scheduleunit_id = s_l.scheduleunit_id AND s_l.lecturer_id = l.lecturer_id AND l.lecturer_id =:param1 "
					+ "AND (SELECT date_trunc('day', su.date))= (SELECT date_trunc('day', cast(:param2 as timestamp)))"
					+ "AND su.scheduleunit_id=s_d.scheduleunit_id "
					+ "AND su.scheduleunit_id=s_l.scheduleunit_id "
					+ "AND su.scheduleunit_id=s_r.scheduleunit_id "
					+ "AND su.scheduleunit_id=s_g.scheduleunit_id ").addEntity(ScheduleUnit.class);
			query.setParameter("param1", lecturerId);
			query.setParameter("param2", timestamp);
			scheduleUnitList = (List<ScheduleUnit>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find day's lecturer scheduleUnits", e);
			throw new DAOException("Cannot find day's lecturer scheduleUnits", e);
		}
		log.info("All scheduleUnits were found");
		return scheduleUnitList;
	}

	@Override
	public List<ScheduleUnit> findMonthLecturerSchedule(int lecturerId, GregorianCalendar calendar)
			throws DAOException {
		log.debug("Finding month's lecturer scheduleUnits");
		List<ScheduleUnit> scheduleUnitList;
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT su.scheduleunit_id, su.date, s_d.discipline_id,s_l.lecturer_id, s_r.room_id, s_g.group_id "
					+ "FROM scheduleunit as su, lecturer as l, scheduleunit_discipline as s_d, scheduleunit_lecturer as s_l, scheduleunit_room as s_r, scheduleunit_group as s_g "
					+ "WHERE su.scheduleunit_id = s_l.scheduleunit_id AND s_l.lecturer_id = l.lecturer_id AND l.lecturer_id =:param1 "
					+ "AND (SELECT date_trunc('month', su.date))= (SELECT date_trunc('month', cast(:param2 as timestamp)))"
					+ "AND su.scheduleunit_id=s_d.scheduleunit_id "
					+ "AND su.scheduleunit_id=s_l.scheduleunit_id "
					+ "AND su.scheduleunit_id=s_r.scheduleunit_id "
					+ "AND su.scheduleunit_id=s_g.scheduleunit_id ").addEntity(ScheduleUnit.class);
			query.setParameter("param1", lecturerId);
			query.setParameter("param2", timestamp);
			scheduleUnitList = (List<ScheduleUnit>) query.list();
		} catch (HibernateException e) {
			log.error("Cannot find day's lecturer scheduleUnits", e);
			throw new DAOException("Cannot find day's lecturer scheduleUnits", e);
		}
		log.info("All scheduleUnits were found");
		return scheduleUnitList;
	}
}