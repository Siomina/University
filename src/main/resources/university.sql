CREATE TABLE groups (
		group_id SERIAL PRIMARY KEY,
		name varchar(20) NOT NULL

);

CREATE TABLE student (
		student_id SERIAL PRIMARY KEY,
		name varchar(20) NOT NULL
);


CREATE TABLE groups_students (
		group_id integer NOT NULL,
		student_id integer NOT NULL,
		FOREIGN KEY (group_id) REFERENCES groups(group_id) ON DELETE CASCADE,
                FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE
);

INSERT INTO groups VALUES
        (NEXTVAL('groups_group_id_seq'), 'SR-01'),
        (NEXTVAL('groups_group_id_seq'), 'SR-02'),
        (NEXTVAL('groups_group_id_seq'), 'SR-03'),
        (NEXTVAL('groups_group_id_seq'), 'SR-04'),
        (NEXTVAL('groups_group_id_seq'), 'SR-05');

INSERT INTO student VALUES
        (NEXTVAL('student_student_id_seq'), 'Coillot'),
        (NEXTVAL('student_student_id_seq'), 'Eckford'),
        (NEXTVAL('student_student_id_seq'), 'Arango'),
        (NEXTVAL('student_student_id_seq'), 'Robstone'),
        (NEXTVAL('student_student_id_seq'), 'Gomes'),
        (NEXTVAL('student_student_id_seq'), 'Maldonado'),
        (NEXTVAL('student_student_id_seq'), 'Leloup'),
        (NEXTVAL('student_student_id_seq'), 'Snope'),
        (NEXTVAL('student_student_id_seq'), 'Ochoa'),
        (NEXTVAL('student_student_id_seq'), 'Anderson'),
        (NEXTVAL('student_student_id_seq'), 'Olsson'),
        (NEXTVAL('student_student_id_seq'), 'Mitchell'),
        (NEXTVAL('student_student_id_seq'), 'Johnson'),
        (NEXTVAL('student_student_id_seq'), 'Bursac'),
        (NEXTVAL('student_student_id_seq'), 'Bauer'),
        (NEXTVAL('student_student_id_seq'), 'Colletti'),
        (NEXTVAL('student_student_id_seq'), 'Saebelkampf'),
        (NEXTVAL('student_student_id_seq'), 'Byron'),
        (NEXTVAL('student_student_id_seq'), 'Cachey'),
        (NEXTVAL('student_student_id_seq'), 'Douglas'),
        (NEXTVAL('student_student_id_seq'), 'Dimitriou'),
        (NEXTVAL('student_student_id_seq'), 'Frank'),
        (NEXTVAL('student_student_id_seq'), 'Elion'),
        (NEXTVAL('student_student_id_seq'), 'Martins'),
        (NEXTVAL('student_student_id_seq'), 'Parodi'); 
 
INSERT INTO groups_students VALUES
        (1, 1),
        (1, 2),
        (1, 3),
        (1, 4),
        (1, 5),
        (2, 6),
        (2, 7),
        (2, 8),
        (2, 9),
        (2, 10),
        (3, 11),
        (3, 12),
        (3, 13),
        (3, 14),
        (3, 15),
        (4, 16),
        (4, 17),
        (4, 18),
        (4, 19),
        (4, 20),
        (5, 21),
        (5, 22),
        (5, 23),
        (5, 24),
        (5, 25);





CREATE TABLE room (
		room_id SERIAL PRIMARY KEY,
		name varchar(20) NOT NULL,
        capacity integer NOT NULL
);

INSERT INTO room VALUES
        (NEXTVAL('room_room_id_seq'), 'room1', 23),
        (NEXTVAL('room_room_id_seq'), 'room2', 25),
        (NEXTVAL('room_room_id_seq'), 'room3', 30),
        (NEXTVAL('room_room_id_seq'), 'room4', 25),
        (NEXTVAL('room_room_id_seq'), 'room5', 20);


CREATE TABLE discipline (
		discipline_id SERIAL PRIMARY KEY,
		name varchar(20) NOT NULL
);


