package com.smarterama.semina.university.dao.jdbc;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.smarterama.semina.university.dao.DAOException;
import com.smarterama.semina.university.dao.RoomDao;
import com.smarterama.semina.university.domain.Room;
import com.smarterama.semina.university.hibernate.HibernateUtil;

public class RoomDaoJdbc implements RoomDao {
	private static final Logger log = LogManager.getLogger(RoomDaoJdbc.class.getName());

	@Override
	public Room create(Room room) throws DAOException {
		Transaction transaction = null;
		log.debug("Creating new room with name = " + room.getName());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.save(room);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot create room", e);
			transaction.rollback();
			throw new DAOException("Cannot create room", e);
		}
		log.info("Room was created:" + room);
		return room;
	}

	@Override
	public Room update(Room room) throws DAOException {
		Transaction transaction = null;
		log.debug("Updating new room with name = " + room.getName());

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			session.update(room);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot update room", e);
			transaction.rollback();
			throw new DAOException("Cannot update room", e);
		}
		log.info("Room was updated:" + room);
		return room;
	}

	@Override
	public void delete(int id) throws DAOException {
		Transaction transaction = null;
		log.debug("Deleting room with id = " + id);

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			transaction = session.beginTransaction();
			Room room = session.get(Room.class, id);
			session.delete(room);
			transaction.commit();
		} catch (HibernateException e) {
			log.error("Cannot delete room with id" + id, e);
			transaction.rollback();
			throw new DAOException("Cannot delete room with id" + id, e);
		}
		log.info("Room was deleted with id =" + id);
	}

	@Override
	public Room findOne(int id) throws DAOException {
		log.debug("Finding room with id = " + id);
		Room room = null;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("FROM Room WHERE id=:param");
			query.setParameter("param", id);
			room = (Room) query.uniqueResult();
		} catch (HibernateException e) {
			log.error("Cannot find one room with id" + id, e);
			throw new DAOException("Cannot find one room with id" + id, e);
		}
		log.info("Room was found with id =" + room);
		return room;
	}

	@Override
	public List<Room> findAll() throws DAOException {
		log.debug("Finding all rooms");
		List<Room> roomList;

		try (Session session = HibernateUtil.getCurrentSession().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Room");
			roomList = query.list();
		} catch (HibernateException e) {
			log.error("Cannot find all rooms", e);
			throw new DAOException("Cannot find all rooms", e);
		}
		log.info("All rooms were found");
		return roomList;
	}
}
