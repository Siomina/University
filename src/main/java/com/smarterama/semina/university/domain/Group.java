package com.smarterama.semina.university.domain;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private int id;
	private String name;
	private List<Student> students = new ArrayList<Student>();

	public Group() {

	}

	public Group(String name) {
		this.name = name;
	}

	public Group(String name, List<Student> students) {
		this.name = name;
		this.students = students;
	}

	public void addStudent(Student studentAdded) {
		if (studentAdded == null) {
			throw new IllegalArgumentException("The argument cannot be null");
		}
		students.add(studentAdded);
	}

	public void removeStudent(Student studentRemoved) {
		students.remove(studentRemoved);
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public int getStudentsNumber() {
		return students.size();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
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
		return "Group [id=" + id + ", name=" + name + "]";
	}
}
