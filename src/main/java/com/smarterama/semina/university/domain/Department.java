package com.smarterama.semina.university.domain;

import java.util.ArrayList;
import java.util.List;

public class Department {

	private int id;
	private String name;
	private List<Lecturer> lecturers = new ArrayList<Lecturer>();
	private List<Discipline> disciplines = new ArrayList<Discipline>();

	public Department() {

	}
//
	public Department(String name) {
		this.name = name;
	}

	public Department(String name, List<Lecturer> lecturers, List<Discipline> disciplines) {
		this.name = name;
		this.lecturers = lecturers;
		this.disciplines = disciplines;
	}

	public void addLecturer(Lecturer lecturerAdded) {
		if (lecturerAdded == null) {
			throw new IllegalArgumentException("The argument cannot be null");
		}
		lecturers.add(lecturerAdded);
	}

	public void removeLecturer(Lecturer lecturerRemoved) {
		lecturers.remove(lecturerRemoved);
	}

	public void addDiscipline(Discipline disciplineAdded) {
		if (disciplineAdded == null) {
			throw new IllegalArgumentException("The argument cannot be null");
		}
		disciplines.add(disciplineAdded);
	}

	public void removeDiscipline(Discipline disciplineRemoved) {
		disciplines.remove(disciplineRemoved);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Lecturer> getLecturers() {
		return lecturers;
	}

	public void setLecturers(List<Lecturer> lecturers) {
		this.lecturers = lecturers;
	}

	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
}
