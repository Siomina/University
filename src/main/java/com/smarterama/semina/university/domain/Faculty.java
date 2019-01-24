package com.smarterama.semina.university.domain;

import java.util.ArrayList;
import java.util.List;

public class Faculty {

	private int id;
	private String name;
	private List<Group> groups = new ArrayList<Group>();
	private List<Department> departments = new ArrayList<Department>();

	public Faculty() {

	}

	public Faculty(String name) {
		this.name = name;
	}

	public Faculty(String name, List<Group> groups, List<Department> departments) {
		this.name = name;
		this.groups = groups;
		this.departments = departments;
	}

	public void addGroup(Group groupAdded) {
		if (groupAdded == null) {
			throw new IllegalArgumentException("The argument cannot be null");
		}
		groups.add(groupAdded);
	}

	public void removeGroup(Group groupRemoved) {
		groups.remove(groupRemoved);
	}

	public void addDepartment(Department departmentAdded) {
		if (departmentAdded == null) {
			throw new IllegalArgumentException("The argument cannot be null");
		}
		departments.add(departmentAdded);
	}

	public void removeDepartment(Department departmentRemoved) {
		departments.remove(departmentRemoved);
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

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
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
		return "Faculty [id=" + id + ", name=" + name + "]";
	}
}
