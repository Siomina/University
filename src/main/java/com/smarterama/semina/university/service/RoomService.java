package com.smarterama.semina.university.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smarterama.semina.university.dao.DAOException;
import com.smarterama.semina.university.dao.RoomDao;
import com.smarterama.semina.university.dao.jdbc.RoomDaoJdbc;
import com.smarterama.semina.university.domain.Room;

public class RoomService {
	private static final Logger log = LogManager.getLogger(RoomService.class.getName());
	private RoomDao roomDao = new RoomDaoJdbc();

	public Room findOne(int id) throws ServiceException {
		Room room;
		try {
			room = roomDao.findOne(id);
		} catch (DAOException e) {
			log.error("Cannot find room", e);
			throw new ServiceException("Cannot find room", e);
		}
		return room;
	}

	public List<Room> findAll() throws ServiceException {
		List<Room> rooms;
		try {
			rooms = roomDao.findAll();
		} catch (DAOException e) {
			log.error("Cannot find all rooms", e);
			throw new ServiceException("Cannot find all rooms", e);
		}
		return rooms;
	}

	public Room create(Room room) throws ServiceException {

		try {
			room = roomDao.create(room);
		} catch (DAOException e) {
			log.error("Cannot create room", e);
			throw new ServiceException("Cannot create room", e);
		}
		return room;
	}

	public Room update(Room room) throws ServiceException {
		try {
			room = roomDao.update(room);
		} catch (DAOException e) {
			log.error("Cannot update room", e);
			throw new ServiceException("Cannot update room", e);
		}
		return room;
	}

	public void delete(int id) throws ServiceException {
		try {
			roomDao.delete(id);
		} catch (DAOException e) {
			log.error("Cannot delete room", e);
			throw new ServiceException("Cannot delete room", e);
		}
	}
}
