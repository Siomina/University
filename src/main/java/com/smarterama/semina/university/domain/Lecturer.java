package com.smarterama.semina.university.domain;

import java.util.ArrayList;
import java.util.List;

public class Lecturer {

	private int id;
	private String name;
	private String position;
	private List<Discipline> disciplines = new ArrayList<Discipline>();

	public Lecturer() {

	}

	public Lecturer(String name, String position) {
		this.name = name;
		this.position = position;
	}

	public Lecturer(String name, String position, List<Discipline> disciplines) {
		this.name = name;
		this.position = position;
		this.disciplines = disciplines;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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
		Lecturer other = (Lecturer) obj;
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
		return "Lecturer [id=" + id + ", name=" + name + "]";
	}
}