INSERT INTO discipline VALUES
        (NEXTVAL('discipline_discipline_id_seq'), 'Biology'),
        (NEXTVAL('discipline_discipline_id_seq'), 'Mathematics'),
        (NEXTVAL('discipline_discipline_id_seq'), 'Chemistry'),
        (NEXTVAL('discipline_discipline_id_seq'), 'Literature'),
        (NEXTVAL('discipline_discipline_id_seq'), 'Physics');


CREATE TABLE lecturer(
		lecturer_id SERIAL PRIMARY KEY,
		name varchar(20) NOT NULL,
                position varchar(20) NOT NULL
);

INSERT INTO lecturer VALUES
        (NEXTVAL('lecturer_lecturer_id_seq'), 'Mitchell', 'professor'),
        (NEXTVAL('lecturer_lecturer_id_seq'), 'Klope', 'associate professor'),
        (NEXTVAL('lecturer_lecturer_id_seq'), 'Watson', 'assistant lecrurer' ),
        (NEXTVAL('lecturer_lecturer_id_seq'), 'Flanagan', 'lecturer'),
        (NEXTVAL('lecturer_lecturer_id_seq'), 'Tenner', 'lecturer');


CREATE TABLE lecturer_disciplines (
		lecturer_id integer NOT NULL,
		discipline_id integer NOT NULL,
		FOREIGN KEY (lecturer_id) REFERENCES lecturer(lecturer_id) ON DELETE CASCADE,
                FOREIGN KEY (discipline_id) REFERENCES discipline(discipline_id) ON DELETE CASCADE
);

INSERT INTO lecturer_disciplines VALUES
        (1, 2),
        (2, 1),
        (3, 5),
        (4, 3),
        (5, 4);

CREATE TABLE department (
		department_id SERIAL PRIMARY KEY,
		name varchar(40) NOT NULL
);

INSERT INTO department VALUES
        (NEXTVAL('department_department_id_seq'), 'Dep. of Economics'),
        (NEXTVAL('department_department_id_seq'), 'Dep. of Natural sciencies'),
        (NEXTVAL('department_department_id_seq'), 'Dep. of Law'),
        (NEXTVAL('department_department_id_seq'), 'Dep. of Finance');

CREATE TABLE department_disciplines (
		department_id integer NOT NULL,
		discipline_id integer NOT NULL,
		FOREIGN KEY (department_id) REFERENCES department(department_id) ON DELETE CASCADE,
                FOREIGN KEY (discipline_id) REFERENCES discipline(discipline_id) ON DELETE CASCADE
);

INSERT INTO department_disciplines VALUES
        (1, 2),
        (2, 1),
        (3, 5),
        (4, 3);

CREATE TABLE department_lecturers (
		department_id integer NOT NULL,
		lecturer_id integer NOT NULL,
		FOREIGN KEY (department_id) REFERENCES department(department_id) ON DELETE CASCADE,
                FOREIGN KEY (lecturer_id) REFERENCES lecturer(lecturer_id) ON DELETE CASCADE
);

INSERT INTO department_lecturers VALUES
        (1, 5),
        (2, 3),
        (3, 1),
        (4, 4);

CREATE TABLE faculty(
		faculty_id SERIAL PRIMARY KEY,
		name varchar(40) NOT NULL
);

INSERT INTO faculty VALUES
        (NEXTVAL('faculty_faculty_id_seq'), 'Faculty 1'),
        (NEXTVAL('faculty_faculty_id_seq'), 'Faculty 2');

CREATE TABLE faculty_groups (
		faculty_id integer NOT NULL,
		group_id integer NOT NULL,
		FOREIGN KEY (faculty_id) REFERENCES faculty(faculty_id) ON DELETE CASCADE,
                FOREIGN KEY (group_id) REFERENCES groups(group_id) ON DELETE CASCADE
);

INSERT INTO faculty_groups VALUES
        (1, 1),
        (1, 2),
        (2, 3),
        (2, 4),
        (2, 5);

CREATE TABLE faculty_departments (
		faculty_id integer NOT NULL,
		department_id integer NOT NULL,
		FOREIGN KEY (faculty_id) REFERENCES faculty(faculty_id) ON DELETE CASCADE,
                FOREIGN KEY (department_id) REFERENCES department(department_id) ON DELETE CASCADE
);


