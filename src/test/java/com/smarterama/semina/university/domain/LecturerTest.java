package com.smarterama.semina.university.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LecturerTest {
	static Discipline discipline1, discipline2, discipline3;
	List<Discipline> disciplines;
	Lecturer lecturer;

	@BeforeClass
	public static void setUpLecturer() {
		discipline1 = new Discipline("Economics");
		discipline2 = new Discipline("History");
		discipline3 = new Discipline("Math");
	}

	@Before
	public void setUpDataLecturer() {
		lecturer = new Lecturer();
		disciplines = new ArrayList<Discipline>();
	}

	@Test
	public void addDisciplineShouldAddOneDiscipline() {
		disciplines.add(discipline1);
		lecturer.addDiscipline(discipline1);
		assertEquals(disciplines, lecturer.getDisciplines());
	}

	@Test
	public void addDisciplineShouldAddSeveralDisciplines() {
		disciplines.add(discipline1);
		disciplines.add(discipline2);
		disciplines.add(discipline3);
		lecturer.addDiscipline(discipline1);
		lecturer.addDiscipline(discipline2);
		lecturer.addDiscipline(discipline3);
		assertEquals(disciplines, lecturer.getDisciplines());
	}

	@Test(expected = IllegalArgumentException.class)
	public void addDisciplineShouldThrowExceptionForNull() {
		lecturer.addDiscipline(null);
	}

	@Test
	public void removeDisciplineShouldRemoveOneDiscipline() {
		disciplines.add(discipline2);
		lecturer.addDiscipline(discipline1);
		lecturer.addDiscipline(discipline2);
		lecturer.removeDiscipline(discipline1);
		assertEquals(disciplines, lecturer.getDisciplines());
	}
}
