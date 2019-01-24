package com.smarterama.semina.university.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FacultyTest {
	static Department department1, department2, department3;
	static Group group1, group2, group3;
	Faculty faculty;
	List<Group> groups;
	List<Department> departments;

	@BeforeClass
	public static void setUpFaculty() {
		group1 = new Group("EG05", new ArrayList<Student>());
		group2 = new Group("MU10", new ArrayList<Student>());
		group3 = new Group("MU12", new ArrayList<Student>());
		department1 = new Department("Department1", new ArrayList<Lecturer>(), new ArrayList<Discipline>());
		department2 = new Department("Department2", new ArrayList<Lecturer>(), new ArrayList<Discipline>());
		department3 = new Department("Department3", new ArrayList<Lecturer>(), new ArrayList<Discipline>());
	}

	@Before
	public void setUpDataFaculty() {
		faculty = new Faculty();
		groups = new ArrayList<Group>();
		departments = new ArrayList<Department>();
	}

	@Test
	public void addGroupShouldAddOneGroup() {
		groups.add(group1);
		faculty.addGroup(group1);
		assertEquals(groups, faculty.getGroups());
	}

	@Test
	public void addGroupShouldAddSeveralGroups() {
		groups.add(group1);
		groups.add(group2);
		groups.add(group3);
		faculty.addGroup(group1);
		faculty.addGroup(group2);
		faculty.addGroup(group3);
		assertEquals(groups, faculty.getGroups());
	}

	@Test(expected = IllegalArgumentException.class)
	public void addGroupShouldThrowExceptionForNull() {
		faculty.addGroup(null);
	}

	@Test
	public void removeGroupShouldRemoveOneGroup() {
		groups.add(group2);
		faculty.addGroup(group1);
		faculty.addGroup(group2);
		faculty.removeGroup(group1);
		assertEquals(groups, faculty.getGroups());
	}

	@Test
	public void addDepartmentShouldAddOneDepartment() {
		departments.add(department1);
		faculty.addDepartment(department1);
		assertEquals(departments, faculty.getDepartments());
	}

	@Test
	public void addDepartmentShouldAddSeveralDepartments() {
		departments.add(department1);
		departments.add(department2);
		departments.add(department3);
		faculty.addDepartment(department1);
		faculty.addDepartment(department2);
		faculty.addDepartment(department3);
		assertEquals(departments, faculty.getDepartments());
	}

	@Test(expected = IllegalArgumentException.class)
	public void addDepartmentShouldThrowExceptionForNull() {
		faculty.addDepartment(null);
	}

	@Test
	public void removeDepartmentShouldRemoveOneDepartment() {
		departments.add(department2);
		faculty.addDepartment(department1);
		faculty.addDepartment(department2);
		faculty.removeDepartment(department1);
		assertEquals(departments, faculty.getDepartments());
	}
}
