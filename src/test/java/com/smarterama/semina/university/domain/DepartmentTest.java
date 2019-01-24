package com.smarterama.semina.university.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DepartmentTest {
	static Lecturer lecturer1, lecturer2, lecturer3;
	static Discipline discipline1, discipline2, discipline3;
	List<Lecturer> lecturers;
	Department department;
	List<Discipline> disciplines;

	@BeforeClass
	public static void setUpDepartment() {
		lecturer1 = new Lecturer("Vasilenko", "Professor", new ArrayList<Discipline>());
		lecturer2 = new Lecturer("Petrenko", "Associate professor", new ArrayList<Discipline>());
		lecturer3 = new Lecturer("Ivanov", "Associate professor", new ArrayList<Discipline>());
		discipline1 = new Discipline("Economics");
		discipline2 = new Discipline("History");
		discipline3 = new Discipline("Math");
	}

	@Before
	public void setUpDataDepartment() {
		department = new Department();
		lecturers = new ArrayList<Lecturer>();
		disciplines = new ArrayList<Discipline>();
	}

	@Test
	public void addLecturerShouldAddOneLecturer() {
		lecturers.add(lecturer1);
		department.addLecturer(lecturer1);
		assertEquals(lecturers, department.getLecturers());
	}

	@Test
	public void addLecturerShouldAddSeveralLecturers() {
		lecturers.add(lecturer1);
		lecturers.add(lecturer2);
		lecturers.add(lecturer3);
		department.addLecturer(lecturer1);
		department.addLecturer(lecturer2);
		department.addLecturer(lecturer3);
		assertEquals(lecturers, department.getLecturers());
	}

	@Test(expected = IllegalArgumentException.class)
	public void addLecturerShouldThrowExceptionForNull() {
		department.addLecturer(null);
	}

	@Test
	public void removeLecturerShouldRemoveOneLecturer() {
		lecturers.add(lecturer2);
		department.addLecturer(lecturer1);
		department.addLecturer(lecturer2);
		department.removeLecturer(lecturer1);
		assertEquals(lecturers, department.getLecturers());
	}

	@Test
	public void addDisciplineShouldAddOneDiscipline() {
		disciplines.add(discipline1);
		department.addDiscipline(discipline1);
		assertEquals(disciplines, department.getDisciplines());
	}

	@Test
	public void addDisciplineShouldAddSeveralDisciplines() {
		disciplines.add(discipline1);
		disciplines.add(discipline2);
		disciplines.add(discipline3);
		department.addDiscipline(discipline1);
		department.addDiscipline(discipline2);
		department.addDiscipline(discipline3);
		assertEquals(disciplines, department.getDisciplines());
	}

	@Test(expected = IllegalArgumentException.class)
	public void addDisciplineShouldThrowExceptionForNull() {
		department.addDiscipline(null);
	}

	@Test
	public void removeDisciplineShouldRemoveOneDiscipline() {
		disciplines.add(discipline2);
		department.addDiscipline(discipline1);
		department.addDiscipline(discipline2);
		department.removeDiscipline(discipline1);
		assertEquals(disciplines, department.getDisciplines());
	}
}
