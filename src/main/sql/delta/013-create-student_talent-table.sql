CREATE TABLE Student_Talent (
	student_id int foreign key references STUDENT(id),
	talent_id int foreign key references TALENT(id),
	primary key (student_id, talent_id)
);