INSERT INTO faculty_departments VALUES
        (1, 1),
        (1, 2),
        (2, 4),
        (2, 3);


CREATE TABLE scheduleunit (
        scheduleunit_id SERIAL PRIMARY KEY,
        date TIMESTAMP
);


INSERT INTO scheduleunit VALUES
        (NEXTVAL('scheduleunit_scheduleunit_id_seq'), '2017-05-16 11:30:00'),
        (NEXTVAL('scheduleunit_scheduleunit_id_seq'), '2017-01-20 16:00:00'),
        (NEXTVAL('scheduleunit_scheduleunit_id_seq'), '2017-02-10 08:00:00'),
        (NEXTVAL('scheduleunit_scheduleunit_id_seq'), '2017-04-05 09:20:00');



CREATE TABLE scheduleunit_room (
		scheduleunit_id integer NOT NULL UNIQUE,
		room_id integer NOT NULL,
		FOREIGN KEY (scheduleunit_id) REFERENCES scheduleunit(scheduleunit_id) ON DELETE CASCADE,
                FOREIGN KEY (room_id) REFERENCES room(room_id) ON DELETE CASCADE
);

CREATE TABLE scheduleunit_group (
		scheduleunit_id integer NOT NULL UNIQUE,
		group_id integer NOT NULL,
		FOREIGN KEY (scheduleunit_id) REFERENCES scheduleunit(scheduleunit_id) ON DELETE CASCADE,
                FOREIGN KEY (group_id) REFERENCES groups(group_id) ON DELETE CASCADE
);

CREATE TABLE scheduleunit_discipline (
		scheduleunit_id integer NOT NULL UNIQUE,
		discipline_id integer NOT NULL,
		FOREIGN KEY (scheduleunit_id) REFERENCES scheduleunit(scheduleunit_id) ON DELETE CASCADE,
                FOREIGN KEY (discipline_id) REFERENCES discipline(discipline_id) ON DELETE CASCADE
);

CREATE TABLE scheduleunit_lecturer (
		scheduleunit_id integer NOT NULL UNIQUE,
		lecturer_id integer NOT NULL,
		FOREIGN KEY (scheduleunit_id) REFERENCES scheduleunit(scheduleunit_id) ON DELETE CASCADE,
                FOREIGN KEY (lecturer_id) REFERENCES lecturer(lecturer_id) ON DELETE CASCADE
);

INSERT INTO scheduleunit_room VALUES
        (1, 1),
        (2, 2),
        (3, 3),
        (4, 5);


INSERT INTO scheduleunit_group VALUES
        (1, 1),
        (2, 2),
        (3, 3),
        (4, 4);
       
INSERT INTO scheduleunit_discipline VALUES
        (1, 1),
        (2, 2),
        (3, 3),
        (4, 4);

INSERT INTO scheduleunit_lecturer VALUES
        (1, 1),
        (2, 2),
        (3, 3),
        (4, 4);

CREATE TABLE semesterschedule(
        semesterschedule_id SERIAL PRIMARY KEY,
        end_date TIMESTAMP,
        start_date TIMESTAMP 
);

INSERT INTO semesterschedule VALUES
        (NEXTVAL('semesterschedule_semesterschedule_id_seq'), '2017-01-01 08:00:00', '2017-07-01 08:00:00' ),
        (NEXTVAL('semesterschedule_semesterschedule_id_seq'), '2017-09-01 08:00:00', '2017-12-01 08:00:00');

CREATE TABLE semesterschedule_scheduleunit  (
		semesterschedule_id integer NOT NULL,
		scheduleunit_id integer NOT NULL,
		FOREIGN KEY (semesterschedule_id) REFERENCES semesterschedule(semesterschedule_id) ON DELETE CASCADE,
                FOREIGN KEY (scheduleunit_id) REFERENCES scheduleunit(scheduleunit_id) ON DELETE CASCADE
);

INSERT INTO semesterschedule_scheduleunit  VALUES
        (1, 1),
        (1, 2),
        (2, 3),
        (2, 4);
       
       