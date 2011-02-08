CREATE TABLE STUDENT_NOTE (
NOTE_ID int foreign key references NOTE(NOTE_ID),
STUDENT_ID int foreign key references Student(id),
PRIMARY KEY (NOTE_ID, STUDENT_ID)
)


