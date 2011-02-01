CREATE TABLE EventAttendees(
event_id int foreign key references Event(event_id),
student_id varchar(255) foreign key references Student(student_id)
);