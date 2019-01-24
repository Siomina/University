package com.smarterama.semina.university.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GroupTest {
	static Student student1, student2, student3;
	List<Student> students;
	Group group;

	@BeforeClass
	public static void setUpGroup() {
		student1 = new Student("Ivanov");
		student2 = new Student("Sidorov");
		student3 = new Student("Petrov");
	}

	@Before
	public void setUpDataGroup() {
		group = new Group();
		students = new ArrayList<Student>();
	}

	@Test
	public void addStudentShouldAddOneStudent() {
		students.add(student1);
		group.addStudent(student1);
		assertEquals(students, group.getStudents());
	}

	@Test
	public void addStudentShouldAddSeveralStudents() {
		students.add(student1);
		students.add(student2);
		students.add(student3);
		group.addStudent(student1);
		group.addStudent(student2);
		group.addStudent(student3);
		assertEquals(students, group.getStudents());
	}

	@Test(expected = IllegalArgumentException.class)
	public void addStudentShouldThrowExceptionForNull() {
		group.addStudent(null);
	}

	@Test
	public void removeStudentShouldRemoveOneStudent() {
		students.add(student2);
		group.addStudent(student1);
		group.addStudent(student2);
		group.removeStudent(student1);
		assertEquals(students, group.getStudents());
	}
}
